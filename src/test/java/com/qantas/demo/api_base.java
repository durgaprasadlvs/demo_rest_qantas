package com.qantas.demo;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class api_base {

    private static String baseURI = "https://api.weatherbit.io/v2.0/";
    private static String apiKey = "6076c331aeb04fabbb72d08d896d48fb";
    protected RequestSpecification httpRequest;


    public api_base() {
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