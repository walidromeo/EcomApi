package com.ecomapi.ws.serviceImp;

import com.ecomapi.ws.Config.BucketName;
import com.ecomapi.ws.entities.ImageEntity;
import com.ecomapi.ws.repositories.ImageRepo;
import com.ecomapi.ws.services.ImageService;
import com.ecomapi.ws.shared.Utils;
import com.ecomapi.ws.userRequest.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class ImageServiceImp implements ImageService {


private final ImageRepo imageRepo;
private final Utils utils;
private final FileStore fileStore;

    @Override
    public ImageEntity store( MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }
        //Check if the file is an image
        if (!Arrays.asList(IMAGE_PNG.getMimeType(),
                IMAGE_BMP.getMimeType(),
                IMAGE_GIF.getMimeType(),
                IMAGE_JPEG.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("FIle uploaded is not an image");
        }
        //get file metadata
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        //Save Image in S3 and then save
        String path = String.format("%s/%s", BucketName.BUCKET_NAME.getBucketName(), UUID.randomUUID());
        String fileName = String.format("%s", file.getOriginalFilename());
        try {
            fileStore.upload(path, fileName, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }
        ImageEntity imageUpload = new ImageEntity();

        imageUpload.setIdBrowserPhoto(utils.generateStringId(20));
        imageUpload.setImagePath(path);
        imageUpload.setImageFileName(fileName);

        imageRepo.save(imageUpload);

        return imageUpload;
    }

    @Override
    public byte[] downloadImage(String idClientPhoto) {
        ImageEntity todo = imageRepo.findByIdBrowserPhoto(idClientPhoto);
        return fileStore.download(todo.getImagePath(), todo.getImageFileName());
    }

    @Override
    public List<ImageEntity> getAllImages() {
        List<ImageEntity> allImages = new ArrayList<>();
        imageRepo.findAll().forEach(allImages::add);
        return allImages;
    }

}
