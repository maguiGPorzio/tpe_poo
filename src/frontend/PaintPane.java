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

//		Este evento se activa cuando el usuario presiona el botón del mouse sobre el lienzo.
//		Obtiene las coordenadas actuales del mouse (event.getX() y event.getY()).
//		Guarda estas coordenadas en la variable startPoint, que define el punto inicial de la figura que se va a dibujar.
		canvas.setOnMousePressed(event -> {
			startPoint = new Point(event.getX(), event.getY());
		});

//		Este evento se activa cuando el usuario suelta el botón del mouse.
//		Las coordenadas actuales del mouse (event.getX() y event.getY()) se guardan en endPoint, que define el punto final de la figura.
		canvas.setOnMouseReleased(event -> {
			Point endPoint = new Point(event.getX(), event.getY());
			if(startPoint == null) {
				return ; //aca deberiamos agregar tema errores
			}
			canvasState.addFigure(generateFigure(startPoint, endPoint));
			startPoint = null;
			redrawCanvas();
		});

//		Detecta cada movimiento del mouse sobre el área del componente al que está asociado.
//		Llama al manejador que se le asigna (un EventHandler<MouseEvent>) cada vez que el evento ocurre.
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

//		Se activa cuando el usuario presiona y suelta el botón del mouse en el componente.
//		Puedes diferenciar entre clic simple, doble clic u otros patrones utilizando las propiedades del evento.
		canvas.setOnMouseClicked(event -> {
			//aca falta poner que pasa si no aprieta
			if(lBox.isSelectionButtonSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				boolean found = false;
				StringBuilder label = new StringBuilder("Se seleccionó: ");
				for (Figure figure : canvasState.getCurrentFigures()) {
					if(figureBelongs(figure, eventPoint)) {
						found = true;
						canvasState.setSelectedFigure(figure);
						canvasState.setFormat(lBox.getShadow(),lBox.hasGradient(), lBox.isBevel(), lBox.getColo1(), lBox.getColo2());
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

//		Cuando el usuario presiona un botón del mouse y, sin soltarlo, mueve el mouse sobre el componente.
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
			if (selectedFigure != null) {
				canvasState.deleteFigure();
				redrawCanvas();
			}
		});

		rBox.setRotateAction(event -> {canvasState.rotate(); redrawCanvas();});
		rBox.setFlipVAction(event -> {canvasState.flipV(); redrawCanvas();});
		rBox.setFlipHAction(event -> {canvasState.flipH(); redrawCanvas();});
		rBox.setDuplicateAction(event -> {canvasState.duplicate(); redrawCanvas();});
		rBox.setDivideAction(event -> {canvasState.divide(); redrawCanvas();});

		lBox.setEraseAction(event -> {canvasState.deleteFigure();});

		tBox.setAddLayerAction(event -> {canvasState.addLayer();});
		tBox.setHideAction(event -> {canvasState.hideLayer();});
		tBox.setShowAction(event -> {canvasState.showLayer();});
		tBox.setMoveToBackAction(event -> {canvasState.moveToBack();});
		tBox.setMoveToFrontAction(event -> {canvasState.moveToFront();});
		tBox.setRemoveLayerAction(event -> {canvasState.removeLayer();});

		setLeft(lBox);
		setRight(rBox);
		setTop(tBox);
		setCenter(canvas);
	}

	private Figure generateFigure(Point startPoint, Point endPoint) {
		if (!lBox.isSelectionButtonSelected()){
			FigureButton figureButton=(FigureButton) lBox.getFigureButtons().getSelectedToggle();
			return figureButton.generate(startPoint, endPoint,lBox.getShadow(),lBox.hasGradient(), lBox.isBevel(), lBox.getColo1(), lBox.getColo2());
		}
		return null;
	}



	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		Figure fig = canvasState.getSelectedFigure();
		if(fig != null){
			fig.draw(gc, true);
		}
	}

	boolean figureBelongs(Figure figure, Point eventPoint) {
		return figure.belongs(eventPoint);
	}

}
