package org.tiling.longview;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;

import java.awt.geom.Line2D;

import org.tiling.longview.calendar.EasterIterator;

/**
 * I am a specialisation of {@link LongViewUI} for Easter Sundays between two given years.
 * In addition to the dates of Easter, I draw horizontal and vertical scales to
 * allow the dates to be read off.
 */
public class EasterUI extends LongViewUI {
	private static final Color[] COLOURS = new Color[] {
		new Color(200, 44, 71),
		new Color(86, 185, 212),
		new Color(94, 30, 120)
	}; 

	protected Shape[] leftYearScale;
	protected Shape[] rightYearScale;
	protected Shape[] topDayScale;
	protected Shape[] bottomDayScale;
	protected Shape[] topMonthLines;
	protected Shape[] bottomMonthLines;

	protected Stroke thinStroke = new BasicStroke(0.15f);
	protected Stroke thickStroke = new BasicStroke(0.25f);

	public EasterUI(int fromGregorianYear, int toGregorianYear) {
		super(new EasterIterator(fromGregorianYear, toGregorianYear),
				new SuperellipseEventShapeFactory(false));
		
		final double topTickY = -(fromGregorianYear + 0.5);

		final double leftScaleX = 79;
		final double rightScaleX = 119;
	
		leftYearScale = new Shape[toGregorianYear - fromGregorianYear];
		rightYearScale = new Shape[toGregorianYear - fromGregorianYear];
		for (int i = 0; i < leftYearScale.length; i++) {
			double offset;
			if (i % 10 == 0) {
				offset = 1;
			} else if (i % 5 == 0) {
				offset = 0.6;
			} else {
				offset = 0.3;
			}
			leftYearScale[i] = new Line2D.Double(leftScaleX, topTickY - i, leftScaleX + offset, topTickY - i);
			rightYearScale[i] = new Line2D.Double(rightScaleX - offset, topTickY - i, rightScaleX, topTickY - i);
			updateBounds(leftYearScale[i]);
			updateBounds(rightYearScale[i]);
		}

		final double leftTickX = 81.5;
		final double topScaleY = -(fromGregorianYear - 2.75);
		final double bottomScaleY = -(toGregorianYear + 2.75);

		topMonthLines = new Shape[2];
		bottomMonthLines = new Shape[2];
		topMonthLines[0] = new Line2D.Double(leftTickX, topScaleY, leftTickX + 9, topScaleY);
		topMonthLines[1] = new Line2D.Double(leftTickX + 10, topScaleY, leftTickX + 34, topScaleY);
		bottomMonthLines[0] = new Line2D.Double(leftTickX, bottomScaleY, leftTickX + 9, bottomScaleY);
		bottomMonthLines[1] = new Line2D.Double(leftTickX + 10, bottomScaleY, leftTickX + 34, bottomScaleY);
		updateBounds(topMonthLines[0]);
		updateBounds(topMonthLines[1]);
		updateBounds(bottomMonthLines[0]);
		updateBounds(bottomMonthLines[1]);
		
		topDayScale = new Shape[35];
		bottomDayScale = new Shape[35];
		for (int i = 0; i < topDayScale.length; i++) {
			double y1, y2;
			double offset;
			if (i == 8 || i == 19 || i == 29) {
				offset = 1 * 2;
			} else if (i == 3 || i == 14 || i == 24 || i == 34) {
				offset = 0.6 * 2;
			} else {
				offset = 0.3 * 2;
			}
			topDayScale[i] = new Line2D.Double(leftTickX + i, topScaleY, leftTickX + i, topScaleY - offset);
			bottomDayScale[i] = new Line2D.Double(leftTickX + i, bottomScaleY + offset, leftTickX + i, bottomScaleY);
		}

	}
	public void paint(Graphics2D g2) {
		g2.setRenderingHints(qualityHints);

		g2.setStroke(thinStroke);
		for (int i = 0; i < shapes.length; i++) {
			g2.setColor(COLOURS[i % 3]);
			g2.fill(shapes[i]);
		}

		g2.setStroke(thickStroke);
		for (int i = 0; i < leftYearScale.length; i++) {
			g2.setColor(COLOURS[i % 3]);
			g2.draw(leftYearScale[i]);
			g2.draw(rightYearScale[i]);
		}

		g2.setStroke(thinStroke);
		g2.setColor(Color.black);
		for (int i = 0; i < topDayScale.length; i++) {
			g2.draw(topDayScale[i]);
			g2.draw(bottomDayScale[i]);
		}
		for (int i = 0; i < topMonthLines.length; i++) {
			g2.draw(topMonthLines[i]);
			g2.draw(bottomMonthLines[i]);
		}
	}
}
