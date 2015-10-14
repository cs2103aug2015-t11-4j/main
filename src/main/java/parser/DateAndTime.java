package main.java.parser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class DateAndTime {
	
	private static final String REGEX_WHITESPACE = " ";
	private static final String REGEX_SLASH = "/";
	private static final String REGEX_DOT = ".";
	private static final String KEYWORD_AM = "am";
	private static final String KEYWORD_PM = "pm";

	// testing purposes
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("ENTER: ");
		String inputA = sc.nextLine();
		String inputB = sc.nextLine();
		System.out.println(compareDates(inputA, inputB));
	}
	//
	
	public static String reformatDate(String input) {
		
		//valid formats examples: 22 oct, 22 october, 22/10, 22.10, 22 oct 2015, 22 october 2015, 22/10/2015, 22.10.2015
		//to return date in format dd/mm/yyyy
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		//get current date time with Date()
		Date year = new Date();
		String date = "";
		
		if (isOneWord(input)) {

			//input can be dd/mm or dd/mm/yyyy; check if valid
			if (input.contains(REGEX_SLASH)) {
				ArrayList<String> dateList = new ArrayList<String>();
				String content[] = input.split(REGEX_SLASH, 2);
				dateList.add(content[0]); //add date into list
				if(content[1].contains(REGEX_SLASH)) {//input is dd/mm/yyyy
					content = content[1].split(REGEX_SLASH, 2);
					dateList.add(content[0]); //add month into list
					dateList.add(content[1]); //add year into list
				}
				else 
					dateList.add(content[1]); //input is dd/mm

				if(Integer.parseInt(dateList.get(1)) <= 12) 	//within month range
					if(isValidDD(dateList.get(0),dateList.get(1))) //check if day is valid within the month
						date = dateList.get(0) + "/" + dateList.get(1);
				
				if(dateList.size() == 3) {//year is specified
					if(isValidYear(dateList.get(2)))
						date = date + "/" + dateList.get(2);
					else 
						date = null;
				}
				else { //year is not specified; append current year to it
					date = date + "/" + dateFormat.format(year);
				}	
			}
			//format is in dd.mm.yyyy or dd.mm
			else if (input.contains(REGEX_DOT)) {
				ArrayList<String> dateList = new ArrayList<String>();
				String content[] = input.split("\\.", 2);
				dateList.add(content[0]); //add date into list
				if(content[1].contains(REGEX_DOT)) {//input is dd.mm.yyyy
					content = content[1].split("\\.", 2);
					dateList.add(content[0]); //add month into list
					dateList.add(content[1]); //add year into list
				}
				else 
					dateList.add(content[1]); //input is dd.mm

				if(Integer.parseInt(dateList.get(1)) <= 12) 	//within month range
					if(isValidDD(dateList.get(0),dateList.get(1))) //check if day is valid within the month
						date = dateList.get(0) + "/" + dateList.get(1);
				
				if(dateList.size() == 3) {//year is specified
					if(isValidYear(dateList.get(2)))
						date = date + "/" + dateList.get(2);
					else 
						date = null;
				}
				else { //year is not specified; append current year to it
					date = date + "/" + dateFormat.format(year);
				}	
			}
			else 
				date = null;
		}
		else {
			//input is in eg (22 october/ 22 oct)
			ArrayList<String> dateList = new ArrayList<String>();
			String content[] = input.split(REGEX_WHITESPACE, 2);
			dateList.add(content[0]); //add date into list
			if(content[1].contains(REGEX_WHITESPACE)) {//input is dd month yyyy
				content = content[1].split(REGEX_WHITESPACE, 2);
				dateList.add(content[0].toLowerCase()); //add month into list
				dateList.add(content[1]); //add year into list
			}
			else 
				dateList.add(content[1].toLowerCase()); //input is dd month 

			switch(dateList.get(1)) {
			//january
			case "jan":
			case "january":
				if(isValidDD(dateList.get(0), dateList.get(1))) {
					System.out.println(1);
					date = appendYear(dateFormat, year, date, dateList, "1");
				}
				break;
			//february
			case "feb":
			case "february":
				if(isValidDD(dateList.get(0), dateList.get(1))) 
					date = appendYear(dateFormat, year, date, dateList, "2");
				break;
			//march
			case "mar":
			case "march":
				if(isValidDD(dateList.get(0), dateList.get(1))) 
					date = appendYear(dateFormat, year, date, dateList, "3");
				break;
			//april
			case "apr":
			case "april":
				if(isValidDD(dateList.get(0), dateList.get(1))) 
					date = appendYear(dateFormat, year, date, dateList, "4");
				break;
			//may
			case "may":
				if(isValidDD(dateList.get(0), dateList.get(1))) 
					date = appendYear(dateFormat, year, date, dateList, "5");
				break;
			//june
			case "jun":
			case "june":
				if(isValidDD(dateList.get(0), dateList.get(1))) 
					date = appendYear(dateFormat, year, date, dateList, "6");
				break;
			//july
			case "jul":
			case "july":
				if(isValidDD(dateList.get(0), dateList.get(1))) 
					date = appendYear(dateFormat, year, date, dateList, "7");
				break;
			//august
			case "aug":
			case "august":
				if(isValidDD(dateList.get(0), dateList.get(1))) 
					date = appendYear(dateFormat, year, date, dateList, "8");
				break;
			//september
			case "sept":
			case "september":
				if(isValidDD(dateList.get(0), dateList.get(1))) 
					date = appendYear(dateFormat, year, date, dateList, "9");
				break;
			//october
			case "oct":
			case "october":
				if(isValidDD(dateList.get(0), dateList.get(1))) 
					date = appendYear(dateFormat, year, date, dateList, "10");
				break;
			//november
			case "nov":
			case "november":
				if(isValidDD(dateList.get(0), dateList.get(1))) 
					date = appendYear(dateFormat, year, date, dateList, "11");
				break;
			//december
			case "dec":
			case "december":
				if(isValidDD(dateList.get(0), dateList.get(1))) 
					date = appendYear(dateFormat, year, date, dateList, "12");
				break;	
			default:
				break;
			}
		}
		if (date.equals(""))
			return null;
		else 
			return date;
	}
	
	public static String reformatTime(String input) {
		
		//valid formats: 9am, 901am, 0901am, 9:01am, 9pm, 0901
		//to return in 24-hour format: 0901
		String time = "";
		
		//to remove cases of 9:01am etc
		input = input.toLowerCase();
		if(input.contains(":"))
			input = input.replaceAll(":", "");
		if(isValid12Format(input)) {
			if(input.contains(KEYWORD_AM)) {
				time = input.substring(0, input.indexOf(KEYWORD_AM));
				//eg. 901am, 801am
				if(time.length() == 3)
					time = "0" + time;
				//eg. 12am, 11am, 09am 
				if(time.length() == 2)
					time = time + "00";
				//eg. 9am, 8am
				if(time.length() == 1)
					time = "0" + time + "00";
				//special case: to change 12am to 0000, eg 1201am -> 0001
				if((time.indexOf("1") == 0) && (time.indexOf("2") == 1)) 
					time = time.replaceFirst("12", "00");		
			}
			else if(input.contains(KEYWORD_PM)) {
				time = input.substring(0, input.indexOf(KEYWORD_PM));
				if(time.length() == 3)
					time = "0" + time;
				//eg. 12am, 11am, 09am 
				if(time.length() == 2)
					time = time + "00";
				//eg. 9am, 8am
				if(time.length() == 1)
					time = "0" + time + "00";
				int timeNumeric = Integer.parseInt(time) + 1200;
				time = String.valueOf(timeNumeric);
			}
			else 
				return null;
		}
		else if(isValid24Format(input))
			time = input;
		else
			return null;
		
		if (time.equals(""))
			return null;
		else 
			return time;
	}
	
	public static boolean isDate(String input) {
		if((reformatDate(input).equals(null)))
			return false;
		else 
			return true;
	}
	
	public static boolean isTime(String input) {
		if((reformatTime(input).equals(null))) 
			return false;
		else 
			return true;
	}
	
	//compare dates; return true only if dateA is earlier than or equal to dateB
	//only for date formats: dd/mm/yyyy
	public static boolean compareDates(String dateA, String dateB) {
		String[] contentA = dateA.split("/", 3); 
		String[] contentB = dateB.split("/", 3); 
		
		if(dateA == null)
			return false;
		else if(dateB == null)
			return false;
		//compare year first
		else if (Integer.parseInt(contentA[2]) > Integer.parseInt(contentB[2]))
			return false;
		else if (Integer.parseInt(contentA[2]) < Integer.parseInt(contentB[2]))
			return true;
		//year is same
		else {
			//compare month
			if (Integer.parseInt(contentA[1]) > Integer.parseInt(contentB[1]))
				return false;
			else if (Integer.parseInt(contentA[1]) < Integer.parseInt(contentB[1]))
				return true;
			//month is same
			else {
				if (Integer.parseInt(contentA[0]) > Integer.parseInt(contentB[0]))
					return false;
				else if (Integer.parseInt(contentA[0]) < Integer.parseInt(contentB[0]))
					return true;
				//day is same
				else 
					return true;
			}
		}
	}
	
	//compare time; return true only if timeA is earlier than or equal to timeB
	//only for 24-hour time format
	public static boolean compareTimes(String timeA, String timeB) {
		if(timeA == null)
			return false;
		else if(timeB == null)
			return false;
		else 
			return (Integer.parseInt(timeA) <= Integer.parseInt(timeB));
	}
	
	private static boolean isValid12Format(String time) {
		time = time.toLowerCase();
		//remove cases of time with 9:30am, 10:30pm
		if(time.contains(":"))
			time = time.replaceAll(":", "");
		if(time.contains(KEYWORD_AM)) 
			time = time.substring(0, time.indexOf(KEYWORD_AM));
		else if(time.contains(KEYWORD_PM))
			time = time.substring(0, time.indexOf(KEYWORD_PM));
		else 
			return false;
		//for cases: 930am, 1020am
		if (onlyDigits(time)) {
			//pure hour; eg 9am, 10am
			if(Integer.parseInt(time) <= 12)
				return true;
			//contains  single digit hours and min; eg 930am, 759am
			else if (time.length() == 3) {
				int count = 0;
				//check last char; can only be digit 0 to 9
				if(Character.isDigit(time.charAt(2))) {
					count++;
				}
				//check second char; can only be digit 0 to 5
				if(Character.isDigit(time.charAt(1)))
					if(Character.getNumericValue(time.charAt(2)) <= 5) {
							count++;
					}
				//check first char; can be digit 0 to 9
				if(Character.isDigit(time.charAt(0))) {
					count++;
				}
				return (count == 3);				}
			//contains  double digit hours and min; eg 0930am, 1259am
			else if (time.length() == 4) {
				int count = 0;
				//check last char; can only be digit 0 to 9
				if(Character.isDigit(time.charAt(3))) {
					count++;
				}
				//check third char; can only be digit 0 to 5
				if(Character.isDigit(time.charAt(2)))
					if(Character.getNumericValue(time.charAt(2)) <= 5) {
							count++;
					}
				//check second char; can be digit 0 to 9
				if(Character.isDigit(time.charAt(1))) {
					count++;
				}
				//check first char; can only be digit 0 to 1
				if(Character.isDigit(time.charAt(0)))
					if(Character.getNumericValue(time.charAt(0)) <= 1) {
							count++;
					}
				return (count == 4);
			}
			else
				return false;
		}
		else 
			return false;
	}
	private static boolean isValid24Format(String time) {
		int count = 0;
		
		if(time.length() != 4)
			return false;
		//check last char; can only be digit 0 to 9
		if(Character.isDigit(time.charAt(3))) {
			count++;
		}
		//check third char; can only be digit 0 to 5
		if(Character.isDigit(time.charAt(2)))
			if(Character.getNumericValue(time.charAt(2)) <= 5) {
					count++;
			}
		//check second char; can be digit 0 to 9
		if(Character.isDigit(time.charAt(1))) {
			count++;
		}
		//check first char; can only be digit 0 to 2
		if(Character.isDigit(time.charAt(0)))
			if(Character.getNumericValue(time.charAt(0)) <= 2) {
					count++;
			}
		return (count == 4);
	}
	
	private static boolean isValidDD(String dd, String mm) {
		
		int day = Integer.parseInt(dd);
		boolean valid = false;
		
		switch(mm.toLowerCase()) {
		//january
		case "jan":
		case "january":
		case "01":
		case "1":
		//march
		case "mar":
		case "march":
		case "03":
		case "3":
		//may
		case "may":
		case "05":
		case "5":
		//july
		case "jul":
		case "july":
		case "07":
		case "7":
		//august
		case "aug":
		case "august":
		case "08":
		case "8":
		//october
		case "oct":
		case "october":
		case "10":
		//december
		case "dec":
		case "december":
		case "12":
			if (day <= 31)
				valid = true;
			break;
		//february
		case "feb":
		case "february":
		case "02":
		case "2":
			if (day <= 29)
				valid = true;
			break;
		//april
		case "apr":
		case "april":
		case "04":
		case "4":
		//june
		case "jun":
		case "june":
		case "06":
		case "6":
		//september
		case "sept":
		case "september":
		case "09":
		case "9":
		//november
		case "nov":
		case "november":
		case "11":
			if (day <= 30)
				valid = true;
			break;
		default:
			break;
		}
		return valid;
	}
	
	//check if a string input is a valid year; cannot be past year
	private static boolean isValidYear(String input) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		Date date = new Date();
		
		return (onlyDigits(input) && (input.length() == 4) && (Integer.parseInt(input) >= Integer.parseInt(dateFormat.format(date)))); 
	}
	//appends current year or specified year to a date
	private static String appendYear(DateFormat dateFormat, Date year, String date, ArrayList<String> dateList, String month) {
		if(dateList.size() == 3) { //year is specified
			if(isValidYear(dateList.get(2)))
				date = dateList.get(0) + "/" + month + "/" + dateList.get(2);
		}
		else { //year not specified; append current year
			date = dateList.get(0) + "/" + month + "/" + dateFormat.format(year);
		}
		return date;
	}
	//check if a string input is only a word
	private final static boolean isOneWord(String input) {
		if (input.contains(REGEX_WHITESPACE))
			return false;
		else 
			return true;
	}
	
	//check if a string input are all digits only
	private final static boolean onlyDigits(String input) {
		int count = 0;
		
		for(int i=0; i<input.length(); i++) {
			if(Character.isDigit(input.charAt(i)))
				count++;
		}
		return (count == input.length());
	}
}
