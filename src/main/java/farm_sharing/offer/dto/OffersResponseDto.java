package farm_sharing.offer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@AllArgsConstructor
@Getter
public class OffersResponseDto {
    List<OfferDto> offers;
    Integer pageNumber;
    Integer numberOfElements;
    Integer totalPages;
    Long totalElements;
}
