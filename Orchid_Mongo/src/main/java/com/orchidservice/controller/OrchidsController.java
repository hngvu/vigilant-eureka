package com.orchidservice.controller;

import com.orchidservice.dto.OrchidDTO;
import com.orchidservice.dto.response.ApiResponse;
import com.orchidservice.service.OrchidsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orchids")
public class OrchidsController {
    @Autowired
    private OrchidsService orchidsService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllOrchids() {
        List<OrchidDTO> orchidDtoList = orchidsService.getAllOrchids();
        return ResponseEntity.ok(new ApiResponse<>(
           true,
                "Orchids fetched successfully",
                200,
                orchidDtoList
        ));

    }

    @GetMapping("/{orchidId}")
    public ResponseEntity<ApiResponse> getOrchidById(@PathVariable String orchidId) {
        OrchidDTO orchidDTO = orchidsService.getOrchidById(orchidId);
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Orchids fetched successfully",
                200,
                orchidDTO
        ));
    }
}
