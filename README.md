# Application
This is a Spring boot application that provides endpoints to filter flight searches

## Steps involved

  * The GET endpoint always requires you to add origin and destination as parameters
  * You have multiple optional query Params that can be added to filter on:   
    - startDepartureTime -> all flight after this departure time are filtered for view
    - startArrivalTime -> all flight before this departure time are filtered for view
    - startArrivalTime -> all flight after this arrival time are filtered for view
    - endArrivalTime -> all flight before this arrival time are filtered for view
    - price -> all flights below this price are filtered for view
    - travelTime -> all flights with less than the mentioned trvaled time in hours are filtered for view
    
  * You can add multiple query params to add more filters to your GET request
  

### Executing API requests in POSTMAN:
```bash
https://localhost:9082/flights/BOM/DEL
https://localhost:9082/flights/BOM/DEL?startDepartureTime=18:00&endDepartureTime=21:00
```

### What can be improved
1. add IT tests
2. proper functioning data: Time Zones for travel time, arrival date and departure Date currencies
3. wrapping error message
4. logging and monitoring


