package com.uniara.inventory.service;

import com.uniara.inventory.domain.parts.Parts;
import com.uniara.inventory.domain.parts.PartsRequest;
import com.uniara.inventory.repositories.PartsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartsService {
        @Autowired
        private PartsRepository partsRepository;

        public List<Parts> getAllParts() {
            return partsRepository.findAll();
        }

        public List<Parts> getAllPartsByPartNumber(String partNumber) {
        return partsRepository.findAllByPartNumber(partNumber);
        }
        public List<Parts> getAllPartsByDealerCod(String DealerCod) {
            return partsRepository.findAllByDealerCod(DealerCod);
        }

        public Parts createParts(PartsRequest partsRequest) {
            var part = new Parts();
            BeanUtils.copyProperties(partsRequest, part);
            return partsRepository.save(part);
        }

        public Parts deleteParts(String partNumber, PartsRequest partsRequest){

            return partsRepository.deleteByPartNumber(partNumber, String.valueOf(partsRequest.dealer()));
        }


         public Parts updateParts(String partNumber, PartsRequest partsRequest) {
         Parts part = partsRepository.findByPartNumber(partNumber, String.valueOf(partsRequest.dealer()));


        if (part!= null) {
                    BeanUtils.copyProperties(partsRequest, part);
                    return partsRepository.save(part);

                }

        else

            return null; // Ou lançar uma exceção indicando que a peça não foi encontrada
        }



}

