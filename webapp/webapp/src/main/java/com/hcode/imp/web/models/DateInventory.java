package com.mojix.web.models;

import java.util.StringTokenizer;

/**
 * Created by Paul Landaeta on 04/12/2015.
 */
public class DateInventory {
    private String date;
    private String group;
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String Name(){
        String name="INV";
        StringTokenizer token=new StringTokenizer(date,"/");
        while(token.hasMoreElements()){
            name+=token.nextToken();
        }
        return name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
