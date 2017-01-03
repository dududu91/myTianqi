/**
  * Copyright 2017 bejson.com 
  */
package com.besjon.pojo;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
/**
 * Auto-generated: 2017-01-02 17:10:57
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Results {

    @JsonProperty("currentCity")
    private String currentcity;
    private String pm25;
    private List<Index> index;
    @JsonProperty("weather_data")
    private List<WeatherData> weatherData;
    public void setCurrentcity(String currentcity) {
         this.currentcity = currentcity;
     }
     public String getCurrentcity() {
         return currentcity;
     }

    public void setPm25(String pm25) {
         this.pm25 = pm25;
     }
     public String getPm25() {
         return pm25;
     }

    public void setIndex(List<Index> index) {
         this.index = index;
     }
     public List<Index> getIndex() {
         return index;
     }

    public void setWeatherData(List<WeatherData> weatherData) {
         this.weatherData = weatherData;
     }
     public List<WeatherData> getWeatherData() {
         return weatherData;
     }

}