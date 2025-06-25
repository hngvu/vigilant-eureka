package com.orchidservice.service;

import com.orchidservice.dto.OrchidDTO;
import com.orchidservice.entity.Orchids;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrchidsService {
    public Orchids getOrchid(String orchidId);
    public void saveOrchid(Orchids orchid);
    public void updateOrchid(String orchidId, Orchids orchid);
    public void deleteOrchid(String orchidId);
    public List<OrchidDTO> getAllOrchids();
    OrchidDTO getOrchidById(String orchidId);
}
