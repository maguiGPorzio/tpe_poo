package frontend;

import backend.CanvasState;
import backend.model.*;
import frontend.buttons.FigureButton;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class PaintPane extends BorderPane {

	CanvasState canvasState;
	Point startPoint;
	StatusPane statusPane;

	private static final int CANVAS_WIDTH = 800;
	private static final int CANVAS_HEIGHT = 600;
	private static final Color LINE_COLOR = Color.BLACK;
	private static final int LINE_WIDTH = 1;

	Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	Color lineColor = LINE_COLOR;

	private final CustomHBox tBox = new CustomHBox();
	private final CustomVBoxRight rBox = new CustomVBoxRight();
	private final CustomVBoxLeft lBox = new CustomVBoxLeft();

	public PaintPane(CanvasState canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;
		gc.setLineWidth(LINE_WIDTH);

		canvas.setOnMousePressed(event -> {
			startPoint = new Point(event.getX(), event.getY());
		});

		canvas.setOnMouseReleased(event -> {
			Point endPoint = new Point(event.getX(), event.getY());
			if(startPoint == null) {
				return ;
			}
			canvasState.addFigure(generateFigure(startPoint, endPoint));
			startPoint = null;
			redrawCanvas();
		});

		canvas.setOnMouseMoved(event -> {
			Point eventPoint = new Point(event.getX(), event.getY());
			boolean found = false;
			StringBuilder label = new StringBuilder();
			for(Figure figure : canvasState.figures()) {
				if(figureBelongs(figure, eventPoint)) {
					found = true;
					label.append(figure);
				}
			}
			if(found) {
				statusPane.updateStatus(label.toString());
			} else {
				statusPane.updateStatus(eventPoint.toString());
			}
		});

		canvas.setOnMouseClicked(event -> {
			if(lBox.isSelectionButtonSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				boolean found = false;
				StringBuilder label = new StringBuilder("Se seleccionó: ");
				for (Figure figure : canvasState.getCurrentFigures()) {
					if(figureBelongs(figure, eventPoint) && canvasState.belongsInCurrentLayer(figure)) {
						found = true;
						canvasState.setSelectedFigure(figure);
						canvasState.setFormat(lBox.getShadow(), lBox.isBevel(), lBox.getColor1(), lBox.getColor2());
						label.append(figure.toString());
					}
				}
				if (found) {
					statusPane.updateStatus(label.toString());
				} else {
					statusPane.updateStatus("Ninguna figura encontrada");
				}
				redrawCanvas();
			}
		});

		canvas.setOnMouseDragged(event -> {
			if(lBox.isSelectionButtonSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				Figure sel = canvasState.getSelectedFigure();
				if(sel != null){
					sel.move((eventPoint.getX() - startPoint.getX()) / 100,(eventPoint.getY() - startPoint.getY()) / 100);
				}
				redrawCanvas();
			}
		});

		lBox.setEraseAction(event -> {
			if (canvasState.getSelectedFigure() != null) {
				canvasState.deleteFigure();
				redrawCanvas();
			}
		});


		rBox.setRotateAction(event -> {canvasState.rotate(); redrawCanvas();});
		rBox.setFlipVAction(event -> {canvasState.flipV(); redrawCanvas();});
		rBox.setFlipHAction(event -> {canvasState.flipH(); redrawCanvas();});
		rBox.setDuplicateAction(event -> {canvasState.duplicate(); redrawCanvas();});
		rBox.setDivideAction(event -> {canvasState.divide(); redrawCanvas();});
		lBox.setEraseAction(event -> {canvasState.deleteFigure(); redrawCanvas();});
		tBox.setAddLayerAction(event -> {
			canvasState.addLayer();
		});
		tBox.setHideAction(event -> {canvasState.hideLayer(); redrawCanvas();});
		tBox.setShowAction(event -> {canvasState.showLayer(); redrawCanvas();});
		tBox.setMoveToBackAction(event -> {canvasState.moveToBack(); redrawCanvas();});
		tBox.setChangeLayerAction(event -> {
			canvasState.changeLayer(tBox.getCurrentLayer());
			tBox.setLayerVisibility(canvasState.isCurrentLayerVisible());
			redrawCanvas();
		});
		tBox.setMoveToFrontAction(event -> {canvasState.moveToFront(); redrawCanvas();});
		tBox.setRemoveLayerAction(event -> {
			canvasState.removeLayer();
			redrawCanvas();
		});

		setLeft(lBox);
		setRight(rBox);
		setTop(tBox);
		setCenter(canvas);
	}

	private Figure generateFigure(Point startPoint, Point endPoint) {
		if (lBox.isFigureSelected()){
			FigureButton figureButton=(FigureButton) lBox.getFigureButtons().getSelectedToggle();
			canvasState.setCurrentLayer(tBox.getCurrentLayer());
			return figureButton.generate(startPoint, endPoint,lBox.getShadow(), lBox.isBevel(), lBox.getColor1(), lBox.getColor2());
		}
		return null;
	}

	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for(Figure figure : canvasState.visibleFigures()) {
			figure.draw(gc,figure == canvasState.getSelectedFigure());
		}
	}

	boolean figureBelongs(Figure figure, Point eventPoint) {
		return figure.belongs(eventPoint);
	}

}
