package farm_sharing.cart.dao;

import farm_sharing.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByClient_Nickname(String nickname);
}
