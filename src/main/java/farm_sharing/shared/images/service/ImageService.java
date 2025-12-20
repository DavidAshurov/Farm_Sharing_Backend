package farm_sharing.shared.images.service;

import farm_sharing.shared.images.dto.S3UploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CopyObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final S3Presigner s3Presigner;
    private final S3Client s3Client;
    @Value("${aws.s3.bucket}")
    private String bucketName;
    @Value("${aws.s3.public-url}")
    private String publicUrl;

    public S3UploadDto generatePresignedUploadUrl(String folderName, String fileName) {

        String key = "tmp/" + folderName + "/" + UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        PresignedPutObjectRequest presignedRequest = s3Presigner.presignPutObject(
                PutObjectPresignRequest.builder()
                        .signatureDuration(Duration.ofMinutes(1))
                        .putObjectRequest(request)
                        .build()
        );
        return new S3UploadDto(presignedRequest.url().toString(), key);
    }

    public void moveS3Object(String sourceKey, String destKey) {
        s3Client.copyObject(CopyObjectRequest.builder()
                .sourceBucket(bucketName)
                .sourceKey(sourceKey)
                .destinationBucket(bucketName)
                .destinationKey(destKey)
                .build());
        deleteFile(sourceKey);
    }

    public void deleteFile(String key) {
        if (key == null || key.isBlank()) return;
        s3Client.deleteObject(DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build());
    }

    public String toPublicUrl(String key) {
        return key == null ? null : publicUrl + "/" + key;
    }
}
