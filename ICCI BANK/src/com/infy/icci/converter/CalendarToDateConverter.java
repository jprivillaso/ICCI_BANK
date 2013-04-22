package com.infy.icci.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class CalendarToDateConverter implements Converter{

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
				
		Calendar calendar = Calendar.getInstance();
		try {
			Date date = new SimpleDateFormat("dd-MMM-yyyy").parse(arg2);
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e) {
			throw new ConverterException("Couln't convert date");
		}
				
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		
		try{
			Calendar calendar = (Calendar)arg2;
			//Calendar with current date and time
			Calendar tempCal= Calendar.getInstance();
			//Add 99 years to the temporal calendar
			tempCal.add(Calendar.YEAR, 99);
			//if today+100 is after today+99 then return "NA"
			if(calendar.after(tempCal)){
				return "NA";
			}
			else{
				Date date = calendar.getTime();
				SimpleDateFormat simple = new SimpleDateFormat("dd-MMM-yyyy");
				return simple.format(date);
			}
		}
		catch(Exception e){
			throw new ConverterException("Error displaying date");
		}
	}
}
