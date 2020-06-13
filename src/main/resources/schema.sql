CREATE TABLE TRAVELAGENCIES (
  TRAVEL_AGENCY_ID INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  NAME VARCHAR(250) NOT NULL,
  CITY VARCHAR(250) NOT NULL,
  PHONE_NUMBER VARCHAR(250) NOT NULL
);

CREATE TABLE CUSTOMERS (
  CUSTOMER_ID INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  FIRST_NAME VARCHAR(250) NOT NULL,
  LAST_NAME VARCHAR(250) NOT NULL,
  LOGIN VARCHAR(250) NOT NULL,
  EMAIL VARCHAR(250) NOT NULL
);

CREATE TABLE HOTELS (
  HOTEL_ID INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  HOTEL_NAME VARCHAR(250) NOT NULL,
  CITY VARCHAR(250) NOT NULL,
  STARS VARCHAR(250) NOT NULL,
  PHONE_NUMBER VARCHAR(250) NOT NULL
);