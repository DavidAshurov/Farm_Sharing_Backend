package farm_sharing.user.service;

import farm_sharing.security.dto.JwtAuthenticationDto;
import farm_sharing.security.dto.RefreshTokenDto;
import farm_sharing.security.dto.UserCredentialsDto;
import farm_sharing.user.dto.*;
import org.apache.coyote.BadRequestException;

import javax.naming.AuthenticationException;
import java.util.List;

public interface UserService {
    boolean signUp(NewUserDto dto) throws BadRequestException;

    JwtAuthenticationDto signIn(UserCredentialsDto dto) throws AuthenticationException;

    JwtAuthenticationDto refreshToken(RefreshTokenDto dto) throws Exception;

    UserDto findById(Long id);

    List<UserDto> getAllUsers();

    UserDto updateUser(String nickname, UpdateUserDto dto);

    boolean deleteUser(String nickname);

    UserDto createAdminAccount(NewAdminDto dto);

    boolean changePassword(String nickname, ChangePasswordDto dto) throws BadRequestException;
}
