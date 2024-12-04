package frontend;

import backend.CanvasState;
import backend.model.*;
import frontend.buttons.FigureButton;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class PaintPane extends BorderPane {

	CanvasState canvasState;
	Point startPoint;
	Figure selectedFigure;
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
			if((startPoint == null) || (endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY())) {
				return ; //aca deberiamos agregar tema errores
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
					label.append(figure.toString());
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
				for (Figure figure : canvasState.figures()) {
					if(figureBelongs(figure, eventPoint)) {
						found = true;
						selectedFigure = figure;
						label.append(figure.toString());
					}
				}
				if (found) {
					statusPane.updateStatus(label.toString());
				} else {
					selectedFigure = null;
					statusPane.updateStatus("Ninguna figura encontrada");
				}
				redrawCanvas();
			}
		});

		canvas.setOnMouseDragged(event -> {
			if(lBox.isSelectionButtonSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				selectedFigure.move((eventPoint.getX() - startPoint.getX()) / 100,(eventPoint.getY() - startPoint.getY()) / 100);
				redrawCanvas();
			}
		});

		lBox.setEraseAction(event -> {
			if (selectedFigure != null) {
				canvasState.deleteFigure(selectedFigure);
				selectedFigure = null;
				redrawCanvas();
			}
		});

//		deleteButton.setOnAction(event -> {
//			if (selectedFigure != null) {
//				canvasState.deleteFigure(selectedFigure);
//				selectedFigure = null;
//				redrawCanvas();
//			}
//		});

		setLeft(lBox);
		setRight(rBox);
		setTop(tBox);
		setCenter(canvas);
	}

	private Figure generateFigure(Point startPoint, Point endPoint) {
	}

	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for(Figure figure : canvasState.figures()) {
			figure.draw(gc,lineColor,figure == selectedFigure);
		}
	}

	boolean figureBelongs(Figure figure, Point eventPoint) {
		return figure.belongs(eventPoint);
	}

}
