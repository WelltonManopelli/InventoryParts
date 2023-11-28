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
import java.util.Optional;


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

     public Boolean deleteParts(String partNumber) {
    String login  = securityFilter.getLoadUser();
    Dealer dealer = usersRepository.findDealerByLogin(login);


    String part = partsRepository.findByPartNumberAndDealer(partNumber, dealer.getCod());

          if (part!=null) {

              partsRepository.deleteById(part);
              return true;

          }

          else

              return false; // Ou lançar uma exceção indicando que a peça não foi encontrada
      }









         public Parts updateParts(PartsRequest partsRequest) {

            String login  = securityFilter.getLoadUser();
            Dealer dealer = usersRepository.findDealerByLogin(login);


         String part = partsRepository.findByPartNumberAndDealer(partsRequest.partNumber(), dealer.getCod());
         Optional<Parts> partUpdate = partsRepository.findById(part);

        if (partUpdate.isPresent()) {

                    BeanUtils.copyProperties(partsRequest, partUpdate.get());
                    return partsRepository.save(partUpdate.get());

                }

        else

            return null; // Ou lançar uma exceção indicando que a peça não foi encontrada
        }



}

