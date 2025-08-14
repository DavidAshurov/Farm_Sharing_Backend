package farm_sharing.user.service;

import farm_sharing.exceptions.EntityNotFoundException;
import farm_sharing.security.dto.RefreshTokenDto;
import farm_sharing.security.dto.UserCredentialsDto;
import farm_sharing.security.jwt.JwtService;
import farm_sharing.user.dao.UserRepository;
import farm_sharing.user.dto.*;
import farm_sharing.user.model.Role;
import farm_sharing.user.model.User;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, CommandLineRunner {

    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;
    final ModelMapper modelMapper;
    final JwtService jwtService;

    @Transactional
    @Override
    public boolean signUp(NewUserDto dto) throws BadRequestException {
        if (userRepository.existsUserByEmail(dto.getEmail())) {
            throw new BadRequestException("User with this email already exists");
        }
        if (dto.getPhoneNumber() != null && userRepository.existsUserByPhoneNumber(dto.getPhoneNumber())) {
            throw new BadRequestException("User with this phone number already exists");
        }
        if (userRepository.existsUserByNickname(dto.getNickname())) {
            throw new BadRequestException("User with this nickname already exists");
        }
        if (!(dto.getRole().toUpperCase().equals(Role.FARM.name())
                || dto.getRole().toUpperCase().equals(Role.CLIENT.name()))) {
            throw new BadRequestException("Role must be client or farm");
        }
        User user = modelMapper.map(dto, User.class);
        user.setRole(Role.valueOf(dto.getRole().toUpperCase()));
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto signIn(UserCredentialsDto dto) throws AuthenticationException {
        return modelMapper.map(checkCredentials(dto),UserDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public RefreshTokenDto refreshToken(String refreshToken) throws Exception {
        if (refreshToken != null && jwtService.validateJwtToken(refreshToken)) {
            User user = findByEmail(jwtService.getEmailFromToken(refreshToken));
            return new RefreshTokenDto(jwtService.refreshBaseToken(user.getEmail()));
        }
        throw new AuthenticationException("Invalid refresh token");
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with this ID doesn't exist in DB"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }

    @Transactional
    @Override
    public UserDto updateUser(String nickname, UpdateUserDto dto) {
        User user = userRepository.findByNickname(nickname).orElseThrow(() -> new EntityNotFoundException("User with this nickname doesn't exist in DB"));
        modelMapper.map(dto, user);
        userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Transactional
    @Override
    public boolean deleteUser(String nickname) {
        User user = userRepository.findByNickname(nickname).orElseThrow(() -> new EntityNotFoundException("User with this nickname doesn't exist in DB"));
        userRepository.delete(user);
        return true;
    }

    @Transactional
    @Override
    public UserDto createAdminAccount(NewAdminDto dto) {
        User newAdmin = new User(null, dto.getNickname(), null,null,null,null,
                dto.getEmail(), passwordEncoder.encode(dto.getPassword()), Role.ADMINISTRATOR);
        userRepository.save(newAdmin);
        return modelMapper.map(newAdmin, UserDto.class);
    }

    @Transactional
    @Override
    public boolean changePassword(String nickname, ChangePasswordDto dto) throws BadRequestException {
        User user = userRepository.findByNickname(nickname).orElseThrow(() -> new EntityNotFoundException("User with this nickname doesn't exist in DB"));
        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
            throw new BadRequestException("The password isn't correct");
        }
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDto getDataOfAuthorizedUser(String name) {
        User user = userRepository.findByNickname(name).orElseThrow(() -> new EntityNotFoundException("User with this ID doesn't exist in DB"));
        return modelMapper.map(user,UserDto.class);
    }

    private User checkCredentials(UserCredentialsDto dto) throws AuthenticationException {
        Optional<User> optionalUser = userRepository.findByEmail(dto.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
                return user;
            }
        }
        throw new AuthenticationException("email or password is not correct");
    }

    private User findByEmail(String email) throws Exception {
        return userRepository.findByEmail(email).orElseThrow(Exception::new);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (!userRepository.existsUserByEmail("admin")) {
            User admin = new User(null, "admin", null, null, null, null,
                    "admin", passwordEncoder.encode("admin1234"), Role.ADMINISTRATOR);
            userRepository.save(admin);
        }
    }
}
