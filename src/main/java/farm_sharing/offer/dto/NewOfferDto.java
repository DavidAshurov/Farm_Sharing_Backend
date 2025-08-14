package farm_sharing.offer.dto;

import lombok.Getter;

@Getter
public class NewOfferDto {
    String category;
    String title;
    String description;
    Integer amount;
    Double price;
    String units;
}
