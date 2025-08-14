package farm_sharing.offer.dto;

import farm_sharing.user.dto.UserDto;
import lombok.Getter;

@Getter
public class OfferDto {
    Long id;
    String category;
    String title;
    String description;
    Integer amount;
    Double price;
    String units;
    UserDto farm;
}
