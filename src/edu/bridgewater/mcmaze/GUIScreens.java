package edu.bridgewater.mcmaze;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.animation.FadeTransition;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

/**
 * The main class for the game application. this class handles the GUI and all
 * associated events
 *
 * @author Jason Laatz, Alan Bowman, Dennis Marchuk
 *
 */
@SuppressWarnings("unused")
public class GUIScreens extends Application {
	int rooms = 0;
	private static final String BASE_MEDIA_URL = new String("file://" + System.getProperty("user.dir") + '/')
			.replace("\\", "/");
	private static final String ArrayList = null;
	ArrayList<String> roomDescriptionArray = new ArrayList<String>();
	ArrayList<String> roomNameArray = new ArrayList<String>();

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage myStage) throws Exception {

		myStage.setTitle("Title Screen");
		myStage.setResizable(false);

		FlowPane rootNode = new FlowPane();
		GridPane playNode = new GridPane();
		BorderPane makerNode = new BorderPane();
		BorderPane makerNode2 = new BorderPane();
		BorderPane makerNode3 = new BorderPane();
		Scene mainScene = new Scene(rootNode, 1300, 900);
		Scene playScene = new Scene(playNode, 1300, 900);
		Scene makerScene = new Scene(makerNode, 1300, 900);
		Scene makerScene2 = new Scene(makerNode2, 1300, 900);
		Scene makerScene3 = new Scene(makerNode3, 1300, 900);
		myStage.setScene(mainScene);

		GridPane.setColumnIndex(playNode, 0);
		GridPane.setRowIndex(playNode, 0);
		GridPane.setColumnSpan(playNode, 13);
		GridPane.setRowSpan(playNode, 9);

		FadeTransition fadeToPlay = new FadeTransition(Duration.millis(3000));
		fadeToPlay.setFromValue(1.0);
		fadeToPlay.setToValue(0.3);
		fadeToPlay.setCycleCount(4);
		fadeToPlay.setAutoReverse(true);

		// Initializing textfield, labels, and buttons for playNode
		TextField userInput = new TextField();

		Label titleLabel = new Label("The McMaze");
		titleLabel.setTextFill(Color.BLUE);
		titleLabel.setPadding(new Insets(70, 1100, 70, 40));

		Label gameOutput = new Label("This is a decription of the room.");

		Button playButton = new Button("Play");
		Button makerButton = new Button("Map Maker");
		Button quitButton = new Button("Quit");

		Button northWestButton = new Button(null, new ImageView(BASE_MEDIA_URL + "assets/NW.jpg"));
		Button northButton = new Button(null, new ImageView(BASE_MEDIA_URL + "assets/N.jpg"));
		Button northEastButton = new Button(null, new ImageView(BASE_MEDIA_URL + "assets/NE.jpg"));
		Button eastButton = new Button(null, new ImageView(BASE_MEDIA_URL + "assets/E.jpg"));
		Button southEastButton = new Button(null, new ImageView(BASE_MEDIA_URL + "assets/SE.jpg"));
		Button southButton = new Button(null, new ImageView(BASE_MEDIA_URL + "assets/S.jpg"));
		Button southWestButton = new Button(null, new ImageView(BASE_MEDIA_URL + "assets/SW.jpg"));
		Button westButton = new Button(null, new ImageView(BASE_MEDIA_URL + "assets/W.jpg"));
		Button upButton = new Button("Up");
		Button downButton = new Button("Down");
		Button helpButton = new Button("Help");

		// CSS for textfield, labels, and buttons for playNode
		userInput.setStyle("");

		titleLabel.setStyle("-fx-font-size: 35 arial;");
		gameOutput.setStyle("");

		playButton.setStyle("-fx-padding: 20; -fx-font: 40 arial;");
		makerButton.setStyle("-fx-padding: 20; -fx-font: 40 arial;");
		quitButton.setStyle("-fx-padding: 20; -fx-font: 40 arial;");

		northWestButton.setStyle("-fx-padding: 5; -fx-base: #b6e7c9;");
		northButton.setStyle("-fx-padding: 5; -fx-base: #b6e7c9;");
		northEastButton.setStyle("-fx-padding: 5; -fx-base: #b6e7c9;");
		eastButton.setStyle("-fx-padding: 5; -fx-base: #b6e7c9;");
		westButton.setStyle("-fx-padding: 5; -fx-base: #b6e7c9;");
		southEastButton.setStyle("-fx-padding: 5; -fx-base: #b6e7c9;");
		southButton.setStyle("-fx-padding: 5; -fx-base: #b6e7c9;");
		southWestButton.setStyle("-fx-padding: 5; -fx-base: #b6e7c9;");
		westButton.setStyle("-fx-padding: 5; -fx-base: #b6e7c9;");
		upButton.setStyle("");
		downButton.setStyle("");
		helpButton.setStyle("");

		// Initializing HBox and VBox's for playNode
		HBox upDownHBox = new HBox(15);
		upDownHBox.setPadding(new Insets(20, 50, 50, 0));
		upDownHBox.getChildren().addAll(upButton, downButton);

		VBox introVBox = new VBox(50);
		introVBox.setPadding(new Insets(50, 0, 0, 60));
		introVBox.getChildren().addAll(playButton, makerButton, quitButton);

		VBox leftArrowsVBox = new VBox(15);
		leftArrowsVBox.setPadding(new Insets(0, 15, 0, 0));
		leftArrowsVBox.getChildren().addAll(northWestButton, westButton, southWestButton);

		VBox middleArrowsVBox = new VBox(85);
		middleArrowsVBox.getChildren().addAll(northButton, southButton);

		VBox rightArrowsVBox = new VBox(15);
		rightArrowsVBox.setPadding(new Insets(0, 0, 0, 15));
		rightArrowsVBox.getChildren().addAll(northEastButton, eastButton, southEastButton);

		VBox helpVBox = new VBox(100);
		helpVBox.getChildren().addAll(middleArrowsVBox, helpButton);

		VBox centerVBox = new VBox(25);
		centerVBox.getChildren().addAll(upDownHBox, helpVBox);

		VBox inputOutputVBox = new VBox(100);
		inputOutputVBox.getChildren().addAll(gameOutput, userInput);

		// Initializing everything for makerNode1
		Label nameOfRoom = new Label("Name of Room : ");
		TextField roomName = new TextField();
		Label descOfRoom = new Label("Room Desription : ");
		TextArea roomDesc = new TextArea();
		Button addRoomButton = new Button("Finish this Room and Add Another Room");
		Button submitButton = new Button("Finish this Room and Continue");

		HBox nameHBox = new HBox(10);
		nameHBox.setPadding(new Insets(100, 100, 100, 100));
		nameHBox.setAlignment(Pos.TOP_CENTER);
		nameHBox.getChildren().addAll(nameOfRoom, roomName);

		VBox descVBox = new VBox(3);
		descVBox.getChildren().addAll(descOfRoom, roomDesc);

		HBox confirmRoomHBox = new HBox(70);
		confirmRoomHBox.setPadding(new Insets(0, 0, 100, 0));
		confirmRoomHBox.setAlignment(Pos.BASELINE_CENTER);
		confirmRoomHBox.getChildren().addAll(addRoomButton, submitButton);

		// Initializing everything for makerNode2
		Region roomRegion = new Region();
		Region outputRegion = new Region();
		Button selectRoomButton = new Button("Select Source Room");
		Button selectDestinationButton = new Button("Select Destination");
		Button northWestButton2 = new Button("North-West");
		Button northButton2 = new Button("North");
		Button northEastButton2 = new Button("North-East");
		Button eastButton2 = new Button("East");
		Button southEastButton2 = new Button("South-East");
		Button southButton2 = new Button("South");
		Button southWestButton2 = new Button("South-West");
		Button westButton2 = new Button("West");
		Button upButton2 = new Button("Up");
		Button downButton2 = new Button("Down");
		HBox upDownHBox2 = new HBox(25);
		upDownHBox2.getChildren().addAll(upButton2, downButton2);
		VBox leftSideVBox = new VBox(50);
		leftSideVBox.getChildren().addAll(northWestButton2, westButton2, southWestButton2);
		VBox middleVBox = new VBox(156);
		middleVBox.getChildren().addAll(northButton2, southButton2);
		VBox rightSideVBox = new VBox(50);
		rightSideVBox.getChildren().addAll(northEastButton2, eastButton2, southEastButton2);
		HBox allDirectionalButtonsHBox = new HBox(15);
		allDirectionalButtonsHBox.getChildren().addAll(leftSideVBox, middleVBox, rightSideVBox);
		VBox finalVBox = new VBox(30);
		finalVBox.setAlignment(Pos.CENTER);
		finalVBox.getChildren().addAll(selectRoomButton, allDirectionalButtonsHBox, upDownHBox2,
				selectDestinationButton);

		// CSS for makerNode2
		roomRegion.setMinSize(430, 900);
		outputRegion.setMinSize(430, 900);
		selectRoomButton.setStyle("-fx-padding: 2; -fx-font: 12 arial;");
		selectDestinationButton.setStyle("-fx-padding: 2; -fx-font: 12 arial;");
		northWestButton2.setStyle("-fx-padding: 2; -fx-font: 14 arial;");
		northButton2.setStyle("-fx-padding: 2; -fx-font: 14 arial;");
		northEastButton2.setStyle("-fx-padding: 2; -fx-font: 14 arial;");
		eastButton2.setStyle("-fx-padding: 2; -fx-font: 14 arial;");
		southEastButton2.setStyle("-fx-padding: 2; -fx-font: 14 arial;");
		southButton2.setStyle("-fx-padding: 2; -fx-font: 14 arial;");
		southWestButton2.setStyle("-fx-padding: 2; -fx-font: 14 arial;");
		westButton2.setStyle("-fx-padding: 2; -fx-font: 14 arial;");
		upButton2.setStyle("-fx-padding: 2; -fx-font: 14 arial;");
		downButton2.setStyle("-fx-padding: 2; -fx-font: 14 arial;");
		selectRoomButton.setMinSize(150, 50);
		selectDestinationButton.setMinSize(150, 50);
		northWestButton2.setMinSize(100, 50);
		northButton2.setMinSize(100, 50);
		northEastButton2.setMinSize(100, 50);
		eastButton2.setMinSize(100, 50);
		southEastButton2.setMinSize(100, 50);
		southButton2.setMinSize(100, 50);
		southWestButton2.setMinSize(100, 50);
		westButton2.setMinSize(100, 50);
		upButton2.setMinSize(100, 50);
		downButton2.setMinSize(100, 50);

		// CSS for makerNode1
		nameOfRoom.setStyle("-fx-font: 40 arial;");
		roomName.setStyle("-fx-padding: 20; -fx-font: 30 arial;");
		BorderPane.setAlignment(nameHBox, Pos.CENTER);
		descOfRoom.setStyle("-fx-font: 30 arial;");
		roomDesc.setStyle("-fx-padding: 20; -fx-font: 20 arial;");
		addRoomButton.setStyle("-fx-padding: 20; -fx-font: 25 arial;");
		submitButton.setStyle("-fx-padding: 20; -fx-font: 25 arial;");

		// Actions for buttons on makerNode1
		submitButton.setOnAction(new EventHandler<ActionEvent>() {// finish this
																	// room and
																	// continue
			public void handle(ActionEvent ae) {

				rooms++;
				myStage.setScene(makerScene2);
				myStage.setTitle("The McMaze Maker - Room Positions");

				String rmName = roomName.getText();
				roomNameArray.add(rmName);

				String rmDescription = descOfRoom.getText();
				roomDescriptionArray.add(rmDescription);

				// call the next room

			}
		});

		addRoomButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {

			}
		});

		addRoomButton.setOnAction(new EventHandler<ActionEvent>() {// finish
																	// this room
																	// and add
																	// another
																	// room
			public void handle(ActionEvent ae) {

				rooms++;
				String rmName = roomName.getText();
				roomNameArray.add(rmName);

				String rmDescription = descOfRoom.getText();
				roomDescriptionArray.add(rmDescription);

			}
		});

		while (rooms >= 0) {

		}

		// set north of also means set south of...
		selectRoomButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				// t
			}
		});

		selectDestinationButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				//
			}
		});

		northButton2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				//
			}
		});

		northWestButton2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				//
			}
		});

		northEastButton2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				//
			}
		});

		eastButton2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				//
			}
		});

		southEastButton2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				//
			}
		});

		southButton2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				//
			}
		});

		southWestButton2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				//
			}
		});

		westButton2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				//
			}
		});

		upButton2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				//
			}
		});

		downButton2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				//
			}
		});
		// Actions for buttons on MakerScreen2

		// Actions for buttons on MakerScreen3

		// Actions for buttons on Main Screen
		playButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				myStage.setScene(playScene);
				myStage.setTitle("The McMaze");

			}
		});

		makerButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				myStage.setScene(makerScene);
				myStage.setTitle("The McMaze Maker - Rooms and Descriptions");

			}
		});

		quitButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				Stage exitPopup = new Stage();
				exitPopup.initModality(Modality.APPLICATION_MODAL);
				exitPopup.initOwner(myStage);
				exitPopup.setTitle("The McMaze - Exit Screen");
				exitPopup.setResizable(false);

				Label exitLabel = new Label("Are you sure you would like to quit?");
				Button exit = new Button("Exit");
				Button cancel = new Button("Cancel");

				exitLabel.setStyle("-fx-font: 20 arial;");
				exit.setStyle("-fx-font: 14 arial;");
				cancel.setStyle("-fx-font: 14 arial;");

				HBox hbox = new HBox(30);
				hbox.getChildren().addAll(exit, cancel);
				hbox.setAlignment(Pos.CENTER);
				VBox vbox = new VBox(15);
				vbox.getChildren().addAll(exitLabel, hbox);
				vbox.setPadding(new Insets(30, 30, 30, 30));

				exit.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent ae) {

						System.exit(0);
					}
				});

				cancel.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent ae) {
						exitPopup.close();
					}
				});

				Scene exitScene = new Scene(vbox);
				exitPopup.setScene(exitScene);
				exitPopup.show();
			}
		});

		// Directional button event listeners for PlayNode
		northButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				movePlayer(0);
			}
		});

		northEastButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				movePlayer(1);
			}
		});

		eastButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				movePlayer(2);
			}
		});

		southEastButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				movePlayer(3);
			}
		});

		southButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				movePlayer(4);
			}
		});

		southWestButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				movePlayer(5);
			}
		});

		westButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				movePlayer(6);
			}
		});

		northWestButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				movePlayer(7);
			}
		});

		upButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				movePlayer(8);
			}
		});

		downButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				movePlayer(9);
			}
		});

		// String bgImage =
		// GUIScreens.class.getResource("assets/maze.jpg").toExternalForm();
		// rootNode.setStyle("-fx-background-image: url('" + bgImage + "'); " +
		// "-fx-background-position: center center; "
		// + "-fx-background-repeat: stretch;");

		rootNode.getChildren().addAll(titleLabel, introVBox);
		playNode.add(inputOutputVBox, 0, 0);
		playNode.add(upDownHBox, 9, 1);
		playNode.add(leftArrowsVBox, 9, 3);
		playNode.add(helpVBox, 10, 3);
		playNode.add(rightArrowsVBox, 11, 3);
		makerNode.setTop(nameHBox);
		makerNode.setCenter(descVBox);
		makerNode.setBottom(confirmRoomHBox);
		makerNode2.setLeft(roomRegion);
		makerNode2.setCenter(finalVBox);
		makerNode2.setRight(outputRegion);
		makerNode3.getChildren().addAll();
		myStage.show();

	}

	// Moves the player based on an int direction
	// 0 North 1 Northeast 2 East etc...
	public void movePlayer(int direction) {
		// playerObject.movePlayer(direction);
	}

}

/*
 * public void setRoom(int direction){
 * 
 * }
 * 
 * setRoom().. 0-7 8 up 9 down
 * 
 * potato potato potato
 * 
 * 
 */
