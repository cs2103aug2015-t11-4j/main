package main.java.parser;

import java.util.Scanner;

public class DateAndTime {
	
	private static final String REGEX_WHITESPACE = " ";
	private static final String REGEX_SLASH = "/";
	private static final String REGEX_DOT = ".";
	private static final String KEYWORD_AM = "am";
	private static final String KEYWORD_PM = "pm";

	/* testing purposes
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("ENTER: ");
		String input = sc.nextLine();
		System.out.println(reformatTime(input));
	}
	*/
	
	public static String reformatDate(String input) {
		
		//valid formats examples: 22 oct, 22 october, 22/10, 22.10
		//to return date in format dd/mm
		
		String date = "";
		
		if (isOneWord(input)) {
			//input is in dd/mm; check if valid
			if (input.contains(REGEX_SLASH)) {
				String content[] = input.split(REGEX_SLASH, 2);
				if(Integer.parseInt(content[1]) <= 12) 	//within month range
					if(isValidDD(content[0],content[1])) //check if day is valid within the month
						date = input;
			}
			//input is in dd/mm; check if valid
			else if (input.contains(REGEX_DOT)) {
				String content[] = input.split("\\.", 2);
				if(Integer.parseInt(content[1]) <= 12) 	//within month range
					if(isValidDD(content[0],content[1])) //check if day is valid within the month
						date = content[0] + "/" + content[1];
			}
			else 
				date = "invalid date";
		}
		else {
			//input is in eg (22 october/ 22 oct)
			String content[] = input.split(REGEX_WHITESPACE, 2);
			switch(content[1].toLowerCase()) {
			//january
			case "jan":
			case "january":
				if(isValidDD(content[0], content[1].toLowerCase())) 
						date = content[0] + "/" + "1";
				break;
			//february
			case "feb":
			case "february":
				if(isValidDD(content[0], content[1].toLowerCase()))
					date = content[0] + "/" + "2";
				break;
			//march
			case "mar":
			case "march":
				if(isValidDD(content[0], content[1].toLowerCase()))
					date = content[0] + "/" + "3";
				break;
			//april
			case "apr":
			case "april":
				if(isValidDD(content[0], content[1].toLowerCase()))
					date = content[0] + "/" + "4";
				break;
			//may
			case "may":
				if(isValidDD(content[0], content[1].toLowerCase()))
					date = content[0] + "/" + "5";
				break;
			//june
			case "jun":
			case "june":
				if(isValidDD(content[0], content[1].toLowerCase()))
					date = content[0] + "/" + "6";
				break;
			//july
			case "jul":
			case "july":
				if(isValidDD(content[0], content[1].toLowerCase()))
					date = content[0] + "/" + "7";
				break;
			//august
			case "aug":
			case "august":
				if(isValidDD(content[0], content[1].toLowerCase()))
					date = content[0] + "/" + "8";
				break;
			//september
			case "sept":
			case "september":
				if(isValidDD(content[0], content[1].toLowerCase()))
					date = content[0] + "/" + "9";
				break;
			//october
			case "oct":
			case "october":
				if(isValidDD(content[0], content[1].toLowerCase()))
					date = content[0] + "/" + "10";
				break;
			//november
			case "nov":
			case "november":
				if(isValidDD(content[0], content[1].toLowerCase()))
					date = content[0] + "/" + "11";
				break;
			//december
			case "dec":
			case "december":
				if(isValidDD(content[0], content[1].toLowerCase()))
					date = content[0] + "/" + "12";
				break;	
			default:
				break;
			}
		}
		if (date.equals(""))
			return "invalid date";
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
				return "invalid time";
		}
		else if(isValid24Format(input))
			time = input;
		else
			return "invalid time";
		
		if (time.equals(""))
			return "invalid time";
		else 
			return time;
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
