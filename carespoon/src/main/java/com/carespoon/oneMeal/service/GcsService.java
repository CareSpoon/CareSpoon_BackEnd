package com.carespoon.oneMeal.service;

import com.google.api.client.util.Value;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@Service
public class GcsService {
    private static final String BUCKET_NAME = "carespoon-storage";

    private final Storage storage;

    private String key
            = "/home/joyrooom15/CareSpoon_BackEnd/carespoon/build/resources/main/google_storage/care-spoon-82c78-957ade423885.json";

    Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(key));
    public GcsService() throws IOException {
        storage = StorageOptions.newBuilder().setCredentials(credentials).setProjectId("care-spoon-82c78").build().getService();
    }

    public String uploadImage(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString();
        String fileType = file.getContentType();
        BlobInfo blobInfo = storage.create(
                BlobInfo.newBuilder(BUCKET_NAME, fileName)
                        .setContentType(fileType)
                        .build(),file.getInputStream());
        byte[] bytes = file.getBytes();
        try(WriteChannel writer = storage.writer(blobInfo)){
            writer.write(ByteBuffer.wrap(bytes, 0 , bytes.length));
        }
        return fileName;
    }
}
