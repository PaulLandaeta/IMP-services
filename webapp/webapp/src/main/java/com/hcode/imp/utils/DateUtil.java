package com.mojix.utils;

import com.mojix.properties.PropertiesController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Paul Landaeta on 15/12/2015.
 */
public class DateUtil {
    private String date;
    private String serialDate;
    private Date startDate;
    private final Logger log = LoggerFactory.getLogger(DateUtil.class);
    private PropertiesController propertiesController = new PropertiesController();
    private final String PREFIX_THING=propertiesController.getPrefixthing();

    public DateUtil(String date){
        this.date=date;
        if(date.contains("/")){
            this.date=this.date.replace('/','-');
        }
        if(!date.contains("-") && date.length()==8){
            this.date=this.date.substring(0,2)+"-"+this.date.substring(2,4)+"-"+this.date.substring(4);
        }
        log.info("{}: Created new report at : '{}'",new Date(),this.date);
    }

    public DateUtil(){

        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        Date today = Calendar.getInstance().getTime();
        this.date = df.format(today);
        log.info("{}: Created new report at : '{}'", new Date(), this.date);
    }
    public boolean isValidDate(){
        String pattern="^(0?[1-9]|1[012])([-. /])(0?[1-9]|[12][0-9]|3[01])([-. /])\\d{4}$";//MM-dd-yyyy
        //String pattern2="^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";//yyyy-MM-dd
        Pattern regex = Pattern.compile(pattern);
        Matcher match=regex.matcher(date);
        if(match.find())
            return true;
        log.info("{}: The Date : '{}' is invalid, the format is MM-dd-yyyy",this.date);
        return false;
    }
    public String getSerialDate(){
        serialDate=PREFIX_THING+getNameDate();
        return serialDate;
    }

    public Date getStartDate() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        startDate = formatter.parse(date);
        return startDate;
    }
    public String getNameDate(){
        String nameDate="";
        StringTokenizer token=new StringTokenizer(date,"-");
        while(token.hasMoreElements())
            nameDate+=token.nextToken();
        return nameDate;
    }
    public boolean compareDates(Date date1,Date date2) {
        if(getDate(date1).equals(getDate(date2)))
              return true;
        return false;
    }
    public String getDate(Date date){
        return String.valueOf(date.getMonth())+"-"+String.valueOf(date.getYear())+"-"+String.valueOf(date.getDay());
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}