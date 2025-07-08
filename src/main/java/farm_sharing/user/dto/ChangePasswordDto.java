package farm_sharing.user.dto;

import lombok.Getter;

@Getter
public class ChangePasswordDto {
    String oldPassword;
    String newPassword;
}
