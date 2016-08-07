package application;

import java.io.File;

import Database.DatabaseSingleton;
import Database.DatabaseType;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SelectDatabaseStage extends Application implements EventHandler<ActionEvent> {

	ComboBox<DatabaseType> databaseSelection = new ComboBox<DatabaseType>();
	CheckBox useDefaultJDBCDriver = new CheckBox();
	TextField jdbcDriverPath = new TextField();
	Label selectDatabaseLabel = new Label("Please select database:");

	Button browseButton = new Button("Browse");
	Button nextButton = new Button("Next");
	Button nextButton2 = new Button("Next");
	Button exitButton = new Button("Cancel");
	Stage currentWindow;
	Scene scene1, scene2;

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == nextButton) {
			DatabaseSingleton.setDatabase(databaseSelection.getValue());
			GridPane grid = new GridPane();
			grid.setPadding(new Insets(10, 10, 10, 10));
			grid.setVgap(8);
			grid.setHgap(10);
			
			jdbcDriverPath.setMinWidth(350);
			GridPane.setConstraints(jdbcDriverPath, 0, 2);
			GridPane.setConstraints(browseButton, 1, 2);
			GridPane.setConstraints(useDefaultJDBCDriver, 0, 3);
			GridPane.setConstraints(nextButton2, 1, 12);
			GridPane.setConstraints(exitButton, 2, 12);
			useDefaultJDBCDriver.setOnAction(this);
			useDefaultJDBCDriver.setText("Use default jdbc driver");
			browseButton.setOnAction(this);
			nextButton2.setOnAction(this);

			grid.getChildren().addAll(jdbcDriverPath, browseButton, useDefaultJDBCDriver, nextButton2, exitButton);
			scene2 = new Scene(grid, 500, 250);
			scene2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			currentWindow.setScene(scene2);

		} else if (event.getSource() == useDefaultJDBCDriver) {
			if (useDefaultJDBCDriver.isSelected()) {
				jdbcDriverPath.setDisable(true);
				browseButton.setDisable(true);
			} else {
				jdbcDriverPath.setDisable(false);
				browseButton.setDisable(false);
			}
		} else if (event.getSource() == browseButton) {
			FileChooser selectJDBCDriver = new FileChooser();
			File file = selectJDBCDriver.showOpenDialog(currentWindow);
			jdbcDriverPath.setText(file.getAbsolutePath());
		}
		 else if (event.getSource() == nextButton2) {	
			 InitializeConnectionStage init = new InitializeConnectionStage();
			 currentWindow.close();
			}
	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			currentWindow = stage;
			GridPane grid = new GridPane();
			grid.setPadding(new Insets(10, 10, 10, 10));
			grid.setVgap(8);
			grid.setHgap(10);
			
			databaseSelection.getItems().addAll(DatabaseType.MS_SQL, DatabaseType.ORACLE, DatabaseType.ASE, DatabaseType.DB2);
			databaseSelection.setValue(DatabaseType.MS_SQL);
			nextButton.setOnAction(this);

			GridPane.setConstraints(selectDatabaseLabel, 1, 1);
			GridPane.setConstraints(databaseSelection, 1, 2);
			GridPane.setConstraints(nextButton, 12, 13);
			GridPane.setConstraints(exitButton, 13, 13);
			grid.getChildren().addAll(databaseSelection, nextButton, selectDatabaseLabel, exitButton);

			scene1 = new Scene(grid, 400, 200);
			scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene1);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
