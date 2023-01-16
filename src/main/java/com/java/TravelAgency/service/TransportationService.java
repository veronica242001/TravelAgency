package com.java.TravelAgency.service;

import com.java.TravelAgency.dto.TransportationDto;
import com.java.TravelAgency.entity.Transportation;
import com.java.TravelAgency.exception.TransportationAlreadyExistsException;
import com.java.TravelAgency.exception.TransportationNotFoundException;
import com.java.TravelAgency.mapper.TransportationMapper;
import com.java.TravelAgency.repository.TransportationRepository;
import com.java.TravelAgency.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransportationService implements BaseService {

    @Autowired
    TransportationRepository transportationRepository;


    @Autowired
    private TransportationMapper transportationMapper;


    public List<TransportationDto> getAllTransportations() {
        return transportationRepository.findAll()
                .stream().map(a -> transportationMapper.mapToTransportationDto(a))
                .collect(Collectors.toList());
    }

    public TransportationDto getTransportationById(Long id) {
        Optional<Transportation> transportation = transportationRepository.findById(id);
        if (transportation.isEmpty()) {
            throw new TransportationNotFoundException(String.format(Constants.TRANSPORTATION_NOT_FOUND, id));
        }
        return transportationMapper.mapToTransportationDto(transportation.get());
    }

    public TransportationDto addTransportation(Transportation transportation) {
        Optional<Transportation> transp = transportationRepository.findByName(transportation.getName());
        if (transp.isPresent()) { // if the already exists, throw exception
            throw new TransportationAlreadyExistsException(String.format(Constants.TRANSPORTATION_EXISTS, transportation.getName()));
        }
        return transportationMapper.mapToTransportationDto(transportationRepository.save(transportation));
    }

    public boolean deleteObject(Long id) {
        Optional<Transportation> transportation = transportationRepository.findById(id);
        if (transportation.isEmpty()) {
            throw new TransportationNotFoundException(String.format(Constants.TRANSPORTATION_NOT_FOUND, id));
        }
        transportationRepository.delete(transportation.get());
        return true;
    }

    public TransportationDto updatePrice(String name, String addressFrom, Double percent) {
        Optional<Transportation> transportation = transportationRepository.findByNameAndAddressfrom(name, addressFrom);
        if (transportation.isEmpty()) {
            throw new TransportationNotFoundException(String.format(Constants.TRANSPORTATION_NOT_FOUND, name));
        }
        Double newPrice = (1 + percent) * transportation.get().getPrice();
        transportation.get().setPrice(newPrice);
        return transportationMapper.mapToTransportationDto(transportationRepository.save(transportation.get()));
    }
}
