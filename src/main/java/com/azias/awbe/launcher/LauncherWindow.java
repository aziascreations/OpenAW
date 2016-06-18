package com.azias.awbe.launcher;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.control.Button;

import javax.swing.*;

import com.azias.awbe.Utils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LauncherWindow {
	public static void main(String [] args){

		SwingUtilities.invokeLater(new Runnable() {
		@Override
			public void run() {
				ApplicationFrame mainFrame = new ApplicationFrame();
				mainFrame.setVisible(true);
			}
		});

	}

}

/**
* Main window used to display some HTML content.
*/
class ApplicationFrame extends JFrame{
	Pane root;
	
	WebView webViewComponent;
	WebEngine webEngineComponent;
	JPanel mainPanel;

	JTextField urlField;
	JButton goButton;

	public ApplicationFrame(){
		root = new Pane();
		initSwingComponents();
		loadJavaFXScene();
	}

	/**
	* Instantiate the Swing compoents to be used
	*/
	private void initSwingComponents(){
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		//mainPanel.add(root, BorderLayout.CENTER);

		JPanel urlPanel = new JPanel(new FlowLayout());
		urlField = new JTextField();
		urlField.setColumns(50);
		urlPanel.add(urlField);
		goButton = new JButton("Go");

		Button button = new Button( "Click me!");
        button.setStyle("-fx-background-image: url('./assets/launcher/img/leaf_plant.png')");
        //urlPanel.add(button);
        //mainPanel.get
		
		urlPanel.add(goButton);
		mainPanel.add(urlPanel, BorderLayout.NORTH);
		
		
		this.add(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,600);
	}

	/**
	* Instantiate the JavaFX Components in
	* the JavaFX Application Thread.
	*/
	private void loadJavaFXScene(){
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				BorderPane borderPane = new BorderPane();
				webViewComponent = new WebView();
				webEngineComponent = webViewComponent.getEngine();
				try {
					webEngineComponent.loadContent(Utils.fileToString("./assets/launcher/index.html"));
					/*webEngineComponent.setUserStyleSheetLocation(getClass().getResource("style.css").toString());
					webEngineComponent.*/

				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				//webComponent.getEngine().load("http://google.com/");
				//webComponent.getEngine().load("./html/index.html");
				//File htmlIndexFile = new File("./assets/launcher/index.html");
				//webComponent.getEngine().load("file:///"+htmlIndexFile.getAbsolutePath());

				borderPane.setCenter(webViewComponent);
				Scene scene = new Scene(borderPane,450,450);
				//javafxPanel.setScene(scene);

			}
		});
	}
}