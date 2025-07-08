package farm_sharing.user.controller;

import farm_sharing.security.dto.JwtAuthenticationDto;
import farm_sharing.security.dto.RefreshTokenDto;
import farm_sharing.security.dto.UserCredentialsDto;
import farm_sharing.user.dto.NewUserDto;
import farm_sharing.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    final UserService userService;
    @PostMapping("/sign-up")
    public boolean register(@RequestBody NewUserDto dto) throws BadRequestException {
        return userService.signUp(dto);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationDto> signIn(@RequestBody UserCredentialsDto dto) throws AuthenticationException {
        JwtAuthenticationDto jwtAuthenticationDto = userService.signIn(dto);
        return ResponseEntity.ok(jwtAuthenticationDto);
    }

    @PostMapping("/refresh")
    public JwtAuthenticationDto refreshToken(@RequestBody RefreshTokenDto dto) throws Exception {
        return userService.refreshToken(dto);
    }
}
