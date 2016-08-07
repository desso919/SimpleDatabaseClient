
package application;

import Database.DatabaseType;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TestStage implements EventHandler<ActionEvent> {

	private ComboBox<DatabaseType> databaseSelection = new ComboBox<DatabaseType>();
	private Label selectDatabaseLabel = new Label("Please select database:");
	private Button cancelButton = new Button("Cancel");
	private Button nextButton = new Button("Next");
	private Stage stage = new Stage();

	public TestStage() {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		stage.setTitle("Select Database");
		
		databaseSelection.getItems().addAll(DatabaseType.MS_SQL, DatabaseType.ORACLE, DatabaseType.ASE,
				DatabaseType.DB2);
		databaseSelection.setValue(DatabaseType.MS_SQL);

		GridPane.setConstraints(selectDatabaseLabel, 1, 1);
		GridPane.setConstraints(databaseSelection, 1, 2);
		GridPane.setConstraints(nextButton, 12, 13);
		GridPane.setConstraints(cancelButton, 13, 13);
		grid.getChildren().addAll(selectDatabaseLabel, databaseSelection, nextButton, cancelButton);

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

		Scene scene = new Scene(grid, 400, 200);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
	}
	public Stage getStage() {
		return stage;
	}

	public Button getNextButton() {
		return nextButton;
	}
	
	public DatabaseType getSelectedDatabase() {
		return databaseSelection.getValue();
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == cancelButton) {
			getStage().close();
		}
	}
}
