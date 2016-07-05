package com.azias.openaw.launcher;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DragableUndecoratedWindow extends Application {
	//private final double[] windowSize = {640,480};
	private final double[] windowSize = {800,600};
	private double xOffset = 0;
	private double yOffset = 0;
	
	private Image imgQuit=null, imgMinimize=null;
	private ImageView buttonQuit, buttonMinimize;
	
	private WebView webViewComponent;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage primaryStage) {
		primaryStage.initStyle(StageStyle.UNDECORATED);
		
		//Loading Images
		try {
			imgQuit = new Image(new BufferedInputStream(new FileInputStream("./mods/launcher/quit_24.png")));
			imgMinimize = new Image(new BufferedInputStream(new FileInputStream("./mods/launcher/minimize_24.png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			primaryStage.close();
		}
		
		//Main Pane
		Pane paneMain = new Pane();
		paneMain.setStyle("");
		
		//Top bar
		Pane paneTopBar = new Pane();
		paneTopBar.setPrefSize(windowSize[0], 50);
		paneTopBar.setStyle(
				"-fx-background-color: #D14841;"+
				"-fx-border-width: 2px;"+
				"-fx-border-color: transparent transparent #AA3A36 transparent;");
		paneTopBar.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		paneTopBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				primaryStage.setX(event.getScreenX() - xOffset);
				primaryStage.setY(event.getScreenY() - yOffset);
			}
		});
		
		//Exit Button
		buttonQuit = new ImageView(imgQuit);
		buttonQuit.setTranslateX(windowSize[0]-imgQuit.getWidth()*1.5);
		buttonQuit.setTranslateY(48/2-imgQuit.getHeight()/2);
		buttonQuit.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				primaryStage.close();
			}
		});
		//Minimize Button
		buttonMinimize = new ImageView(imgMinimize);
		buttonMinimize.setTranslateX(windowSize[0]-imgMinimize.getWidth()*3);
		buttonMinimize.setTranslateY(48/2-imgMinimize.getHeight()/2);
		buttonMinimize.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				primaryStage.setIconified(true);
			}
		});
		
		//MenuBar
		
		
		
		//WebView
		webViewComponent = new WebView();
		webViewComponent.getEngine().load("http://google.com/");
		//webViewComponent.setPrefSize(prefWidth, prefHeight);
		
		paneTopBar.getChildren().add(buttonQuit);
		paneTopBar.getChildren().add(buttonMinimize);
		paneMain.getChildren().add(paneTopBar);
		
		Scene scene = new Scene(paneMain, windowSize[0], windowSize[1]);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}