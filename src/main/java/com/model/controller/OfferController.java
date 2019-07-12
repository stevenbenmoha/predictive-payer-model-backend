package com.model.controller;

import com.model.domain.entity.Offer;
import com.model.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smile.classification.LogisticRegression;
import smile.data.Attribute;
import smile.data.AttributeDataset;
import smile.data.NominalAttribute;
import smile.data.NumericAttribute;
import smile.data.parser.DelimitedTextParser;
import smile.plot.Plot;
import smile.plot.PlotCanvas;
import smile.plot.ScatterPlot;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
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

        attributes[0] = new NominalAttribute("Channel");
        attributes[1] = new NominalAttribute("Customer");
        attributes[2] = new NominalAttribute("Segment/Plan");
        attributes[3] = new NominalAttribute("Deal_Level_of_Control");
        attributes[4] = new NominalAttribute("Therapeutic_Area");
        attributes[5] = new NominalAttribute("Product");
        attributes[6] = new NumericAttribute("WAC_Price");
        attributes[7] = new NominalAttribute("Start_Date");
        attributes[8] = new NominalAttribute("End_Date");
        attributes[9] = new NominalAttribute("2018_Status_Quo_Brand_Manufacturer");
        attributes[10] = new NominalAttribute("2018_Status_Quo_Tier");
        attributes[11] = new NominalAttribute("2018_Status_Quo_Placement");
        attributes[12] = new NominalAttribute("2018_Status_Quo_Restriction");
        attributes[13] = new NominalAttribute("Deal_Brand_Manufacturer");
        attributes[14] = new NominalAttribute("Deal_Tier");
        attributes[15] = new NominalAttribute("Deal_Placement");
        attributes[16] = new NominalAttribute("Deal_Restriction");
        attributes[17] = new NumericAttribute("Deal_Rebate");
        attributes[18] = new NominalAttribute("PP_Type");
        attributes[19] = new NumericAttribute("PP_Threshold");
        attributes[20] = new NominalAttribute("PP_Lookback_Date");
        attributes[21] = new NominalAttribute("2019_Actual_Brand_Manufacturer");
        attributes[22] = new NominalAttribute("2019_Actual_Tier");
        attributes[23] = new NominalAttribute("2019_Actual_Placement");
        attributes[24] = new NominalAttribute("2019_Actual_Restriction");
        attributes[25] = new NumericAttribute("Share_Shift");
        Attribute attribute26 = new NominalAttribute("Decision");

        delimitedTextParser.setDelimiter(",");
        delimitedTextParser.setColumnNames(true);
        delimitedTextParser.setResponseIndex(attribute26, 26);
        AttributeDataset deals = delimitedTextParser.parse(attributes, "/Users/stevenbenmoha/IdeaProjects/payer-model/payer-model/src/main/java/com/model/data/data.csv");
        double[][] x = deals.toArray(new double[deals.size()][]);

        System.out.println(Arrays.deepToString(x).replace("], ", "]\n"));

        System.out.println(deals.responseAttribute());
        System.out.println(deals.response());
        int[] y = deals.toArray(new int[deals.size()]);
        LogisticRegression logisticRegression = new LogisticRegression(x,y,0,0.00001, 1000);

        System.out.println(logisticRegression.loglikelihood());

        double[] newData = {2.0, 7.0, 8.0, 2.0, 4.0, 7.0, 107.49, 0.0, 0.0, 1.0, 0.0, 3.0, 0.0, 1.0, 0.0, 0.0, 0.0, 75.0, 0.0, 7.0, 0.0, 1.0, 1.0, 0.0, 0.0, 86.0};
        System.out.println(logisticRegression.predict(newData));

    }
}

