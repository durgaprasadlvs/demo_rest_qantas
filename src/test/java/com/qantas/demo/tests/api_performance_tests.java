package com.qantas.demo.tests;

import com.google.gson.Gson;
import com.qantas.demo.Pojos.CurrentWeather.CurrentWeatherPOJO;
import com.qantas.demo.Pojos.HourlyWeather.Datum;
import com.qantas.demo.Pojos.HourlyWeather.HourlyWeatherPOJO;
import com.qantas.demo.Pojos.HourlyWeather.Weather;
import com.qantas.demo.api_base;
import io.restassured.response.Response;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringEscapeUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class api_performance_tests {

    @Test(testName = "Get Statecodes for given latitudes and longitudes in the csv file")
    public void performanceTestGetCurrentWeather() throws Exception {

        String directory_path = new File("").getAbsolutePath()
                + File.separator + "src"
                + File.separator + "test"
                + File.separator + "java"
                + File.separator + "com"
                + File.separator + "qantas"
                + File.separator + "demo";

        Reader in = new FileReader(directory_path + File.separator + "lat_long_data.csv");

        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);

        long response_time;
        Double total_response_time = 0.0;

        api_base base = new api_base();

        for (CSVRecord record : records) {

            Double latitude = Double.parseDouble(record.get("latitude"));
            Double longitude = Double.parseDouble(record.get("longitude"));

            Response response = base.GetCurrentWeather(latitude, longitude);

            String json_response = StringEscapeUtils.unescapeJson(response.getBody().asString());

            CurrentWeatherPOJO currentWeatherPOJO = new Gson().fromJson(json_response, CurrentWeatherPOJO.class);

            String stateCode = currentWeatherPOJO.getData().get(0).getStateCode();

            String cityName = currentWeatherPOJO.getData().get(0).getCityName();

            response_time = response.getTime();

            total_response_time += response_time;

            System.out.println("Latitude - " + latitude + " == Longitude - " + longitude);

            System.out.println("City - " + cityName + " == State - " + stateCode);

            System.out.println("Response Time - " + response_time + " milli seconds\n\n");

        }

        System.out.println("Total response time - " + total_response_time / 1000 + " seconds");

    }

    @Test(testName = "Get Hourly forecast for given postcodes in the csv file")
    public void performanceTestGetHourlyWeather() throws Exception {

        String directory_path = new File("").getAbsolutePath()
                + File.separator + "src"
                + File.separator + "test"
                + File.separator + "java"
                + File.separator + "com"
                + File.separator + "qantas"
                + File.separator + "demo";

        Reader in = new FileReader(directory_path + File.separator + "postcodes.csv");

        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);

        long response_time;

        Double total_response_time = 0.0;

        api_base base = new api_base();

        for (CSVRecord record : records) {

            String postcode = record.get("postcode");

            Response response = base.GetHourlyWeatherByPostcode(postcode);

            String json_response = StringEscapeUtils.unescapeJson(response.getBody().asString());

            HourlyWeatherPOJO hourlyWeatherPOJO = new Gson().fromJson(json_response, HourlyWeatherPOJO.class);

            List<Datum> hourlyData = hourlyWeatherPOJO.getData();

            Map<String, Weather> hourlyMap = new TreeMap<String, Weather>();

            for (Datum ha : hourlyData) {

                hourlyMap.put(ha.getTimestampUtc(), ha.getWeather());
            }

            for (Map.Entry<String, Weather> entry : hourlyMap.entrySet()) {
                //System.out.println(entry.getKey() + " = " + entry.getValue().getDescription());
            }

            response_time = response.getTime();

            total_response_time += response_time;

            System.out.println("Postcode - " + postcode + " -- Response Time - " + response_time + " milli seconds");

        }

        System.out.println("Total response time - " + total_response_time / 1000 + " seconds");

    }
}