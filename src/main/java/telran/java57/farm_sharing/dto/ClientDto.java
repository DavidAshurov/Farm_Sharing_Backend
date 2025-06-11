package telran.java57.farm_sharing.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ClientDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private String city;
    private String email;
}
