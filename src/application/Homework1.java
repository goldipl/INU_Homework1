package application;
	
/*
 * Author: @goldipl Marcin Godlewski
 * Source: @FreeTymeKiyan
 */
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;


public class Homework1 extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,250);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Praca domowa nr 1");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//Ustawienie stalych wymiarow aplikacji
			
			primaryStage.setResizable(false);
			
			//Wyglad okienka - lewa czesc
			
			Label label1 = new Label("Zrodlo: "); 
			label1.setLayoutX(10);
			label1.setLayoutY(0);
			
			Label label2 = new Label("Wynik operacji: ");
			label2.setLayoutX(10);
			label2.setLayoutY(80);
			
			TextField text1 = new TextField();
			text1.getStyleClass().add("fieldstyle");
			text1.setLayoutX(10);
			text1.setLayoutY(20);
			
			TextField text2 = new TextField();
			text2.getStyleClass().add("fieldstyle");
			text2.setLayoutX(10);
			text2.setLayoutY(100);
			
			Button button1 = new Button("Kopiuj");
			button1.setLayoutX(135);
			button1.setLayoutY(60);
			
			Button button2 = new Button("Wykonaj");
			button2.setLayoutX(10);
			button2.setLayoutY(140);
			
			Button button3 = new Button("Wyczysc");
			button3.setLayoutX(125);
			button3.setLayoutY(140);
			
			//Wyglad okienka - prawa czesc
			
			Label label3 = new Label("Operacja: "); 
			label3.setLayoutX(230);
			label3.setLayoutY(0);
			
			RadioButton radioButton1 = new RadioButton("kodowanie");
			radioButton1.setLayoutX(230);
			radioButton1.setLayoutY(25);
			
			RadioButton radioButton2 = new RadioButton("dekodowanie");
			radioButton2.setLayoutX(230);
			radioButton2.setLayoutY(50);
			
			//Dodanie radioButton'ow do wspolnej grupy przelacznikow
			
			ToggleGroup tgroup = new ToggleGroup();
			radioButton1.setToggleGroup(tgroup);
			radioButton2.setToggleGroup(tgroup);
			
			//Ustawienie radioButtona domyslnie na opcje "kodowanie"
			
			radioButton1.setSelected(true);
			
			//Wstawienie etykiet, przyciskow, pol tekstowych itd. na szybce okienka
			
			Group group = new Group();
			root.setCenter(group);
			group.getChildren().addAll(text1,text2,label1,label2,button1,button2,button3,label3,radioButton1,radioButton2);
			
			//Kopiowanie znakow z pola tekstowego "Wynik operacji" do "Zrodla" i kolejno wyczyszczenie pola tekstowego "Wynik operacji"
			
			button1.setOnAction(copyEvent-> {
				System.out.println("Przycisk 'Kopiuj' - wcisniety");
				text2.getText();
				String input = text2.getText();
				System.out.println("Pobrano tekst z pola tekstowego 'Wynik operacji'");
				text1.setText(input);
				System.out.println("Wklejono pobrany tekst do 'Zrodla'");
				text2.setText("");
				System.out.println("Wyczyszczono pole tekstowe 'Wynik operacji'");
			});
			
			//Obsluga zdarzen kodowania

			
			button2.setOnAction(doItEvent-> {
				System.out.println("Przycisk 'Wykonaj' - wcisniety");
				if (radioButton1.isSelected()) {
			        System.out.println("radioButton1 'kodowanie' zaznaczony");
			        
					String plainText = text1.getText();
					StringBuilder encodedText = new StringBuilder();
					
					for (int i = 0; i < plainText.length(); i++) {
						encodedText.append(plainText.charAt(i));
						int count = 1;
						while (i + 1 < plainText.length() && plainText.charAt(i) == plainText.charAt(i + 1)) {
							i++;
							count++;
						}
						encodedText.append(count);
						encodedText.append(",");
					}
				String s = encodedText.toString();
			    text2.setText(s);
			    return;	        
				}
				
			//Obsluga zdarzen dekodowania
			
				if (radioButton2.isSelected()) {
			        System.out.println("radioButton2 'dekodowanie' zaznaczony");
		          
					String encodedText = text1.getText();
					StringBuilder plainText = new StringBuilder();
					StringBuilder plainText2 = new StringBuilder();
					
					for (int i = 0; i < encodedText.length() - 1; i++) {
						char charAt2 = encodedText.charAt(i);
						
						if (charAt2 != ',') {
							plainText2.append(charAt2);
						}
					}
					
					encodedText=plainText2.toString();
					
					for (int i = 0; i < encodedText.length() - 1; i = i + 2) {
						char charAt = encodedText.charAt(i);
						int count = encodedText.charAt(i + 1) - '0';
						for (int j = 0; j < count; j++) {
							plainText.append(charAt);	
						}
					}
				String s = plainText.toString();
			    text2.setText(s);
			    return;        
				}
			});
			
			//Czyszczenie pol tekstowych
			
			button3.setOnAction(clearEvent-> {
				text1.setText("");
				text2.setText("");
				System.out.println("Wyczyszczono wszystkie pola tekstowe");
			});		
			
			//Dodanie podpowiedzi do przyciskow
			
			Tooltip t1 = new Tooltip("Wytnij zawartosc z pola 'Wynik operacji' i wklej do 'Zrodlo'");
			Tooltip t2 = new Tooltip("Uruchom program");
			Tooltip t3 = new Tooltip("Wyczysc obydwa pola tekstowe");
			
			button1.setTooltip(t1);
			button2.setTooltip(t2);
			button3.setTooltip(t3);
							
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
