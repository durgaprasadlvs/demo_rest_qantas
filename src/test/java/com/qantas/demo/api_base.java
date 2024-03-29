package com.qantas.demo;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Properties;


public class api_base {

    private static String baseURI;
    private static String apiKey;
    protected RequestSpecification httpRequest;


    public api_base() throws Exception {

        Properties configProperties = new Properties();
        configProperties.load(api_base.class.getResourceAsStream("config.properties"));

        this.baseURI = configProperties.getProperty("url");
        this.apiKey = configProperties.getProperty("apiKey");;
    }

    public Response GetCurrentWeather(Double lat, Double lon) {

        RestAssured.baseURI = this.baseURI;

        this.httpRequest = RestAssured.given().param("key", apiKey)
                .given().pathParam("lat", lat)
                .given().pathParam("lon", lon);

        Response response = this.httpRequest.request(Method.GET, "/current?lat={lat}&lon={lon}");

        return response;
    }

    public Response GetHourlyWeatherByPostcode(String postcode) {

        RestAssured.baseURI = this.baseURI;

        this.httpRequest = RestAssured.given().param("key", apiKey)
                .given().pathParam("postcode", postcode);

        Response response = this.httpRequest.request(Method.GET, "forecast/hourly?postal_code={postcode}");

        return response;
    }
}