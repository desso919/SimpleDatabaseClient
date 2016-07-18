package application;

import java.awt.Checkbox;
import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DatabaseSelection extends Application implements EventHandler<ActionEvent> {

	ComboBox<String> databaseSelection = new ComboBox<String>();
	CheckBox useDefaultJDBCDriver = new CheckBox();
	TextField jdbcDriverPath = new TextField();
	
	Button browseButton = new Button("Browse");
	Button nextButton = new Button("Next");
	Stage currentWindow;

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == nextButton) {
			InitWindow hm = new InitWindow();
			try {
				currentWindow.close();
				hm.start(new Stage());
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}	
		else if (event.getSource() == useDefaultJDBCDriver) {
			if(useDefaultJDBCDriver.isSelected()) {
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
	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			GridPane grid = new GridPane();
			grid.setPadding(new Insets(10, 10, 10, 10));
			grid.setVgap(8);
			grid.setHgap(10);

			currentWindow = stage;
			databaseSelection.getItems().addAll("MS SQL Database", "Oracle Database", "ASE Database", "DB2 Database");
			GridPane.setConstraints(databaseSelection, 0, 1);
			GridPane.setConstraints(jdbcDriverPath, 0, 2);
			GridPane.setConstraints(useDefaultJDBCDriver, 0, 3);
			GridPane.setConstraints(browseButton, 0, 4);
			GridPane.setConstraints(nextButton, 0, 5);
			
			useDefaultJDBCDriver.setOnAction(this);	
			useDefaultJDBCDriver.setText("Use default jdbc driver");
			browseButton.setOnAction(this);
			nextButton.setOnAction(this);

			grid.getChildren().addAll(jdbcDriverPath, useDefaultJDBCDriver, browseButton, databaseSelection, nextButton);

			Scene scene = new Scene(grid, 450, 300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
