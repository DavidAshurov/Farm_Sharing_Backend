package farm_sharing.user.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Setter
    @Column(unique = true, nullable = false)
    String nickname;
    @Setter
    Float rating;
    @Setter
    @Column(unique = true)
    String phoneNumber;
    @Setter
    String city;
    @Setter
    String address;
    @Setter
    @Column(unique = true, nullable = false)
    String email;
    @Setter
    @Column(nullable = false)
    String password;
    @Setter
    Role role;
    @Setter
    String avatar;
}
