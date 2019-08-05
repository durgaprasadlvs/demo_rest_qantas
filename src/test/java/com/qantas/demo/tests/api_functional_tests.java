package com.qantas.demo.tests;

import com.google.gson.Gson;
import com.qantas.demo.Pojos.CurrentWeather.CurrentWeatherPOJO;
import com.qantas.demo.Pojos.HourlyWeather.Datum;
import com.qantas.demo.Pojos.HourlyWeather.HourlyWeatherPOJO;
import com.qantas.demo.Pojos.HourlyWeather.Weather;
import com.qantas.demo.api_base;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringEscapeUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class api_functional_tests {

    @Test(testName = "Get Statecode for given latitude and longitude")
    public void testGetStateCodeFromCurrentWeather() throws Exception{

        api_base base = new api_base();

        Response response = base.GetCurrentWeather(40.730610, -73.935242);

        String json_response = StringEscapeUtils.unescapeJson(response.getBody().asString());

        CurrentWeatherPOJO currentWeatherPOJO = new Gson().fromJson(json_response, CurrentWeatherPOJO.class);

        String stateCode = currentWeatherPOJO.getData().get(0).getStateCode();

        Assert.assertEquals(stateCode, "NY");

    }

    @Test(testName = "Get Hourly forecast for given postcodes")
    public void testGetHourlyTimestampWeather() throws Exception{

        api_base base = new api_base();

        Response response = base.GetHourlyWeatherByPostcode("28546");

        String json_response = StringEscapeUtils.unescapeJson(response.getBody().asString());

        HourlyWeatherPOJO hourlyWeatherPOJO = new Gson().fromJson(json_response, HourlyWeatherPOJO.class);

        List<Datum> hourlyData = hourlyWeatherPOJO.getData();

        Map<String, Weather> hourlyMap = new TreeMap<String, Weather>();

        for (Datum ha : hourlyData) {

            hourlyMap.put(ha.getTimestampUtc(), ha.getWeather());
        }

        for (Map.Entry<String, Weather> entry : hourlyMap.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue().getDescription());
        }

        System.out.println("Response Time - " + response.getTime() / 1000 + "seconds");

        Assert.assertNotNull(hourlyMap);
    }
}