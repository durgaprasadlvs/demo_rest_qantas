
package com.qantas.demo.Pojos.HourlyWeather;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class HourlyWeatherPOJO {

    @SerializedName("city_name")
    private String mCityName;
    @SerializedName("country_code")
    private String mCountryCode;
    @SerializedName("data")
    private List<Datum> mData;
    @SerializedName("lat")
    private String mLat;
    @SerializedName("lon")
    private String mLon;
    @SerializedName("state_code")
    private String mStateCode;
    @SerializedName("timezone")
    private String mTimezone;

    public String getCityName() {
        return mCityName;
    }

    public void setCityName(String cityName) {
        mCityName = cityName;
    }

    public String getCountryCode() {
        return mCountryCode;
    }

    public void setCountryCode(String countryCode) {
        mCountryCode = countryCode;
    }

    public List<Datum> getData() {
        return mData;
    }

    public void setData(List<Datum> data) {
        mData = data;
    }

    public String getLat() {
        return mLat;
    }

    public void setLat(String lat) {
        mLat = lat;
    }

    public String getLon() {
        return mLon;
    }

    public void setLon(String lon) {
        mLon = lon;
    }

    public String getStateCode() {
        return mStateCode;
    }

    public void setStateCode(String stateCode) {
        mStateCode = stateCode;
    }

    public String getTimezone() {
        return mTimezone;
    }

    public void setTimezone(String timezone) {
        mTimezone = timezone;
    }

}
