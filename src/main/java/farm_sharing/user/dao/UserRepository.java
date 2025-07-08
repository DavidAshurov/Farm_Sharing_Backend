package farm_sharing.user.dao;

import farm_sharing.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmail(String email);

    boolean existsUserByPhoneNumber(String phoneNumber);

    boolean existsUserByNickname(String nickname);

    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);
}
