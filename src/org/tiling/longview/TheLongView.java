package org.tiling.longview;





import org.tiling.gui.Canvas2D;import org.tiling.gui.Viewer2D;import org.tiling.gui.ViewerApplication;/**
 * I am an application for seeing the Long View.
 */
public class TheLongView extends ViewerApplication {




	public TheLongView() {
		super("The Long View");
		viewer.fitToCanvas();
	}

	public static void main(String[] args) {
		ViewerApplication app = new TheLongView();
	}

	public Viewer2D buildViewer() {
		Viewer2D viewer = new Viewer2D("101 Easters", false, false, false);

		LongViewUI ui = new EasterUI(2000, 2101);
		Canvas2D canvas = new ToolTipCanvas2D(ui);
		viewer.setCanvas2D(canvas);
		return viewer;
	}}