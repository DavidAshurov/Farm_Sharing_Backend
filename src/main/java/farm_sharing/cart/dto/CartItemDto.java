package farm_sharing.cart.dto;

import farm_sharing.offer.dto.OfferDto;
import lombok.Getter;

@Getter
public class CartItemDto {
    Long id;
    OfferInCartDto offer;
    Integer quantity;
}
