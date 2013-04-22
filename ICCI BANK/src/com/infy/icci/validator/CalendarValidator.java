/**
* Project Name: ICCI BANK
* User: leslie_406760
* Date: Oct 12, 2012
*/

package com.infy.icci.validator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * @author leslie_406760
 *
 */

public class CalendarValidator implements Validator {

	public CalendarValidator() {
	}
	
	public void validate(FacesContext facesContext, UIComponent component, Object arg2)
			throws ValidatorException {
		
		if(facesContext==null || component==null){
			throw new NullPointerException();
		}
		if(!(component instanceof UIInput)){
			return;
		}
//		FacesMessage message = new FacesMessage();
//		message.setSummary("Please enter 'From Date' greater than 'To Date'");
//				
//		try{
//			Calendar calendar = (GregorianCalendar)arg2;
//			Date date = calendar.getTime();
//			if(!(date.after(date))){
//				throw new ValidatorException(message);
//			}
//		}catch(Exception e){
//			throw new ValidatorException(message);
//		}	
		FacesMessage message = new FacesMessage();
		message.setSummary("Please enter From Date in 'dd-MON-yyyy format'");
		message.setDetail("Invalid Date (Must Be In dd-MMM-yyyy format)!");
				
//		try{
//			SimpleDateFormat simple = new SimpleDateFormat("dd-MMM-yyyy");
//			
//			Calendar calendar = (GregorianCalendar)arg2;
//			Date date = calendar.getTime();
//			if(!(date.after(date))){
//				throw new ValidatorException(message);
//			}
//		}catch(Exception e){
//			throw new ValidatorException(message);
//		}
		
		if (arg2 == null) {
			throw new ValidatorException(message);
		}
		SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar calendar = (GregorianCalendar)arg2;
		System.out.println("-----"+calendar);
		Date date1 = calendar.getTime();
		System.out.println("-----date"+date1);
		Object object = date1;
		System.out.println("-----ob"+object.toString());
		
		String [] date=new String[3];
		/*checking the format of date */
		if (!object.toString().trim().matches("(0[1-9]||" +
				"[1-2][0-9]||3[0-1])-(JAN||FEB||MAR||APR||MAY||" +
				"JUN||JUL||AUG||SEP||OCT||NOV||DEC)-((19||20)[0-9]{2})")){
			throw new ValidatorException(message);
		}	
		else{
			date=object.toString().split("-");
			int day=Integer.parseInt(date[0]);
			String month=date[1];
			int year=Integer.parseInt(date[2]);
			/*for Feb in a leap year*/
			if((month.equals("APR")||month.equals("JUN")||month.equals("SEP")||
					month.equals("NOV") )&&(day==31)){
				message.setDetail("The entered month has only 30 days");
				throw new ValidatorException(message);
			}
			/*for Feb in a leap year */
			else if(((year%400)==0||(year%4)==0)&&
					month.equals("FEB")&&day>29){
				message.setDetail("February in a leap year has only 29 days");
				throw new ValidatorException(message);
			}
			/*for Feb in a non leap year*/
			else if(((year%400)==0||(year%4)==0)&&
					month.equals("FEB")&&day>28){
				message.setDetail("February in a non leap year has only 28 days");
				throw new ValidatorException(message);
			}	
		}
		
		
	}
	
/*	public void validate2(FacesContext facesContext, UIComponent component, Object object)throws ValidatorException{
		if(facesContext==null || component==null){
			throw new NullPointerException();
		}
		if(!(component instanceof UIInput)){
			return;
		}
		FacesMessage message=new FacesMessage();
		message.setSummary("Invalid Date");
		message.setDetail("Invalid Date (Must Be In dd-MMM-yyyy format)!");
		
		String [] date=new String[3];
		//checking the format of date 
		if (!object.toString().trim().matches("(0[1-9]||" +
				"[1-2][0-9]||3[0-1])-(JAN||FEB||MAR||APR||MAY||" +
				"JUN||JUL||AUG||SEP||OCT||NOV||DEC)-((19||20)[0-9]{2})")){
			throw new ValidatorException(message);
		}	
		else{
			date=object.toString().split("-");
			int day=Integer.parseInt(date[0]);
			String month=date[1];
			int year=Integer.parseInt(date[2]);
			//for Feb in a leap year
			if((month.equals("APR")||month.equals("JUN")||month.equals("SEP")||
					month.equals("NOV") )&&(day==31)){
				message.setDetail("The entered month has only 30 days");
				throw new ValidatorException(message);
			}
			//for Feb in a leap year 
			else if(((year%400)==0||(year%4)==0)&&
					month.equals("FEB")&&day>29){
				message.setDetail("February in a leap year has only 29 days");
				throw new ValidatorException(message);
			}
			//for Feb in a non leap year
			else if(((year%400)==0||(year%4)==0)&&
					month.equals("FEB")&&day>28){
				message.setDetail("February in a non leap year has only 28 days");
				throw new ValidatorException(message);
			}	
		}
	}*/

}
