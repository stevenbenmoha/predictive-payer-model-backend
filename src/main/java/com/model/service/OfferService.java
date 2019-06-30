package com.model.service;

import com.model.domain.entity.Offer;
import com.model.domain.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    @Autowired
    OfferRepository offerRepository;

    public List<Offer> getOffers() {
        return (List<Offer>) offerRepository.findAll();
    }

    public void addOffer(Offer offer) {
        offerRepository.save(offer);
    }

}
