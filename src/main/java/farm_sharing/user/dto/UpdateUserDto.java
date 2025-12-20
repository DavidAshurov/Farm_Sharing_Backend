package farm_sharing.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class UpdateUserDto {
    String nickname;
    String phoneNumber;
    String city;
    String address;
    String email;
    @Setter
    String avatarTmpKey;
}
