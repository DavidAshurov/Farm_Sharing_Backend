package farm_sharing.security;

import farm_sharing.exceptions.EntityNotFoundException;
import farm_sharing.user.dao.UserRepository;
import farm_sharing.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserServiceImpl implements UserDetailsService {

    final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws EntityNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User with this ID doesn't exist in DB"));
        return new org.springframework.security.core.userdetails.User(user.getNickname(), user.getPassword(),
                AuthorityUtils.createAuthorityList("ROLE_" + user.getRole().name()));
    }
}
