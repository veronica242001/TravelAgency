package com.java.TravelAgency.service;

import com.java.TravelAgency.dto.AccommodationDto;
import com.java.TravelAgency.entity.Accommodation;
import com.java.TravelAgency.exception.AccommodationAlreadyExistsException;
import com.java.TravelAgency.exception.AccommodationNotFoundException;
import com.java.TravelAgency.mapper.AccommodationMapper;
import com.java.TravelAgency.repository.AccommodationRepository;
import com.java.TravelAgency.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccommodationService implements BaseService {

    @Autowired
    AccommodationRepository accommodationRepository;


    @Autowired
    private AccommodationMapper accommodationMapper;


    public List<AccommodationDto> getAllAccommodations() {
        return accommodationRepository.findAll()
                .stream().map(a -> accommodationMapper.mapToAccommodationDto(a))
                .collect(Collectors.toList());
    }

    public AccommodationDto getAccommodationById(Long id) {
        Optional<Accommodation> accommodation = accommodationRepository.findById(id);
        if (accommodation.isEmpty()) {
            throw new AccommodationNotFoundException(String.format(Constants.ACCOMMODATION_NOT_FOUND, id));
        }
        return accommodationMapper.mapToAccommodationDto(accommodation.get());
    }

    public AccommodationDto addAccommodation(Accommodation accommodation) {
        if (accommodationRepository.findByName(accommodation.getName()).isPresent()) { // if the name already exists, throw exception
            throw new AccommodationAlreadyExistsException(String.format(Constants.ACCOMMODATION_EXISTS, accommodation.getName()));
        }
        return accommodationMapper.mapToAccommodationDto(accommodationRepository.save(accommodation));
    }
    public AccommodationDto updateAccommodation(Long id, AccommodationDto accommodationDto ) {
        Optional<Accommodation> accommodation = accommodationRepository.findById(id);
        if (accommodation.isEmpty()) {
            throw new AccommodationNotFoundException(String.format(Constants.ACCOMMODATION_NOT_FOUND, id));
        }
        return accommodationMapper.mapToAccommodationDto(accommodationRepository.save(accommodationMapper.mapToAccommodation(accommodationDto)));
    }

    public boolean deleteObject(Long id) {
        Optional<Accommodation> accommodation = accommodationRepository.findById(id);
        if (accommodation.isEmpty()) {
            throw new AccommodationNotFoundException(String.format(Constants.ACCOMMODATION_NOT_FOUND, id));
        }
        accommodationRepository.delete(accommodation.get());
        return true;
    }
}


