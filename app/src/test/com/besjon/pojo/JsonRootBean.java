/**
  * Copyright 2017 bejson.com 
  */
package com.besjon.pojo;
import java.util.Date;
import java.util.List;

/**
 * Auto-generated: 2017-01-02 17:10:57
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean {

    private int error;
    private String status;
    private Date date;
    private List<Results> results;
    public void setError(int error) {
         this.error = error;
     }
     public int getError() {
         return error;
     }

    public void setStatus(String status) {
         this.status = status;
     }
     public String getStatus() {
         return status;
     }

    public void setDate(Date date) {
         this.date = date;
     }
     public Date getDate() {
         return date;
     }

    public void setResults(List<Results> results) {
         this.results = results;
     }
     public List<Results> getResults() {
         return results;
     }

}