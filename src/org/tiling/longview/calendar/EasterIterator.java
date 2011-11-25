package org.tiling.longview.calendar;

import calendrica.Gregorian;
import calendrica.Julian;

import java.util.Iterator;

/**
 * I iterate over Easter Sundays between two Gregorian years. I return {@link Event} objects.
 */
public class EasterIterator implements Iterator {
	private int fromGregorianYear;
	private int toGregorianYear;

	private int gregorianYear;
	public EasterIterator(int fromGregorianYear, int toGregorianYear) {
		this.fromGregorianYear = fromGregorianYear;
		this.toGregorianYear = toGregorianYear;

		this.gregorianYear = fromGregorianYear;
	}
	private Gregorian calculateEaster(int gregorianYear) {
		return new Gregorian(Julian.easter(gregorianYear));
	}
	private Gregorian checkEaster(int gregorianYear) {
		// Thomas H. O'Beirne's method (1956) as reproduced by Ian Stewart (Scientific American, March 2001)

		int a = gregorianYear % 19;
		int b = gregorianYear / 100;
		int c = gregorianYear % 100;
		int d = b / 4;
		int e = b % 4;
		
		int g = (8 * b + 13) / 25;
		int h = (19 * a + b - d - g + 15) % 30;

		int m = (a + 11 * h) / 319;

		int j = c / 4;
		int k = c % 4;

		int l = (2 * e + 2 * j - k - h + m + 32) % 7;
		int n = (h - m + l + 90) / 25;
		int p = (h - m + l + n + 19) % 32;
		
		return new Gregorian(n, p, gregorianYear);
	}
	public boolean hasNext() {
		return gregorianYear < toGregorianYear;
	}
	public Object next() {
		Gregorian easter = calculateEaster(gregorianYear);
		Gregorian easterCheck = checkEaster(gregorianYear);
		if (!easter.equals(easterCheck)) {
			throw new IllegalStateException("Error calculating Easter for " + gregorianYear + ": " + easter + " vs. " + easterCheck);
		}
		gregorianYear++;
		return new Event(easter, "Easter Sunday");
	}
	public void remove() {
		throw new UnsupportedOperationException();	
	}
}
