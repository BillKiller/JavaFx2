package view;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import model.MyRectangle;

public class RootLayoutController {
	@FXML
	private BorderPane rootLayout;//fxid

	@FXML
	private TextField myTextField;
	@FXML
	private Button myButton;

	private int clickCount=1;
	@FXML
	private void clickButton() {
		myTextField.setText("你点了"+clickCount+"次了！");
		clickCount++;
	}

	@FXML
	private ImageView rectangleImage;

	@FXML
	private void clickRectangle() {
		MyRectangle myRectangle=new MyRectangle(500,500,100,200);
		myRectangle.getPane(rootLayout);

	}

	@FXML
	private void enteredRectangle() {
		rectangleImage.setCursor(Cursor.HAND);
	}
}
