package com.model.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "OFFER")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long offerId;

    @Column(name = "channel")
    public String channel;

    @Column(name = "customer")
    public String customer;

    @Column(name = "segment")
    public String segment;

    @Column(name = "brandOrManufacturer")
    public String brandOrManufacturer;

    @Column(name = "tier")
    public String tier;

    @Column(name = "placement")
    public String placement;

    @Column(name = "formulary")
    public String formulary;

    @Column(name = "restriction")
    public String restriction;

    @Column(name = "product")
    public String product;

    @Column(name = "startDate")
    public Date startDate;

    @Column(name = "endDate")
    public Date endDate;

    @Column(name = "rebate")
    public int rebate;

    @Column(name = "priceProtectionType")
    public String priceProtectionType;

    @Column(name = "priceProtectionThreshold")
    public int priceProtectionThreshold;

    @Column(name = "priceProtectionLookbackDate")
    public Date priceProtectionLookbackDate;

    @Column(name = "shareShift")
    public int shareShift;


}
