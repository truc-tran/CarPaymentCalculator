package car_payment_calculator;

import java.text.NumberFormat;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * <code>CarPayment<code> calculate The amount of each monthly payment is based on 
 * the length of the loan, the amount borrowed and the interest rate. 
 * @author ttran1214
 *
 */
public class CarPayment extends Application
{	//variable
	private Slider yearSlider = new Slider(2,5,2);
	private Label yearLabel = new Label("Years:");
	private TextField priceTF = new TextField();
	private TextField downTF = new TextField();
	private TextField rateTF = new TextField();
	private TextField monthTF = new TextField();
	
	private Button clearButton = new Button("Clear");
	private Button calculateButton = new Button("Calculate");
	
	/**
	 * set up elements in stage, layout, scene and
	 * calculate the monthly payment 
	 */
	public void start(Stage stage) throws Exception {
		//NOT edible
		monthTF.setEditable(false); 
		//Layout
		GridPane pane = new GridPane();
		pane.add(new Label("Car Price $"), 0, 0);
		pane.add(yearLabel, 0, 1);
		pane.add(new Label("Down Payment $"), 0, 2);
		pane.add(new Label("Interest Rate %"), 0, 3);
		pane.add(new Label("Monthly Payment $"), 0, 4);
		pane.add(yearSlider, 1, 1);
		pane.add(priceTF, 1, 0);
		pane.add(downTF, 1, 2);
		pane.add(rateTF, 1, 3);
		pane.add(monthTF, 1, 4);
		pane.setHgap(5);
		pane.setVgap(5);
		pane.setAlignment(Pos.CENTER);
		
		//Slider
		yearSlider.setShowTickMarks(true);
		yearSlider.setBlockIncrement(0.2);
		yearSlider.setShowTickLabels(true);
		yearSlider.setMajorTickUnit(1);
		
		//Button
		HBox button = new HBox();
		button.setSpacing(5);
		button.getChildren().add(clearButton);
		button.getChildren().add(calculateButton);
		pane.add(button, 1, 5);
		button.setAlignment(Pos.CENTER_RIGHT);
		
		//title
		stage.setTitle("Monthly Car Payment");
		//scene
		Scene scene = new Scene(pane, 400, 300);
		stage.setScene(scene);
		//show
		stage.show();
		
		//method
		yearSlider.valueProperty().addListener((obs, oldVal, newVal)-> updateSlider(newVal.intValue()) );
		clearButton.setOnAction(c-> clear());
		calculateButton.setOnAction(c-> calculate());
		
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	/**
	 * update the value of years
	 * @param newValue
	 */
	private void updateSlider(Number newVal) {
		
		yearLabel.setText(newVal.intValue() + " years" );
		calculate();
		
	}
	
	/**
	 * Reset all the text fields to empty(clear )
	 */
	private void clear() {
		yearSlider.setValue(2);
		priceTF.setText("");
		downTF.setText("");
		rateTF.setText("");
		monthTF.setText("");
		
	}
	/**
	 * Calculate monthly payment in $
	 */
	private void calculate() {
		 if ( priceTF.getText().isEmpty()|| 
			  downTF.getText().isEmpty() ||
			  rateTF.getText().isEmpty())
		        return;
		 double carPrice = Double.parseDouble(priceTF.getText());
		 double downPayment = Double.parseDouble(downTF.getText());
		 double r = (Double.parseDouble(rateTF.getText()) / 12) / 100;
		 int years = (int) yearSlider.getValue();
		 int n = (int) (years * 12);
		 double P = carPrice - downPayment;
		 double monthPayment = P*(r*Math.pow(1+r, n))/(Math.pow(1+r, n) -1);
		 NumberFormat currency = NumberFormat.getCurrencyInstance();
		 monthTF.setText(currency.format(monthPayment));
		 
	}
	
}
