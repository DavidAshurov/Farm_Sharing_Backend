package farm_sharing.shared.images.controller;

import farm_sharing.shared.images.dto.S3UploadDto;
import farm_sharing.shared.images.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
