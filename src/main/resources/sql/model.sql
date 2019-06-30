
CREATE SCHEMA IF NOT EXISTS MODEL;

CREATE TABLE OFFER (

offerId int primary key,
channel varchar(100),
customer varchar(100),
segment varchar(100),
brandOrManufacturer varchar(100),
tier varchar(100),
placement varchar(100),
formulary varchar(100),
restriction varchar(100),
product varchar(100),
startDate Date,
endDate Date,
rebate int,
priceProtectionType varchar(100),
priceProtectionThreshold int,
priceProtectionLookbackDate Date,
shareShift int,
Primary Key(offerId)
);

Select * from Offer;

Insert into Offer values (1,
"Brand",
"Commercial",
"CVS Caremark",
"2000-12-01",
"aaa",
"1 of 1",
"2020-12-21",
 "11",
 "Cumulative",
 "Levemir",
"10",
"Prior Authorization",
 "FEP",
"15",
 "1999-12-01",
 "Tier 1 - Generic");