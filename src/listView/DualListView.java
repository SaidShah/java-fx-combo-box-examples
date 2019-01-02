package listView;


import java.util.Arrays;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DualListView extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception{
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 500,600);
		Image image = new Image("file:image1.jpg");
		ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setFitWidth(500);
		imageView.setFitHeight(600);
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(25,25,25,25));
		gridPane.setHgap(30);
		gridPane.setVgap(30);
		
		// first 150 is the prefered value second is the minimum value
		ColumnConstraints column1 = new ColumnConstraints(150,150,Double.MAX_VALUE);
		ColumnConstraints column2 = new ColumnConstraints(50);
		ColumnConstraints column3 = new ColumnConstraints(150,150,Double.MAX_VALUE);

		// this means everything will grow with it but the minimum will be 150 becuse thats what we set
		column1.setHgrow(Priority.ALWAYS);
		column3.setHgrow(Priority.ALWAYS);
		
		gridPane.getColumnConstraints().addAll(column1, column2,column3);

		Label coursesToTakeLabel = new Label("Courses To Take");
		gridPane.setHalignment(coursesToTakeLabel, HPos.CENTER);
		gridPane.add(coursesToTakeLabel, 0, 0);

		Label coursesTakingLabel = new Label("Courses To Taking");
		gridPane.setHalignment(coursesTakingLabel, HPos.CENTER);
		gridPane.add(coursesTakingLabel, 2, 0);

		String[] courses = {"CSE111","CSE242","MAT151","CST272"};
		// this is a list that keeps track of what is happeneng in real time, its an arraylist
		ObservableList<String> coursesToTakeList = FXCollections.observableArrayList(Arrays.asList(courses));
		ListView<String> coursesToTakeView = new ListView<>(coursesToTakeList);
		gridPane.add(coursesToTakeView, 0,1);

		// the list view is similar to a choice box
		ObservableList<String> coursesTakingList = FXCollections.observableArrayList(Arrays.asList(courses));
		ListView<String> coursesTakingView = new ListView<>(coursesTakingList);
		gridPane.add(coursesTakingView, 2,1);

		Button sendRightButton = new Button(">");
		sendRightButton.setOnAction(e->{
			// this get the item thats selected
			String course = coursesToTakeView.getSelectionModel().getSelectedItem();
			if(course!=null){
				coursesToTakeView.getSelectionModel().clearSelection();
				coursesToTakeList.remove(course);
				coursesTakingList.add(course);
			}
		});

		Button sendLeftButton = new Button("<");
		sendLeftButton.setOnAction(e->{
			// this get the item thats selected
			String course = coursesTakingView.getSelectionModel().getSelectedItem();
			if(course!=null){
				coursesTakingView.getSelectionModel().clearSelection();
				coursesTakingList.remove(course);
				coursesToTakeList.add(course);
			}
		});

		VBox vBox = new VBox();
		vBox.getChildren().addAll(sendRightButton,sendLeftButton);
		gridPane.add(vBox, 1, 1);
	//	gridPane.getChildren().add(imageView);
		root.setCenter(gridPane);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
