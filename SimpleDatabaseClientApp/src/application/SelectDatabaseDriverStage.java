package application;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SelectDatabaseDriverStage implements EventHandler<ActionEvent> {
	private CheckBox useDefaultJDBCDriver = new CheckBox();
	private TextField jdbcDriverPath = new TextField();
	private Button browseButton = new Button("Browse");
	private Button nextButton = new Button("Next");
	private Button cancelButton = new Button("Cancel");
	private Stage stage = new Stage();

	public SelectDatabaseDriverStage() {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		stage.setTitle("Select Database Driver");
		
		jdbcDriverPath.setMinWidth(350);
		GridPane.setConstraints(jdbcDriverPath, 0, 2);
		GridPane.setConstraints(browseButton, 1, 2);
		GridPane.setConstraints(useDefaultJDBCDriver, 0, 3);
		GridPane.setConstraints(nextButton, 1, 12);
		GridPane.setConstraints(cancelButton, 2, 12);
		useDefaultJDBCDriver.setOnAction(this);
		useDefaultJDBCDriver.setText("Use default jdbc driver");
		browseButton.setOnAction(this);
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
		
		nextButton.setOnMouseMoved( new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				nextButton.setStyle("-fx-base: " + Colors.GREEN + ";");
			}
		});
		
		nextButton.setOnMouseExited( new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				nextButton.setStyle(null);
			}
		});

		grid.getChildren().addAll(jdbcDriverPath, browseButton, useDefaultJDBCDriver, nextButton, cancelButton);

		Scene scene = new Scene(grid, 500, 250);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
	}

	public Button getNextButton() {
		return nextButton;
	}

	public Stage getStage() {
		return stage;
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == useDefaultJDBCDriver) {
			if (useDefaultJDBCDriver.isSelected()) {
				jdbcDriverPath.setDisable(true);
				browseButton.setDisable(true);
			} else {
				jdbcDriverPath.setDisable(false);
				browseButton.setDisable(false);
			}
		} else if (event.getSource() == browseButton) {
			FileChooser selectJDBCDriver = new FileChooser();
			File file = selectJDBCDriver.showOpenDialog(stage);
			jdbcDriverPath.setText(file.getAbsolutePath());
		} else if (event.getSource() == cancelButton) {
			getStage().close();
		}
	}
}
