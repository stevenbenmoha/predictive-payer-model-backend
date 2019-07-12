package com.model.controller;

import com.model.domain.entity.Offer;
import com.model.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smile.classification.LogisticRegression;
import smile.data.*;
import smile.data.parser.ArffParser;
import smile.data.parser.DelimitedTextParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
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
    void addOffer(@RequestBody Offer offer) throws IOException, ParseException {
        offerService.addOffer(offer);


        DelimitedTextParser delimitedTextParser = new DelimitedTextParser();
        Attribute[] attributes = new Attribute[26];

        attributes[0] = new StringAttribute("Channel");
        attributes[1] = new StringAttribute("Customer");
        attributes[2] = new StringAttribute("Segment/Plan");
        attributes[3] = new StringAttribute("Deal_Level_of_Control");
        attributes[4] = new StringAttribute("Therapeutic_Area");
        attributes[5] = new StringAttribute("Product");
        attributes[6] = new NumericAttribute("WAC_Price");
        attributes[7] = new StringAttribute("Start_Date");
        attributes[8] = new StringAttribute("End_Date");
        attributes[9] = new StringAttribute("2018_Status_Quo_Brand_Manufacturer");
        attributes[10] = new StringAttribute("2018_Status_Quo_Tier");
        attributes[11] = new StringAttribute("2018_Status_Quo_Placement");
        attributes[12] = new StringAttribute("2018_Status_Quo_Restriction");
        attributes[13] = new StringAttribute("Deal_Brand_Manufacturer");
        attributes[14] = new StringAttribute("Deal_Tier");
        attributes[15] = new StringAttribute("Deal_Placement");
        attributes[16] = new StringAttribute("Deal_Restriction");
        attributes[17] = new NumericAttribute("Deal_Rebate");
        attributes[18] = new StringAttribute("PP_Type");
        attributes[19] = new NumericAttribute("PP_Threshold");
        attributes[20] = new StringAttribute("PP_Lookback_Date");
        attributes[21] = new StringAttribute("2019_Actual_Brand_Manufacturer");
        attributes[22] = new StringAttribute("2019_Actual_Tier");
        attributes[23] = new StringAttribute("2019_Actual_Placement");
        attributes[24] = new StringAttribute("2019_Actual_Restriction");
        attributes[25] = new NumericAttribute("Share_Shift");
        Attribute attribute26 = new NominalAttribute("Decision");

        delimitedTextParser.setDelimiter(",");
        delimitedTextParser.setResponseIndex(attribute26, 26);
        AttributeDataset deals = delimitedTextParser.parse(attributes, "/Users/stevenbenmoha/IdeaProjects/payer-model/payer-model/src/main/java/com/model/data/data.csv");
        double[][] x = deals.toArray(new double[deals.size()][]);
        System.out.println("This is x: " + x);
        int[] y = deals.toArray(new int[deals.size()]);
        System.out.println("This is y: " + y);
        LogisticRegression logisticRegression = new LogisticRegression(deals);
        logisticRegression.loglikelihood();
    }
}

