package com.store.car.service;

import com.store.car.dto.CarPostDTO;
import com.store.car.dto.OwnerPostDTO;
import com.store.car.entity.OwnerPostEntity;
import com.store.car.repository.OwnerPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OwnerPostServiceImpl implements OwnerPostService {

    @Autowired
    private OwnerPostRepository ownerPostRepository;

    @Override
    public void createOwner(OwnerPostDTO ownerPostDTO) {
        OwnerPostEntity ownerPostEntity = mapOwnerDtoToEntity(ownerPostDTO);
        ownerPostRepository.save(ownerPostEntity);
    }

    @Override
    public List<OwnerPostDTO> getOwner() {
        List<OwnerPostDTO> ownerList = new ArrayList<>();
        ownerPostRepository.findAll().forEach(item -> ownerList.add(mapOwnerEntityToDto(item)));
        return ownerList;
    }

    @Override
    public OwnerPostDTO getOwnerById(Long id) {
        OwnerPostEntity ownerPostEntity = ownerPostRepository.findById(id).orElseThrow();
        return mapOwnerEntityToDto(ownerPostEntity);
    }

    @Override
    public void changeOwner(OwnerPostDTO ownerPostDTO, Long id) {
        ownerPostRepository.findById(id).ifPresentOrElse(item -> {
            item.setType(ownerPostDTO.getType());
            item.setName(ownerPostDTO.getName());
            item.setPhone(ownerPostDTO.getContactNumber());

            ownerPostRepository.save(item);
        }, () -> {
            throw new NoSuchElementException();
        });
    }

    @Override
    public void removerOwner(Long ownerId) {
        ownerPostRepository.deleteById(ownerId);
    }

    private OwnerPostEntity mapOwnerDtoToEntity(OwnerPostDTO ownerPostDTO) {
        OwnerPostEntity ownerPostEntity = new OwnerPostEntity();

        ownerPostEntity.setName(ownerPostDTO.getName());
        ownerPostEntity.setPhone(ownerPostDTO.getContactNumber());
        ownerPostEntity.setType(ownerPostDTO.getType());

        return ownerPostEntity;
    }

    private OwnerPostDTO mapOwnerEntityToDto(OwnerPostEntity ownerPostEntity) {
        return OwnerPostDTO.builder()
                .contactNumber(ownerPostEntity.getPhone())
                .name(ownerPostEntity.getName())
                .type(ownerPostEntity.getType())
                .build();
    }
}
