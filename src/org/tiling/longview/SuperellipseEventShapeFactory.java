package org.tiling.longview;

import java.awt.Shape;

import org.tiling.longview.calendar.Event;
import org.tiling.superellipse.SuperEllipse2D;

/**
 * I am a specialisation of {@link LongViewUI} to turn
 * {@link org.tiling.longview.calendar.Event}s into superellipses.
 */
public class SuperellipseEventShapeFactory extends EventShapeFactory {
	private static final double WIDTH = 1.5;
	private static final double HALF_WIDTH = WIDTH / 2;

	private static final double HEIGHT = 1.5;
	private static final double HALF_HEIGHT = HEIGHT / 2;
	public SuperellipseEventShapeFactory(boolean leftJustified) {
		super(leftJustified);
	}
	protected Shape buildShape(int adjustedDayNumber, int year) {
		return new SuperEllipse2D(
			adjustedDayNumber + 0.5 - HALF_WIDTH,
			-(year + 0.5) - HALF_HEIGHT,
			WIDTH,
			HEIGHT,
			Math.E).getShape();
	}
}
