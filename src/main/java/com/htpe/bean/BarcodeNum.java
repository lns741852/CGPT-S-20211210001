package com.htpe.bean;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.htpe.mapper.nnew.CsrBarcodeMapper;

public final class BarcodeNum {
	
	private static int num = 0;
	
	private static int date;
	
	private static Calendar now;

    private BarcodeNum() {
    	
    }
    

    public static int printNum() { 
    	
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(new Date());
    	int now = cal.get(Calendar.DAY_OF_MONTH);
    	if(now != date) {
    		num =1;
    		date =now;
    	}else {
    		num=num+1;        
    	}
    	return num;
    }

}
