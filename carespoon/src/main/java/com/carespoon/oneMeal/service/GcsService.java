package com.carespoon.oneMeal.service;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.UUID;

@Service
public class GcsService {
    private static final String BUCKET_NAME = "carespoon";

    private final Storage storage;

    private String key
            = "/Applications/Develop/CareSpoon_BackEnd/carespoon/src/main/resources/google_storage/carespoon-388405-dc30d9709ba6.json";

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
