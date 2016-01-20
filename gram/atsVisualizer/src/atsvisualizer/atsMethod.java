/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atsvisualizer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
/**
 *
 * @author filipeoliveira
 */
public class atsMethod {
    String methodDescription;
    Metric CYCLO_PER_LOC;
    Metric LOC_PER_OPERATION;
    Metric NOM_PER_CLASS;
    Metric NOC_PER_PACKAGE;
    Metric CALLS_PER_OPERATION;
    Metric FANOUT_PER_CALL; 
    Metric ANDC; 
    Metric AHH;
    Metric CYCLO;
    Metric LOC;
    Metric NOM;
    Metric NOC;
    Metric NOP;
    Metric FANOUT;
    Metric CALLS;
    Metric VOLUME;
    Metric DIFFICULTY;
    Metric EFFORT;
    Metric BUGS;
    Metric MAINTAIN_INDEX;
    Metric NOCOM;
   Metric IPL;
   Metric UNARG;
   Metric UNDEC;
    Metric NEGNEG;
    Metric NEGIF;
    Metric NARGS;
                            
    public atsMethod ( String method_desc, double ats_CYCLO_PER_LOC, double ats_LOC_PER_OPERATION, 
    double ats_NOM_PER_CLASS, double ats_NOC_PER_PACKAGE, double ats_CALLS_PER_OPERATION,
    double ats_FANOUT_PER_CALL, double ats_ANDC, double ats_AHH , double ats_CYCLO, double ats_LOC, double ats_NOM, 
    double ats_NOC, double ats_NOP, double ats_FANOUT, double ats_CALLS, 
    double ats_VOLUME, double  ats_DIFFICULTY, double ats_EFFORT, double ats_BUGS, double ats_MAINTAIN, 
    double  ats_NOCOM , double ats_IPL , double   ats_UNARG , double  ats_UNDEC ,  double  ats_NEGNEG ,
  double   ats_NEGIF , double ats_NARGS, String valuesFileName 
    ){   
    this.methodDescription = method_desc;
    this.CYCLO_PER_LOC = new Metric ( "CYCLO_PER_LOC" , ats_CYCLO_PER_LOC );
    this.LOC_PER_OPERATION = new Metric ( "LOC_PER_OPERATION" , ats_LOC_PER_OPERATION );
    this.NOM_PER_CLASS = new Metric ( "NOM_PER_CLASS" , ats_NOM_PER_CLASS );
    this.NOC_PER_PACKAGE = new Metric ( "NOC_PER_PACKAGE" , ats_NOC_PER_PACKAGE );
    this.CALLS_PER_OPERATION = new Metric ("CALLS_PER_OPERATION" , ats_CALLS_PER_OPERATION );
    this.FANOUT_PER_CALL = new Metric ("FANOUT_PER_CALL" , ats_FANOUT_PER_CALL ); 
    this.ANDC = new Metric ("ANDC" , ats_ANDC ); 
    this.AHH = new Metric ("AHH" , ats_AHH );
    this.CYCLO = new Metric ("CYCLO" , ats_CYCLO );
    this.LOC = new Metric ("LOC" , ats_LOC );
    this.NOM = new Metric ("NOM" , ats_NOM );
    this.NOC = new Metric ("NOC" , ats_NOC );
    this.NOP = new Metric ("NOP" , ats_NOP );
    this.FANOUT = new Metric ("FANOUT" , ats_FANOUT );
    this.CALLS = new Metric ("CALLS" , ats_CALLS );
    this.VOLUME = new Metric ("VOLUME" , ats_VOLUME );
    this.DIFFICULTY = new Metric ("DIFFICULTY" , ats_DIFFICULTY );
    this.EFFORT = new Metric ("EFFORT" , ats_EFFORT );
    this.BUGS = new Metric ("BUGS" , ats_BUGS );
    this.MAINTAIN_INDEX = new Metric ("MAINTAIN_INDEX" , ats_MAINTAIN );
    this.NOCOM = new Metric ("NOCOM" , ats_NOCOM );
   this.IPL = new Metric ("IPL" , ats_IPL );
   this.UNARG = new Metric ("UNARG" , ats_UNARG );
   this.UNDEC = new Metric ("UNDEC" , ats_UNDEC );
    this.NEGNEG = new Metric ("NEGNEG" , ats_NEGNEG );
    this.NEGIF = new Metric ("NEGIF" , ats_NEGIF );
    this.NARGS = new Metric ("NARGS" , ats_NARGS );
    readProperties(valuesFileName);
    
    }
    
    public int getScore(){
    int valor = 0;
        valor += CYCLO_PER_LOC.getRate();
     valor += LOC_PER_OPERATION.getRate();
     valor += NOM_PER_CLASS.getRate();
     valor += NOC_PER_PACKAGE.getRate();
     valor += CALLS_PER_OPERATION.getRate();
     valor += FANOUT_PER_CALL.getRate();
    valor += ANDC.getRate();
     valor += AHH.getRate();
     valor += MAINTAIN_INDEX.getRate();
     valor += LOC.getRate();
     valor += UNARG.getRate();
     valor += NARGS.getRate();
     return valor;
        
    }
    
    public int getMaxScore(){
     return 36;
    }
    
    public int getNumberStars (){
        int valor = this.getScore();
     if(valor > 24 ){
         return 3;
     }
     else {
         if ( valor > 12){
             return 2;
         }
     else {
             if (valor > 0){
             return 1;
             }
             else {
                 return 0;
             }
          }
     }
    }
    
    public void readProperties(String filename){
    Properties prop = new Properties();
	InputStream input = null;

	try {

		input = new FileInputStream(filename);

		// load a properties file
                
		prop.load(input);
                this.CYCLO_PER_LOC.setLow(Double.parseDouble(prop.getProperty("CYCLO_PER_LOC_LOW")));
                this.CYCLO_PER_LOC.setNormal(Double.parseDouble(prop.getProperty("CYCLO_PER_LOC_AVERAGE")));
                this.CYCLO_PER_LOC.setHigh(Double.parseDouble(prop.getProperty("CYCLO_PER_LOC_HIGH")));
               
                this.LOC_PER_OPERATION.setLow(Double.parseDouble(prop.getProperty("LOC_PER_OPERATION_LOW")));
                this.LOC_PER_OPERATION.setNormal(Double.parseDouble(prop.getProperty("LOC_PER_OPERATION_AVERAGE")));
                this.LOC_PER_OPERATION.setHigh(Double.parseDouble(prop.getProperty("LOC_PER_OPERATION_HIGH")));

                this.NOM_PER_CLASS.setLow(Double.parseDouble(prop.getProperty("NOM_PER_CLASS_LOW")));
                this.NOM_PER_CLASS.setNormal(Double.parseDouble(prop.getProperty("NOM_PER_CLASS_AVERAGE")));
                this.NOM_PER_CLASS.setHigh(Double.parseDouble(prop.getProperty("NOM_PER_CLASS_HIGH")));
                
                this.NOC_PER_PACKAGE.setLow(Double.parseDouble(prop.getProperty("NOC_PER_PACKAGE_LOW")));
                this.NOC_PER_PACKAGE.setNormal(Double.parseDouble(prop.getProperty("NOC_PER_PACKAGE_AVERAGE")));
                this.NOC_PER_PACKAGE.setHigh(Double.parseDouble(prop.getProperty("NOC_PER_PACKAGE_HIGH")));
                
                this.CALLS_PER_OPERATION.setLow(Double.parseDouble(prop.getProperty("CALLS_PER_OPERATION_LOW")));
                this.CALLS_PER_OPERATION.setNormal(Double.parseDouble(prop.getProperty("CALLS_PER_OPERATION_AVERAGE")));
                this.CALLS_PER_OPERATION.setHigh(Double.parseDouble(prop.getProperty("CALLS_PER_OPERATION_HIGH")));
                
                this.FANOUT_PER_CALL.setLow(Double.parseDouble(prop.getProperty("FANOUT_PER_CALL_LOW")));
                this.FANOUT_PER_CALL.setNormal(Double.parseDouble(prop.getProperty("FANOUT_PER_CALL_AVERAGE")));
                this.FANOUT_PER_CALL.setHigh(Double.parseDouble(prop.getProperty("FANOUT_PER_CALL_HIGH")));
                
                this.ANDC.setLow(Double.parseDouble(prop.getProperty("ANDC_LOW")));
                this.ANDC.setNormal(Double.parseDouble(prop.getProperty("ANDC_AVERAGE")));
                this.ANDC.setHigh(Double.parseDouble(prop.getProperty("ANDC_HIGH")));
                
                this.AHH.setLow(Double.parseDouble(prop.getProperty("AHH_LOW")));
                this.AHH.setNormal(Double.parseDouble(prop.getProperty("AHH_AVERAGE")));
                this.AHH.setHigh(Double.parseDouble(prop.getProperty("AHH_HIGH")));
                
                this.MAINTAIN_INDEX.setLow(Double.parseDouble(prop.getProperty("MAINTAIN_INDEX_LOW")));
                this.MAINTAIN_INDEX.setNormal(Double.parseDouble(prop.getProperty("MAINTAIN_INDEX_AVERAGE")));
                this.MAINTAIN_INDEX.setHigh(Double.parseDouble(prop.getProperty("MAINTAIN_INDEX_HIGH")));
                
                this.LOC.setLow(Double.parseDouble(prop.getProperty("LOC_LOW")));
                this.LOC.setNormal(Double.parseDouble(prop.getProperty("LOC_AVERAGE")));
                this.LOC.setHigh(Double.parseDouble(prop.getProperty("LOC_HIGH")));
                
                this.UNARG.setLow(Double.parseDouble(prop.getProperty("UNARG_LOW")));
                this.UNARG.setNormal(Double.parseDouble(prop.getProperty("UNARG_AVERAGE")));
                this.UNARG.setHigh(Double.parseDouble(prop.getProperty("UNARG_HIGH")));
                
                this.NARGS.setLow(Double.parseDouble(prop.getProperty("NARGS_LOW")));
                this.NARGS.setNormal(Double.parseDouble(prop.getProperty("NARGS_AVERAGE")));
                this.NARGS.setHigh(Double.parseDouble(prop.getProperty("NARGS_HIGH")));
                

	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
    }
    
    
}
