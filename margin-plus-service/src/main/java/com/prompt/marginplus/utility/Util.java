package com.prompt.marginplus.utility;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.Base64Utils;

public class Util {

	public static String getInvoiceNumber(int sequenceNumber) {
		StringBuilder sb = new StringBuilder();
		DecimalFormat numberFormat = new DecimalFormat("000000");
		String newSeq = numberFormat.format(sequenceNumber+1);
		//sb.append(getFinancialYearString()+"/"+getCurrentDateInString()+newSeq);
        sb.append("INV"+"/"+getCurrentDateInString()+newSeq);
		return sb.toString();
	}
	
	private static String getCurrentDateInString() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyMMdd");
		return df.format(date);
	}
	
	private static String getFinancialYearString() {
		StringBuilder sb = new StringBuilder();
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int year1 = 0;
		if(month >= 3) {
			year1 = year +1;
			sb.append(year+"-"+year1);
		}
		else {
			year1 = year - 1;
			sb.append(year1+"-"+year);
		}
		
		return sb.toString();
	}
	
	public static String getDecodedPassword(String encodedPassword) {
		String encodedPassWithoutBasic = encodedPassword.replace("Basic ", "");
		byte[] passwordBytes = Base64Utils.decodeFromString(encodedPassWithoutBasic);
		String decodedPassword = new String(passwordBytes);
		return decodedPassword;
	}
	
	public static String getDateInPrintableFormat(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy");
		return dateFormat.format(date);
	}
	
}
