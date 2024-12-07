package frontend;

import backend.CanvasState;
import frontend.formatted.FormattedFigure;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppLauncher extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) { //configuras tu ventana principal (stage) y su contenido
		CanvasState<FormattedFigure> canvasState = new CanvasState<>(); // BackEnd
		MainFrame frame = new MainFrame(canvasState);
		Scene scene = new Scene(frame); //representación visual que será mostrada en el stage, contenido principal visual de la escena es frame.
		primaryStage.setResizable(false); //hace que la ventana no se pueda cambiar de tamaño.
		primaryStage.setScene(scene); //establece la escena que se mostrará en la ventana principal.
		primaryStage.show(); //muestra la ventana en la pantalla.
		primaryStage.setOnCloseRequest(event -> System.exit(0));//con esto cuando cerras la ventana se cierra la app.
	}

}
