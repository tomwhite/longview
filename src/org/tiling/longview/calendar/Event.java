package org.tiling.longview.calendar;

import calendrica.*;

/**
 * I am an event at a point in time.
 */
public class Event {
	private Gregorian start;
	private String description;
	public Event(Date start, String description) throws BogusDateException {
		this(new Gregorian(start), description);
	}
	public Event(Gregorian start, String description) {
		this.start = start;
		this.description = description;
	}
	public Gregorian getStart() {
		return start;
	}
	public int getStartDayNumber() {
		return start.dayNumber();
	}
	public int getStartYear() {
		return start.year;
	}
	public String toString() {
		return description + " (" + start.day + "/" + start.month + "/" + start.year + ")";
	}
}
