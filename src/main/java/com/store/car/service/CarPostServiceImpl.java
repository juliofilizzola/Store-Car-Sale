package com.store.car.service;

import com.store.car.dto.CarPostDTO;
import com.store.car.entity.CarPostEntity;
import com.store.car.repository.CarPostRepository;
import com.store.car.repository.OwnerPostRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CarPostServiceImpl implements CarPostService{

    @Autowired
    private CarPostRepository carPostRepository;

    @Autowired
    private OwnerPostRepository ownerPostRepository;

    @Override
    public void newPostDetails(CarPostDTO carPostDTO) {

    }

    @Override
    public List<CarPostDTO> getCarSales() {
        List<CarPostDTO> listCarSale = new ArrayList<>();
        carPostRepository.findAll().forEach(item -> {
            listCarSale.add(mapCarEntityToDTO(item));
        });
        return listCarSale;
    }

    @Override
    public void changeCarSale(CarPostDTO carPostDTO, Long id) {
        carPostRepository.findById(id).ifPresentOrElse(item -> {
            item.setDescription(carPostDTO.getDescription());
            item.setContact(carPostDTO.getContact());
            item.setPrice(carPostDTO.getPrice());
            item.setBrand(carPostDTO.getBrand());
            item.setCity(carPostDTO.getCity());
            item.setEngine(carPostDTO.getEngineVersion());
            item.setModel(carPostDTO.getModel());

            carPostRepository.save(item);
        },  () -> {
            throw new NoSuchElementException();
        });
    }

    @Override
    public void removerCarSale(Long carId) {
        carPostRepository.deleteById(carId);
    }

    private CarPostDTO mapCarEntityToDTO(CarPostEntity carPostEntity) {
        return  CarPostDTO.builder()
                .brand(carPostEntity.getBrand())
                .city(carPostEntity.getCity())
                .model(carPostEntity.getModel())
                .description(carPostEntity.getDescription())
                .engineVersion(carPostEntity.getEngine())
                .createdDate(carPostEntity.getCreatedAt())
                .ownerName(carPostEntity.getOwnerPost().getName())
                .price(carPostEntity.getPrice()).build();
    }
}
