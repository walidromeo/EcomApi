package com.ecomapi.ws.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponse implements Serializable {
    private static final long serialVersionUID = 6540050208691607841L;

    private String idBrowserPhoto;
    private String title;

    private String description;

    private String imagePath;

    private String imageFileName;
}
