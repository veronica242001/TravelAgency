package com.java.TravelAgency.service;


import com.java.TravelAgency.constants.Constants;
import com.java.TravelAgency.dto.OfferDto;
import com.java.TravelAgency.entity.Offer;
import com.java.TravelAgency.exception.OfferNotFoundException;
import com.java.TravelAgency.mapper.OfferMapper;
import com.java.TravelAgency.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferService {
    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private OfferMapper offerMapper;


    public List<OfferDto> getAllOffers() {
        return offerRepository.findAll()
                .stream().map(a -> offerMapper.mapToOfferDto(a))
                .collect(Collectors.toList());
    }

    public OfferDto getOfferById(Long id) {
        Optional<Offer> offer = offerRepository.findById(id);
        if (offer.isEmpty()) {
            throw new OfferNotFoundException(String.format(Constants.OFFER_NOT_FOUND, id));
        }
        return offerMapper.mapToOfferDto(offer.get());
    }

    public OfferDto addOffer(Offer offer) {

        return offerMapper.mapToOfferDto(offerRepository.save(offer));
    }

    public OfferDto updateOffer(Offer updatedOffer) {
        Optional<Offer> offer = offerRepository.findById(updatedOffer.getId());
        if (offer.isEmpty()) {
            throw new OfferNotFoundException(String.format(Constants.OFFER_NOT_FOUND, updatedOffer.getId()));
        }
        return offerMapper.mapToOfferDto(offerRepository.save(updatedOffer));
    }

    public boolean deleteOffer(Long id) {
        Optional<Offer> offer = offerRepository.findById(id);
        if (offer.isEmpty()) {
            throw new OfferNotFoundException(String.format(Constants.OFFER_NOT_FOUND, id));
        }
        offerRepository.delete(offer.get());
        return true;
    }

    public Page<OfferDto> findPaginatedOffers(Pageable pageable) {
        Page<OfferDto> offerPage = offerRepository.findAll(pageable).map(offer -> offerMapper.mapToOfferDto(offer));;
        return offerPage;
    }
}
