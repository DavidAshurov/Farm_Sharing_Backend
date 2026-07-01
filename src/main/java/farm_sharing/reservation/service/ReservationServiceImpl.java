package farm_sharing.reservation.service;

import farm_sharing.cart.dao.CartRepository;
import farm_sharing.cart.dto.CartItemDto;
import farm_sharing.cart.model.Cart;
import farm_sharing.cart.model.CartItem;
import farm_sharing.exceptions.EntityNotFoundException;
import farm_sharing.exceptions.NothingAvailableForReservationException;
import farm_sharing.offer.dao.OfferRepository;
import farm_sharing.offer.model.Offer;
import farm_sharing.reservation.dao.ReservationRepository;
import farm_sharing.reservation.dto.AdjustedCartItemDto;
import farm_sharing.reservation.dto.RemovedCartItemDto;
import farm_sharing.reservation.dto.ReservationResultDto;
import farm_sharing.reservation.model.Reservation;
import farm_sharing.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    final ReservationRepository reservationRepository;
    final OfferRepository offerRepository;
    final UserRepository userRepository;
    final CartRepository cartRepository;
    final ModelMapper modelMapper;

    @Override
    @Transactional
    public ReservationResultDto createReservation(String userNickname) {
        Cart cart = cartRepository.findByClient_Nickname(userNickname).orElseThrow(() ->
                new EntityNotFoundException("Cart of this user doesn't exist"));
        Reservation newReservation = new Reservation(cart.getClient());
        List<Long> ids = cart.getItems().stream()
                .map(i -> i.getOffer().getId())
                .toList();
        Map<Long, Offer> lockedOffers = offerRepository.findAllByIdsForUpdate(ids).stream()
                .collect(Collectors.toMap(Offer::getId, Function.identity()));
        List<CartItem> itemsToRemove = new ArrayList<>();
        List<AdjustedCartItemDto> adjustedItems = new ArrayList<>();
        List<RemovedCartItemDto> removedItems = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            Offer offer = lockedOffers.get(item.getOffer().getId());
            int required = item.getQuantity();
            int available = offer.getAvailableAmount();
            if (available == 0) {
                itemsToRemove.add(item);
                removedItems.add(new RemovedCartItemDto(offer.getId(),offer.getTitle()));
            } else if (available < required) {
                item.changeQuantity(available);
                offer.makeReservation(available);
                newReservation.addItem(offer, available);
                adjustedItems.add(new AdjustedCartItemDto(offer.getId(), offer.getTitle(), required, available));
            } else {
                offer.makeReservation(required);
                newReservation.addItem(offer, required);
            }
        }
        itemsToRemove.forEach(cart::removeItem);
        if (newReservation.getItems().isEmpty()) {
            throw new NothingAvailableForReservationException();
        }
        reservationRepository.save(newReservation);
        return new ReservationResultDto(
                newReservation.getId(),
                cart.getItems().stream().map(i -> modelMapper.map(i, CartItemDto.class)).toList(),
                removedItems,
                adjustedItems
        );
    }
}
