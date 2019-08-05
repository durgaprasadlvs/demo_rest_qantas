
# Rest API Framework

### Rest-Assured + Maven + TestNG + gson + Apache Commons CSV:

- Rest Assured framework is used to run RESI API operations

- Maven is used as Build automation and dependency management tool, pom.xml has all the dependencies, plugins and build setup required

- TestNG 6.14.3 testing framework is used for test coverage

- gson is used for serializing and de-serializing json data and vice-versa

- Apache Commons CSV is used to csv file data handling

### Implementation :

- _**Base url**_ and _**apiKey**_ are configured in **_com/qantas/demo/resources/com/qantas/demo/config.properties_**

- POJOs are created to deserialize the json response from the API 


### Note : 

- **GET /forecast/3hourly?postal_code={postal_code}** is not available with the free account, so did **forecast/hourly?postal_code={postcode}**


### How to run the test suite

- Testsuite is mentioned in TestNG.xml

- Run the below command inside the project directory

```jshelllanguage
> mvn clean install
```

- The above command will run the 2 functional tests and 2 performance tests provided in the tests directory

### Ran performance tests on different latitudes and longitudes 
##### test data is present in **_lat_long_data.csv_** 

- End point : https://api.weatherbit.io/v2.0/current?lat={lat}&lon={lon}

```jshelllanguage
Latitude - 47.1443 == Longitude - -122.1408
City - Prairie Ridge == State - WA
Response Time - 1912 milli seconds


Latitude - 37.5501 == Longitude - -78.5556
City - Buckingham == State - VA
Response Time - 1476 milli seconds


Latitude - 42.5707 == Longitude - -88.1044
City - Paddock Lake == State - WI
Response Time - 1395 milli seconds


Latitude - 37.805 == Longitude - -81.9923
City - Monaville == State - WV
Response Time - 1529 milli seconds


Latitude - 21.3865 == Longitude - -157.9232
City - Hālawa == State - HI
Response Time - 1530 milli seconds


Latitude - 30.7828 == Longitude - -85.6846
City - Bonifay == State - FL
Response Time - 1529 milli seconds


Latitude - 43.6441 == Longitude - -108.198
City - East Thermopolis == State - WY
Response Time - 1326 milli seconds


Latitude - 42.8257 == Longitude - -72.1828
City - Troy == State - NH
Response Time - 1529 milli seconds


Latitude - 39.9452 == Longitude - -74.0787
City - Dover Beaches South == State - NJ
Response Time - 1507 milli seconds


Latitude - 36.0625 == Longitude - -108.68
City - Naschitti == State - NM
Response Time - 1564 milli seconds


Total response time - 15.297 seconds
```

### Ran performance tests on different postcodes are given 
##### test data is present in **_postcodes.csv_** 

- End point : https://api.weatherbit.io/v2.0/forecast/hourly?postal_code={postcode}

```jshelllanguage
Postcode - 98391 -- Response Time - 1389 milli seconds

Postcode - 22604 -- Response Time - 1554 milli seconds

Postcode - 54703 -- Response Time - 1523 milli seconds

Postcode - 82240 -- Response Time - 1528 milli seconds

Postcode - 28512 -- Response Time - 1426 milli seconds

Postcode - 58707 -- Response Time - 1526 milli seconds

Postcode - 68701 -- Response Time - 1225 milli seconds

Postcode - 69154 -- Response Time - 1423 milli seconds

Postcode - 68881 -- Response Time - 1529 milli seconds

Total response time - 13.123 seconds
```
