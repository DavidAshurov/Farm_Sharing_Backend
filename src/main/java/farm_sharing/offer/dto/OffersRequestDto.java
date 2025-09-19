package farm_sharing.offer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OffersRequestDto {
    Integer pageNumber;
    Integer pageSize;
    String sortField;
    String sortDirection;
    Double minPrice;
    Double maxPrice;
    String category;
    String search;

    public OffersRequestDto() {
        if (pageNumber == null) pageNumber = 0;
        if (pageSize == null) pageSize = 20;
        if (sortField == null) sortField = "title";
        if (sortDirection == null) sortDirection = "asc";
    }
}
