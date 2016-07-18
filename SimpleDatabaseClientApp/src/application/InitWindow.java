package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sap.idm.main.DatabaseConnectionDetails;
import com.sap.idm.main.TestConnection;

import Database.DatabaseCredentials;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class InitWindow extends Application implements EventHandler<ActionEvent>  {

	TextArea queryEditor = new TextArea();
	Button executeButton = new Button("Execute");
	Button clearButton = new Button("Clear");
	Button continueButton = new Button("Continue");
	Button testConnectionButton = new Button("Test Connection");
	Stage currentWindow;

	TextField databaseHostFiled = new TextField();
	TextField databasePortFiled = new TextField();
	TextField databaseNameFiled = new TextField();
	TextField usernameFiled = new TextField();
	PasswordField passwordFiled = new PasswordField();
	
	Label resultLabel = new Label();

	@Override
	public void start(Stage primaryStage) {
		try {
			currentWindow = primaryStage;
			GridPane grid = new GridPane();
			grid.setPadding(new Insets(10, 10, 10, 10));
			grid.setVgap(8);
			grid.setHgap(10);

			Label databaseHostLabel = new Label("Database server Host: ");
			GridPane.setConstraints(databaseHostLabel, 0, 0);

			GridPane.setConstraints(databaseHostFiled, 1, 0);

			Label databasePortLabel = new Label("Database server Port: ");
			GridPane.setConstraints(databasePortLabel, 0, 1);

			GridPane.setConstraints(databasePortFiled, 1, 1);

			Label databaseNameLabel = new Label("Database name: ");
			GridPane.setConstraints(databaseNameLabel, 0, 2);

			GridPane.setConstraints(databaseNameFiled, 1, 2);

			Label usernameLabel = new Label("Username for the database: ");
			GridPane.setConstraints(usernameLabel, 0, 3);

			GridPane.setConstraints(usernameFiled, 1, 3);

			Label passwordLabel = new Label("User password: ");
			GridPane.setConstraints(passwordLabel, 0, 4);
			
			
			
			GridPane.setConstraints(passwordFiled, 1, 4);

			testConnectionButton.setOnAction(this);
			GridPane.setConstraints(testConnectionButton, 1, 5);

			continueButton.setOnAction(this);
			continueButton.setDisable(true);
			GridPane.setConstraints(continueButton, 2, 5);
			
			GridPane.setConstraints(resultLabel, 0, 6);		

			grid.getChildren().addAll(databaseHostLabel, databaseHostFiled, databasePortLabel, databasePortFiled,
					databaseNameLabel, databaseNameFiled, usernameLabel, usernameFiled, passwordLabel, passwordFiled,
					testConnectionButton, continueButton, resultLabel);

			Scene scene = new Scene(grid, 450, 300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Map<DatabaseCredentials, String> getUserInput() {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		Map<DatabaseCredentials, String> databaseCredentials = new HashMap<DatabaseCredentials, String>();

		databaseCredentials.put(DatabaseCredentials.DATABASE_HOST, databaseHostFiled.getText());
		databaseCredentials.put(DatabaseCredentials.DATABASE_PORT, databasePortFiled.getText());
		databaseCredentials.put(DatabaseCredentials.DATABASE_NAME, databaseNameFiled.getText());
		databaseCredentials.put(DatabaseCredentials.USERNAME, usernameFiled.getText());
		databaseCredentials.put(DatabaseCredentials.PASSWORD, passwordFiled.getText());

		DatabaseConnectionDetails.setDatabaseConnectionDetails(databaseCredentials);
		return databaseCredentials;
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == testConnectionButton) {
			Map<DatabaseCredentials, String> userInput = getUserInput();
	    	boolean isConnected = TestConnection.testConnectionToDatabase(userInput);
			if(isConnected) {
				continueButton.setDisable(false);
				resultLabel.setTextFill(Color.web("#00FF00"));
				resultLabel.setText("Connection is OK");
			} else {
				resultLabel.setTextFill(Color.web("#FF0000"));
				resultLabel.setText("No Connection");
			}	
		} else if (event.getSource() == continueButton) {
			Home hm = new Home();
			try {
				currentWindow.close();
				hm.start(new Stage());				
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

}
