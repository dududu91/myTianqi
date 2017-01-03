/**
  * Copyright 2017 bejson.com 
  */
package com.besjon.pojo;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
/**
 * Auto-generated: 2017-01-02 17:10:57
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class WeatherData {

    private String date;
    @JsonProperty("dayPictureUrl")
    private String daypictureurl;
    @JsonProperty("nightPictureUrl")
    private String nightpictureurl;
    private String weather;
    private String wind;
    private String temperature;
    public void setDate(String date) {
         this.date = date;
     }
     public String getDate() {
         return date;
     }

    public void setDaypictureurl(String daypictureurl) {
         this.daypictureurl = daypictureurl;
     }
     public String getDaypictureurl() {
         return daypictureurl;
     }

    public void setNightpictureurl(String nightpictureurl) {
         this.nightpictureurl = nightpictureurl;
     }
     public String getNightpictureurl() {
         return nightpictureurl;
     }

    public void setWeather(String weather) {
         this.weather = weather;
     }
     public String getWeather() {
         return weather;
     }

    public void setWind(String wind) {
         this.wind = wind;
     }
     public String getWind() {
         return wind;
     }

    public void setTemperature(String temperature) {
         this.temperature = temperature;
     }
     public String getTemperature() {
         return temperature;
     }

}