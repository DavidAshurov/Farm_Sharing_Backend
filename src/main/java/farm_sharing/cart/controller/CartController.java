package farm_sharing.cart.controller;

import farm_sharing.cart.dto.CartItemDto;
import farm_sharing.cart.dto.NewCartItemDto;
import farm_sharing.cart.dto.UpdateItemQuantityDto;
import farm_sharing.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    final CartService cartService;

    @PostMapping("/items")
    public boolean addItemToCart(Principal principal, @RequestBody NewCartItemDto dto) throws BadRequestException {
        return cartService.addItemToCart(principal.getName(), dto);
    }

    @DeleteMapping("/items/{id}")
    public boolean removeItemFromCart(Principal principal, @PathVariable Long id) throws BadRequestException {
        return cartService.removeItemFromCart(principal.getName(), id);
    }

    @PutMapping("/items/{id}")
    public boolean changeItemQuantity(Principal principal, @PathVariable Long id, @RequestBody UpdateItemQuantityDto dto) throws BadRequestException {
        return cartService.changeItemQuantity(principal.getName(), id, dto);
    }

    @GetMapping
    public List<CartItemDto> getCart(Principal principal) {
        return cartService.getCart(principal.getName());
    }

    @DeleteMapping
    public boolean clearCart(Principal principal) {
        return cartService.clearCart(principal.getName());
    }
}
