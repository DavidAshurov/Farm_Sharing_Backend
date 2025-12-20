package farm_sharing.shared.images.controller;

import farm_sharing.shared.images.dto.S3UploadDto;
import farm_sharing.shared.images.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/upload-url/{folderName}/{fileName}")
    public S3UploadDto getUploadUrl(@PathVariable String folderName, @PathVariable String fileName) {
        return imageService.generatePresignedUploadUrl(folderName, fileName);
    }
}
