package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.MyShape;

public class PropertyController {

	private TextField textFieldH;
	private TextField textFieldW;
	private TextField textFieldX;
	private TextField textFieldY;
	private TextArea textArea;
	private MyShape shape;
	private Button button;
	public PropertyController(TextField x, TextField y, TextField W, TextField H,TextArea textArea) {
		this.textFieldH = H;
		this.textFieldW = W;
		this.textFieldY = y;
		this.textFieldX = x;
		this.textArea = textArea;
	}

	public void setWorkShape(MyShape shape) {
		this.shape = shape;
	}
	public void setButton(Button button){
			this.button = button;
	}
	public void update() {
		if (shape != null) {
			textFieldH.setText(String.valueOf(shape.getHeight()));
			textFieldW.setText(String.valueOf(shape.getWidth()));
			textFieldX.setText(String.valueOf(shape.getX()));
			textFieldY.setText(String.valueOf(shape.getY()));
			textArea.setText(shape.getText().getText());
		}else{
			textArea.setText("");
			textFieldH.setText("");
			textFieldW.setText("");
			textFieldX.setText("");
			textFieldY.setText("");
		}
	}
	public void edit(){
		button.setOnMouseClicked(e->{
			if (shape != null) {
				shape.setX(Double.parseDouble(textFieldX.getText()));
				shape.setY(Double.parseDouble(textFieldY.getText()));
				shape.setWidth(Double.parseDouble(textFieldW.getText()));
				shape.setHeight(Double.parseDouble(textFieldH.getText()));
				shape.getText().setText(textArea.getText());
				System.out.println(shape.getX()+"  s" +shape.getY());
				shape.updateLocation(shape.getX(), shape.getY());
				shape.update();
			}
		});

	}
}
