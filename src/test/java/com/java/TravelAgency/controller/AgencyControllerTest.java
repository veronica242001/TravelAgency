package com.java.TravelAgency.controller;

import com.java.TravelAgency.dto.AgencyDto;
import com.java.TravelAgency.entity.Agency;
import com.java.TravelAgency.mapper.AgencyMapper;
import com.java.TravelAgency.service.AgencyService;
import com.java.TravelAgency.utils.AgenciesMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AgencyControllerTest {

    @InjectMocks
    AgencyController agencyController;
    @Mock
    AgencyService agencyService;
    @Mock
    AgencyMapper agencyMapper;
    @Mock
    Model model;
    @Mock
    BindingResult bindingResult;
    AgencyDto agencyDto;
    Agency agency;


    @Test
    public void getAgencyByIdTest() throws ParseException {
        //GIVEN
        agencyDto = AgenciesMocks.mockAgencyDto();

        //WHEN
        when(agencyService.getAgencyById(1L)).thenReturn(agencyDto);

        //THEN
        ModelAndView modelAndViewAgency = agencyController.getAgencyById(1L);
        assertEquals("/agencyDetails", modelAndViewAgency.getViewName());
        verify(agencyService, times(1)).getAgencyById(1L);
    }


    @Test
    public void saveAgencyTest() throws ParseException {
        //GIVEN
        agencyDto = AgenciesMocks.mockAgencyDto();

        //WHEN
        when(agencyService.addAgency(agencyMapper.mapToAgency(agencyDto))).thenReturn(agencyDto);

        //THEN
        String viewName = agencyController.saveAgency(agencyDto, bindingResult);
        assertEquals("redirect:/agencies", viewName);
        verify(agencyService, times(1)).addAgency(agencyMapper.mapToAgency(agencyDto));
    }

    @Test
    public void deleteAgencyTest() throws ParseException {
        //GIVEN
        agencyDto = AgenciesMocks.mockAgencyDto();

        //WHEN
        when(agencyService.deleteAgency(1L)).thenReturn(true);

        //THEN
        String viewName = agencyController.deleteAgency(1L);
        assertEquals("redirect:/agencies", viewName);
        verify(agencyService, times(1)).deleteAgency(1L);
    }

}

