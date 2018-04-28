package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.MyShape;

public class PropertyController {


	private TextField textFieldH;
	private TextField textFieldW;
	private TextField textFieldX;
	private TextField textFieldY;
	private MyShape shape;
	public PropertyController(TextField x,TextField y,TextField W,TextField H){
		this.textFieldH =H;
		this.textFieldW =W;
		this.textFieldY =y;
		this.textFieldX =x;
	}
	public void setWorkShape(MyShape shape){
			this.shape = shape;
	}
	public void update(){
			textFieldH.setText(String.valueOf(shape.getHeight()));
			textFieldW.setText(String.valueOf(shape.getWidth()));
			textFieldX.setText(String.valueOf(shape.getX()));
			textFieldY.setText(String.valueOf(shape.getY()));
	}
}
