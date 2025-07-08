package farm_sharing.offer.controller;

import farm_sharing.offer.dto.NewOfferDto;
import farm_sharing.offer.dto.OfferDto;
import farm_sharing.offer.service.OfferService;
import lombok.RequiredArgsConstructor;
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
    public List<OfferDto> getAllOffers() {
        return offerService.getAllOffers();
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
