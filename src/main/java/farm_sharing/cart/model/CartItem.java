package farm_sharing.cart.model;

import farm_sharing.offer.model.Offer;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Builder
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    Cart cart;
    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    Offer offer;
    @Setter
    Integer quantity;
}
