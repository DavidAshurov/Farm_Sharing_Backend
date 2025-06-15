package farm_sharing.farm.model;

import farm_sharing.offer.model.Offer;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Setter
    String companyName;
    @Setter
    @Column(unique = true)
    String phoneNumber;
    @Setter
    String city;
    @Setter
    String address;
    @Setter
    @Column(unique = true)
    String email;
    @OneToMany(mappedBy = "farm", cascade = CascadeType.REMOVE)
    List<Offer> offers;
}
