package com.uniara.inventory.service;

import com.uniara.inventory.domain.dealer.Dealer;
import com.uniara.inventory.domain.parts.Parts;
import com.uniara.inventory.domain.parts.PartsRequest;
import com.uniara.inventory.domain.user.Users;
import com.uniara.inventory.repositories.DealerRepository;
import com.uniara.inventory.repositories.PartsRepository;
import com.uniara.inventory.repositories.UsersRepository;
import com.uniara.inventory.security.SecurityConfig;
import com.uniara.inventory.security.SecurityFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class PartsService {

       @Autowired
        private SecurityFilter securityFilter;
        @Autowired
        private PartsRepository partsRepository;

        @Autowired
        private UsersRepository usersRepository;

        @Autowired
        private DealerRepository dealerRepository;

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

            String login  = securityFilter.getLoadUser();
            Dealer dealer = usersRepository.findDealerByLogin(login);

            var part = new Parts(partsRequest, dealer);
            return partsRepository.save(part);
        }

    /*    public Parts deleteParts(String partNumber, PartsRequest partsRequest){

            return partsRepository.deleteByPartNumber(partNumber, String.valueOf(partsRequest.dealer()));
        }

*//*
         public Parts updateParts(String partNumber, PartsRequest partsRequest) {
         Parts part = partsRepository.findByPartNumber(partNumber, String.valueOf(partsRequest.dealer()));


        if (part!= null) {
                    BeanUtils.copyProperties(partsRequest, part);
                    return partsRepository.save(part);

                }

        else

            return null; // Ou lançar uma exceção indicando que a peça não foi encontrada
        }

*/

}

