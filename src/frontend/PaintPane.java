package frontend;

import backend.CanvasState;
import backend.model.*;
import frontend.buttons.FigureButton;
import frontend.formatted.FormattedFigure;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class PaintPane extends BorderPane {

	CanvasState<FormattedFigure> canvasState;
	Point startPoint;
	StatusPane statusPane;

	private static final int CANVAS_WIDTH = 800;
	private static final int CANVAS_HEIGHT = 600;
	private static final Color LINE_COLOR = Color.BLACK;
	private static final int LINE_WIDTH = 1;

	Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
	GraphicsContext gc = canvas.getGraphicsContext2D();

	private final CustomHBox tBox = new CustomHBox();
	private final CustomVBoxRight rBox = new CustomVBoxRight();
	private final CustomVBoxLeft lBox = new CustomVBoxLeft();

	@SuppressWarnings("unused")
	public PaintPane(CanvasState<FormattedFigure> canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;
		gc.setLineWidth(LINE_WIDTH);
		gc.setStroke(LINE_COLOR);

		canvas.setOnMousePressed(event -> startPoint = new Point(event.getX(), event.getY()));

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

				for (FormattedFigure figure : canvasState.getCurrentFigures().reversed()) {
					if (figureBelongs(figure, eventPoint) && canvasState.belongsInCurrentLayer(figure)) {
						lBox.setProperties(figure.getFormat().duplicate());
						found = true;
						if (figure == canvasState.getSelectedFigure()) { //al volver a clickear, se deselecciona
							canvasState.setSelectedFigure(null);
						}
						else{
							canvasState.setSelectedFigure(figure);
							label.append(figure);
							if (lBox.hasCopiedFormat()) {
								figure.setFormat(lBox.getCopiedFormat().duplicate());
								lBox.setProperties(figure.getFormat().duplicate());
							}
						}
						redrawCanvas();
						break;
					}
				}
				if (found) {
						statusPane.updateStatus(label.toString());
				} else {
						statusPane.updateStatus("Ninguna figura encontrada");
						canvasState.setSelectedFigure(null);
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

		rBox.setRotateAction(event -> { canvasState.rotate(); redrawCanvas();});
		rBox.setFlipVAction(event -> {canvasState.flipV(); redrawCanvas();});
		rBox.setFlipHAction(event -> {canvasState.flipH(); redrawCanvas();});
		rBox.setDuplicateAction(event -> {canvasState.duplicate(); redrawCanvas();});
		rBox.setDivideAction(event -> {canvasState.divide(); redrawCanvas();});
		lBox.setEraseAction(event -> {canvasState.deleteFigure(); redrawCanvas();});
		tBox.setHideAction(event -> {canvasState.hideLayer(); redrawCanvas();});
		tBox.setShowAction(event -> {canvasState.showLayer(); redrawCanvas();});
		tBox.setMoveToBackAction(event -> {canvasState.moveToBack(); redrawCanvas();});
		tBox.setMoveToFrontAction(event -> {canvasState.moveToFront(); redrawCanvas();});
		lBox.setChangeShadowAction(event -> {
			if(canvasState.getSelectedFigure() != null){
				canvasState.getSelectedFigure().setShadow(lBox.getShadow());
				redrawCanvas();
			}
		});
		lBox.setBevelAction(event -> {
			if(canvasState.getSelectedFigure() != null){
				canvasState.getSelectedFigure().setBevel(lBox.isBevel());
				redrawCanvas();
			}
		});
		lBox.setColor1Action(event -> {
			if(canvasState.getSelectedFigure() != null){
				canvasState.getSelectedFigure().setColor1(lBox.getColor1());
				redrawCanvas();
			}
		});
		lBox.setColor2Action(event -> {
			if(canvasState.getSelectedFigure() != null){
				canvasState.getSelectedFigure().setColor2(lBox.getColor2());
				redrawCanvas();
			}
		});
		lBox.setCopyFormatAction(event -> {
			if(canvasState.getSelectedFigure() != null){
				lBox.setSavedFormat(canvasState.getSelectedFigure().getFormat().duplicate());
			}
		});

// 		-----------* LAYERS *-----------
		tBox.setChangeLayerAction(event -> {
			canvasState.changeLayer(tBox.getCurrentLayer());
			tBox.setLayerVisibility(canvasState.isCurrentLayerVisible());
			redrawCanvas();
		});
		tBox.setRemoveLayerAction(event -> {canvasState.removeLayer();redrawCanvas();});
		tBox.setAddLayerAction(event -> canvasState.addLayer());

//		-----------* DISPLAY *-----------
		setLeft(lBox);
		setRight(rBox);
		setTop(tBox);
		setCenter(canvas);
	}

	private FormattedFigure generateFigure(Point startPoint, Point endPoint) {
		if (lBox.isFigureSelected()){
			canvasState.setSelectedFigure(null);
			FigureButton figureButton=(FigureButton) lBox.getFigureButtons().getSelectedToggle();
			canvasState.setCurrentLayer(tBox.getCurrentLayer());
			return figureButton.generate(startPoint, endPoint, lBox.getFormat().duplicate(), gc);
		}
		return null;
	}


	private void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for(FormattedFigure figure : canvasState.visibleFigures()) {
			figure.draw(figure == canvasState.getSelectedFigure());
		}
	}

	private boolean figureBelongs(Figure figure, Point eventPoint) {
		return figure.belongs(eventPoint);
	}


}
