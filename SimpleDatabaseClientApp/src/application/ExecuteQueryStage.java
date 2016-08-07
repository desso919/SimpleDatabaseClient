package application;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import com.sap.idm.main.Connect;
import com.sap.idm.main.DatabaseConnectionDetails;
import com.sap.idm.main.TestConnection;

import Database.Database;
import Database.DatabaseCredentials;
import Database.DatabaseSingleton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import queries.SQLQuery;

public class ExecuteQueryStage implements EventHandler<ActionEvent> {

	private TextArea queryEditor = new TextArea();
	private Button executeButton = new Button("Execute");
	private Button clearButton = new Button("Clear");
	private Button selectQueryButton = new Button("Load Query");
	private TextField parametarsFiled = new TextField();
	private Label resultLabel = new Label("Status:");
	private Stage stage = new Stage();
	private Label queryExecutorLabel = new Label("Enter the query:");
	private Label parametarsLabel = new Label("Enter parametars:");

	public ExecuteQueryStage() {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		stage.setTitle("Execute Query");

		GridPane.setConstraints(queryExecutorLabel, 0, 0);
		GridPane.setConstraints(queryEditor, 0, 1);
		GridPane.setConstraints(parametarsLabel, 0, 3);
		GridPane.setConstraints(parametarsFiled, 0, 4);
		GridPane.setConstraints(clearButton, 0, 8);
		GridPane.setConstraints(executeButton, 0, 7);
		GridPane.setConstraints(selectQueryButton, 0,9);
		GridPane.setConstraints(resultLabel, 0, 6);

		executeButton.setOnAction(this);
		clearButton.setOnAction(this);
		selectQueryButton.setOnAction(this);
		
		queryEditor.setWrapText(true);
		queryEditor.setMinSize(700, 420);

		grid.getChildren().addAll(queryExecutorLabel, queryEditor, parametarsLabel, resultLabel, parametarsFiled, executeButton, selectQueryButton,
				clearButton);

		Scene scene = new Scene(grid, 750, 700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
	}

	public Stage getStage() {
		return stage;
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == clearButton) {
			queryEditor.clear();
		} else if (event.getSource() == executeButton) {
			List<Object> params = Arrays.asList(parametarsFiled.getText().split(","));
			Database database = DatabaseSingleton.getInstance();
			String sqlQuery = queryEditor.getText();
			ResultSet result = null;
			
			
			
			if(params.isEmpty()) {
				 result = database.executeAndTimeQuery(sqlQuery);
			} else {
				 result = database.executeAndTimeQuery(sqlQuery, params);
			}

			if (result != null) {
				resultLabel.setTextFill(Color.web("#00FF00"));
				resultLabel.setText("Took: " + database.getExecutionTime());
			} else {
				resultLabel.setTextFill(Color.web("#FF0000"));
				resultLabel.setText("Error while executing the query");
			}
		} else if (event.getSource() == selectQueryButton) {
			SQLQuery query = ChooseQueryWindow.displayQueries();
			if (query != null) {
				queryEditor.setText(query.getQuery());				
			}
		}
	}
}
