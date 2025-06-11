package telran.java57.farm_sharing.model;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
    private Long id;
    private String name;
    @Column(nullable = false) // Ensures phone number is not null
    private String phoneNumber;
    @Column(nullable = false) // Ensures the address is not null
    private String city;
    @Column(nullable = false, unique = true) // Ensures email is unique and not null
    private String email;


}



