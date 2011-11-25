package org.tiling.longview;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;



import org.tiling.gui.Canvas2D; 
import org.tiling.UI; 

import calendrica.BogusDateException;import java.awt.geom.AffineTransform;import java.awt.geom.Rectangle2D;import org.tiling.gui.PrintPreview;/**
 * I am a Canvas2D with Tool Tip descriptions. I am customised for {@link LongViewUI}
 * display the textual description of {@link org.tiling.longview.calendar.Event}s.
 */
public class ToolTipCanvas2D extends Canvas2D {




	public String getToolTipText(MouseEvent event) {
		Point2D point = transformToLogicalSpace(event.getX(), event.getY());
		try {
			return ((LongViewUI) ui).whatHappenedAt(point).toString();
		} catch (BogusDateException e) {
			return "(Not in calendar)";
		}
	}



	public ToolTipCanvas2D(LongViewUI ui) {
		super(ui, calculatePostcardTransform(ui.getBounds2D()));
		setToolTipsEnabled(true);
	}	private static AffineTransform calculatePostcardTransform(Rectangle2D bounds) {
		double postcardProportion = PrintPreview.A6.getWidth() / PrintPreview.A6.getHeight();
		double boundsProportion = bounds.getWidth() / bounds.getHeight();
		return AffineTransform.getScaleInstance(1, boundsProportion / postcardProportion);
	}}