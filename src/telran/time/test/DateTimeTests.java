package telran.time.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.time.BarMizvaAdjuster;
import telran.time.NextFriday13;
import telran.time.WorkingDays;

public class DateTimeTests {

	LocalDate current = LocalDate.now();
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void localDateTest() {
		LocalDate birthDateAS = LocalDate.parse("1799-06-06");
		LocalDate barMizvaAS = birthDateAS.plusYears(13);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM,YYYY,d");
		System.out.println(barMizvaAS.format(dtf));
		ChronoUnit unit = ChronoUnit.MONTHS;
		System.out.printf("Number of %s between %s and %s is %d", unit,
				birthDateAS, barMizvaAS, unit.between(birthDateAS, barMizvaAS));
		
	}
	@Test
	void barMizvaTest() {
		assertEquals(current.plusYears(13), current.with(new BarMizvaAdjuster()));
	}
	
	@Test
	void displayCurrentDateTimeCanadaTimeZones () {
		//displaying current local date and time for all Canada time zones
		//displaying should contains time zone name
		
		for (int i = 8; i>3; i--) {
			ZoneId zoneId = ZoneId.of("UTC-"+i);
			LocalDateTime dateTime = ZonedDateTime.now(zoneId).toLocalDateTime();
			System.out.println(dateTime + " - "+ zoneId.getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
		}
	}
	
	@Test
	void workingDaysTest() {
		assertEquals(LocalDate.of(2023, 03, 10), current.with(new WorkingDays(new DayOfWeek[] {DayOfWeek.SUNDAY}, 20)));
		assertEquals(LocalDate.of(2023, 03, 2), current.with(new WorkingDays(new DayOfWeek[] {DayOfWeek.THURSDAY, DayOfWeek.FRIDAY}, 11)));
		assertEquals(LocalDate.of(2023, 03, 3), current.with(new WorkingDays(new DayOfWeek[] {DayOfWeek.MONDAY}, 14)));

	}
	
	@Test
	void nextFridey13Test () {
		assertEquals(LocalDate.of(2023, 10, 13), current.with(new NextFriday13()));

	}
}
