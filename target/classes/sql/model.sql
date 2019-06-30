
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