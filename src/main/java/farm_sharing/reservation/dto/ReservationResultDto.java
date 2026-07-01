package farm_sharing.reservation.dto;

import farm_sharing.cart.dto.CartItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ReservationResultDto {
    Long reservationId;
    List<CartItemDto> updatedCart;
    List<RemovedCartItemDto> removedItems;
    List<AdjustedCartItemDto> adjustedItems;
}
