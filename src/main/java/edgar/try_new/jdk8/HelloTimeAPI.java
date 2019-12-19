package edgar.try_new.jdk8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Java 8 新特性-Time Api 使用指南
 * https://www.toutiao.com/a6754963482279412231/
 * 
 * @author Administrator
 *
 */
public class HelloTimeAPI {
	
	public static void test_LocalDate() {
		LocalDate today = LocalDate.now();
		System.out.println(today);
		
		System.out.println(LocalDate.of(2015, 02, 20));
		System.out.println(LocalDate.parse("2015-02-20"));
		
		LocalDate tomorrow = today.plusDays(1);
		System.out.println(tomorrow);
		
		LocalDate sameDayLastMonth = today.plusMonths(-1);
		System.out.println(sameDayLastMonth);
		
		boolean leapYear = today.isLeapYear();
		assert !leapYear;
		boolean isAfter = tomorrow.isAfter(today);
		assert isAfter;
		
		// 给定日期的一天的开始
		LocalDateTime beginningOfDay = today.atStartOfDay();
		System.out.println(beginningOfDay);
		
		// 代表月初的LocalDate
		LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
		System.out.println(firstDayOfMonth);
	}
	
	public static void test_LocalTime() {
		LocalTime now = LocalTime.now();
		System.out.println(now);
		
		LocalTime sixThirty = LocalTime.parse("06:30");
		System.out.println(sixThirty);
		sixThirty = LocalTime.of(6, 30);
		System.out.println(sixThirty);
		
		LocalTime sevenThirty = sixThirty.plus(1, ChronoUnit.HOURS);
		System.out.println(sevenThirty);
		
		System.out.println(LocalTime.MIN);
		System.out.println(LocalTime.MAX);
	}
	
	public static void test_LocalDateTime() {
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now);
		System.out.println(now.plusDays(1));
		System.out.println(now.minusHours(2));
		
		System.out.println(LocalDateTime.of(2019, Month.FEBRUARY, 20, 06, 30));
		System.out.println(LocalDateTime.parse("2019-02-20T06:30:00"));
	}
	
	public static void test_ZonedDateTime() {
		Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
		List<String> listZones = new ArrayList<String>(allZoneIds);
		Collections.sort(listZones);
		for(String zoneId : listZones) {
			System.out.println(zoneId);
		}
		
		ZoneId zoneId = ZoneId.of("Asia/Shanghai");
		LocalDateTime now = LocalDateTime.now();
		ZonedDateTime zonedDateTime = ZonedDateTime.of(now, zoneId);
		System.out.println(zonedDateTime);
	}
	
	public static void test_Period() {
		LocalDate initialDate = LocalDate.parse("2007-05-10");
		LocalDate finalDate = initialDate.plus(Period.ofDays(5));
		
		int five = Period.between(finalDate, initialDate).getDays();
		five = (int) ChronoUnit.DAYS.between(finalDate , initialDate);
	}
	
	public static void test_DateFormat() {
		LocalDateTime now = LocalDateTime.now();
		
		String localDateString = now.format(DateTimeFormatter.ISO_DATE_TIME);
		System.out.println(localDateString);
	}

	public static void main(String[] args) {
		test_LocalDate();
		test_LocalTime();
		test_LocalDateTime();
		test_ZonedDateTime();
		test_Period();
		test_DateFormat();
	}

}
