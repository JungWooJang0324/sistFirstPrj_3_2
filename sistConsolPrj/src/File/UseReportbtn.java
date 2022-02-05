package File;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Event.DialogEvent;

public class UseReportbtn {   
	
    public void write() throws IOException{
		File folder=new File("c:/dev/report/");
		folder.mkdirs();
		SimpleDateFormat sfm=new SimpleDateFormat("yyyyMMdd");
		Date date=new Date();
		String fmt=sfm.format(date);
		File outFile=new File("c:/dev/report/report_"+fmt+".dat");
		
		FileWriter of=null;
		
		try {
			of=new FileWriter(outFile);
			of.write("");
			of.flush();
		}finally {
			if(of!=null) {of.close();}
		}
    
    }
    public static void main(String[] args) {
    	UseReportbtn urb=new UseReportbtn();
    	try {
    		urb.write();
    	}catch (IOException e) {
    		e.printStackTrace();
    	}
    }
}
