package com.orchidservice.service.Impl;

import com.orchidservice.dto.OrchidDTO;
import com.orchidservice.entity.Orchids;
import com.orchidservice.repository.OrchidsRepository;
import com.orchidservice.service.OrchidsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrchidsServiceImpl implements OrchidsService {
    @Autowired
    private OrchidsRepository orchidsRepository;

    @Override
    public Orchids getOrchid(String orchidId) {
        return orchidsRepository.findById(orchidId)
                .orElseThrow(() -> new IllegalArgumentException("Orchid with ID " + orchidId + " does not exist."));
    }

    @Override
    public void saveOrchid(Orchids orchid) {
        if (orchidsRepository.existsById(orchid.getId())) {
            throw new IllegalArgumentException("Orchid with ID " + orchid.getId() + " already exists.");
        } else {
            orchidsRepository.save(orchid);
            System.out.println("Orchid created successfully: " + orchid);
        }
    }


    @Override
    public void updateOrchid(String orchidId, Orchids orchid) {
        Optional<Orchids> existing = orchidsRepository.findById(orchidId);
        if (existing.isPresent()) {
            Orchids existingOrchid = existing.get();
            if (orchid.getOrchidName() != null) existingOrchid.setOrchidName(orchid.getOrchidName());
            if (orchid.getOrchidUrl() != null) existingOrchid.setOrchidUrl(orchid.getOrchidUrl());
            if (orchid.getOrchidDescription()!= null) existingOrchid.setOrchidDescription(orchid.getOrchidDescription());
            if (orchid.getPrice() != null) existingOrchid.setPrice(orchid.getPrice());
            // Add other fields as needed
            orchidsRepository.save(existingOrchid);
            System.out.println("Orchid updated successfully: " + existingOrchid);
        } else {
            throw new IllegalArgumentException("Orchid with ID " + orchidId + " does not exist.");
        }
    }

    @Override
    public void deleteOrchid(String orchidId) {
        if (orchidsRepository.existsById(orchidId)) {
            orchidsRepository.deleteById(orchidId);
            System.out.println("Orchid deleted successfully: " + orchidId);
        } else {
            throw new IllegalArgumentException("Orchid with ID " + orchidId + " does not exist.");
        }
    }

    @Override
    public List<OrchidDTO> getAllOrchids() {
        List<Orchids> orchidsList = orchidsRepository.findAll();
        List<OrchidDTO> orchidDtoList = new ArrayList<>();
        for (Orchids orchids : orchidsList) {
            OrchidDTO orchidDto = convertDTO(orchids);
            orchidDtoList.add(orchidDto);
        }
        return orchidDtoList;
    }

    @Override
    public OrchidDTO getOrchidById(String orchidId) {
        Orchids orchids = orchidsRepository.findById(orchidId).orElseThrow(
                () -> new RuntimeException("Orchid with ID " + orchidId + " not found")
        );
        return convertDTO(orchids);
    }

    public OrchidDTO convertDTO(Orchids orchids) {
        OrchidDTO orchidDto = new OrchidDTO().builder()
                .id(orchids.getId())
                .isNatural(orchids.isNatural())
                .orchidDescription(orchids.getOrchidDescription())
                .orchidName(orchids.getOrchidName())
                .orchidUrl(orchids.getOrchidUrl())
                .price(orchids.getPrice())
                .orchidType(orchids.getCategories().getCategoryName())
                .bloomingSeason(orchids.getBloomingSeason())
                .difficultyLevel(orchids.getDifficultyLevel())
                .origin(orchids.getOrigin())
                .build();

        return orchidDto;
    }
}
