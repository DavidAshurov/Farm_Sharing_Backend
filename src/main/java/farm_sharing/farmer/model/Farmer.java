package farm_sharing.farmer.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Farmer {
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
}
