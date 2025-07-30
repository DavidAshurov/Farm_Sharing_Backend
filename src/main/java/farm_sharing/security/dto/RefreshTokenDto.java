package farm_sharing.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RefreshTokenDto {
    private String accessToken;
}
