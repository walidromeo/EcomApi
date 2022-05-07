package com.ecomapi.ws.Config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BucketName {
    BUCKET_NAME("yourBucketName");
    private final String bucketName;
}
