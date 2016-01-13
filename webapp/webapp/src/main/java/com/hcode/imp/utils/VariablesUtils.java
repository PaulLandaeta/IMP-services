package com.mojix.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONObject;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by aquiroz on 9/23/2015.
 */
public class VariablesUtils {

    //People per Zone
    //public static final String END_POINT_PEOPLE_PER_ZONE ="http://vizix.xivelylive.com:8080/riot-core-services/api/reportExecution/tableSummary/4";
    public static final String END_POINT_PEOPLE_PER_ZONE ="http://vizix.xivelylive.com:8080/riot-core-services/api/reportExecution/mapSummary/4";
    //Drinks per Type
    public static final String END_POINT_5="http://vizix.xivelylive.com:8080/riot-core-services/api/reportExecution/tableSummary/5";
    //Drinks per Region
    //public static final String END_POINT_DRINKS_PER_REGION ="http://vizix.xivelylive.com:8080/riot-core-services/api/reportExecution/tableSummary/7";
    public static final String END_POINT_DRINKS_PER_REGION ="http://vizix.xivelylive.com:8080/riot-core-services/api/reportExecution/tableSummary/7";
    //Drinks per Type
    // public static final String END_POINT_DRINKS_PER_TYPE ="http://vizix.xivelylive.com:8080/riot-core-services/api/reportExecution/tableSummary/8";
    public static final String END_POINT_DRINKS_PER_TYPE ="http://vizix.xivelylive.com:8080/riot-core-services/api/reportExecution/tableSummary/8";
    //Assistants
    //public static final String  END_POINT_ASSISTANTS="http://xively-vizyx-saturn.tierconnect.com:8080/riot-core-services/api/thing/?where=thingType.id%3D3&extra=fields";
    public static final String END_POINT_ASSISTANTS="http://vizix.xivelylive.com:8080/riot-core-services/api/reportExecution/tableSummary/9";
    //Assistants Served
    //public static final String END_POINT_ASSISTANTS_SERVED ="http://vizix.xivelylive.com:8080/riot-core-services/api/reportExecution/tableSummary/10";
    public static final String END_POINT_ASSISTANTS_SERVED ="http://vizix.xivelylive.com:8080/riot-core-services/api/reportExecution/tableSummary/10";
    //PUT VIZIX
    public static final String END_POINT_PUT="http://api.xively.com/v2/feeds/827889267";

    public static final String HEADER_KEY="api_key";
    public static final String HEADER_VALUE="root";
    public static final String CONTENT_TYPE="application/json";
    public static final String HEADER_KEY_PUT="api_key";
    public static final String HEADER_VALUE_PUT="root";

    //END_POINT XDASHBOARD
    public static final String END_POINT_XDASHBOARD="https://kiosk-mmayorivera.c9.io/xdashboard";
    //END_POINT VDASHBOARD
    public static final String END_POINT_VDASHBOARD="https://kiosk-mmayorivera.c9.io/vdashboard";

    public static final int  DRINK_TYPE_ONZA=12;
    public static final int  AME_TYPE_ONZA=12;
    public static final int  CAP_TYPE_ONZA=12;
    public static final int  COFFEE_TYPE_ONZA=12;
    public static final int  DECAF_TYPE_ONZA=12;
    public static final int  ESP_TYPE_ONZA=12;
    public static final int  TEA_TYPE_ONZA=12;

    public static String getBODYDrinksPerType(){
        JSONObject obj = new JSONObject();
        obj.put("600", "5");
        obj.put("602", "4");
        obj.put("603", "0");
        return  obj.toJSONString();
    }

    public static String getBODYPeoplePerZone(){
        JSONObject obj = new JSONObject();
        obj.put("497", "5");
        obj.put("499", "4");
        return  obj.toJSONString();
    }

    public static String getBODYDrinksPerRegion(){
        JSONObject obj = new JSONObject();
        obj.put("446", "5");
        obj.put("448", "4");
        return  obj.toJSONString();
    }

    public static String getBODY_Assistants(){
        JSONObject obj = new JSONObject();
        obj.put("470", "5");
        obj.put("472", "3");
        return  obj.toJSONString();
    }

    public static String getBODY_AssistatsServed(){
        JSONObject obj = new JSONObject();
        obj.put("621", "5");
        obj.put("623", "4");
        obj.put("624", "0");
        return  obj.toJSONString();
    }

    public static long getStarDateToday(){
        Calendar fecha = Calendar.getInstance();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        fecha.set(Calendar.HOUR_OF_DAY,00);
        fecha.set(Calendar.MINUTE, 00);
        fecha.set(Calendar.SECOND, 1);
        return fecha.getTimeInMillis();
    }

    public static long getEndDateToday(){
        Calendar fecha = Calendar.getInstance();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        fecha.set(Calendar.HOUR_OF_DAY,23);
        fecha.set(Calendar.MINUTE,59);
        fecha.set(Calendar.SECOND,59);
        return fecha.getTimeInMillis();
    }


    public static Map<String,Object> convertJsonStringToMap(String json){
        Gson gson = new Gson();
        Type stringStringMap = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String,Object> map = gson.fromJson(json, stringStringMap);
        return map;
    }
}
