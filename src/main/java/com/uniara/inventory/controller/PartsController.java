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
    private PartsRepository partsRepository;


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


  @PutMapping("/parts/update")
    public ResponseEntity<Parts> updateParts(@RequestBody @Valid PartsRequest partsRequest) {
      Parts updatedParts = partsService.updateParts(partsRequest);

      if (updatedParts != null) {
          return ResponseEntity.ok(updatedParts);
      } else {
          throw new EntityNotFoundException();
      }
  }

    @DeleteMapping("/parts/delete={id}")
    public ResponseEntity <?> delete(@PathVariable String id) {
        return partsRepository.findById(id)
                .map(record -> {
                    partsRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

 /*   @DeleteMapping("/parts/delete={partNumber}")
    public ResponseEntity <?> delete(@PathVariable String partNumber) {
       Boolean delete = partsService.deleteParts(partNumber);
       if(delete)
       return ResponseEntity.ok().build();
       else
           return ResponseEntity.notFound().build();
    }
*/
}
