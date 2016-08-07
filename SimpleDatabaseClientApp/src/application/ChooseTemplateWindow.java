package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import templates.IManageTemplates;
import templates.ManageTemplates;
import templates.Template;

public class ChooseTemplateWindow {

	private static IManageTemplates templatesManager = new ManageTemplates();
	private static String templateName;
	private static Template template;
	

	public static Template displayTemplates() {
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

		templatesManager.loadTemplates();
		templatesView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				templateName = newValue;
			}
		});

		okButton.setOnAction(event -> {
			template = templatesManager.getTemplateByName(templateName);
			stage.close();
		});
		cancelButton.setOnAction(event -> {
			stage.close();
		});

		templatesView.getItems().addAll(templatesManager.getTemplatesNames());
		GridPane.setConstraints(templatesView, 0, 0);
		GridPane.setConstraints(okButton, 0, 12);
		GridPane.setConstraints(cancelButton, 1, 12);
		grid.getChildren().addAll(templatesView, okButton, cancelButton);

		Scene scene = new Scene(grid, 280, 300);
		// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.showAndWait();

		return template;
	}
}
