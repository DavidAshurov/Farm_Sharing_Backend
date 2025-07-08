package farm_sharing.offer.service;

import farm_sharing.offer.dto.NewOfferDto;
import farm_sharing.offer.dto.OfferDto;

import java.util.List;

public interface OfferService {
    boolean createOffer(NewOfferDto dto, String farmNickname);

    OfferDto findOfferById(Long id);

    List<OfferDto> getAllOffers();

    OfferDto updateOffer(Long id, NewOfferDto dto);

    boolean deleteOffer(Long id);
}
