package org.tiling.longview.test;

import org.tiling.longview.calendar.Event;
import org.tiling.longview.calendar.EasterIterator;

import junit.framework.*;

public class EasterIteratorTest extends TestCase {
	public EasterIteratorTest(String name) {
		super(name);
	}
	private void checkNext(EasterIterator i, int year, int month, int day) {
		Event e = (Event) i.next();
		assertEquals(year, e.getStartYear());
		assertEquals(year, e.getStart().year);
		assertEquals(month, e.getStart().month);
		assertEquals(day, e.getStart().day);
	}
	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}
	public static Test suite() {
		return new TestSuite(EasterIteratorTest.class);
	}
	public void test() {
		EasterIterator i = new EasterIterator(2000, 2010);
		assertTrue(i.hasNext());
		checkNext(i, 2000, 4, 23);
		checkNext(i, 2001, 4, 15);
		checkNext(i, 2002, 3, 31);
		checkNext(i, 2003, 4, 20);
		checkNext(i, 2004, 4, 11);
		checkNext(i, 2005, 3, 27);
		checkNext(i, 2006, 4, 16);
		checkNext(i, 2007, 4, 8);
		checkNext(i, 2008, 3, 23);
		checkNext(i, 2009, 4, 12);
		assertTrue("Should end", !i.hasNext());
	}
}
