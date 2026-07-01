package farm_sharing.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdjustedCartItemDto {
    Long offerId;
    String title;
    int oldQuantity;
    int newQuantity;
}
