package com.uniara.inventory.controller;

import com.uniara.inventory.domain.parts.Parts;
import com.uniara.inventory.domain.parts.PartsRequest;
import com.uniara.inventory.service.PartsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/parts")
public class PartsController {
    @Autowired
    private PartsService partsService;

    @GetMapping
    public ResponseEntity getAllParts() {
        return ResponseEntity.ok(partsService.getAllParts());
    }

    @GetMapping("/{partNumber}")
    public ResponseEntity<List<Parts>> getAllPartsByPartNumber(@PathVariable String partNumber) {
        List<Parts> parts = partsService.getAllPartsByPartNumber(partNumber);

        if (!parts.isEmpty()) {
            return ResponseEntity.ok(parts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{dealerCod}")
    public ResponseEntity<List<Parts>> getAllPartsByDealerCod(@PathVariable String DealerCod) {
        List<Parts> parts = partsService.getAllPartsByDealerCod(DealerCod);

        if (!parts.isEmpty()) {
            return ResponseEntity.ok(parts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<Parts> createParts(@RequestBody @Valid  PartsRequest partsRequest) {
        Parts part = partsService.createParts(partsRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(part);
    }


    @PutMapping("/{partNumber}")
    public ResponseEntity<Parts> updateParts(@PathVariable String partNumber, @RequestBody @Valid PartsRequest partsRequest) {
        Parts updatedParts = partsService.updateParts(partNumber, partsRequest);

        if (updatedParts != null) {
            return ResponseEntity.ok(updatedParts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{partNumber}")
    public ResponseEntity<Parts> deleteParts(@PathVariable String partNumber, @RequestBody @Valid PartsRequest partsRequest) {
        var deleteParts = partsService.deleteParts(partNumber, partsRequest);

        if (deleteParts != null) {
            return ResponseEntity.ok(deleteParts);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
