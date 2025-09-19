package farm_sharing.offer.controller;

import farm_sharing.offer.dto.*;
import farm_sharing.offer.model.Offer;
import farm_sharing.offer.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/offers")
public class OfferController {

    final OfferService offerService;

    @PostMapping
    public boolean createOffer(@RequestBody NewOfferDto dto, Principal principal) {
        return offerService.createOffer(dto, principal.getName());
    }

    @GetMapping("/{id}")
    public OfferDto findOfferById(@PathVariable Long id) {
        return offerService.findOfferById(id);
    }

    @GetMapping
    public OffersResponseDto getAllOffers(@ModelAttribute OffersRequestDto dto) {
        return offerService.getAllOffers(dto);
    }

    @GetMapping("/min-max-price")
    public MinMaxPriceDto getMinMaxPrice() {
        return offerService.getMinMaxPrice();
    }

    @PutMapping("/{id}")
    public OfferDto updateOffer(@PathVariable Long id,@RequestBody NewOfferDto dto) {
        return offerService.updateOffer(id,dto);
    }

    @DeleteMapping("/{id}")
    public boolean deleteOffer(@PathVariable Long id) {
        return offerService.deleteOffer(id);
    }
}
