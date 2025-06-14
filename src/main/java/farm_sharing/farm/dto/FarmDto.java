package farm_sharing.farm.dto;

import farm_sharing.offer.dto.OfferDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FarmDto {
    Long id;
    String companyName;
    String phoneNumber;
    String city;
    String address;
    String email;
}
