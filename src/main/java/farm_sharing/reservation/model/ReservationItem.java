package farm_sharing.reservation.model;

import farm_sharing.offer.model.Offer;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class ReservationItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Reservation reservation;
    @ManyToOne(fetch = FetchType.LAZY)
    private Offer product;
    private Integer quantity;

    public ReservationItem(Reservation reservation, Offer product, Integer quantity) {
        this.reservation = reservation;
        this.product = product;
        this.quantity = quantity;
    }
}
