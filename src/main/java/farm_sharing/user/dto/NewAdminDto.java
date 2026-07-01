package farm_sharing.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewAdminDto {
    String nickname;
    String email;
    String password;
}
