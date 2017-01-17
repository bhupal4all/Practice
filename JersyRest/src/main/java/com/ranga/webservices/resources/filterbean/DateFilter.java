package com.ranga.webservices.resources.filterbean;

import javax.ws.rs.QueryParam;

public class DateFilter {
	@QueryParam("year")
	int year;
	@QueryParam("month")
	int month;
	@QueryParam("day")
	int day;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	@Override
	public String toString() {
		return "DateFilter [year=" + year + ", month=" + month + ", day=" + day
				+ "]";
	}

}
