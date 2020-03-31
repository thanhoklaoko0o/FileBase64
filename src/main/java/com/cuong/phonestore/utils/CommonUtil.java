package com.cuong.phonestore.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class CommonUtil {
	
	/**
	 * Convert date to string with input format
	 * 
	 * @param inputDate
	 * @param dateFormat
	 * @return outputDateStr
	 */
	public static String cvDateToString(Date inputDate, String dateFormat) {

		String outputDate = StringUtils.EMPTY;
		if (inputDate != null) {
			SimpleDateFormat dateFormatOutput = new SimpleDateFormat(dateFormat);
			outputDate = dateFormatOutput.format(inputDate);
		}
		
		return outputDate;
	}
	
    public static LocalDate getDate(String date, String format) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }
    
    public static LocalDateTime getLocalDateTime(String date, String format) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime localDate = LocalDateTime.parse(date, formatter);
        return localDate;
    }
    
    public static java.sql.Date getDate(java.util.Date d) {
        return new java.sql.Date(d.getTime());
    }
    
    public static Timestamp getDate(LocalDateTime ldt) {
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    	String str = ldt.format(dtf);
    	Timestamp ts = Timestamp.valueOf(str);
        return ts;
    }

    public static java.sql.Date parseDate(LocalDate date, String format) throws ParseException{
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
    	String str = date.format(dtf);
        return getDateFromString(str, format);
    }
    
    public static java.sql.Date getDateFromString(String stDMY, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        java.util.Date date = sdf.parse(stDMY);
        return getDate(date);
    }

    public static boolean isEmpty(Object string) {
		return string == null || Constants.EMPTY_STRING.equals(string);
	}
    /*
     * public static java.sql.Date convertStringTo(String date, String format) {
     * java.sql.Date date1 = java.sql.Date.valueOf(getDate(date, format)); return
     * date1; }
     */
    
    /**
	 * Append Criteria With Operator
	 * 
	 * @param fieldName
	 * @param fieldValue
	 * @param operator
	 * @return String
	 */
	public static String appendCriteria(String fieldName, Object fieldValue, String operator) {

		String strQuery = Constants.EMPTY_STRING;
		if (!CommonUtil.isEmpty(fieldValue)) {
			strQuery = String.format(Constants.CRITERIAL_COMPARE, fieldName, operator,
					fieldValue.toString().toUpperCase().trim());
		}
		return strQuery;
	}

	/**
	 * Append Like Criteria
	 * 
	 * @param fieldName
	 * @param fieldValue
	 * @return String
	 */
	public static String appendCriteriaLike(String fieldName, String fieldValue) {

		String strQuery = Constants.EMPTY_STRING;
		if (!CommonUtil.isEmpty(fieldValue)) {
			strQuery = String.format(Constants.CRITERIAL_LIKE, fieldName, fieldValue.toUpperCase().trim());
		}
		return strQuery;
	}

	/**
	 * Append Between Criteria
	 * 
	 * @param fieldName
	 * @param fromValue
	 * @param toValue
	 * @return String
	 */
	public static String appendCriteriaBetween(String fieldName, String fromValue, String toValue) {

		String strQuery = Constants.EMPTY_STRING;
		if (!CommonUtil.isEmpty(fromValue) && !CommonUtil.isEmpty(toValue)) {
			strQuery = String.format(Constants.CRITERIAL_BETWEEN, fieldName, fromValue, toValue);
		}
		return strQuery;
	}

	public static boolean validateKyTuDacBiet(String text) {
		Pattern pattern = Pattern.compile(new String ("^[a-zA-Z\\s]*$"));
	    Matcher matcher = pattern.matcher(text);
	    return matcher.matches();
	}
}
