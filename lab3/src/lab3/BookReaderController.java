package lab3;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import textproc.GeneralWordCounter;
import textproc.TextProcessor;

public class BookReaderController extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		//
		Scanner d= new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords= new HashSet<String>();
		while (d.hasNext()) {
			stopwords.add(d.next());
		}
		
		BorderPane root = new BorderPane();
		GeneralWordCounter counter = new GeneralWordCounter(stopwords);
		while(s.hasNext()) {
			counter.process(s.next().toLowerCase());
		}
		
		
		ObservableList<Map.Entry<String, Integer>> words = FXCollections.observableArrayList(counter.getWords());
		ListView<Map.Entry<String, Integer>> listView = new ListView<>(words);
		
		
		
		Button b1 = new Button("Alphabetic");
		b1.setOnAction(event -> {
			words.sort((c1,c2)->c1.getKey().compareTo(c2.getKey()));
			System.out.println("Sorted list Alphabetically");
		});
		Button b2 = new Button("Frequency");
		b2.setOnAction(event -> {
			words.sort((c1,c2)-> c2.getValue().compareTo(c1.getValue()));
			System.out.println("Sorted list by Frequency");
		});
		
		/*Söka ord**/
		TextField text = new TextField();
		
		//text.
		
		Button b3 = new Button("Search");
		b3.setDefaultButton(true);
		b3.setOnAction(event -> {
			Boolean found = false;
			for(Map.Entry<String, Integer> word: words) {
				if(word.getKey().contentEquals(text.getText())) {
					listView.scrollTo(word);
//					listView.getSelectionModel(); //fixa
					found = true;
				}
				
			}
			if(found == false) {
		    Alert alert = new Alert(AlertType.CONFIRMATION, "Finns ej");
		    alert.showAndWait();
			}
			
			
			
			//System.out.println("Finns ej i Listan");
			
			//text.getPromptText();
			//int pos = 0;
			//listView.scrollTo(50);
		});
		
		HBox hbox2 = new HBox();
		hbox2.getChildren().addAll(text, b3);
		HBox.setHgrow(text, Priority.ALWAYS);
		root.setBottom(hbox2);
		
		
		HBox hbox = new HBox();
		hbox.getChildren().addAll(b1, b2);
		
		HBox buttonBox = new HBox();
		buttonBox.setPadding(new Insets(15, 12, 15, 12));
		buttonBox.setSpacing(10);
		buttonBox.setAlignment(Pos.CENTER);
		
		
		//HBox hbox3 = new HBox();
		root.setCenter(listView);
		root.setRight(hbox);
		
		
		
		Scene scene = new Scene(root, 500, 500);
		primaryStage.setTitle("BookReader");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//s.close();
		//d.close();

	}

	public static void main(String[] args) {
		Application.launch(args);

	}

}
