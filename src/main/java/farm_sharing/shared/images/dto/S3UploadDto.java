package farm_sharing.shared.images.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class S3UploadDto {
    private String uploadUrl;
    private String tmpUrl;
}
