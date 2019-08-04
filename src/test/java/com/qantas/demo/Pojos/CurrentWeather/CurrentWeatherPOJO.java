
package com.qantas.demo.Pojos.CurrentWeather;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class CurrentWeatherPOJO {

    @SerializedName("count")
    private Long mCount;
    @SerializedName("data")
    private List<Datum> mData;

    public Long getCount() {
        return mCount;
    }

    public void setCount(Long count) {
        mCount = count;
    }

    public List<Datum> getData() {
        return mData;
    }

    public void setData(List<Datum> data) {
        mData = data;
    }

}
