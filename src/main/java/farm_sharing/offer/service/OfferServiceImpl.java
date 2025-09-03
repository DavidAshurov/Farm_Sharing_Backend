package farm_sharing.offer.service;

import farm_sharing.exceptions.EntityNotFoundException;
import farm_sharing.offer.dao.OfferRepository;
import farm_sharing.offer.dto.NewOfferDto;
import farm_sharing.offer.dto.OfferDto;
import farm_sharing.offer.dto.OffersRequestDto;
import farm_sharing.offer.dto.OffersResponseDto;
import farm_sharing.offer.model.Offer;
import farm_sharing.offer.specification.OfferSpecification;
import farm_sharing.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService{

    final UserRepository userRepository;
    final OfferRepository offerRepository;
    final ModelMapper modelMapper;

    @Transactional
    @Override
    public boolean createOffer(NewOfferDto dto, String farmNickname) {
        Offer offer = modelMapper.map(dto, Offer.class);
        offer.setFarm(userRepository.findByNickname(farmNickname).get());
        offerRepository.save(offer);
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public OfferDto findOfferById(Long id) {
        Offer offer = offerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Offer with this ID doesn't exist in DB"));
        return modelMapper.map(offer, OfferDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public OffersResponseDto getAllOffers(OffersRequestDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNumber(),dto.getPageSize(),
                Sort.by(Sort.Direction.fromString(dto.getSortDirection()), dto.getSortField()));
        Specification<Offer> spec = OfferSpecification.offerSpecification(dto.getCategory());

        Page<Offer> page = offerRepository.findAll(spec,pageable);

        return new OffersResponseDto(
                page.getContent().stream().map(offer -> modelMapper.map(offer, OfferDto.class)).toList(),
                page.getPageable().getPageNumber(),
                page.getNumberOfElements(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }

    @Transactional
    @Override
    public OfferDto updateOffer(Long id, NewOfferDto dto) {
        Offer offer = offerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Offer with this ID doesn't exist in DB"));
        modelMapper.map(dto,offer);
        return modelMapper.map(offer, OfferDto.class);
    }

    @Transactional
    @Override
    public boolean deleteOffer(Long id) {
        Offer offer = offerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Offer with this ID doesn't exist in DB"));
        offerRepository.delete(offer);
        return true;
    }
}
