package application;

import Database.DatabaseSingleton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent> {

	private TestStage selectDatabaseStage;
	private SelectDatabaseDriverStage selectDriverStage;
	private InitializeConnectionStage createConnectionStage;

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		selectDatabaseStage = new TestStage();
		selectDatabaseStage.getNextButton().setOnAction(this);
		selectDatabaseStage.getStage().show();
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == selectDatabaseStage.getNextButton()) {
			DatabaseSingleton.setDatabase(selectDatabaseStage.getSelectedDatabase());
			selectDatabaseStage.getStage().close();
			
			selectDriverStage = new SelectDatabaseDriverStage();	
			selectDriverStage.getNextButton().setOnAction(this);
			selectDriverStage.getStage().show();
		
		} 
		else if (event.getSource() == selectDriverStage.getNextButton()) {
			selectDriverStage.getStage().close();
			
			createConnectionStage = new InitializeConnectionStage();
			createConnectionStage.getContinueButton().setOnAction(this);
			createConnectionStage.getStage().show();		
		} 
		else if(event.getSource() == createConnectionStage.getContinueButton()) {
			createConnectionStage.getStage().close();
			
			ExecuteQueryStage executeQueryStage = new ExecuteQueryStage();
			executeQueryStage.getStage().show();
		}
	}
}
