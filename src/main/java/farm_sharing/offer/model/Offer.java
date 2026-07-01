package farm_sharing.offer.model;

import farm_sharing.user.model.User;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String category;
    private String title;
    private String description;
    private int totalAmount;
    private int reservedAmount;
    private Double price;
    private String units;
    private String image;
    @ManyToOne(fetch = FetchType.LAZY)
    private User farm;

    public Integer getAvailableAmount() {
        return totalAmount - reservedAmount;
    }

    public void makeReservation(int amountToReserve) {
        if (amountToReserve > getAvailableAmount()) {
            return;
        }
        reservedAmount += amountToReserve;
    }
}
