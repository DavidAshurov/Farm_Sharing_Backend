package farm_sharing.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RemovedCartItemDto {
    Long offerId;
    String title;
}
