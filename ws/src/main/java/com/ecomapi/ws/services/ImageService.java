package com.ecomapi.ws.services;

import com.ecomapi.ws.entities.ImageEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    ImageEntity store( MultipartFile file) throws IOException;
    byte[] downloadImage(String idClientPhoto);
    List<ImageEntity> getAllImages();
}
