package com.store.car.service;

import com.store.car.dto.OwnerPostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OwnerPostService {
    void createOwner(OwnerPostDTO ownerPostDTO);

    List<OwnerPostDTO> getOwner();

    OwnerPostDTO getOwnerById(Long id);

    void changeOwner(OwnerPostDTO ownerPostDTO, Long id);

    void removerOwner(Long ownerId);
}
