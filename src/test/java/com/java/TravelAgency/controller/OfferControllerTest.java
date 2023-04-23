package com.java.TravelAgency.controller;

import com.java.TravelAgency.dto.OfferDto;
import com.java.TravelAgency.entity.Offer;
import com.java.TravelAgency.service.OfferService;
import com.java.TravelAgency.utils.OffersMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OfferControllerTest {

    @InjectMocks
    OfferController offerController;
    @Mock
    OfferService offerService;
    OfferDto offerDto;
    Offer offer;


    @Test
    public void deleteOfferTest() throws ParseException {
        //GIVEN
        offerDto = OffersMocks.mockOfferDto();

        //WHEN
        when(offerService.deleteOffer(1L)).thenReturn(true);

        //THEN
        String viewName = offerController.deleteOffer(1L);
        assertEquals("redirect:/offers", viewName);
        verify(offerService, times(1)).deleteOffer(1L);
    }
}

