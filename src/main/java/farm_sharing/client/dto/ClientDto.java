package farm_sharing.client.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ClientDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private String city;
    private String email;
}
