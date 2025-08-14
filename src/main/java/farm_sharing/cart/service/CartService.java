package farm_sharing.cart.service;

import farm_sharing.cart.dto.CartItemDto;
import farm_sharing.cart.dto.NewCartItemDto;
import farm_sharing.cart.dto.UpdateItemQuantityDto;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface CartService {
    boolean addItemToCart(String nickname, NewCartItemDto dto) throws BadRequestException;

    boolean removeItemFromCart(String nickname, Long itemId) throws BadRequestException;

    List<CartItemDto> getCart(String nickname);

    boolean changeItemQuantity(String nickname, Long itemId, UpdateItemQuantityDto dto) throws BadRequestException;

    boolean removeCart(String nickname);
}
