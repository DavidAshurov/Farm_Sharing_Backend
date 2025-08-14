package farm_sharing.cart.service;

import farm_sharing.cart.dao.CartRepository;
import farm_sharing.cart.dto.CartItemDto;
import farm_sharing.cart.dto.NewCartItemDto;
import farm_sharing.cart.dto.UpdateItemQuantityDto;
import farm_sharing.cart.model.Cart;
import farm_sharing.cart.model.CartItem;
import farm_sharing.exceptions.EntityNotFoundException;
import farm_sharing.offer.dao.OfferRepository;
import farm_sharing.offer.model.Offer;
import farm_sharing.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    final CartRepository cartRepository;
    final UserRepository userRepository;
    final OfferRepository offerRepository;
    final ModelMapper modelMapper;

    @Override
    @Transactional
    public boolean addItemToCart(String nickname, NewCartItemDto dto) throws BadRequestException {
        Cart cart = cartRepository.findByClient_Nickname(nickname).orElseGet(() ->
                Cart.builder()
                        .client(userRepository.findByNickname(nickname).get())
                        .build());
        Offer offer = offerRepository.findById(dto.getOfferId()).orElseThrow(() -> new EntityNotFoundException("Offer with this ID doesn't exist in DB"));
        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(i -> i.getOffer().getId().equals(offer.getId()))
                .findFirst();
        if (existingItem.isPresent()) {
            int newQuantity = dto.getQuantity() + existingItem.get().getQuantity();
            if (newQuantity <= offer.getAmount()) {
                existingItem.get().setQuantity(newQuantity);
            } else {
                throw new BadRequestException("You want to add to cart more products than available");
            }
        } else {
            cart.getItems().add(CartItem.builder()
                    .cart(cart)
                    .offer(offer)
                    .quantity(dto.getQuantity())
                    .build()
            );
        }
        cartRepository.save(cart);
        return true;
    }

    @Transactional
    @Override
    public boolean removeItemFromCart(String nickname, Long itemId) throws BadRequestException {
        Cart cart = cartRepository.findByClient_Nickname(nickname).
                orElseThrow(() -> new EntityNotFoundException("Cart of authorized user doesn't exist"));
        boolean removed = cart.getItems().removeIf(i -> i.getId().equals(itemId));
        if (removed) {
            cartRepository.save(cart);
        } else {
            throw new BadRequestException("Cart item with this ID is not in your cart");
        }
        return removed;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CartItemDto> getCart(String nickname) {
        Cart cart = cartRepository.findByClient_Nickname(nickname).orElseThrow(() -> new EntityNotFoundException("Cart of authorized user doesn't exist"));
        return cart.getItems().stream()
                .map(item -> modelMapper.map(item,CartItemDto.class))
                .toList();
    }

    @Transactional
    @Override
    public boolean changeItemQuantity(String nickname, Long itemId, UpdateItemQuantityDto dto) throws BadRequestException {
        Cart cart = cartRepository.findByClient_Nickname(nickname).
                orElseThrow(() -> new EntityNotFoundException("Cart of authorized user doesn't exist"));
        Optional<CartItem> item = cart.getItems().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst();
        if (item.isPresent()) {
            if (item.get().getOffer().getAmount() < dto.getQuantity()) {
                throw new BadRequestException("You want to add to cart more products than available");
            }
            item.get().setQuantity(dto.getQuantity());
            cartRepository.save(cart);
            return true;
        } else {
            throw new BadRequestException("Cart item with this ID is not in your cart");
        }
    }

    @Transactional
    @Override
    public boolean removeCart(String nickname) {
        Cart cart = cartRepository.findByClient_Nickname(nickname).
                orElseThrow(() -> new EntityNotFoundException("Cart of authorized user doesn't exist"));
        cartRepository.delete(cart);
        return true;
    }
}
