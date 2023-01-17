package com.java.TravelAgency.service;

import com.java.TravelAgency.constants.Constants;
import com.java.TravelAgency.dto.OfferDto;
import com.java.TravelAgency.entity.Offer;
import com.java.TravelAgency.exception.OfferNotFoundException;
import com.java.TravelAgency.mapper.OfferMapper;
import com.java.TravelAgency.repository.OfferRepository;
import com.java.TravelAgency.utils.OffersMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OfferServiceTest {

    @InjectMocks
    OfferService offerService;

    @Mock
    OfferRepository offerRepository;

    @Mock
    OfferMapper offerMapper;

    Offer offer;

    OfferDto offerDto;

    @Test
    public void testGetAllOffers() throws ParseException {
        //GIVEN
        offer = OffersMocks.mockOffer();
        offerDto = OffersMocks.mockOfferDto();

        List<Offer> offers = new ArrayList<>();
        offers.add(offer);
        List<OfferDto> OffersDto = new ArrayList<>();
        OffersDto.add(offerDto);

        //WHEN
        when(offerRepository.findAll()).thenReturn(offers);
        when(offerMapper.mapToOfferDto(offer)).thenReturn(offerDto);

        //THEN
        List<OfferDto> result = offerService.getAllOffers();
        assertEquals(result, OffersDto);
    }


    @Test
    public void testAddOffer() throws ParseException {
        //GIVEN
        offer = OffersMocks.mockOffer();
        offerDto = OffersMocks.mockOfferDto();

        //WHEN
        when(offerRepository.save(offer)).thenReturn(offer);
        when(offerMapper.mapToOfferDto(offer)).thenReturn(offerDto);

        OfferDto result = offerService.addOffer(offer);

        //THEN
        assertTrue(result.getBookDate().equals(offerDto.getBookDate()));
        assertNotNull(result);
    }
    @Test
    public void testUpdateOffer() throws ParseException {
        //GIVEN
        offer = OffersMocks.mockOffer();
        offerDto = OffersMocks.mockOfferDto();

        //WHEN
        when(offerRepository.findById(offer.getId())).thenReturn(Optional.ofNullable(offer));

        Offer offer2 = OffersMocks.mockOffer();

        when(offerMapper.mapToOfferDto(offer2)).thenReturn(offerDto);
        when(offerRepository.save(offer)).thenReturn(offer2);

        //THEN
        OfferDto result = offerService.updateOffer(offer);
        assertThat(result).isNotNull();
        assertTrue(result.equals(offerMapper.mapToOfferDto(offer2)));
    }
    @Test
    public void testUpdateOfferThrowException() throws ParseException {
        //GIVEN
        offer = null;
        Offer offer2 =OffersMocks.mockOffer();
        offerDto = null;

        //WHEN
        when(offerRepository.findById(offer2.getId())).thenReturn(Optional.ofNullable(offer));
        //THEN
        OfferNotFoundException OfferNotFoundException = assertThrows(OfferNotFoundException.class, () -> offerService.updateOffer(offer2));
        assertEquals(Constants.OFFER_NOT_FOUND, OfferNotFoundException.getMessage());
    }

    @Test
    public void testDeleteOffer() throws ParseException {
        //GIVEN
        offer = OffersMocks.mockOffer();
        offerDto = OffersMocks.mockOfferDto();

        //WHEN
        when(offerRepository.findById(offer.getId())).thenReturn(Optional.ofNullable(offer));

        //THEN
        Boolean result = offerService.deleteOffer(offer.getId());
        assertEquals(result, true);
    }

}