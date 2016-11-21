package edu.bridgewater.mcmaze;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
	ArrayList<Room> roomArray = new ArrayList<Room>();


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

		//Menu bar for all node screens
		Menu sqlMenu = new Menu("mySQL");
		MenuItem login = new MenuItem("Login");
		sqlMenu.getItems().addAll(login);
		Menu loadMenu = new Menu("Load");
		MenuItem loadMap = new MenuItem("Load Map");
		loadMenu.getItems().addAll(loadMap);
		MenuBar menuBar = new MenuBar();
		menuBar.setPadding(new Insets(0, 1300, 0, 0));
		menuBar.getMenus().addAll(sqlMenu, loadMenu);
		
		//Initializing Buttons and Label for rootNode
		Label titleLabel = new Label("The McMaze");
		Button playButton = new Button("Play");
		Button makerButton = new Button("Map Maker");
		Button quitButton = new Button("Quit");
		
		//CSS for rootNode		
		titleLabel.setStyle("-fx-font-size: 35 arial;");
		titleLabel.setTextFill(Color.BLUE);
		titleLabel.setPadding(new Insets(70, 1100, 70, 40));
		
		playButton.setStyle("-fx-padding: 20; -fx-font: 40 arial;");
		makerButton.setStyle("-fx-padding: 20; -fx-font: 40 arial;");
		quitButton.setStyle("-fx-padding: 20; -fx-font: 40 arial;");
		
		// Initializing textfield, labels, scrollPane, and buttons for playNode
		TextField userInput = new TextField();
		Label gameOutput = new Label("This is a decription of the room.");
		ScrollPane sPane = new ScrollPane();
		
		Button northWestButton = new Button("North-West");
		Button northButton = new Button("North");
		Button northEastButton = new Button("North-East");
		Button eastButton = new Button("East");
		Button southEastButton = new Button("South-East");
		Button southButton = new Button("South");
		Button southWestButton = new Button("South-West");
		Button westButton = new Button("West");
		Button upButton = new Button("Up");
		Button downButton = new Button("Down");
		Button helpButton = new Button("Help");

		// CSS for textfield, labels, and buttons for playNode
		userInput.setStyle("-fx-padding: 10; -fx-font: 20 arial;");
		userInput.setPadding(new Insets(0,0,0,25));
		userInput.setPrefWidth(700);
		gameOutput.setStyle("-fx-padding: 20; -fx-font: 30 arial;");
		

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
		
		northWestButton.setMinSize(100, 50);
		northButton.setMinSize(100, 50);
		northEastButton.setMinSize(100, 50);
		eastButton.setMinSize(100, 50);
		southEastButton.setMinSize(100, 50);
		southButton.setMinSize(100, 50);
		southWestButton.setMinSize(100, 50);
		westButton.setMinSize(100, 50);
		upButton.setMinSize(100, 50);
		downButton.setMinSize(100, 50);

		// Initializing HBox and VBox's (and scrollPane) for playNode

		VBox introVBox = new VBox(50);
		introVBox.setPadding(new Insets(50, 0, 0, 60));
		introVBox.getChildren().addAll(playButton, makerButton, quitButton);

		VBox leftArrowsVBox = new VBox(15);
		leftArrowsVBox.setPadding(new Insets(0, 15, 0, 0));
		leftArrowsVBox.getChildren().addAll(upButton, northWestButton, westButton, southWestButton);

		VBox middleArrowsVBox = new VBox(85);
		middleArrowsVBox.getChildren().addAll(northButton, southButton);

		VBox rightArrowsVBox = new VBox(15);
		rightArrowsVBox.setPadding(new Insets(0, 0, 0, 15));
		rightArrowsVBox.getChildren().addAll(downButton, northEastButton, eastButton, southEastButton);

		VBox centerVBox = new VBox(100);
		centerVBox.getChildren().addAll(middleArrowsVBox, helpButton);

		VBox inputOutputVBox = new VBox(100);
		inputOutputVBox.getChildren().addAll(gameOutput, userInput);
		
		sPane.setPrefSize(720, 900);
		sPane.setContent(inputOutputVBox);


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
		Label selectRoomLabel = new Label("Select a Room:");
		Label fillerText1 = new Label("is");
		Label fillerText2 = new Label("of");
		ChoiceBox<String> selectRoomChoiceBox = new ChoiceBox<String>();//TODO
		ChoiceBox<String> destinationRoomChoiceBox = new ChoiceBox<String>(); 
		Button northWestButton2 = new Button("North-West");
		Button northButton2 = new Button("North");
		Button northEastButton2 = new Button("North-East");
		Button eastButton2 = new Button("East");
		Button southEastButton2 = new Button("South-East");
		Button southButton2 = new Button("South");
		Button southWestButton2 = new Button("South-West");
		Button westButton2 = new Button("West");
		Button upButton2 = new Button("Above");
		Button downButton2 = new Button("Below");
		Button nextNodeButton = new Button("Continue to next screen");
		
		VBox leftSideVBox = new VBox(50);
		leftSideVBox.getChildren().addAll(northWestButton2, westButton2, southWestButton2);
		VBox middleVBox = new VBox(150);
		middleVBox.getChildren().addAll(northButton2, southButton2);
		VBox rightSideVBox = new VBox(50);
		rightSideVBox.getChildren().addAll(northEastButton2, eastButton2, southEastButton2);
		HBox allDirectionalButtonsHBox = new HBox(15);
		allDirectionalButtonsHBox.getChildren().addAll(leftSideVBox, middleVBox, rightSideVBox);
		VBox finalVBox = new VBox(30);
		finalVBox.getChildren().addAll(selectRoomLabel, selectRoomChoiceBox, fillerText1, 
				allDirectionalButtonsHBox, fillerText2, destinationRoomChoiceBox, nextNodeButton);

		//CSS for makerNode2
		selectRoomLabel.setStyle("-fx-font: 18 arial;");
		fillerText1.setStyle("-fx-font: 18 arial;");
		fillerText2.setStyle("-fx-font: 18 arial;");
		selectRoomChoiceBox.setStyle("-fx-padding: 2; -fx-font: 12 arial;");
		destinationRoomChoiceBox.setStyle("-fx-padding: 2; -fx-font: 12 arial;");
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
		allDirectionalButtonsHBox.setAlignment(Pos.CENTER);
		finalVBox.setAlignment(Pos.CENTER);
		upButton2.setAlignment(Pos.CENTER);
		downButton2.setAlignment(Pos.CENTER);
		selectRoomChoiceBox.setMinSize(150, 50);
		destinationRoomChoiceBox.setMinSize(150, 50);
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



		//CSS for makerNode1
		nameOfRoom.setStyle("-fx-font: 40 arial;");
		roomName.setStyle("-fx-padding: 20; -fx-font: 30 arial;");
		BorderPane.setAlignment(nameHBox, Pos.CENTER);
		descOfRoom.setStyle("-fx-font: 30 arial;");
		roomDesc.setStyle("-fx-padding: 20; -fx-font: 20 arial;");
		addRoomButton.setStyle("-fx-padding: 20; -fx-font: 25 arial;");
		submitButton.setStyle("-fx-padding: 20; -fx-font: 25 arial;");

		//Actions for buttons on makerNode1

		//Actions for buttons on MakerScreen2

		addRoomButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				//Code for storing data from the input areas
				myStage.setScene(makerScene);
			}
		});
		
		submitButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				//Code for storing final room data from input areas
				myStage.setScene(makerScene2);
				myStage.setTitle("The McMaze Maker - Room Positioning");
			}
		});

		//Actions for buttons on MakerScreen2
		nextNodeButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				//Code for storing all room positioning
				myStage.setScene(makerScene3);
				myStage.setTitle("The McMaze Maker - Event Rooms");
			}
		});


		//Actions for buttons on MakerScreen3


		//Actions for buttons on Main Screen
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
		playNode.add(sPane, 0, 0);
		playNode.add(leftArrowsVBox, 9, 3);
		playNode.add(centerVBox, 10, 3);
		playNode.add(rightArrowsVBox, 11, 3);
		makerNode.setTop(nameHBox);
		makerNode.setCenter(descVBox);
		makerNode.setBottom(confirmRoomHBox);
		makerNode2.setLeft(upButton2);
		makerNode2.setCenter(finalVBox);
	    makerNode2.setRight(downButton2);
		makerNode3.getChildren().addAll();
		myStage.show();
		
		
		//commands for the buttons

		
		
		submitButton.setOnAction(new EventHandler<ActionEvent>() {//finish this room and continue
			public void handle(ActionEvent ae) {

				rooms++;
				myStage.setScene(makerScene2);
				myStage.setTitle("The McMaze Maker - Room Positions");

				String rmName = roomName.getText();
				String rmDescription = roomDesc.getText();
				roomDesc.setText(" ");
				roomName.setText(" ");

				roomArray.add(new Room(rmName, rmDescription, false, false, false, rooms)); 	//(String roomName, String roomDesc, boolean isStartingRoom, boolean isEndingRoom, boolean hasMcGregor, final int roomID)

				
				
			}
		});
		
		addRoomButton.setOnAction(new EventHandler<ActionEvent>() {//finish this room and add another room
			public void handle(ActionEvent ae) {
				
				rooms++;
				String rmName = roomName.getText();
				roomNameArray.add(rmName);

				String rmDescription = roomDesc.getText();
				roomDescriptionArray.add(rmDescription);
				roomDesc.setText(" ");
				roomName.setText(" ");
				
				roomArray.add(new Room(rmName, rmDescription, false, false, false, rooms)); 

			}
		});

		//ChoiceBox selectRoomChoiceBox = new ChoiceBox();//TODO
		//ChoiceBox destinationRoomChoiceBox = new ChoiceBox();
		
		
		//0 north 8 up 9 down
		//set north of also means set south of...
		selectRoomButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				//srcRoom = inputBox.getText();
			}
		});		
		
		selectDestinationButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				//destRoom = input
			}
		});
		
		northButton2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				//Edge edge = new Edge(srcRoom, destRoom, 0);

			}
		});	
		
		northEastButton2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				//Edge edge = new Edge(srcRoom, destRoom, 1);
			}
		});	
		
		eastButton2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				//Edge edge = new Edge(srcRoom, destRoom, 2);
			}
		});	
		
		southEastButton2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				//Edge edge = new Edge(srcRoom, destRoom, 3);
			}
		});
				
		southButton2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				//Edge edge = new Edge(srcRoom, destRoom, 4);
			}
		});
				
		southWestButton2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				//Edge edge = new Edge(srcRoom, destRoom, 5);
			}
		});
				
		westButton2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				//Edge edge = new Edge(srcRoom, destRoom, 6);
			}
		});		
		
		northWestButton2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				//Edge edge = new Edge(srcRoom, destRoom, 7);
			}
		});
				

		upButton2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				//Edge edge = new Edge(srcRoom, destRoom, 8);
			}
		});
			
		downButton2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				//Edge edge = new Edge(srcRoom, destRoom, 9);
			}
		});
	}


	// Moves the player based on an int direction
	// 0 North 1 Northeast 2 East etc...
	public void movePlayer(int direction) {
		//playerObject.movePlayer(direction);
	}
}