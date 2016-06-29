package com.azias.openaw.launcher;

import com.azias.openaw.Utils;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class LauncherTest2 extends Application {
	WebView webViewComponent;
	WebEngine webEngineComponent;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();
			
			Button button = new Button("Click me!");
			button.setStyle(Utils.fileToString("./assets/launcher/css/button_test.css"));
			//button.setStyle("-fx-background-color: #FF00FF;");
			
			root.getChildren().add(button);
			
			Scene scene = new Scene(root, 800, 400);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}