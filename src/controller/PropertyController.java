package controller;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.MyLine;
import model.MyShape;

public class PropertyController {

	private TextField textFieldH;
	private TextField textFieldW;
	private TextField textFieldX;
	private TextField textFieldY;
	private TextArea textArea;
	private Object shape;
	private Button button;
	private DrawController drawController;

	public PropertyController(TextField x, TextField y, TextField W, TextField H, TextArea textArea) {
		this.textFieldH = H;
		this.textFieldW = W;
		this.textFieldY = y;
		this.textFieldX = x;
		this.textArea = textArea;
	}

	public void setWorkShape(Object object) {
		this.shape = object;
	}
	public void setButton(Button button) {
		this.button = button;
	}

	public void update() {
		if (shape != null) {
			if (shape instanceof MyShape) {
				textFieldH.setText(String.valueOf((((MyShape) shape).getHeight())));
				textFieldW.setText(String.valueOf((((MyShape) shape).getWidth())));
				textFieldX.setText(String.valueOf((((MyShape) shape).getX())));
				textFieldY.setText(String.valueOf((((MyShape) shape).getY())));
				textArea.setText(String.valueOf((((MyShape) shape).getText().getText())));
			} else {
				textFieldH.setText(String.valueOf((((MyLine)shape).getEY())));
				textFieldW.setText(String.valueOf((((MyLine)shape).getEX())));
				textFieldX.setText(String.valueOf((((MyLine)shape).getSX())));
				textFieldY.setText(String.valueOf((((MyLine)shape).getSY())));
				System.out.println("???");
			}
		} else {
			textArea.setText("");
			textFieldH.setText("");
			textFieldW.setText("");
			textFieldX.setText("");
			textFieldY.setText("");
			System.out.println("fuck");
		}
	}

	public void edit() {
		button.setOnMouseClicked(e -> {
//			if (shape != null) {
//				shape.setX(Double.parseDouble(textFieldX.getText()));
//				shape.setY(Double.parseDouble(textFieldY.getText()));
//				shape.setWidth(Double.parseDouble(textFieldW.getText()));
//				shape.setHeight(Double.parseDouble(textFieldH.getText()));
//				shape.getText().setText(textArea.getText());
//				shape.updateLocation(shape.getX(), shape.getY());
//				shape.update();
//				drawController.saveChange();
//			}
		});

	}

	public DrawController getDrawController() {
		return drawController;
	}

	public void setDrawController(DrawController drawController) {
		this.drawController = drawController;
	}

}
