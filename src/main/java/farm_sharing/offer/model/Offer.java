package farm_sharing.offer.model;

import farm_sharing.user.model.User;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Setter
    String category;
    @Setter
    String title;
    @Setter
    String description;
    @Setter
    Integer amount;
    @Setter
    Integer price;
    @Setter
    @ManyToOne
    User farm;
}
