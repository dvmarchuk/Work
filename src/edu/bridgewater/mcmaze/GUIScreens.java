package edu.bridgewater.mcmaze;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
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
	private static TextArea outputText, outputRooms;
	private static String outputRoomInfo;
	private static boolean edgeConfirmed = true;
	static Player p; // TODO implement player stuff
	// private Room srcRoom, destRoom;

	public static void main(String[] args) {
		launch(args);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javafx.application.Application#stop()
	 */
	@Override
	public void stop() {
		try {
			DBInterface.terminateConnection();
		} catch (SQLException e) {
			System.err.println("=== PROBLEM TERMINATING CONNECTION ===");
			e.printStackTrace();
			System.err.println("=== END TERMINATION ERROR ===");
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage myStage) throws Exception {
		myStage.setTitle("The McMaze - Title Screen");
		myStage.setResizable(false);

		// Nodes
		FlowPane rootNode = new FlowPane();
		rootNode.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		BorderPane playNode = new BorderPane();
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

		Menu sqlMenu2 = new Menu("mySQL");
		MenuItem login2 = new MenuItem("Login");
		sqlMenu2.getItems().addAll(login2);
		Menu loadMenu2 = new Menu("Load");
		MenuItem loadMap2 = new MenuItem("Load Map");
		loadMenu2.getItems().addAll(loadMap2);
		Menu mainScreenMenu2 = new Menu("Back");
		MenuItem mainScreenMenuItem2 = new MenuItem("Return to Main Screen");
		mainScreenMenu2.getItems().addAll(mainScreenMenuItem2);

		Menu sqlMenu3 = new Menu("mySQL");
		MenuItem login3 = new MenuItem("Login");
		sqlMenu3.getItems().addAll(login3);
		Menu loadMenu3 = new Menu("Load");
		MenuItem loadMap3 = new MenuItem("Load Map");
		loadMenu3.getItems().addAll(loadMap3);
		Menu mainScreenMenu3 = new Menu("Back");
		MenuItem mainScreenMenuItem3 = new MenuItem("Return to Main Screen");
		mainScreenMenu3.getItems().addAll(mainScreenMenuItem3);

		Menu sqlMenu4 = new Menu("mySQL");
		MenuItem login4 = new MenuItem("Login");
		sqlMenu4.getItems().addAll(login4);
		Menu loadMenu4 = new Menu("Load");
		MenuItem loadMap4 = new MenuItem("Load Map");
		loadMenu4.getItems().addAll(loadMap4);
		Menu mainScreenMenu4 = new Menu("Back");
		MenuItem mainScreenMenuItem4 = new MenuItem("Return to Main Screen");
		mainScreenMenu4.getItems().addAll(mainScreenMenuItem4);

		Menu sqlMenu5 = new Menu("mySQL");
		MenuItem login5 = new MenuItem("Login");
		sqlMenu5.getItems().addAll(login5);
		Menu loadMenu5 = new Menu("Load");
		MenuItem loadMap5 = new MenuItem("Load Map");
		loadMenu5.getItems().addAll(loadMap5);
		Menu mainScreenMenu5 = new Menu("Back");
		MenuItem mainScreenMenuItem5 = new MenuItem("Return to Main Screen");
		mainScreenMenu5.getItems().addAll(mainScreenMenuItem5);

		MenuBar menuBar = new MenuBar();
		menuBar.setPadding(new Insets(0, 1250, 0, 0));
		menuBar.getMenus().addAll(sqlMenu, loadMenu, mainScreenMenu);

		MenuBar menuBar2 = new MenuBar();
		menuBar2.getMenus().addAll(sqlMenu2, loadMenu2, mainScreenMenu2);

		MenuBar menuBar3 = new MenuBar();
		menuBar3.setPadding(new Insets(0, 1100, 0, 0));
		menuBar3.getMenus().addAll(sqlMenu3, loadMenu3, mainScreenMenu3);

		MenuBar menuBar4 = new MenuBar();
		menuBar4.setPadding(new Insets(0, 1100, 0, 0));
		menuBar4.getMenus().addAll(sqlMenu4, loadMenu4, mainScreenMenu4);

		MenuBar menuBar5 = new MenuBar();
		menuBar5.setPadding(new Insets(0, 1100, 0, 0));
		menuBar5.getMenus().addAll(sqlMenu5, loadMenu5, mainScreenMenu5);

		mainScreenMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				myStage.setScene(mainScene);
				myStage.setTitle("The McMaze - Title Screen");
			}
		});

		mainScreenMenuItem2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				myStage.setScene(mainScene);
				myStage.setTitle("The McMaze - Title Screen");
			}
		});

		mainScreenMenuItem3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				myStage.setScene(mainScene);
				myStage.setTitle("The McMaze - Title Screen");
			}
		});

		mainScreenMenuItem4.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				myStage.setScene(mainScene);
				myStage.setTitle("The McMaze - Title Screen");
			}
		});

		mainScreenMenuItem5.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				myStage.setScene(mainScene);
				myStage.setTitle("The McMaze - Title Screen");
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
						DBInterface.setAdminCreds(enterUsername.getText(), enterPassword.getText());
						try {
							DBInterface.establishConnection();
						} catch (ClassNotFoundException | SQLException e) {
							System.err.print("=== PROBLEM ESTABLISHING SQL CONNECTION ===");
							e.printStackTrace();
							System.err.println("=== END SQL CONNECTION PROBLEM ===");
						}
						credPopup.close();
						credPopup.close();
					}
				});

				Scene loginScene = new Scene(credVBox);
				credPopup.setScene(loginScene);
				credPopup.show();
			}
		});

		login2.setOnAction(new EventHandler<ActionEvent>() {
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
						DBInterface.setAdminCreds(enterUsername.getText(), enterPassword.getText());
						try {
							DBInterface.establishConnection();
						} catch (ClassNotFoundException | SQLException e) {
							System.err.print("=== PROBLEM ESTABLISHING SQL CONNECTION ===");
							e.printStackTrace();
							System.err.println("=== END SQL CONNECTION PROBLEM ===");
						}
						credPopup.close();
					}
				});

				Scene loginScene = new Scene(credVBox);
				credPopup.setScene(loginScene);
				credPopup.show();
			}
		});

		login3.setOnAction(new EventHandler<ActionEvent>() {
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
						DBInterface.setAdminCreds(enterUsername.getText(), enterPassword.getText());
						try {
							DBInterface.establishConnection();
						} catch (ClassNotFoundException | SQLException e) {
							System.err.print("=== PROBLEM ESTABLISHING SQL CONNECTION ===");
							e.printStackTrace();
							System.err.println("=== END SQL CONNECTION PROBLEM ===");
						}
						credPopup.close();
					}
				});

				Scene loginScene = new Scene(credVBox);
				credPopup.setScene(loginScene);
				credPopup.show();
			}
		});

		login4.setOnAction(new EventHandler<ActionEvent>() {
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
						DBInterface.setAdminCreds(enterUsername.getText(), enterPassword.getText());
						try {
							DBInterface.establishConnection();
						} catch (ClassNotFoundException | SQLException e) {
							System.err.print("=== PROBLEM ESTABLISHING SQL CONNECTION ===");
							e.printStackTrace();
							System.err.println("=== END SQL CONNECTION PROBLEM ===");
						}
						credPopup.close();
					}
				});

				Scene loginScene = new Scene(credVBox);
				credPopup.setScene(loginScene);
				credPopup.show();
			}
		});

		login5.setOnAction(new EventHandler<ActionEvent>() {
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
						DBInterface.setAdminCreds(enterUsername.getText(), enterPassword.getText());
						try {
							DBInterface.establishConnection();
						} catch (ClassNotFoundException | SQLException e) {
							System.err.print("=== PROBLEM ESTABLISHING SQL CONNECTION ===");
							e.printStackTrace();
							System.err.println("=== END SQL CONNECTION PROBLEM ===");
						}
						credPopup.close();
					}
				});

				Scene loginScene = new Scene(credVBox);
				credPopup.setScene(loginScene);
				credPopup.show();
			}
		});

		loadMap.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				File f = new FileChooser().showOpenDialog(myStage);
				try {
					DBInterface.loadMap(f);
				} catch (ClassNotFoundException | IOException | SQLException e) {
					System.err.println("=== PROBLEM LOADING MAP ===");
					e.printStackTrace();
					System.err.println("=== END MAP PROBLEM ===");
				}
			}
		});

		loadMap2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				File f = new FileChooser().showOpenDialog(myStage);
				try {
					DBInterface.loadMap(f);
				} catch (ClassNotFoundException | IOException | SQLException e) {
					System.err.println("=== PROBLEM LOADING MAP ===");
					e.printStackTrace();
					System.err.println("=== END MAP PROBLEM ===");
				}
			}
		});

		loadMap3.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				File f = new FileChooser().showOpenDialog(myStage);
				try {
					DBInterface.loadMap(f);
				} catch (ClassNotFoundException | IOException | SQLException e) {
					System.err.println("=== PROBLEM LOADING MAP ===");
					e.printStackTrace();
					System.err.println("=== END MAP PROBLEM ===");
				}
			}
		});

		loadMap4.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				File f = new FileChooser().showOpenDialog(myStage);
				try {
					DBInterface.loadMap(f);
				} catch (ClassNotFoundException | IOException | SQLException e) {
					System.err.println("=== PROBLEM LOADING MAP ===");
					e.printStackTrace();
					System.err.println("=== END MAP PROBLEM ===");
				}
			}
		});

		loadMap5.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				File f = new FileChooser().showOpenDialog(myStage);
				try {
					DBInterface.loadMap(f);
				} catch (ClassNotFoundException | IOException | SQLException e) {
					System.err.println("=== PROBLEM LOADING MAP ===");
					e.printStackTrace();
					System.err.println("=== END MAP PROBLEM ===");
				}
			}
		});

		// **********************************************************************************************************************************
		// MENU BAR

		// **********************************************************************************************************************************
		// ROOT NODE

		// Initializing Buttons and Label for rootNode
		Text titleLabel = new Text("The McMaze");
		Button playButton = new Button("Play");
		Button makerButton = new Button("Map Maker");
		Button quitButton = new Button("Quit");
		DropShadow dropShadow = new DropShadow();

		// CSS for rootNode
		titleLabel.setStyle("-fx-font-size: 90 arial;");
		playButton.setStyle("-fx-padding: 20; -fx-font: 60 arial;");
		makerButton.setStyle("-fx-padding: 20; -fx-font: 60 arial;");
		quitButton.setStyle("-fx-padding: 20; -fx-font: 60 arial;");

		VBox introVBox = new VBox(60);
		introVBox.setPadding(new Insets(130, 1100, 50, 60));
		introVBox.getChildren().addAll(titleLabel, playButton, makerButton, quitButton);

		dropShadow.setOffsetY(3.5);
		dropShadow.setColor(Color.color(.4, .4, .4));
		titleLabel.setEffect(dropShadow);
		titleLabel.setCache(true);
		titleLabel.setFill(Color.BLUE);
		titleLabel.setFont(Font.font(null, FontWeight.BOLD, 32));
		titleLabel.setTranslateX(40);
		titleLabel.setTranslateY(70);

		// Actions on Root Node
		playButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				myStage.setScene(playScene);
				myStage.setTitle("The McMaze");
				// TODO here's the play button
				try {
					p = new Player(DBInterface.getStartingRoom());
				} catch (SQLException e) {
					System.err.println("=== PROBLEM CREATING PLAYER ===");
					e.printStackTrace();
					System.err.println("=== END PLAYER PROBLEM ===");
				}

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

				exit.setOnAction(eventHandler -> System.exit(0));

				cancel.setOnAction(eventHandler -> exitPopup.close());

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
		Label directionalSeclectorLabel = new Label("Move in a Direction:");
		Label helpLabel = new Label("Stuck? Get Help!");

		HBox upDownHBox = new HBox(50);
		upDownHBox.setPadding(new Insets(0, 0, 100, 36));
		upDownHBox.getChildren().addAll(upButton, downButton);
		VBox upDownLabeledVBox = new VBox(50);
		upDownLabeledVBox.getChildren().addAll(directionalSeclectorLabel, upDownHBox);
		VBox leftArrowsVBox = new VBox(60);
		leftArrowsVBox.setPadding(new Insets(0, 15, 0, 0));
		leftArrowsVBox.getChildren().addAll(northWestButton, westButton, southWestButton);
		VBox middleArrowsVBox = new VBox(170);
		middleArrowsVBox.getChildren().addAll(northButton, southButton);
		VBox rightArrowsVBox = new VBox(60);
		rightArrowsVBox.setPadding(new Insets(0, 0, 0, 15));
		rightArrowsVBox.getChildren().addAll(northEastButton, eastButton, southEastButton);
		VBox helpButtonVBox = new VBox(20);
		helpButtonVBox.getChildren().addAll(helpLabel, helpButton);
		VBox centerVBox = new VBox(100);
		centerVBox.getChildren().addAll(middleArrowsVBox, helpButtonVBox);
		HBox allArrowsHBox = new HBox(20);
		allArrowsHBox.getChildren().addAll(leftArrowsVBox, centerVBox, rightArrowsVBox);
		VBox fullRightPaneVBox = new VBox(20);
		fullRightPaneVBox.setPadding(new Insets(150, 22, 0, 0));
		fullRightPaneVBox.getChildren().addAll(upDownLabeledVBox, allArrowsHBox);
		VBox inputOutputVBox = new VBox(20);
		inputOutputVBox.getChildren().addAll(outputText, userInput);

		// CSS for playNode
		Platform.runLater(new Runnable() {
			// Sets the player's cursor automatically on the TextField
			@Override
			public void run() {
				userInput.requestFocus();
			}
		});
		userInput.setStyle(
				"-fx-padding: 10; -fx-font: 20 arial;-fx-background-color: transparent, black, transparent, beige;");
		userInput.setPadding(new Insets(0, 0, 0, 25));
		userInput.setPrefWidth(700);
		outputText.setStyle(
				"-fx-padding: 5; -fx-font: 22 arial;-fx-background-color: transparent, gray, transparent, grey;");
		outputText.setPrefRowCount(30);
		outputText.setEditable(false);
		outputText.setWrapText(true);
		outputText.setText(""); // TODO is this necessary?
		northWestButton.setStyle("-fx-padding: 5; -fx-font: 14 arial; -fx-base: #a7f1e9;");
		northButton.setStyle("-fx-padding: 5; -fx-font: 14 arial; -fx-base: #a7f1e9;");
		northEastButton.setStyle("-fx-padding: 5; -fx-font: 14 arial; -fx-base: #a7f1e9;");
		eastButton.setStyle("-fx-padding: 5; -fx-font: 14 arial; -fx-base: #a7f1e9;");
		westButton.setStyle("-fx-padding: 5; -fx-font: 14 arial; -fx-base: #a7f1e9;");
		southEastButton.setStyle("-fx-padding: 5; -fx-font: 14 arial; -fx-base: #a7f1e9;");
		southButton.setStyle("-fx-padding: 5; -fx-font: 14 arial; -fx-base: #a7f1e9;");
		southWestButton.setStyle("-fx-padding: 5; -fx-font: 14 arial; -fx-base: #a7f1e9;");
		westButton.setStyle("-fx-padding: 5; -fx-font: 14 arial; -fx-base: #a7f1e9;");
		upButton.setStyle("-fx-padding: 5; -fx-font: 14 arial; -fx-base: #a7f1e9;");
		downButton.setStyle("-fx-padding: 5; -fx-font: 14 arial; -fx-base: #a7f1e9;");
		helpButton.setStyle("-fx-padding: 5; -fx-font: 14 arial; -fx-base: #00e64d;");
		helpButton.setTextFill(Color.DEEPPINK);
		northWestButton.setMinSize(100, 50);
		northButton.setMinSize(100, 50);
		northEastButton.setMinSize(100, 50);
		eastButton.setMinSize(100, 50);
		southEastButton.setMinSize(100, 50);
		southButton.setMinSize(100, 50);
		southWestButton.setMinSize(100, 50);
		westButton.setMinSize(100, 50);
		upButton.setMinSize(125, 62);
		downButton.setMinSize(125, 62);
		helpButton.setMinSize(50, 25);
		directionalSeclectorLabel.setStyle("-fx-font: 35 arial");
		directionalSeclectorLabel.setAlignment(Pos.CENTER);
		helpLabel.setStyle("-fx-font: 10 arial");
		helpLabel.setAlignment(Pos.CENTER);
		helpButtonVBox.setAlignment(Pos.CENTER);
		upDownLabeledVBox.setAlignment(Pos.CENTER);

		// Actions for Buttons on PlayNode
		helpButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				print("Press a directional button to travel in that direction or type in \"move\" followed by the direction you want to travel. "
						+ "Try to make it to the exit before McGregor catches you! WARNING: He's not a vegetarian.");
				Platform.runLater(new Runnable() {
					// Sets the player's cursor automatically on the TextField
					@Override
					public void run() {
						userInput.requestFocus();
					}
				});
			}
		});
		northButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				movePlayer(0);
				// print("You attempt to travel north.");
				Platform.runLater(new Runnable() {
					// Sets the player's cursor automatically on the TextField
					@Override
					public void run() {
						userInput.requestFocus();
					}
				});
			}
		});

		northEastButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				movePlayer(1);
				// print("You attempt to travel north-east.");
				Platform.runLater(new Runnable() {
					// Sets the player's cursor automatically on the TextField
					@Override
					public void run() {
						userInput.requestFocus();
					}
				});
			}
		});

		eastButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				movePlayer(2);
				// print("You attempt to travel east.");
				Platform.runLater(new Runnable() {
					// Sets the player's cursor automatically on the TextField
					@Override
					public void run() {
						userInput.requestFocus();
					}
				});
			}
		});

		southEastButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				movePlayer(3);
				// print("You attempt to travel south-east.");
				Platform.runLater(new Runnable() {
					// Sets the player's cursor automatically on the TextField
					@Override
					public void run() {
						userInput.requestFocus();
					}
				});
			}
		});

		southButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				movePlayer(4);
				// print("You attempt to travel south.");
				Platform.runLater(new Runnable() {
					// Sets the player's cursor automatically on the TextField
					@Override
					public void run() {
						userInput.requestFocus();
					}
				});
			}
		});

		southWestButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				movePlayer(5);
				// print("You attempt to travel south-west.");
				Platform.runLater(new Runnable() {
					// Sets the player's cursor automatically on the TextField
					@Override
					public void run() {
						userInput.requestFocus();
					}
				});
			}
		});

		westButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				movePlayer(6);
				// print("You attempt to travel west.");
				Platform.runLater(new Runnable() {
					// Sets the player's cursor automatically on the TextField
					@Override
					public void run() {
						userInput.requestFocus();
					}
				});
			}
		});

		northWestButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				movePlayer(7);
				// print("You attempt to travel north-west.");
				Platform.runLater(new Runnable() {
					// Sets the player's cursor automatically on the TextField
					@Override
					public void run() {
						userInput.requestFocus();
					}
				});
			}
		});

		upButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				movePlayer(8);
				// print("You attempt to travel up.");
				Platform.runLater(new Runnable() {
					// Sets the player's cursor automatically on the TextField
					@Override
					public void run() {
						userInput.requestFocus();
					}
				});
			}
		});

		downButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				movePlayer(9);
				// print("You attempt to travel down.");
				Platform.runLater(new Runnable() {
					// Sets the player's cursor automatically on the TextField
					@Override
					public void run() {
						userInput.requestFocus();
					}
				});
			}
		});

		userInput.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				print(userInput.getText());
				userInput.clear();
				Platform.runLater(new Runnable() {
					// Sets the player's cursor automatically on the TextField
					@Override
					public void run() {
						userInput.requestFocus();
					}
				});
			}
		});

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
		confirmRoomHBox.setPadding(new Insets(50, 0, 50, 0));
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

		// CSS for makerNode1
		nameOfRoom.setStyle("-fx-font: 45 arial;");
		roomName.setStyle(
				"-fx-padding: 20; -fx-font: 30 arial; -fx-background-color: transparent, black, transparent, beige;");
		roomName.setPrefWidth(550);
		BorderPane.setAlignment(nameHBox, Pos.CENTER);
		descOfRoom.setStyle("-fx-font: 30 arial;");
		descOfRoom.setPadding(new Insets(0, 0, 0, 20));
		roomDesc.setStyle(
				"-fx-padding: 20; -fx-font: 20 arial; -fx-background-color: transparent, black, transparent, gray; text-area-background: beige; ");
		roomDesc.setPrefRowCount(20);
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
				roomDesc.setText("");
				roomName.setText("");
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
				roomDesc.setText("");
				roomName.setText("");
				roomList.add(new Room(rmName, rmDescription, false, false, false, rooms));
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
		ToggleButton northWestButton2 = new ToggleButton("North-West");
		ToggleButton northButton2 = new ToggleButton("North");
		ToggleButton northEastButton2 = new ToggleButton("North-East");
		ToggleButton eastButton2 = new ToggleButton("East");
		ToggleButton southEastButton2 = new ToggleButton("South-East");
		ToggleButton southButton2 = new ToggleButton("South");
		ToggleButton southWestButton2 = new ToggleButton("South-West");
		ToggleButton westButton2 = new ToggleButton("West");
		ToggleButton upButton2 = new ToggleButton("Above");
		ToggleButton downButton2 = new ToggleButton("Below");
		Button confirmRoomPositioningButton = new Button("Confirm this room position");
		Button nextNodeButton = new Button("Continue to next screen");
		outputRooms = new TextArea();
		ToggleGroup toggleGroup = new ToggleGroup();
		northWestButton2.setToggleGroup(toggleGroup);
		northButton2.setToggleGroup(toggleGroup);
		northEastButton2.setToggleGroup(toggleGroup);
		eastButton2.setToggleGroup(toggleGroup);
		southEastButton2.setToggleGroup(toggleGroup);
		southButton2.setToggleGroup(toggleGroup);
		southWestButton2.setToggleGroup(toggleGroup);
		westButton2.setToggleGroup(toggleGroup);
		upButton2.setToggleGroup(toggleGroup);
		downButton2.setToggleGroup(toggleGroup);

		VBox leftSideVBox = new VBox(50);
		leftSideVBox.getChildren().addAll(northWestButton2, westButton2, southWestButton2);
		VBox middleVBox = new VBox(150);
		middleVBox.getChildren().addAll(northButton2, southButton2);
		VBox rightSideVBox = new VBox(50);
		rightSideVBox.getChildren().addAll(northEastButton2, eastButton2, southEastButton2);
		HBox allDirectionalButtonsHBox = new HBox(35);
		allDirectionalButtonsHBox.getChildren().addAll(leftSideVBox, middleVBox, rightSideVBox);
		HBox allUpHBox = new HBox(50);
		allUpHBox.getChildren().addAll(upButton2, allDirectionalButtonsHBox);
		HBox allUpDownHBox = new HBox(50);
		allUpDownHBox.getChildren().addAll(allUpHBox, downButton2);
		VBox finalVBox = new VBox(30);
		finalVBox.getChildren().addAll(selectRoomLabel, selectRoomChoiceBox, fillerText1, allUpDownHBox, fillerText2,
				destinationRoomChoiceBox, confirmRoomPositioningButton, nextNodeButton);

		// CSS for makerNode2
		selectRoomLabel.setStyle("-fx-font: 45 arial;");
		fillerText1.setStyle("-fx-font: 35 arial;");
		fillerText2.setStyle("-fx-font: 35 arial;");
		selectRoomChoiceBox.setStyle("-fx-padding: 4; -fx-font: 16 arial;");
		destinationRoomChoiceBox.setStyle("-fx-padding: 4; -fx-font: 16 arial;");
		northWestButton2.setStyle("-fx-padding: 8; -fx-font: 18 arial;");
		northButton2.setStyle("-fx-padding: 8; -fx-font: 18 arial;");
		northEastButton2.setStyle("-fx-padding: 8; -fx-font: 18 arial;");
		eastButton2.setStyle("-fx-padding: 8; -fx-font: 18 arial;");
		southEastButton2.setStyle("-fx-padding: 8; -fx-font: 18 arial;");
		southButton2.setStyle("-fx-padding: 8; -fx-font: 18 arial;");
		southWestButton2.setStyle("-fx-padding: 8; -fx-font: 18 arial;");
		westButton2.setStyle("-fx-padding: 8; -fx-font: 18 arial;");
		upButton2.setStyle("-fx-padding: 8; -fx-font: 18 arial;");
		downButton2.setStyle("-fx-padding: 8; -fx-font: 18 arial;");
		confirmRoomPositioningButton.setStyle("-fx-padding: 10; -fx-font: 28 arial;");
		nextNodeButton.setStyle("-fx-padding: 10; -fx-font: 24 arial;");
		allDirectionalButtonsHBox.setAlignment(Pos.CENTER);
		allUpHBox.setAlignment(Pos.CENTER);
		allUpDownHBox.setAlignment(Pos.CENTER);
		finalVBox.setAlignment(Pos.CENTER);
		selectRoomChoiceBox.setMinSize(150, 50);
		selectRoomChoiceBox.setPrefWidth(550);
		destinationRoomChoiceBox.setMinSize(150, 50);
		destinationRoomChoiceBox.setPrefWidth(550);
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
		outputRooms.setStyle(
				"-fx-padding: 5; -fx-font: 22 arial; -fx-background-color: transparent, gray, transparent, gray;");
		outputRooms.setEditable(false);
		outputRooms.setWrapText(true);
		outputRooms.setPrefSize(500, 500);

		// Actions for buttons on MakerScreen2
		confirmRoomPositioningButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				selectRoomChoiceBox.setValue(null);
				destinationRoomChoiceBox.setValue(null);
				confirmOutputRoomInfo();
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
		northButton2.setOnAction(eventHandler -> updateEdgeList(
				new Edge(selectRoomChoiceBox.getValue(), destinationRoomChoiceBox.getValue(), 0)));
		northEastButton2.setOnAction(eventHandler -> updateEdgeList(
				new Edge(selectRoomChoiceBox.getValue(), destinationRoomChoiceBox.getValue(), 1)));
		eastButton2.setOnAction(eventHandler -> updateEdgeList(
				new Edge(selectRoomChoiceBox.getValue(), destinationRoomChoiceBox.getValue(), 2)));
		southEastButton2.setOnAction(eventHandler -> updateEdgeList(
				new Edge(selectRoomChoiceBox.getValue(), destinationRoomChoiceBox.getValue(), 3)));
		southButton2.setOnAction(eventHandler -> updateEdgeList(
				new Edge(selectRoomChoiceBox.getValue(), destinationRoomChoiceBox.getValue(), 4)));
		southWestButton2.setOnAction(eventHandler -> updateEdgeList(
				new Edge(selectRoomChoiceBox.getValue(), destinationRoomChoiceBox.getValue(), 5)));
		westButton2.setOnAction(eventHandler -> updateEdgeList(
				new Edge(selectRoomChoiceBox.getValue(), destinationRoomChoiceBox.getValue(), 6)));
		northWestButton2.setOnAction(eventHandler -> updateEdgeList(
				new Edge(selectRoomChoiceBox.getValue(), destinationRoomChoiceBox.getValue(), 7)));
		upButton2.setOnAction(eventHandler -> updateEdgeList(
				new Edge(selectRoomChoiceBox.getValue(), destinationRoomChoiceBox.getValue(), 8)));
		downButton2.setOnAction(eventHandler -> updateEdgeList(
				new Edge(selectRoomChoiceBox.getValue(), destinationRoomChoiceBox.getValue(), 9)));

		// **********************************************************************************************************************************
		// MAKER NODE 2

		// **********************************************************************************************************************************
		// MAKER NODE 3

		// Initializing everything for MakerNode3
		Label whichRoomLabel = new Label("Which Room is...");
		Label theFirstRoomLabel = new Label("the First Room:");
		HBox firstRoomHBox = new HBox(30);
		firstRoomHBox.getChildren().addAll(theFirstRoomLabel, cbFirstRoom);
		CheckBox checkBox = new CheckBox("Enable Easter Egg Room");
		checkBox.setIndeterminate(false);
		Label theBonusRoomLabel = new Label("The Bonus Room:");
		HBox bonusRoomHBox = new HBox(30);
		bonusRoomHBox.getChildren().addAll(theBonusRoomLabel, cbBonusRoom);
		Label theFinalRoomLabel = new Label("the Final Room:");
		HBox finalRoomHBox = new HBox(30);
		finalRoomHBox.getChildren().addAll(theFinalRoomLabel, cbFinalRoom);
		Button confirmFinishedMapButton = new Button("Confirm and Name your Custom Map!");
		VBox almostAllChoicesVBox = new VBox(50);
		almostAllChoicesVBox.getChildren().addAll(firstRoomHBox, checkBox, bonusRoomHBox, finalRoomHBox);
		VBox almostAllChoicesVBox2 = new VBox(95);
		almostAllChoicesVBox2.getChildren().addAll(whichRoomLabel, almostAllChoicesVBox);
		VBox allMakerNode3ChoicesVBox = new VBox(100);
		allMakerNode3ChoicesVBox.getChildren().addAll(almostAllChoicesVBox2, confirmFinishedMapButton);

		// CSS for MakerNode3
		firstRoomHBox.setAlignment(Pos.CENTER);
		bonusRoomHBox.setAlignment(Pos.CENTER);
		finalRoomHBox.setAlignment(Pos.CENTER);
		almostAllChoicesVBox.setAlignment(Pos.CENTER);
		almostAllChoicesVBox2.setAlignment(Pos.CENTER);
		allMakerNode3ChoicesVBox.setAlignment(Pos.CENTER);
		whichRoomLabel.setStyle("-fx-font: 55 arial;");
		theFirstRoomLabel.setStyle("-fx-font: 35 arial;");
		cbFirstRoom.setStyle("-fx-padding: 2; -fx-font: 35 arial;");
		cbFirstRoom.setPrefWidth(650);
		checkBox.setStyle("-fx-padding: 2; -fx-font: 33 arial;");
		theBonusRoomLabel.setStyle("-fx-font: 35 arial;");
		theBonusRoomLabel.setTextFill(Color.GRAY);
		cbBonusRoom.setStyle("-fx-padding: 2; -fx-font: 35 arial;");
		cbBonusRoom.setDisable(true);
		cbBonusRoom.setPrefWidth(650);
		cbFinalRoom.setStyle("-fx-padding: 2; -fx-font: 35 arial;");
		cbFinalRoom.setPrefWidth(650);
		theFinalRoomLabel.setStyle("-fx-padding: 2; -fx-font: 35 arial;");
		confirmFinishedMapButton.setStyle("-fx-padding: 12; -fx-font: 38 arial;");

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
						// get starting room
						Room start = cbFirstRoom.getValue();
						start.setStartingRoom(true);
						// get ending room
						Room end = cbFinalRoom.getValue();
						end.setEndingRoom(true);
						// get bonus room
						Room bonus = cbBonusRoom.getValue();
						if (bonus != null)
							bonus.setHasMcGregor(true);
						// save map
						saveMap(newNameTextField.getText());

						// gui stuff
						nameNewMaze.close();
						myStage.setScene(mainScene);
						myStage.setTitle("The McMaze - Title Screen");
					}
				});
			}
		});

		checkBox.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				if (checkBox.isSelected()) {
					theBonusRoomLabel.setTextFill(Color.BLACK);
					cbBonusRoom.setDisable(false);
				} else {
					theBonusRoomLabel.setTextFill(Color.GRAY);
					cbBonusRoom.setDisable(true);
				}
			}
		});

		// **********************************************************************************************************************************
		// MAKER NODE 3

		// Setting everything onto the nodes to be displayed
		rootNode.getChildren().addAll(menuBar, titleLabel, introVBox);
		playNode.setTop(menuBar2);
		playNode.setLeft(inputOutputVBox);
		playNode.setRight(fullRightPaneVBox);
		makerNode.setTop(menuBar3);
		makerNode.setCenter(nameDescVBox);
		makerNode.setBottom(confirmRoomHBox);
		makerNode2.setTop(menuBar4);
		makerNode2.setCenter(finalVBox);
		makerNode2.setRight(outputRooms);
		makerNode3.setTop(menuBar5);
		makerNode3.setCenter(allMakerNode3ChoicesVBox);
		myStage.show();
	}

	// **********************************************************************************************************************************
	// METHODS

	/**
	 * move the player
	 *
	 * @param direction
	 *            <ul>
	 *            <li>0 - north</li>
	 *            <li>1 - north-east</li>
	 *            <li>2 - east</li>
	 *            <li>3 - south-east</li>
	 *            <li>4 - south</li>
	 *            <li>5 - south-west</li>
	 *            <li>6 - west</li>
	 *            <li>7 - north-west</li>
	 *            <li>8 - up</li>
	 *            <li>9 - down</li>
	 *            </ul>
	 */
	private void movePlayer(int direction) {
		try {
			p.move(direction); // initialize player if necessary
			if (p == null) {
				try {
					p = new Player(DBInterface.getStartingRoom());
				} catch (SQLException e) {
					System.err.println("=== PROBLEM CREATING PLAYER ===");
					e.printStackTrace();
					System.err.println("=== END PLAYER PROBLEM ===");
				}
			}
			// move the player
		} catch (SQLException e) {
			System.err.println("=== PROBLEM MOVING PLAYER ===");
			e.printStackTrace();
			System.err.println("=== END MOVING PROBLEM ===");
		}
	}

	/**
	 * @param e
	 *            the edge to add/modify
	 */
	private void updateEdgeList(Edge e) {
		// this is just a hack since there were weird design conflicts
		int realFirstNode = e.getSecondNode();
		int realSecondNode = e.getFirstNode();
		e.setFirstNode(realFirstNode);
		e.setSecondNode(realSecondNode);
		// actually update the edge list
		if (edgeConfirmed) {
			edgeList.add(e);
			genOutputRoomInfo(e);
		} else {
			edgeList.remove(edgeList.size() - 1); // remove old edge
			edgeList.add(e); // add edge (modified)
			genOutputRoomInfo(e);
		}
	}

	/**
	 * generate and temporarily store output room info
	 * 
	 * @param e
	 *            the edge to store info for
	 */
	private void genOutputRoomInfo(Edge e) {
		Room firstNode = getRoom(e.getFirstNode());
		Room secondNode = getRoom(e.getSecondNode());
		outputRoomInfo = e.getEdgeID() + " : " + translateEdgeType(e.getEdgeType()) + " : " + firstNode.getRoomName()
				+ " : " + secondNode.getRoomName();
	}

	/**
	 * @param type
	 *            integer representation of direction
	 * @return corresponding string representation of direction
	 */
	private String translateEdgeType(int type) {
		String result = "";
		switch (type) {
		case 0:
			result = "north";
			break;
		case 1:
			result = "north-east";
			break;
		case 2:
			result = "east";
			break;
		case 3:
			result = "south-east";
			break;
		case 4:
			result = "south";
			break;
		case 5:
			result = "south-west";
			break;
		case 6:
			result = "west";
			break;
		case 7:
			result = "north-west";
			break;
		case 8:
			result = "up";
			break;
		case 9:
			result = "down";
			break;
		}
		return result;
	}

	/**
	 * commit the temporarily stored info to outputRooms
	 */
	private void confirmOutputRoomInfo() {
		outputRooms.appendText(outputRoomInfo + '\n');
	}

	/**
	 * @param mapName
	 *            the name of the map to save
	 */
	private void saveMap(String mapName) {
		try {
			DBInterface.writeMapFile(roomList.toArray(new Room[roomList.size()]),
					edgeList.toArray(new Edge[edgeList.size()]), new File(mapName + ".mcmaze"), mapName);
		} catch (IOException e) {
			System.err.println("=== PROBLEM WRITING MAP FILE ===");
			e.printStackTrace();
			System.err.println("=== END MAP FILE PROBLEM ===");
		}
	}

	/**
	 * display text in GUI output area
	 *
	 * @param text
	 *            the text to display in the output area
	 */
	public static void print(String text) {
		outputText.appendText(text + '\n');
	}
	// TODO make sure that all methods have javadoc comments

	/**
	 * get room (this is ONLY for the map-maker, which is not connected to the
	 * database)
	 * 
	 * @param roomID
	 *            the room id to find
	 * @return the {@code Room} object, or {@code null}
	 */
	private Room getRoom(int roomID) {
		for (Room r : roomList)
			if (r.getRoomID() == roomID)
				return r;
		return null;
	}
}
