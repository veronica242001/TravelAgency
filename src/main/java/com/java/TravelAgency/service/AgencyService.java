package com.java.TravelAgency.service;


import com.java.TravelAgency.dto.AgencyDto;
import com.java.TravelAgency.entity.Agency;
import com.java.TravelAgency.exception.AgencyAlreadyExistsException;
import com.java.TravelAgency.exception.AgencyNotFoundException;
import com.java.TravelAgency.mapper.AgencyMapper;
import com.java.TravelAgency.repository.AgencyRepository;
import com.java.TravelAgency.constants.Constants;
import com.java.TravelAgency.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgencyService {
    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AgencyMapper agencyMapper;


    public List<AgencyDto> getAllAgencies() {
        return agencyRepository.findAll()
                .stream().map(a -> agencyMapper.mapToAgencyDto(a))
                .collect(Collectors.toList());
    }

    public AgencyDto getAgencyById(Long id) {
        Optional<Agency> agency = agencyRepository.findById(id);
        if (agency.isEmpty()) {
            throw new AgencyNotFoundException(String.format(Constants.AGENCY_NOT_FOUND, id));
        }
        return agencyMapper.mapToAgencyDto(agency.get());
    }

    public AgencyDto addAgency(Agency agency) {
        if (agencyRepository.findByName(agency.getName()).isPresent()) { // if the name already exists, throw exception
            throw new AgencyAlreadyExistsException(String.format(Constants.AGENCY_EXISTS, agency.getName()));
        }
        return agencyMapper.mapToAgencyDto(agencyRepository.save(agency));
    }

    public AgencyDto updateAgency(Long id, AgencyDto agencyDto) {
        Optional<Agency> agency = agencyRepository.findById(id);
        if (agency.isEmpty()) {
            throw new AgencyNotFoundException(String.format(Constants.AGENCY_NOT_FOUND, id));
        }
        return agencyMapper.mapToAgencyDto(agencyRepository.save(agencyMapper.mapToAgency(agencyDto)));
    }

    public boolean deleteAgency(Long id) {
        Optional<Agency> agency = agencyRepository.findById(id);
        if (agency.isEmpty()) {
            throw new AgencyNotFoundException(String.format(Constants.AGENCY_NOT_FOUND, id));
        }
        agencyRepository.delete(agency.get());
        return true;
    }

}
