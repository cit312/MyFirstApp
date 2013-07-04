package com.example.myfirstapp;
import java.io.Serializable;
import java.util.HashMap;


public class CommBean implements Serializable{
    public HashMap data = null;
    public String command = null;
    
    public CommBean(String command){
    	this.command = command;
    }
    
    public void setData(HashMap data){
    	this.data = data;
    }
}
