package com.ecomapi.ws.Config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public AmazonS3 s3(){
        /*
        AWSCredentials credentials = new BasicAWSCredentials("accesKey"
                ,"secretKey");
         */
        AWSCredentials credentials = new BasicAWSCredentials("AZERREZA"
                ,"AZERREZA");
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.EU_WEST_3).build();
    }

}
