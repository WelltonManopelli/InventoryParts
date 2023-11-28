package com.uniara.inventory.controller;

import com.uniara.inventory.domain.parts.Parts;
import com.uniara.inventory.domain.parts.PartsRequest;
import com.uniara.inventory.repositories.PartsRepository;
import com.uniara.inventory.service.PartsService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class PartsController {
    @Autowired
    private PartsService partsService;

    @Autowired
    private PartsRepository repository;


    @GetMapping("/parts")
    public ResponseEntity getAllParts() {
        List<Parts> parts = partsService.getAllParts();

        if (!parts.isEmpty()){
            return ResponseEntity.ok(parts);
        } else {
            return ResponseEntity.notFound().build(); // Ou outra resposta apropriada
        }
    }

    @GetMapping("parts/partNumber={partNumber}")
    public ResponseEntity<List<Parts>> getAllPartsByPartNumber(@PathVariable String partNumber) {
        List<Parts> parts = partsService.getAllPartsByPartNumber(partNumber);

        if (!parts.isEmpty()) {
            return ResponseEntity.ok(parts);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @GetMapping("/parts/dealerCod={DealerCod}")
    public ResponseEntity<List<Parts>> getAllPartsByDealerCod(@PathVariable String DealerCod) {
        List<Parts> parts = partsService.getAllPartsByDealerCod(DealerCod);

        if (!parts.isEmpty()) {
            return ResponseEntity.ok(parts);
        } else {
            throw new EntityNotFoundException();
        }
    }


  @PostMapping("/parts")
    public ResponseEntity<Parts> createParts(@RequestBody @Valid  PartsRequest partsRequest) {
        Parts part = partsService.createParts(partsRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(part);
    }


  /*  @PutMapping("/{partNumber}")
    public ResponseEntity<Parts> updateParts(@PathVariable String partNumber, @RequestBody @Valid PartsRequest partsRequest) {
        Parts updatedParts = partsService.updateParts(partNumber, partsRequest);

        if (updatedParts != null) {
            return ResponseEntity.ok(updatedParts);
        } else {
            throw new EntityNotFoundException();
        }
    }*/

    @DeleteMapping("/parts/delete={id}")
    public ResponseEntity <?> delete(@PathVariable String id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }


}
