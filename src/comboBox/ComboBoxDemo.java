package comboBox;

import java.util.Arrays; 



import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ComboBoxDemo extends Application {
	String myValue;

	public static void main(String[] args) {
		launch(args);

	}

	public void start(Stage primaryStage) {
		
		ComboBox<String> box = new ComboBox<>();

		String[] courses = { "CST111", "CST112", "CST141", "CST242" };
		ObservableList<String> list = FXCollections.observableArrayList(Arrays.asList(courses));
		box.setItems(list);
		box.setOnAction(e -> {
			myValue = box.getValue();
			System.out.println("the course your chose was " + myValue);
		});
		
		box.valueProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					System.out.println(oldValue+" was changed to "+newValue);
			}
			
			
		});
			
		

		HBox root = new HBox();
		root.setAlignment(Pos.CENTER);
		root.getChildren().add(box);
		primaryStage.setScene(new Scene(root, 500, 600));
		primaryStage.show();
	}

}
