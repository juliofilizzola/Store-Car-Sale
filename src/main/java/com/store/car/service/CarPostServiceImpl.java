package com.store.car.service;

import com.store.car.dto.CarPostDTO;
import com.store.car.entity.CarPostEntity;
import com.store.car.repository.CarPostRepository;
import com.store.car.repository.OwnerPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarPostServiceImpl implements CarPostService{

    @Autowired
    private CarPostRepository carPostRepository;

    @Autowired
    private OwnerPostRepository ownerPostRepository;

    @Override
    public void newPostDetails(CarPostDTO carPostDTO) {
        CarPostEntity carPostEntity = mapCarDtoToEntity(carPostDTO);

        carPostRepository.save(carPostEntity);
    }

    @Override
    public List<CarPostDTO> getCarSales() {
        List<CarPostDTO> listCarSale = new ArrayList<>();
        carPostRepository.findAll().forEach(item -> listCarSale.add(mapCarEntityToDTO(item)));
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

    private CarPostEntity mapCarDtoToEntity(CarPostDTO carPostDTO) {
        CarPostEntity carPostEntity = new CarPostEntity();
        System.out.println("x");
        ownerPostRepository.findById(carPostDTO.getOwnerId())
                .ifPresentOrElse( item -> {
            carPostEntity.setOwnerPost(item);
            carPostEntity.setContact(item.getPhone());
        }, () -> {
                    System.out.println("Log aqui");

               throw new RuntimeException();
        });

        carPostEntity.setModel(carPostDTO.getModel());
        carPostEntity.setBrand(carPostDTO.getBrand());
        carPostEntity.setPrice(carPostDTO.getPrice());
        carPostEntity.setCity(carPostDTO.getCity());
        carPostEntity.setDescription(carPostDTO.getDescription());
        carPostEntity.setEngine(carPostDTO.getEngineVersion());
        carPostEntity.setCreatedAt(String.valueOf(new Date()));

        return carPostEntity;
    }
}
