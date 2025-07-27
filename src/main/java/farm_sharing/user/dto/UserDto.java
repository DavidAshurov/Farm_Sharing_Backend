package farm_sharing.user.dto;

import lombok.Getter;

@Getter
public class UserDto {
    Long id;
    String nickname;
    String phoneNumber;
    String city;
    String address;
    String email;
    String role;
    Float rating;
}
