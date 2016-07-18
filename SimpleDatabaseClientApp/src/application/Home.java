package application;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import com.sap.idm.main.Connect;
import com.sap.idm.main.DatabaseConnectionDetails;
import com.sap.idm.main.TestConnection;

import Database.DatabaseCredentials;
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

public class Home extends Application implements EventHandler<ActionEvent> {

	TextArea queryEditor = new TextArea();
	Button executeButton = new Button("Execute");
	Button clearButton = new Button("Clear");
	TextField parametarsFiled = new TextField();
	Label resultLabel = new Label("Status:");

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == clearButton) {
			Executors.newSingleThreadExecutor().execute(new Runnable() {
			    @Override 
			    public void run() {
			    	queryEditor.clear();		
			    }
			});
		} else if(event.getSource() == executeButton) {
			List<Object> params = Arrays.asList(parametarsFiled.getText().split(","));
			Connect conn = new Connect();
			boolean result = conn.execute(DatabaseConnectionDetails.getDatabaseConnectionDetails(), queryEditor.getText(), params);
			if(result) {
				resultLabel.setTextFill(Color.web("#00FF00"));
				resultLabel.setText("Took: " + conn.getExecutionTime());
			} else {
				resultLabel.setTextFill(Color.web("#FF0000"));
				resultLabel.setText("Error while executing the query");
			}	
		}
	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			GridPane grid = new GridPane();
			grid.setPadding(new Insets(10, 10, 10, 10));
			grid.setVgap(8);
			grid.setHgap(10);
			

			GridPane.setConstraints(queryEditor, 0, 1);	
			GridPane.setConstraints(parametarsFiled, 0, 4);		
			GridPane.setConstraints(clearButton, 0, 5);
			GridPane.setConstraints(executeButton, 0, 6);
			GridPane.setConstraints(resultLabel, 0, 7);
			
			executeButton.setOnAction(this);
			clearButton.setOnAction(this);

			grid.getChildren().addAll(queryEditor,resultLabel, parametarsFiled, executeButton, clearButton);

			Scene scene = new Scene(grid, 450, 300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
