DROP TABLE IF EXISTS FLIGHT_DETAILS;
CREATE TABLE FLIGHT_DETAILS (
  FLIGHT_NUMBER VARCHAR(4) PRIMARY KEY,
  ORIGIN VARCHAR(3) NOT NULL,
  DESTINATION VARCHAR(3) NOT NULL,
  ARRIVAL_TIME TIME DEFAULT NULL,
  DEPARTURE_TIME TIME DEFAULT NULL,
  PRICE INT DEFAULT NULL
);