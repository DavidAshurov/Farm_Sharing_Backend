package farm_sharing.user.dto;

import lombok.Getter;

@Getter
public class NewUserDto {
    String nickname;
    String phoneNumber;
    String city;
    String address;
    String email;
    String password;
    String role;
}
