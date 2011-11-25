package org.tiling.longview;

import calendrica.*;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import org.tiling.longview.calendar.Event;

/**
 * I turn {@link org.tiling.longview.calendar.Event}s into java.awt.Shape instances
 * for display.
 */
public class EventShapeFactory {
	private static final double WIDTH = 0.75;
	private static final double HALF_WIDTH = WIDTH / 2;
	private boolean leftJustified;
	public EventShapeFactory() {
		this(true);
	}
	public EventShapeFactory(boolean leftJustified) {
		this.leftJustified = leftJustified;
	}
	protected Shape buildShape(int adjustedDayNumber, int year) {
		return new Rectangle2D.Double(adjustedDayNumber - HALF_WIDTH, -year - HALF_WIDTH, WIDTH, WIDTH);
	}
	public Shape buildShape(Event event) {
		int adjustedDayNumber = event.getStartDayNumber();
		if (!isLeftJustified()) {
			int lastDayOfYear = new Gregorian(Gregorian.DECEMBER, 31, event.getStartYear()).dayNumber();
			adjustedDayNumber += 365 - lastDayOfYear;
		}
		int year = event.getStartYear();
		return buildShape(adjustedDayNumber, year);
	}
	public boolean isLeftJustified() {
		return leftJustified;
	}
}
