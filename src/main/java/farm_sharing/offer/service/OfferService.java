package farm_sharing.offer.service;

import farm_sharing.offer.dto.NewOfferDto;
import farm_sharing.offer.dto.OfferDto;
import farm_sharing.offer.dto.OffersRequestDto;
import farm_sharing.offer.dto.OffersResponseDto;
import farm_sharing.offer.model.Offer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OfferService {
    boolean createOffer(NewOfferDto dto, String farmNickname);

    OfferDto findOfferById(Long id);

    OffersResponseDto getAllOffers(OffersRequestDto dto);

    OfferDto updateOffer(Long id, NewOfferDto dto);

    boolean deleteOffer(Long id);
}
