package farm_sharing.user.controller;

import farm_sharing.user.dto.ChangePasswordDto;
import farm_sharing.user.dto.NewAdminDto;
import farm_sharing.user.dto.UpdateUserDto;
import farm_sharing.user.dto.UserDto;
import farm_sharing.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    final UserService userService;

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping()
    public UserDto getDataOfAuthorizedUser(Principal principal) {
        return userService.getDataOfAuthorizedUser(principal.getName());
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping()
    public UserDto updateUser(Principal principal, @RequestBody UpdateUserDto dto) {
        return userService.updateUser(principal.getName(),dto);
    }

    @DeleteMapping("/{nickname}")
    public boolean deleteUser(@PathVariable String nickname) {
        return userService.deleteUser(nickname);
    }

    @PostMapping("/new-admin")
    public UserDto createAdminAccount(@RequestBody NewAdminDto dto) {
        return userService.createAdminAccount(dto);
    }

    @PostMapping("/change-password")
    public boolean changePassword(@RequestBody ChangePasswordDto dto, Principal principal) throws BadRequestException {
        return userService.changePassword(principal.getName(),dto);
    }
}
