package com.store.car.controller;

import com.store.car.dto.OwnerPostDTO;

import com.store.car.service.OwnerPostService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class OwnerPostController {

    @Autowired
    private OwnerPostService ownerPostService;

    @PostMapping()
    public ResponseEntity createOwner(@RequestBody OwnerPostDTO ownerPostDTO) {
        ownerPostService.createOwner(ownerPostDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<OwnerPostDTO>> getOwner() {
        return ResponseEntity.status(HttpStatus.FOUND).body(ownerPostService.getOwner());
    }

    @PutMapping("/{id}")
    public ResponseEntity changeOwner(@RequestBody OwnerPostDTO ownerPostDTO, @PathVariable("id") String id) {
        ownerPostService.changeOwner(ownerPostDTO, Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeOwner(@PathVariable("id") String id) {
        ownerPostService.removerOwner(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerPostDTO> getOwnerById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(ownerPostService.getOwnerById(Long.valueOf(id)));
    }
}
