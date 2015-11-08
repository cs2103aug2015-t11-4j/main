package main.java.parser;
//@@author: A0124524N; wenbin 

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import main.java.resources.Task;
public class DateAndTime {
	
	private static final String REGEX_WHITESPACE = " ";
	private static final String REGEX_SLASH = "/";
	private static final String REGEX_DOT = ".";
	private static final String KEYWORD_AM = "am";
	private static final String KEYWORD_PM = "pm";

	//for task creation
	public final static String reformatDate(String input) {
		
		//valid formats examples: 22 oct, 22 october, 22/10, 22.10, 22 oct 2015, 22 october 2015, 22/10/2015, 22.10.2015
		//to return date in format dd/mm/yyyy
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		//get current date time with Date()
		Date year = new Date();
		String currentYear = dateFormat.format(year);
		String date = "";
		boolean invalidDay = false;
		boolean invalidMonth = false;
		boolean invalidYear = false;
		boolean invalidDateFormat = false;
		
		if (isOneWord(input)) {

			//input can be dd/mm or dd/mm/yyyy; check if valid
			if (input.contains(REGEX_SLASH)) {
				ArrayList<String> dateList = new ArrayList<String>();
				String content[] = input.split(REGEX_SLASH, 2);
				dateList.add(reformatDateAndMonth(content[0])); //add date into list
				if(content[1].contains(REGEX_SLASH)) {//input is dd/mm/yyyy
					content = content[1].split(REGEX_SLASH, 2);
					dateList.add(reformatDateAndMonth(content[0])); //add month into list
					dateList.add(content[1]); //add year into list
				}
				else {
					dateList.add(reformatDateAndMonth(content[1])); //input is dd/mm
				}

				if(Integer.parseInt(dateList.get(1)) <= 12) {	//within month range
					if(isValidDD(dateList.get(0),dateList.get(1))) {//check if day is valid within the month
						date = dateList.get(0) + "/" + dateList.get(1);
					}
					else { 
						invalidDay = true;
					}
				}
				else {
					invalidMonth = true;
				}
				
				if(dateList.size() == 3) {//year is specified
					if(isValidYear(dateList.get(2))) {
						date = date + "/" + dateList.get(2);
					}
					else { 
						invalidYear = true;
					}
				}
				else { //year is not specified; append current year to it
					date = date + "/" + currentYear;
				}	
			}
			//format is in dd.mm.yyyy or dd.mm
			else if (input.contains(REGEX_DOT)) {
				ArrayList<String> dateList = new ArrayList<String>();
				String content[] = input.split("\\.", 2);
				dateList.add(reformatDateAndMonth(content[0])); //add date into list
				if(content[1].contains(REGEX_DOT)) {//input is dd.mm.yyyy
					content = content[1].split("\\.", 2);
					dateList.add(reformatDateAndMonth(content[0])); //add month into list
					dateList.add(content[1]); //add year into list
				}
				else {
					dateList.add(reformatDateAndMonth(content[1])); //input is dd.mm
				}
				if(Integer.parseInt(dateList.get(1)) <= 12) { 	//within month range
					if(isValidDD(dateList.get(0),dateList.get(1))) { //check if day is valid within the month
						date = dateList.get(0) + "/" + dateList.get(1);
					}
					else {
						invalidDay = true;
					}
				}
				else {
					invalidMonth = true;
				}
				
				if(dateList.size() == 3) {//year is specified
					if(isValidYear(dateList.get(2))) {
						date = date + "/" + dateList.get(2);
					}
					else { 
						invalidYear = true;
					}
				}
				else { //year is not specified; append current year to it
					date = date + "/" + currentYear;
				}	
			}
			else {
				invalidDateFormat = true;
			}
		}
		else {
			//input is in eg (22 october/ 22 oct)
			ArrayList<String> dateList = new ArrayList<String>();
			String content[] = input.split(REGEX_WHITESPACE, 2);
			dateList.add(reformatDateAndMonth(content[0])); //add date into list
			if(content[1].contains(REGEX_WHITESPACE)) {//input is dd month yyyy
				content = content[1].split(REGEX_WHITESPACE, 2);
				dateList.add(content[0].toLowerCase()); //add month into list
				dateList.add(content[1]); //add year into list
			}
			else {
				dateList.add(content[1].toLowerCase()); //input is dd month 
			}
			
			switch(dateList.get(1)) {
			//january
			case "jan":
			case "january":
				if(isValidDD(dateList.get(0), dateList.get(1))) {
					date = dateList.get(0) + "/01/";
					if(dateList.size() == 3) {//year is specified
						if(isValidYear(dateList.get(2))) {
							date = date  + dateList.get(2);
						}
						else { 
							invalidYear = true;
						}
					}
					else { //year is not specified; append current year to it
						date = date + currentYear;
					}	
				}
				else {
					date = "invalid day";
				}
				break;
			//february
			case "feb":
			case "february":
				if(isValidDD(dateList.get(0), dateList.get(1))) {
					date = dateList.get(0) + "/02/";
					if(dateList.size() == 3) {//year is specified
						if(isValidYear(dateList.get(2))) {
							date = date  + dateList.get(2);
						}
						else { 
							invalidYear = true;
						}
					}
					else { //year is not specified; append current year to it
						date = date + currentYear;
					}	
				}
				else {
					date = "invalid day";
				}
				break;
			//march
			case "mar":
			case "march":
				if(isValidDD(dateList.get(0), dateList.get(1))) {
					date = dateList.get(0) + "/03/";
					if(dateList.size() == 3) {//year is specified
						if(isValidYear(dateList.get(2))) {
							date = date  + dateList.get(2);
						}
						else { 
							invalidYear = true;
						}
					}
					else { //year is not specified; append current year to it
						date = date + currentYear;
					}	
				}
				else {
					date = "invalid day";
				}
				break;	
			//april
			case "apr":
			case "april":
				if(isValidDD(dateList.get(0), dateList.get(1))) {
					date = dateList.get(0) + "/04/";
					if(dateList.size() == 3) {//year is specified
						if(isValidYear(dateList.get(2))) {
							date = date + dateList.get(2);
						}
						else { 
							invalidYear = true;
						}
					}
					else { //year is not specified; append current year to it
						date = date + currentYear;
					}	
				}
				else {
					date = "invalid day";
				}
				break;
			//may
			case "may":
				if(isValidDD(dateList.get(0), dateList.get(1))) {
					date = dateList.get(0) + "/05/";
					if(dateList.size() == 3) {//year is specified
						if(isValidYear(dateList.get(2))) {
							date = date  + dateList.get(2);
						}
						else { 
							invalidYear = true;
						}
					}
					else { //year is not specified; append current year to it
						date = date + currentYear;
					}	
				}
				else {
					date = "invalid day";
				}
				break;
			//june
			case "jun":
			case "june":
				if(isValidDD(dateList.get(0), dateList.get(1))) {
					date = dateList.get(0) + "/06/";
					if(dateList.size() == 3) {//year is specified
						if(isValidYear(dateList.get(2))) {
							date = date  + dateList.get(2);
						}
						else { 
							invalidYear = true;
						}
					}
					else { //year is not specified; append current year to it
						date = date + currentYear;
					}	
				}
				else {
					date = "invalid day";
				}
				break;
			//july
			case "jul":
			case "july":
				if(isValidDD(dateList.get(0), dateList.get(1))) {
					date = dateList.get(0) + "/07/";
					if(dateList.size() == 3) {//year is specified
						if(isValidYear(dateList.get(2))) {
							date = date  + dateList.get(2);
						}
						else {
							invalidYear = true;
						}
					}
					else { //year is not specified; append current year to it
						date = date + currentYear;
					}	
				}
				else {
					date = "invalid day";
				}
				break;
			//august
			case "aug":
			case "august":
				if(isValidDD(dateList.get(0), dateList.get(1))) {
					date = dateList.get(0) + "/08/";
					if(dateList.size() == 3) {//year is specified
						if(isValidYear(dateList.get(2))) {
							date = date + dateList.get(2);
						}
						else { 
							invalidYear = true; 
						}
					}
					else { //year is not specified; append current year to it
						date = date + currentYear;
					}	
				}
				else {
					date = "invalid day";
				}
				break;
			//september
			case "sept":
			case "september":
				if(isValidDD(dateList.get(0), dateList.get(1))) {
					date = dateList.get(0) + "/09/";
					if(dateList.size() == 3) {//year is specified
						if(isValidYear(dateList.get(2))) {
							date = date + dateList.get(2);
						}
						else {
							invalidYear = true;
						}
					}
					else { //year is not specified; append current year to it
						date = date + currentYear;
					}	
				}
				else {
					date = "invalid day";
				}
				break;
			//october
			case "oct":
			case "october":
				if(isValidDD(dateList.get(0), dateList.get(1))) {
					date = dateList.get(0) + "/10/";
					if(dateList.size() == 3) {//year is specified
						if(isValidYear(dateList.get(2))) {
							date = date + dateList.get(2);
						}
						else { 
							invalidYear = true;
						}
					}
					else { //year is not specified; append current year to it
						date = date + currentYear;
					}	
				}
				else {
					date = "invalid day";
				}
				break;
			//november
			case "nov":
			case "november":
				if(isValidDD(dateList.get(0), dateList.get(1))) {
					date = dateList.get(0) + "/11/";
					if(dateList.size() == 3) {//year is specified
						if(isValidYear(dateList.get(2))) {
							date = date + dateList.get(2);
						}
						else { 
							invalidYear = true;
						}
					}
					else { //year is not specified; append current year to it
						date = date + currentYear;
					}	
				}
				else {
					date = "invalid day";
				}
				break;
			//december
			case "dec":
			case "december":
				if(isValidDD(dateList.get(0), dateList.get(1))) {
					date = dateList.get(0) + "/12/";
					if(dateList.size() == 3) {//year is specified
						if(isValidYear(dateList.get(2))) {
							date = date + dateList.get(2);
						}
						else { 
							invalidYear = true;
						}
					}
					else { //year is not specified; append current year to it
						date = date + currentYear;
					}	
				}
				else {
					date = "invalid day";
				}
				break;	
			default:
				date = "invalid month";
				break;
			}
		}
		if (!invalidDay && !invalidMonth && !invalidYear && !invalidDateFormat) {
			return date;
		}
		else if(invalidDay) {
			return "invalid day";
		}
		else if(invalidMonth) {
			return "invalid month";
		}
		else if(invalidYear) {
			return "invalid year";
		}
		else {
			return "invalid date format";
		}
	}
	
	//for task creation
	public final static String reformatTime(String input) {
		
		//valid formats: 9am, 901am, 0901am, 9:01am, 9pm, 0901
		//to return in 24-hour format: 0901
		String time = "";
		
		//to remove cases of 9:01am etc
		input = input.toLowerCase();
		if(input.contains(":")) {
			input = input.replaceAll(":", "");
		}
		if(isValid12Format(input)) {
			if(input.contains(KEYWORD_AM)) {
				time = input.substring(0, input.indexOf(KEYWORD_AM));
				//eg. 901am, 801am
				if(time.length() == 3) {
					time = "0" + time;
				}
				//eg. 12am, 11am, 09am 
				if(time.length() == 2) {
					time = time + "00";
				}
				//eg. 9am, 8am
				if(time.length() == 1) {
					time = "0" + time + "00";
				}
				//special case: to change 12am to 0000, eg 1201am -> 0001
				if((time.indexOf("1") == 0) && (time.indexOf("2") == 1)) { 
					time = time.replaceFirst("12", "00");		
				}
			}
			else if(input.contains(KEYWORD_PM)) {
				time = input.substring(0, input.indexOf(KEYWORD_PM));
				if(time.length() == 3) {
					time = "0" + time;
				}
				//eg. 12am, 11am, 09am 
				if(time.length() == 2) {
					time = time + "00";
				}
				//eg. 9am, 8am
				if(time.length() == 1) {
					time = "0" + time + "00";
				}
				int timeNumeric = Integer.parseInt(time) + 1200;
				time = String.valueOf(timeNumeric);
			}
			else {
				return "invalid time format";
			}
		}
		else if(isValid24Format(input)) {
			time = input;
		}
		else {
			return "invalid time format"; 
		}
		
		if (time.equals("")) {
			return "invalid time format";
		}
		else { 
			return time; 
		}
	}
	
	//for task creation
	public final static boolean isDate(String input) {
		if((reformatDate(input).contains("invalid"))) {
			return false;
		}
		else { 
			return true;
		}
	}
	
	//for task creation
	public final static boolean isTime(String input) {
		if((reformatTime(input).contains("invalid"))) {
			return false;
		}
		else { 
			return true;
		}
	}
	
	//for task creation
	//compare dates; return true only if dateA is earlier than or equal to dateB
	//only for date formats: dd/mm/yyyy
	public final static boolean compareDates(String dateA, String dateB) {
		String[] contentA = dateA.split("/", 3); 
		String[] contentB = dateB.split("/", 3);
		
		//check if dateA is in dd/mm/yyyy format
		if (!dateA.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
			return false;
		}
		else if(!dateB.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
			return false;
		}
		//compare year first
		else  {
			if (Integer.parseInt(contentA[2]) > Integer.parseInt(contentB[2])) {
				return false;
			}
			else if (Integer.parseInt(contentA[2]) < Integer.parseInt(contentB[2])) {
				return true;
			}
			//year is same
			else {
				//compare month
				if (Integer.parseInt(contentA[1]) > Integer.parseInt(contentB[1])) {
					return false;
				}
				else if (Integer.parseInt(contentA[1]) < Integer.parseInt(contentB[1])) {
					return true;
				}
				//month is same
				else {
					if (Integer.parseInt(contentA[0]) > Integer.parseInt(contentB[0])) {
						return false;
					}
					else if (Integer.parseInt(contentA[0]) < Integer.parseInt(contentB[0])) {
						return true;
					}
					//day is same
					else { 
						return true;
					}
				}
			}
		}
	}
	
	//for task creation
	//compare time; return true only if timeA is earlier than or equal to timeB
	//only for 24-hour time format
	public final static boolean compareTimes(String timeA, String timeB) {
		if(!isValid24Format(timeA)) {
			return false;
		}
		else if(!isValid24Format(timeB)) {
			return false;
		}
		else { 
			return (Integer.parseInt(timeA) <= Integer.parseInt(timeB));
		}
	}
	//for logic updating checking
	public final static boolean isValidUpdateET(Task event, String newET) {
		//if same date; ST must be earlier than or equal to newET
		if(event.getEndDate().equals(event.getStartDate())) {
			if(compareTimes(event.getStartTime(), newET)) {
				return true;
			}
			else {
				return false;
			}
		}
		//if different dates(ED is later than SD), ET can be any time
		else 
			return true;
	}
	
	//for logic updating checking
	public final static boolean isValidUpdateST(Task event, String newST) {
		//if same date; newST must be earlier than or equal to ET
		if(event.getEndDate().equals(event.getStartDate())) {
			if(compareTimes(newST, event.getEndTime())) {
				return true;
			}
			else {
				return false;
			}
		}
		//if different dates(ED is later than SD), ET can be any time
		else {
			return true;
		}
	}
	
	private static boolean isValid12Format(String time) {
		time = time.toLowerCase();
		//remove cases of time with 9:30am, 10:30pm
		if(time.contains(":")) {
			time = time.replaceAll(":", "");
		}
		if(time.contains(KEYWORD_AM)) { 
			time = time.substring(0, time.indexOf(KEYWORD_AM));
		}
		else if(time.contains(KEYWORD_PM)) {
			time = time.substring(0, time.indexOf(KEYWORD_PM));
		}
		else { 
			return false;
		}
		//for cases: 930am, 1020am
		if (onlyDigits(time)) {
			//pure hour; eg 9am, 10am
			if(Integer.parseInt(time) <= 12 && Integer.parseInt(time) > 0) {
				return true;
			}
			//contains  single digit hours and min; max 959am, 959pm, min 000am,000pm
			else if (time.length() == 3) {
				int count = 0;
				//check last char; can only be digit 0 to 9
				if(Character.isDigit(time.charAt(2))) {
					count++;
				}
				//check second char; can only be digit 0 to 5
				if(Character.isDigit(time.charAt(1))) {
					if(Character.getNumericValue(time.charAt(2)) <= 5) {
							count++;
					}
				}
				//check first char; can be digit 0 to 9
				if(Character.isDigit(time.charAt(0))) {
					count++;
				}
				return (count == 3);				
			}
			//contains  double digit hours and min; eg 0930am, 1259am
			else if (time.length() == 4) {
				int count = 0;
				//check last char; can only be digit 0 to 9
				if(Character.isDigit(time.charAt(3))) {
					count++;
				}
				//check third char; can only be digit 0 to 5
				if(Character.isDigit(time.charAt(2))) {
					if(Character.getNumericValue(time.charAt(2)) <= 5) {
							count++;
					}
				}
				//check second char; can be digit 0 to 9
				if(Character.isDigit(time.charAt(1))) {
					count++;
				}
				//check first char; can only be digit 0 to 1
				//if digit is 1; second char can only be 0, 1 or 2
				if(Character.isDigit(time.charAt(0))) {
					if(Character.getNumericValue(time.charAt(0)) == 0) {
							count++;
					}
					else if(Character.getNumericValue(time.charAt(0)) == 1) {
						if(Character.getNumericValue(time.charAt(1)) <= 2) {
							count++;
						}
					}
				}	
				return (count == 4);
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	private static boolean isValid24Format(String time) {
		int count = 0;
		
		if(time.length() != 4) {
			return false;
		}
		//check last char; can only be digit 0 to 9
		if(Character.isDigit(time.charAt(3))) {
			count++;
		}
		//check third char; can only be digit 0 to 5
		if(Character.isDigit(time.charAt(2))) {
			if(Character.getNumericValue(time.charAt(2)) <= 5) {
					count++;
			}
		}
		//check second char; can be digit 0 to 9
		if(Character.isDigit(time.charAt(1))) {
			count++;
		}
		//check first char; can only be digit 0 to 2
		//if digit is 2, second digit can only in range of 0 <= x <= 3
		if(Character.isDigit(time.charAt(0))) {
			if(Character.getNumericValue(time.charAt(0)) <= 1) {
					count++;
			}
			else if(Character.getNumericValue(time.charAt(0)) == 2) {
				if(Character.getNumericValue(time.charAt(1)) <= 3) {
					count++;
				}
			}
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
	private static String reformatDateAndMonth(String input) {
		switch (input) {
		case "1":
			return "01";
		case "2":
			return "02";
		case "3":
			return "03";
		case "4":
			return "04";
		case "5":
			return "05";
		case "6":
			return "06";
		case "7":
			return "07";
		case "8":
			return "08";
		case "9":
			return "09";
		default:
			return input;
		}
	}
	//check if a string input is a valid year; cannot be past year
	private static boolean isValidYear(String input) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		Date date = new Date();
		
		return (onlyDigits(input) && (input.length() == 4) && (Integer.parseInt(input) >= Integer.parseInt(dateFormat.format(date)))); 
	}
	//check if a string input is only a word
	private final static boolean isOneWord(String input) {
		if (input.contains(REGEX_WHITESPACE)) {
			return false;
		}
		else { 
			return true;
		}
	}
	//check if a string input are all digits only
	private final static boolean onlyDigits(String input) {
		int count = 0;
		
		for(int i=0; i<input.length(); i++) {
			if(Character.isDigit(input.charAt(i))) {
				count++;
			}
		}
		return (count == input.length());
	}
	
	//@@author: A0124524N -unused
	/* testing purposes
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		while(true) {
		System.out.println("ENTER: ");
		String inputA = sc.nextLine();
		System.out.println("ENTER: ");
		String inputB = sc.nextLine();
		System.out.println(compareTimes(inputA, inputB));
//		System.out.println(reformatDate(inputB));
		}
	}
	*/
}
