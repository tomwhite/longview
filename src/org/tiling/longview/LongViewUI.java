package org.tiling.longview; 


import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.Shape;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;





import java.util.Iterator;import org.tiling.AbstractUI;import calendrica.*;import java.util.HashMap;import org.tiling.longview.calendar.Event;import java.util.Map;/**
 * I render the Long View of a collection of {@link org.tiling.longview.calendar.Event}s.
 */
public class LongViewUI extends AbstractUI {







	protected Shape[] shapes;













	public void paint(Graphics2D g2) {
		super.paint(g2);

		g2.setColor(Color.black);
		for (int i = 0; i < shapes.length; i++) {
			g2.fill(shapes[i]);
		}
	}










	protected Map events = new HashMap();	protected boolean isLeftJustified;	public LongViewUI(Iterator eventIterator) {
		this(eventIterator, new EventShapeFactory());
	}	public LongViewUI(Iterator eventIterator, EventShapeFactory eventShapeFactory) {
		initialiseShapes(eventIterator, eventShapeFactory);
		this.isLeftJustified = eventShapeFactory.isLeftJustified();
	}	private void initialiseShapes(Iterator eventIterator, EventShapeFactory eventShapeFactory) {
		ArrayList shapes = new ArrayList();
		while (eventIterator.hasNext()) {
			Event event = (Event) eventIterator.next();
			remember(event);
			Shape shape = eventShapeFactory.buildShape(event);
			shapes.add(shape);
			updateBounds(shape);
		}
		this.shapes = (Shape[]) shapes.toArray(new Shape[shapes.size()]);
	}	private void remember(Event event) {
		events.put(new Integer(event.getStart().toFixed()), event);
	}	public Event whatHappenedAt(Point2D point) throws BogusDateException {
		int dayOfYear = (int) point.getX();
		int year = (int) -point.getY();
		int lastDayOfYear = new Gregorian(Gregorian.DECEMBER, 31, year).dayNumber();
		if (!isLeftJustified) {
			dayOfYear += -365 + lastDayOfYear;
		}
		if (dayOfYear < 1 || dayOfYear > lastDayOfYear) {
			throw new BogusDateException();
		} else {
			Gregorian date = new Gregorian(Gregorian.toFixed(Gregorian.DECEMBER, 31, year - 1) + dayOfYear);
			return whatHappenedOn(date);
		}
	}	private Event whatHappenedOn(Date date) throws BogusDateException {
		Event event = (Event) events.get(new Integer(date.toFixed()));
		return event == null ? new Event(date, "") : event;
	}}