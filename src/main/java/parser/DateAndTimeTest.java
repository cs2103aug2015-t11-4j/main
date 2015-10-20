package main.java.parser;
//@author: A0124524N; wenbin 

import static org.junit.Assert.*;

import org.junit.Test;

public class DateAndTimeTest {

	@Test
	public void testReformatDate() {
		//invalid date format 
		assertEquals("wrong date format", "invalid date format", DateAndTime.reformatDate("abcdef"));
		assertEquals("wrong date format", "invalid date format", DateAndTime.reformatDate("22,12,2015"));
		
		//test date formats dd/mm, dd/mm/yyyy
		//check month first, then date then year
		
		//test correct date, month and year
		assertEquals("correct date, month and year", "22/10/2015", DateAndTime.reformatDate("22/10/2015"));
		
		// valid date and month, year not specified
		assertEquals("current year will be appended", "22/10/2015", DateAndTime.reformatDate("22/10"));
		
		//invalid month but valid date
		assertEquals("prints invalid month", "invalid month", DateAndTime.reformatDate("22/13"));
		
		//invalid date but valid month
		assertEquals("prints invalid day", "invalid day", DateAndTime.reformatDate("32/10"));
		
		//invalid date and invalid month
		assertEquals("looks at month first", "invalid month", DateAndTime.reformatDate("32/13"));
		
		//invalid year but valid day and month
		assertEquals("2014 is before current year so not treated as a valid year", "invalid year", DateAndTime.reformatDate("22/12/2014"));
		
		//test date formats dd.mm, dd.mm.yyyy
		//reformatDate will change to expected date format: dd/mm/yyyy
		//check month first, then date then year
				
		//test correct date, month and year
		assertEquals("correct date, month and year", "22/10/2015", DateAndTime.reformatDate("22.10.2015"));
				
		// valid date and month, year not specified
		assertEquals("current year will be appended", "22/10/2015", DateAndTime.reformatDate("22.10"));
				
		//invalid month but valid date
		assertEquals("prints invalid month", "invalid month", DateAndTime.reformatDate("22.13"));
				
		//invalid date but valid month
		assertEquals("prints invalid day", "invalid day", DateAndTime.reformatDate("32.10"));
				
		//invalid date and invalid month
		assertEquals("looks at month first", "invalid month", DateAndTime.reformatDate("32.13"));
				
		//invalid year but valid day and month
		assertEquals("2014 is before current year so not treated as a valid year", "invalid year", DateAndTime.reformatDate("22.10.2014"));
		
		//test formats: dd month yyyy, dd month, month can be full word or short form
		//testing a valid month and valid date without specified year
		//month is a full word
		assertEquals("prints in dd/mm/yyyy format", "22/10/2015", DateAndTime.reformatDate("22 october"));
		
		//month is short form
		assertEquals("prints in dd/mm/yyyy format", "22/10/2015", DateAndTime.reformatDate("22 oct"));
		
		//case is ignored, method shld read the month regardless of case
		assertEquals("prints in dd/mm/yyyy format", "22/10/2015", DateAndTime.reformatDate("22 oCtoBEr"));
		
		//append the specified year
		assertEquals("appends the specified year: 2016", "22/10/2016", DateAndTime.reformatDate("22 oct 2016"));
		
		//month that is spelled wrongly & a month that is not valid
		assertEquals("month spelled wrongly will not be processed", "invalid month", DateAndTime.reformatDate("22 octobre"));
		assertEquals("month that is not valid will not be processed", "invalid month", DateAndTime.reformatDate("22 abcdef"));
		
		//invalid day of the month will not be processed
		assertEquals("invalid day will be printed", "invalid day", DateAndTime.reformatDate("32 oct"));
	}
	
	@Test
	public void testReformatTime() {
		//unaccepted time formats will not be processed
		assertEquals("wrong time format", "invalid time format", DateAndTime.reformatTime("abcdef"));
		assertEquals("wrong time format", "invalid time format", DateAndTime.reformatTime("11om"));
		assertEquals("wrong time format", "invalid time format", DateAndTime.reformatTime("9999"));
		assertEquals("wrong time format", "invalid time format", DateAndTime.reformatTime("001"));
		assertEquals("wrong time format", "invalid time format", DateAndTime.reformatTime("10"));
		assertEquals("wrong time format", "invalid time format", DateAndTime.reformatTime("23564"));
		
		//tested format: time am, time pm
		//returns in 24 hour format
		assertEquals("pure hour am", "0900", DateAndTime.reformatTime("9am"));
		assertEquals("pure hour pm", "2100", DateAndTime.reformatTime("9pm"));
		assertEquals("minutes will be added in", "0932", DateAndTime.reformatTime("932am"));
		//invalid minutes will not be processed
		assertEquals("minutes should be max of 59", "invalid time format", DateAndTime.reformatTime("999am"));
		//invalid hour will not be processed
		assertEquals("hours should be max of 12", "invalid time format", DateAndTime.reformatTime("1358am"));
		//hours can only be in a range from 1 to 12
		assertEquals("0am is invalid", "invalid time format", DateAndTime.reformatTime("0am"));
		//formats can include ":" to separate mins and hours
		assertEquals("semi-colon can be used to separate", "0900", DateAndTime.reformatTime("9:00am"));
		
		//tested format: 24 hour format
		assertEquals("valid time format", "0000", DateAndTime.reformatTime("0000"));
		assertEquals("valid time format", "1323", DateAndTime.reformatTime("1323"));
		assertEquals("valid time format", "2359", DateAndTime.reformatTime("2359"));
		//24 hour format is only from 0000 to 2359
		assertEquals("over time range", "invalid time format", DateAndTime.reformatTime("2459"));
		assertEquals("over time format", "invalid time format", DateAndTime.reformatTime("12359"));
		assertEquals("under time format", "invalid time format", DateAndTime.reformatTime("000"));
	}
	
	@Test
	//compares only valid dates and date format can only be dd/mm/yyyy
	public void testCompareDates() {
		//return false compared dates is not in dd/mm/yyyy format
		assertFalse(DateAndTime.compareDates("22 oct", "23 oct"));
		//first date must be earlier than second date
		//compare year first
		assertTrue(DateAndTime.compareDates("22/10/2015", "22/10/2016"));
		assertFalse(DateAndTime.compareDates("22/10/2016", "22/10/2015"));
		
		//if year is the same, compare months
		assertTrue(DateAndTime.compareDates("22/10/2015", "22/11/2015"));
		assertFalse(DateAndTime.compareDates("22/11/2015", "22/10/2015"));
		
		//if month is also same, compare days
		assertTrue(DateAndTime.compareDates("21/10/2015", "22/10/2015"));
		assertFalse(DateAndTime.compareDates("22/10/2015", "21/10/2015"));
		//same dates will return true
		assertTrue(DateAndTime.compareDates("22/10/2015", "22/10/2015"));
	}
	
	@Test
	//compares only valid time and time format is only for 24 hour
	public void testCompareTimes() {
		//return false if not in 24 hour format
		assertFalse(DateAndTime.compareTimes("9am", "10am"));
		assertFalse(DateAndTime.compareTimes("2400", "000"));
		
		//first time must be earlier than second time
		//same time will return true
		assertTrue(DateAndTime.compareTimes("0000", "2359"));
		assertFalse(DateAndTime.compareTimes("2359", "0000"));
		assertTrue(DateAndTime.compareTimes("0000", "0000"));
	}
}
