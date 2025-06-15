package farm_sharing.offer.service;

import farm_sharing.farm.dao.FarmRepository;
import farm_sharing.offer.dao.OfferRepository;
import farm_sharing.offer.dto.NewOfferDto;
import farm_sharing.offer.dto.OfferDto;
import farm_sharing.offer.dto.exceptions.OfferNotFoundException;
import farm_sharing.offer.model.Offer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService{

    final FarmRepository farmRepository;
    final OfferRepository offerRepository;
    final ModelMapper modelMapper;

    @Transactional
    @Override
    public boolean createOffer(NewOfferDto dto, Long farmId) {
        Offer offer = modelMapper.map(dto, Offer.class);
        offer.setFarm(farmRepository.findById(farmId).get());
        offerRepository.save(offer);
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public OfferDto findOfferById(Long id) {
        Offer offer = offerRepository.findById(id).orElseThrow(OfferNotFoundException::new);
        return modelMapper.map(offer, OfferDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<OfferDto> getAllOffers() {
        return offerRepository.findAll().stream()
                .map(offer -> modelMapper.map(offer, OfferDto.class))
                .toList();
    }

    @Transactional
    @Override
    public OfferDto updateOffer(Long id, NewOfferDto dto) {
        Offer offer = offerRepository.findById(id).orElseThrow(OfferNotFoundException::new);
        modelMapper.map(dto,offer);
        return modelMapper.map(offer, OfferDto.class);
    }

    @Transactional
    @Override
    public boolean deleteOffer(Long id) {
        Offer offer = offerRepository.findById(id).orElseThrow(OfferNotFoundException::new);
        offerRepository.delete(offer);
        return true;
    }
}
