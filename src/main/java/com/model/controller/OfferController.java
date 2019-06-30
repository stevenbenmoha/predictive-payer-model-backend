package com.model.controller;

import com.model.domain.entity.Offer;
import com.model.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @RequestMapping(value = "/offer", method = RequestMethod.GET)
    public List<Offer> getOffers() {
        return (List<Offer>) offerService.getOffers();
    }

    @RequestMapping(value = "/offer/save", method = RequestMethod.POST)
    void addOffer(@RequestBody Offer offer) {
        offerService.addOffer(offer);
    }
}

