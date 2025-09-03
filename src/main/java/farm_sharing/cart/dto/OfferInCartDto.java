package farm_sharing.cart.dto;

import lombok.Getter;

@Getter
public class OfferInCartDto {
    Long id;
    String title;
    Double price;
    Integer amount;
}
