package farm_sharing.farmer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FarmerDto {
    Long id;
    String companyName;
    String phoneNumber;
    String city;
    String address;
    String email;
}
