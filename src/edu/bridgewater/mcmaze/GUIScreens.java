package edu.bridgewater.mcmaze;

import java.awt.Choice;
import java.util.ArrayList;

import com.sun.corba.se.spi.orbutil.fsm.Action;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The main class for the game application. this class handles the GUI and all
 * associated events
 *
 * @author Jason Laatz, Dennis Marchuk, Charles German, Alan Bowman
 *
 */
public class GUIScreens extends Application {
	private int rooms = 0;
	private ArrayList<Room> roomList = new ArrayList<>();
	private ArrayList<Edge> edgeList = new ArrayList<>();
	private TextArea outputText;
	private Room srcRoom, destRoom;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage myStage) throws Exception {
		myStage.setTitle("The McMaze - Title Screen");
		myStage.setResizable(false);

		// Nodes
		FlowPane rootNode = new FlowPane();
		rootNode.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		GridPane playNode = new GridPane();
		playNode.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		BorderPane makerNode = new BorderPane();
		makerNode
				.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		BorderPane makerNode2 = new BorderPane();
		makerNode2
				.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		BorderPane makerNode3 = new BorderPane();
		makerNode3
				.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

		// Scenes
		Scene mainScene = new Scene(rootNode, 1300, 900);
		Scene playScene = new Scene(playNode, 1300, 900);
		Scene makerScene = new Scene(makerNode, 1300, 900);
		Scene makerScene2 = new Scene(makerNode2, 1300, 900);
		Scene makerScene3 = new Scene(makerNode3, 1300, 900);
		myStage.setScene(mainScene);

		// Setting constraints for playNode's GridPane
		GridPane.setColumnIndex(playNode, 0);
		GridPane.setRowIndex(playNode, 0);
		GridPane.setColumnSpan(playNode, 13);
		GridPane.setRowSpan(playNode, 9);

		// **********************************************************************************************************************************
		// MENU BAR

		Menu sqlMenu = new Menu("mySQL");
		MenuItem login = new MenuItem("Login");
		sqlMenu.getItems().addAll(login);
		Menu loadMenu = new Menu("Load");
		MenuItem loadMap = new MenuItem("Load Map");
		loadMenu.getItems().addAll(loadMap);
		Menu mainScreenMenu = new Menu("Back");
		MenuItem mainScreenMenuItem = new MenuItem("Return to Main Screen");
		mainScreenMenu.getItems().addAll(mainScreenMenuItem);

		MenuBar menuBar = new MenuBar();
		menuBar.setPadding(new Insets(0, 1250, 0, 0));
		menuBar.getMenus().addAll(sqlMenu, loadMenu, mainScreenMenu);

		MenuBar menuBar2 = new MenuBar();
		menuBar2.setPadding(new Insets(0, 1100, 0, 0));
		menuBar2.getMenus().addAll(sqlMenu, loadMenu, mainScreenMenu);

		MenuBar menuBar3 = new MenuBar();
		menuBar3.setPadding(new Insets(0, 1100, 0, 0));
		menuBar3.getMenus().addAll(sqlMenu, loadMenu, mainScreenMenu);

		MenuBar menuBar4 = new MenuBar();
		menuBar4.setPadding(new Insets(0, 1100, 0, 0));
		menuBar4.getMenus().addAll(sqlMenu, loadMenu, mainScreenMenu);

		MenuBar menuBar5 = new MenuBar();
		menuBar5.setPadding(new Insets(0, 1100, 0, 0));
		menuBar5.getMenus().addAll(sqlMenu, loadMenu, mainScreenMenu);

		mainScreenMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				myStage.setScene(mainScene);
				myStage.setTitle("The McMaze");
			}
		});

		login.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				Stage credPopup = new Stage();
				credPopup.initModality(Modality.APPLICATION_MODAL);
				credPopup.initOwner(myStage);
				credPopup.setTitle("The McMaze - mySQL Credentials");
				credPopup.setResizable(false);

				Label credLabel = new Label("Please enter your mySQL login information:");
				Label usernameLabel = new Label("Username:");
				Label passwordLabel = new Label("Password:");
				TextField enterUsername = new TextField();
				PasswordField enterPassword = new PasswordField();
				Button submitCredButton = new Button("Login");

				credLabel.setStyle("-fx-font: 20 arial;");
				usernameLabel.setStyle("-fx-padding: 10; -fx-font: 16 arial;");
				passwordLabel.setStyle("-fx-padding: 10; -fx-font: 16 arial;");
				enterUsername.setStyle("-fx-padding: 8; -fx-font: 16 arial;");
				enterPassword.setStyle("-fx-padding: 8; -fx-font: 16 arial;");
				submitCredButton.setStyle("-fx-padding: 10; -fx-font: 20 arial;");

				HBox usernameHBox = new HBox(8);
				usernameHBox.getChildren().addAll(usernameLabel, enterUsername);
				usernameHBox.setAlignment(Pos.CENTER);
				HBox passwordHBox = new HBox(9);
				passwordHBox.getChildren().addAll(passwordLabel, enterPassword);
				passwordHBox.setAlignment(Pos.CENTER);
				VBox credVBox = new VBox(15);
				credVBox.getChildren().addAll(credLabel, usernameHBox, passwordHBox, submitCredButton);
				credVBox.setPadding(new Insets(30, 30, 30, 30));
				credVBox.setAlignment(Pos.CENTER);

				submitCredButton.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent ae) {
						// Code for submitting credentials to mySQL
						// Server*****************
						credPopup.close();
					}
				});

				Scene loginScene = new Scene(credVBox);
				credPopup.setScene(loginScene);
				credPopup.show();
			}
		});

		// **********************************************************************************************************************************
		// MENU BAR

		// **********************************************************************************************************************************
		// ROOT NODE

		// Initializing Buttons and Label for rootNode
		Label titleLabel = new Label("The McMaze");
		Button playButton = new Button("Play");
		Button makerButton = new Button("Map Maker");
		Button quitButton = new Button("Quit");

		// CSS for rootNode
		titleLabel.setStyle("-fx-font-size: 60 arial;");
		titleLabel.setTextFill(Color.BLUE);
		titleLabel.setPadding(new Insets(70, 1100, 70, 40));
		playButton.setStyle("-fx-padding: 20; -fx-font: 50 arial;");
		makerButton.setStyle("-fx-padding: 20; -fx-font: 50 arial;");
		quitButton.setStyle("-fx-padding: 20; -fx-font: 50 arial;");

		// Actions on Root Node
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

				exit.setOnAction(eh -> System.exit(0));

				cancel.setOnAction(eh -> exitPopup.close());

				Scene exitScene = new Scene(vbox);
				exitPopup.setScene(exitScene);
				exitPopup.show();
			}
		});

		// **********************************************************************************************************************************
		// ROOT NODE

		// **********************************************************************************************************************************
		// PLAY NODE

		// Initializing everything for playNode
		TextField userInput = new TextField();
		outputText = new TextArea();
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
		VBox centerVBox = new VBox(100);
		centerVBox.getChildren().addAll(middleArrowsVBox, helpButton);
		VBox inputOutputVBox = new VBox(100);
		inputOutputVBox.getChildren().addAll(outputText, userInput);

		// CSS for playNode
		userInput.setStyle("-fx-padding: 10; -fx-font: 20 arial;");
		userInput.setPadding(new Insets(0, 0, 0, 25));
		userInput.setPrefWidth(700);
		outputText.setStyle("-fx-padding: 5; -fx-font: 22 arial;");
		outputText.setEditable(false);
		outputText.setWrapText(true);
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

		// Directional button event listeners for
		// PlayNode**************************************************************************************************************************
		northButton.setOnAction(eh -> movePlayer(0));
		northEastButton.setOnAction(eh -> movePlayer(1));
		eastButton.setOnAction(eh -> movePlayer(2));
		southEastButton.setOnAction(eh -> movePlayer(3));
		southButton.setOnAction(eh -> movePlayer(4));
		southWestButton.setOnAction(eh -> movePlayer(5));
		westButton.setOnAction(eh -> movePlayer(6));
		northWestButton.setOnAction(eh -> movePlayer(7));
		upButton.setOnAction(eh -> movePlayer(8));
		downButton.setOnAction(eh -> movePlayer(9));

		// **********************************************************************************************************************************
		// PLAY NODE

		// **********************************************************************************************************************************
		// MAKER NODE 1

		// Initializing everything for makerNode1
		Label nameOfRoom = new Label("Name of Room : ");
		TextField roomName = new TextField();
		Label descOfRoom = new Label("Room Desription : ");
		TextArea roomDesc = new TextArea();
		Button addRoomButton = new Button("Finish this Room and Add Another Room");
		Button submitButton = new Button("Finish this Room and Continue");

		HBox nameHBox = new HBox(10);
		nameHBox.setPadding(new Insets(100, 100, 50, 100));
		nameHBox.setAlignment(Pos.TOP_CENTER);
		nameHBox.getChildren().addAll(nameOfRoom, roomName);
		VBox descVBox = new VBox(3);
		descVBox.getChildren().addAll(descOfRoom, roomDesc);
		VBox nameDescVBox = new VBox(20);
		nameDescVBox.getChildren().addAll(nameHBox, descVBox);
		HBox confirmRoomHBox = new HBox(70);
		confirmRoomHBox.setPadding(new Insets(0, 0, 100, 0));
		confirmRoomHBox.setAlignment(Pos.BASELINE_CENTER);
		confirmRoomHBox.getChildren().addAll(addRoomButton, submitButton);

		// Initializing everything for Dennis' code (not sure where this
		// *should* go)
		ChoiceBox<Room> selectRoomChoiceBox = new ChoiceBox<>();
		ChoiceBox<Room> destinationRoomChoiceBox = new ChoiceBox<>();
		ChoiceBox<Room> cbFirstRoom = new ChoiceBox<>();
		ChoiceBox<Room> cbSecondRoom = new ChoiceBox<>();
		ChoiceBox<Room> cbFinalRoom = new ChoiceBox<>();
		ChoiceBox<Room> cbBonusRoom = new ChoiceBox<>();
		// TODO make this code integrate nicely

		// CSS for makerNode1
		nameOfRoom.setStyle("-fx-font: 40 arial;");
		roomName.setStyle("-fx-padding: 20; -fx-font: 30 arial;");
		BorderPane.setAlignment(nameHBox, Pos.CENTER);
		descOfRoom.setStyle("-fx-font: 30 arial;");
		roomDesc.setStyle("-fx-padding: 20; -fx-font: 20 arial;");
		addRoomButton.setStyle("-fx-padding: 20; -fx-font: 25 arial;");
		submitButton.setStyle("-fx-padding: 20; -fx-font: 25 arial;");

		// Actions on makerNode1
		addRoomButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				// Code for storing data from the input areas
				myStage.setScene(makerScene);
				rooms++;
				String rmName = roomName.getText();
				String rmDescription = roomDesc.getText();
				roomDesc.setText(" ");
				roomName.setText(" ");
				roomList.add(new Room(rmName, rmDescription, false, false, false, rooms));
				selectRoomChoiceBox.getItems().add(rooms - 1, roomList.get(rooms - 1));
				destinationRoomChoiceBox.getItems().add(rooms - 1, roomList.get(rooms - 1));
				cbFirstRoom.getItems().add(rooms - 1, roomList.get(rooms - 1));
				cbSecondRoom.getItems().add(rooms - 1, roomList.get(rooms - 1));
				cbFinalRoom.getItems().add(rooms - 1, roomList.get(rooms - 1));
				cbBonusRoom.getItems().add(rooms - 1, roomList.get(rooms - 1));
			}
		});

		submitButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				// Code for storing final room data from input areas
				myStage.setScene(makerScene2);
				myStage.setTitle("The McMaze Maker - Room Positioning");
				rooms++;
				String rmName = roomName.getText();
				String rmDescription = roomDesc.getText();
				roomDesc.setText(" ");
				roomName.setText(" ");
				roomList.add(new Room(rmName, rmDescription, false, false, false, rooms)); // (String
																							// roomName,
																							// String
																							// roomDesc,
																							// boolean
																							// isStartingRoom,
																							// boolean
																							// isEndingRoom,
																							// boolean
																							// hasMcGregor,
																							// final
																							// int
																							// roomID)
				selectRoomChoiceBox.getItems().add(rooms - 1, roomList.get(rooms - 1));
				destinationRoomChoiceBox.getItems().add(rooms - 1, roomList.get(rooms - 1));
				cbFirstRoom.getItems().add(rooms - 1, roomList.get(rooms - 1));
				cbSecondRoom.getItems().add(rooms - 1, roomList.get(rooms - 1));
				cbFinalRoom.getItems().add(rooms - 1, roomList.get(rooms - 1));
				cbBonusRoom.getItems().add(rooms - 1, roomList.get(rooms - 1));
			}
		});

		// **********************************************************************************************************************************
		// MAKER NODE 1

		// **********************************************************************************************************************************
		// MAKER NODE 2

		// Initializing everything for makerNode2
		Label selectRoomLabel = new Label("Select a Room:");
		Label fillerText1 = new Label("is");
		Label fillerText2 = new Label("of");
		// TODO integrate dennis's code better
		// ChoiceBox<Room> selectRoomChoiceBox = new ChoiceBox<>();
		// ChoiceBox<Room> destinationRoomChoiceBox = new ChoiceBox<>();
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
		Button confirmRoomPositioningButton = new Button("Confirm this room position");
		Button nextNodeButton = new Button("Continue to next screen");
		TextArea outputRooms = new TextArea();
		outputRooms.setStyle("-fx-padding: 5; -fx-font: 22 arial;");
		outputRooms.setEditable(false);
		outputRooms.setWrapText(true);

		VBox leftSideVBox = new VBox(50);
		leftSideVBox.getChildren().addAll(northWestButton2, westButton2, southWestButton2);
		VBox middleVBox = new VBox(150);
		middleVBox.getChildren().addAll(northButton2, southButton2);
		VBox rightSideVBox = new VBox(50);
		rightSideVBox.getChildren().addAll(northEastButton2, eastButton2, southEastButton2);
		HBox allDirectionalButtonsHBox = new HBox(15);
		allDirectionalButtonsHBox.getChildren().addAll(leftSideVBox, middleVBox, rightSideVBox);
		HBox allUpHBox = new HBox(50);
		allUpHBox.getChildren().addAll(upButton2, allDirectionalButtonsHBox);
		HBox allUpDownHBox = new HBox(50);
		allUpDownHBox.getChildren().addAll(allUpHBox, downButton2);
		VBox finalVBox = new VBox(30);
		finalVBox.getChildren().addAll(selectRoomLabel, selectRoomChoiceBox, fillerText1, allUpDownHBox, fillerText2,
				destinationRoomChoiceBox, confirmRoomPositioningButton, nextNodeButton);

		// CSS for makerNode2
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
		confirmRoomPositioningButton.setStyle("-fx-padding: 10; -fx-font: 24 arial;");
		nextNodeButton.setStyle("-fx-padding: 10; -fx-font: 24 arial;");
		allDirectionalButtonsHBox.setAlignment(Pos.CENTER);
		allUpHBox.setAlignment(Pos.CENTER);
		allUpDownHBox.setAlignment(Pos.CENTER);
		finalVBox.setAlignment(Pos.CENTER);
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

		// Actions for buttons on MakerScreen2
		confirmRoomPositioningButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				srcRoom = selectRoomChoiceBox.getValue();
				destRoom = destinationRoomChoiceBox.getValue();
				selectRoomChoiceBox.setValue(null);
				destinationRoomChoiceBox.setValue(null);
			}
		});
		// TODO setHasMcGregor(true) somewhere

		nextNodeButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				// Code for storing all room positioning
				myStage.setScene(makerScene3);
				myStage.setTitle("The McMaze Maker - Event Rooms");
			}
		});

		// 0 north 8 up 9 down
		// set north of also means set south of...
		northButton2.setOnAction(eh -> edgeList.add(new Edge(srcRoom, destRoom, 0)));
		northEastButton2.setOnAction(eh -> edgeList.add(new Edge(srcRoom, destRoom, 1)));
		eastButton2.setOnAction(eh -> edgeList.add(new Edge(srcRoom, destRoom, 2)));
		southEastButton2.setOnAction(eh -> edgeList.add(new Edge(srcRoom, destRoom, 3)));
		southButton2.setOnAction(eh -> edgeList.add(new Edge(srcRoom, destRoom, 4)));
		southWestButton2.setOnAction(eh -> edgeList.add(new Edge(srcRoom, destRoom, 5)));
		westButton2.setOnAction(eh -> edgeList.add(new Edge(srcRoom, destRoom, 6)));
		northWestButton2.setOnAction(eh -> edgeList.add(new Edge(srcRoom, destRoom, 7)));
		upButton2.setOnAction(eh -> edgeList.add(new Edge(srcRoom, destRoom, 8)));
		downButton2.setOnAction(eh -> edgeList.add(new Edge(srcRoom, destRoom, 9)));

		// **********************************************************************************************************************************
		// MAKER NODE 2

		// **********************************************************************************************************************************
		// MAKER NODE 3

		// Initializing everything for MakerNode3
		Label whichRoomLabel = new Label("Which Room is...");
		Label theFirstRoomLabel = new Label("the first room:");
		// TODO integrate dennis's code better
		// ChoiceBox<Room> cbFirstRoom = new ChoiceBox<>();
		HBox firstRoomHBox = new HBox(30);
		firstRoomHBox.getChildren().addAll(theFirstRoomLabel, cbFirstRoom);
		CheckBox checkBox = new CheckBox("Enable Easter Egg Room");
		checkBox.setIndeterminate(false);
		Label theBonusRoomLabel = new Label("the bonus room:");
		// ChoiceBox<Room> cbBonusRoom = new ChoiceBox<>();
		HBox bonusRoomHBox = new HBox(30);
		bonusRoomHBox.getChildren().addAll(theBonusRoomLabel, cbBonusRoom);
		Label theFinalRoomLabel = new Label("the final room:");
		// ChoiceBox<Room> cbFinalRoom = new ChoiceBox<>();
		HBox finalRoomHBox = new HBox(30);
		finalRoomHBox.getChildren().addAll(theFinalRoomLabel, cbFinalRoom);
		Button confirmFinishedMapButton = new Button("Confirm and Name your Custom Map!");
		VBox allMakerNode3ChoicesVBox = new VBox(40);
		allMakerNode3ChoicesVBox.getChildren().addAll(whichRoomLabel, firstRoomHBox, checkBox, bonusRoomHBox,
				finalRoomHBox, confirmFinishedMapButton);

		// CSS for MakerNode3
		firstRoomHBox.setAlignment(Pos.CENTER);
		bonusRoomHBox.setAlignment(Pos.CENTER);
		finalRoomHBox.setAlignment(Pos.CENTER);
		allMakerNode3ChoicesVBox.setAlignment(Pos.CENTER);
		whichRoomLabel.setStyle("-fx-font: 24 arial;");
		theFirstRoomLabel.setStyle("-fx-font: 20 arial;");
		cbFirstRoom.setStyle("-fx-font: 20 arial;");
		checkBox.setStyle("-fx-padding: 2; -fx-font: 18 arial;");
		theBonusRoomLabel.setStyle("-fx-font: 20 arial;");
		cbBonusRoom.setStyle("-fx-padding: 2; -fx-font: 20 arial;");
		theFinalRoomLabel.setStyle("-fx-padding: 2; -fx-font: 20 arial;");
		confirmFinishedMapButton.setStyle("-fx-padding: 8; -fx-font: 24 arial;");

		// Actions for buttons on MakerScreen3
		confirmFinishedMapButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				Stage nameNewMaze = new Stage();
				nameNewMaze.initModality(Modality.APPLICATION_MODAL);
				nameNewMaze.initOwner(myStage);
				nameNewMaze.setTitle("The McMaze Maker- Name and Save your Room");
				nameNewMaze.setResizable(false);

				Label newNameLabel = new Label("Please name your newly created maze:");
				TextField newNameTextField = new TextField();
				Button saveAndFinishButton = new Button("Save and Finish");

				newNameLabel.setStyle("-fx-font: 18 arial;");
				newNameTextField.setStyle("-fx-font: 16 arial;");
				saveAndFinishButton.setStyle("-fx-padding: 9; -fx-font: 16 arial;");

				VBox newNameVBox = new VBox(15);
				newNameVBox.getChildren().addAll(newNameLabel, newNameTextField, saveAndFinishButton);
				newNameVBox.setPadding(new Insets(30, 30, 30, 30));
				newNameVBox.setAlignment(Pos.CENTER);

				Scene newNameScene = new Scene(newNameVBox);
				nameNewMaze.setScene(newNameScene);
				nameNewMaze.show();

				saveAndFinishButton.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent ae) {
						// TODO write to file
						// send room/edge arrays somewhere
						// make map object
						// map.save()
						nameNewMaze.close();
						myStage.setScene(mainScene);
						myStage.setTitle("The McMaze - Title Screen");
					}
				});
			}
		});

		// **********************************************************************************************************************************
		// MAKER NODE 3

		// Setting everything onto the nodes to be displayed
		rootNode.getChildren().addAll(menuBar, titleLabel, introVBox);
		playNode.add(menuBar2, 0, 0, 13, 1);
		playNode.add(inputOutputVBox, 0, 2);
		playNode.add(upButton, 9, 2);
		playNode.add(leftArrowsVBox, 9, 3);
		playNode.add(centerVBox, 10, 3);
		playNode.add(downButton, 11, 2);
		playNode.add(rightArrowsVBox, 11, 3);
		makerNode.setTop(menuBar3);
		makerNode.setCenter(nameDescVBox);
		makerNode.setBottom(confirmRoomHBox);
		makerNode2.setTop(menuBar4);
		makerNode2.setCenter(finalVBox);
		makerNode3.setTop(menuBar5);
		makerNode3.setCenter(allMakerNode3ChoicesVBox);
		myStage.show();

	}

	// **********************************************************************************************************************************
	// METHODS
	// Moves the player based on an int direction
	// 0 North 1 Northeast 2 East etc...
	public void movePlayer(int direction) {
		// playerObject.movePlayer(direction);
		// TODO implement player moving logic
	}

	public void print(String text) {
		outputText.appendText(text + '\n');
	}

	// **********************************************************************************************************************************
	// METHODS
}
