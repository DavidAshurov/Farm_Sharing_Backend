package farm_sharing.offer.dto;

import farm_sharing.farm.dto.FarmDto;
import lombok.Getter;

@Getter
public class OfferDto {
    Long id;
    String category;
    String title;
    String description;
    Integer amount;
    Integer price;
    FarmDto farm;
}
