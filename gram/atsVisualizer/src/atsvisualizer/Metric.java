/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atsvisualizer;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author filipeoliveira
 */
public class Metric {
    String name;
    double low, normal, high;
    double registered_value;
    
    public Metric (  String ats_name, double ats_registered_value, double ats_low, double ats_normal, double ats_high  ){
        this.name = ats_name;
        this.registered_value = ats_registered_value;
        this.low = ats_low;
        this.normal = ats_normal;
        this.high = ats_high;
    }
    
    public Metric ( String name, double ats_registered_value){
                this.name = name;
            this.registered_value = ats_registered_value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }

    public void setHigh(double high) {
        this.high = high;
        
         
    }
    
    public Color getColor (){
      if (this.registered_value <= this.low ){
          return Color.BLUE;
      }
      else {
          if (this.registered_value <= this.normal ){
                              Color customGreen = new Color (34,139,34);
                              return customGreen;
          }

      else {
              return Color.RED;
              }
      }
    }
    
    public int getRate (){
      if (this.registered_value <= this.low ){
          return 3;
      }
      else {
          if (this.registered_value <= this.normal ){
                              return 1;
          }

      else {
              return -6;
              }
      }
    }
    
    @Override
    public String toString(){
     StringBuilder sb = new StringBuilder();
     sb.append(this.name).append("\t").append(this.registered_value).append(" ").append(this.low).append(" ").append(this.normal).append(" ").append(this.high);
     return sb.toString();
    }
    
    
    
}
