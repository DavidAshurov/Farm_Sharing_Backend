package farm_sharing.cart.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class OfferInCartDto {
    Long id;
    String title;
    Double price;
    @Setter
    Integer amount;
}
