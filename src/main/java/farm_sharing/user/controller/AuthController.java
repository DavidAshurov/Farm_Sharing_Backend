package farm_sharing.user.controller;

import farm_sharing.security.dto.JwtAuthenticationDto;
import farm_sharing.security.dto.RefreshTokenDto;
import farm_sharing.security.dto.UserCredentialsDto;
import farm_sharing.security.jwt.JwtService;
import farm_sharing.user.dto.NewUserDto;
import farm_sharing.user.dto.SignInDto;
import farm_sharing.user.dto.UserDto;
import farm_sharing.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.time.Duration;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    final UserService userService;
    final JwtService jwtService;

    @PostMapping("/sign-up")
    public boolean register(@RequestBody NewUserDto dto) throws BadRequestException {
        return userService.signUp(dto);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SignInDto> signIn(@RequestBody UserCredentialsDto dto, HttpServletResponse response) throws AuthenticationException {
        UserDto user = userService.signIn(dto);
        JwtAuthenticationDto jwt = jwtService.generateAuthToken(user.getEmail());
        ResponseCookie cookie = ResponseCookie.from("refreshToken",jwt.getRefreshToken())
                .httpOnly(true)
                .secure(false)
                .path("/api/auth/refresh")
                .maxAge(Duration.ofDays(7))
                .sameSite("Lax")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE,cookie.toString());
        SignInDto res = new SignInDto(jwt.getToken(),user);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("refreshToken","")
                .httpOnly(true)
                .secure(false)
                .path("/api/auth/refresh")
                .maxAge(0)
                .sameSite("Lax")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE,cookie.toString());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenDto> refreshToken(@CookieValue(value = "refreshToken", required = false) String refreshToken) throws Exception {
        return ResponseEntity.ok(userService.refreshToken(refreshToken));
    }
}
