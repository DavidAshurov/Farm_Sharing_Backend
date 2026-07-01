package farm_sharing.reservation.model;

import farm_sharing.offer.model.Offer;
import farm_sharing.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@EqualsAndHashCode(of = "id")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant createdAt;
    private Instant expiresAt;
    @Setter
    private ReservationStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ReservationItem> items = new ArrayList<>();

    public Reservation(User user) {
        this.user = user;
        this.createdAt = Instant.now();
        this.expiresAt = createdAt.plus(Duration.ofMinutes(10));
        this.status = ReservationStatus.ACTIVE;
    }
    public void addItem(Offer product, Integer quantity) {
        ReservationItem newItem = new ReservationItem(this,product,quantity);
        items.add(newItem);
    }
}
