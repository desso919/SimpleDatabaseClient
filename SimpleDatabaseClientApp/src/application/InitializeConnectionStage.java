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
import Database.DatabaseSingleton;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import templates.Template;

public class InitializeConnectionStage implements EventHandler<ActionEvent>  {
	
	private Button testConnectionButton = new Button("Test Connection");
	private Button selecetTemplateButton = new Button("Load Template");
	private Button continueButton = new Button("Continue");
	private Button cancelButton = new Button("Cancel");
	private TextField databaseHostFiled = new TextField();
	private TextField databasePortFiled = new TextField();
	private TextField databaseNameFiled = new TextField();
	private TextField usernameFiled = new TextField();
	private PasswordField passwordFiled = new PasswordField();	
	private Label resultLabel = new Label();
	private Stage stage = new Stage();
	private Template choosedTemplate;

	public InitializeConnectionStage() {
			GridPane grid = new GridPane();
			grid.setPadding(new Insets(10, 10, 10, 10));
			grid.setVgap(8);
			grid.setHgap(10);

			stage.setTitle("Initialize Database Connection");
			
			grid.setAlignment(Pos.BASELINE_CENTER);
			
			Label databaseHostLabel = new Label("Database server Host: ");
			GridPane.setConstraints(databaseHostLabel, 0, 0);

			GridPane.setConstraints(databaseHostFiled, 1, 0, 3, 1);

			Label databasePortLabel = new Label("Database server Port: ");
			GridPane.setConstraints(databasePortLabel, 0, 1);

			GridPane.setConstraints(databasePortFiled, 1, 1, 3, 1);

			Label databaseNameLabel = new Label("Database name: ");
			GridPane.setConstraints(databaseNameLabel, 0, 2);

			GridPane.setConstraints(databaseNameFiled, 1, 2, 3, 1);

			Label usernameLabel = new Label("Username for the database: ");
			GridPane.setConstraints(usernameLabel, 0, 3);

			GridPane.setConstraints(usernameFiled, 1, 3, 3, 1);

			Label passwordLabel = new Label("User password: ");
			GridPane.setConstraints(passwordLabel, 0, 4);
			
			GridPane.setConstraints(passwordFiled, 1, 4, 3, 1);

			testConnectionButton.setOnAction(this);
			GridPane.setConstraints(testConnectionButton, 0, 12);

			selecetTemplateButton.setOnAction(this);
			GridPane.setConstraints(selecetTemplateButton, 2, 12);
			
			continueButton.setOnAction(this);
			//continueButton.setDisable(true);
			GridPane.setConstraints(continueButton, 1, 12);
			
			GridPane.setConstraints(resultLabel, 0, 6);	
			GridPane.setConstraints(cancelButton, 3, 12);	
			
			cancelButton.setOnAction(this);
			cancelButton.setOnMouseMoved( new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					cancelButton.setStyle("-fx-base: " + Colors.RED + ";");
				}
			});
			
			cancelButton.setOnMouseExited( new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					cancelButton.setStyle(null);
				}
			});
			
			testConnectionButton.setOnMouseMoved( new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					testConnectionButton.setStyle("-fx-base: " + Colors.YELLOW + ";");
				}
			});
			
			testConnectionButton.setOnMouseExited( new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					testConnectionButton.setStyle(null);
				}
			});
			
			continueButton.setOnMouseMoved( new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					continueButton.setStyle("-fx-base: " + Colors.GREEN + ";");
				}
			});
			
			continueButton.setOnMouseExited( new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					continueButton.setStyle(null);
				}
			});
			
			selecetTemplateButton.setOnMouseMoved( new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					selecetTemplateButton.setStyle("-fx-base: " + Colors.LIGHT_BLUE + ";");
				}
			});
			
			selecetTemplateButton.setOnMouseExited( new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					selecetTemplateButton.setStyle(null);
				}
			});
			

			grid.getChildren().addAll(databaseHostLabel, databaseHostFiled, databasePortLabel, databasePortFiled,
					databaseNameLabel, databaseNameFiled, usernameLabel, usernameFiled, passwordLabel, passwordFiled,
					testConnectionButton, continueButton, selecetTemplateButton, cancelButton, resultLabel);

			Scene scene = new Scene(grid, 550, 300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);

	}
	

	public Button getContinueButton() {
		return continueButton;
	}

	public Stage getStage() {
		return stage;
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
				resultLabel.setTextFill(Color.web(Colors.GREEN));
				resultLabel.setText("Connection is OK");
			} else {
				resultLabel.setTextFill(Color.web(Colors.RED));
				resultLabel.setText("No Connection");
			}	
		} else if (event.getSource() == selecetTemplateButton) {
			Template template = ChooseTemplateWindow.displayTemplates();
			if(template != null) {
				databaseHostFiled.setText(template.getHost());
				databasePortFiled.setText(template.getPort());
				databaseNameFiled.setText(template.getDatabseName());
				usernameFiled.setText(template.getUsername());
				passwordFiled.setText(template.getPassword());
			}			
		} else if (event.getSource() == cancelButton) {
			getStage().close();
		}
	}
}
