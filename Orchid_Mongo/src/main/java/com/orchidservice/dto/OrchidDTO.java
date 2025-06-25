package com.orchidservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrchidDTO {
    private String id;
    private Boolean isNatural;
    private String orchidDescription;
    private String orchidName;
    private String orchidUrl;
    private Integer price;
    private String orchidType;
    private String bloomingSeason;
    private String origin;
    private String difficultyLevel;
}
