package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import queries.IManageQueries;
import queries.ManageQueries;
import queries.SQLQuery;

public class ChooseQueryWindow {

	private static IManageQueries queriesManager = new ManageQueries();
	private static String queryName;
	private static SQLQuery query;
	
	public static SQLQuery displayQueries() {
		ListView<String> templatesView = new ListView<String>();
		Button okButton = new Button("Select");
		Button cancelButton = new Button("Cancel");
		Stage stage = new Stage();

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Choose Template");

		queriesManager.loadQueries();
		templatesView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				queryName = newValue;
			}
		});

		okButton.setOnAction(event -> {
			query = queriesManager.getQuery(queryName);
			stage.close();
		});
		cancelButton.setOnAction(event -> {
			stage.close();
		});

		templatesView.getItems().addAll(queriesManager.getQueriesNames());
		GridPane.setConstraints(templatesView, 0, 0);
		GridPane.setConstraints(okButton, 0, 12);
		GridPane.setConstraints(cancelButton, 1, 12);
		grid.getChildren().addAll(templatesView, okButton, cancelButton);

		Scene scene = new Scene(grid, 280, 300);
		// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.showAndWait();

		return query;
	}
}
