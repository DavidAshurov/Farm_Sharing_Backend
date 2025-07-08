package farm_sharing.security;

import farm_sharing.exceptions.EntityNotFoundException;
import farm_sharing.offer.dao.OfferRepository;
import farm_sharing.offer.model.Offer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("webSecurity")
@RequiredArgsConstructor
public class CustomWebSecurity {
    final OfferRepository offerRepository;

    public boolean checkOfferOwner(Long offerId, String nickname) {
        Offer offer = offerRepository.findById(offerId).orElse(null);
        return offer != null && offer.getFarm().getNickname().equals(nickname);
    }
}
