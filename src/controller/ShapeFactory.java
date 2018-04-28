package controller;


import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import model.CurvedRectangle;
import model.Decision;
import model.InputRectangle;
import model.MyCircle;
import model.MyRectangle;
import model.MyShape;
import model.RoundRectangle;

public class ShapeFactory {
	private int countShapeID = 0;
	private AnchorPane drawingArea;
	private DrawController drawController;

	public ShapeFactory(AnchorPane drawingArea,DrawController drawController){
		this.drawingArea = drawingArea;
		this.drawController=drawController;
	}
	public MyRectangle newMyRectangle(double x, double y) {
		countShapeID++;
		return new MyRectangle(x, y, countShapeID);
	}
	public RoundRectangle newRoundRectangle(double x, double y) {
		countShapeID++;
		return new RoundRectangle(x, y, countShapeID);
	}
	public Decision newDecision(double x, double y) {
		countShapeID++;
		return new Decision(x, y, countShapeID);
	}
	public InputRectangle newInputRectangle(double x, double y) {
		countShapeID++;
		return new InputRectangle(x, y, countShapeID);
	}
	public MyCircle newMyCircle(double x, double y) {
		countShapeID++;
		return new MyCircle(x, y, countShapeID);
	}
	public CurvedRectangle newCurvedRectangle(double x, double y) {
		countShapeID++;
		return new CurvedRectangle(x, y, countShapeID);
	}

	//-----------下面主要用于生成
	public MyShape product(String kind){
		MyShape shape= product(kind,300,300);
		return shape;
	}
	public MyShape product(String kind,double x,double y,double width,double height){
		MyShape shape = null;
		switch (kind) {
		case "CurvedRectangle":
			shape = newCurvedRectangle(x, y);
			break;
		case "Decision":
			shape = newDecision(x, y);
			break;
		case "InputRectangle":
			shape = newInputRectangle(x, y);
			break;
		case "MyCircle":
			shape = newMyCircle(x, y);
			break;
		case "RoundRectangle":
			shape = newRoundRectangle(x, y);
			break;
		case "MyRectangle":
			shape = newMyRectangle(x, y);
			break;
		default:
			break;
		}
		shape.setWidth(width);
		shape.setHeight(height);
		drawController.regriste(shape);
		return shape;
	}
	public MyShape product(String kind,double x,double y){
		MyShape shape = null;
		switch (kind) {
		case "CurvedRectangle":
			shape = newCurvedRectangle(x, y);
			break;
		case "Decision":
			shape = newDecision(x, y);
			break;
		case "InputRectangle":
			shape = newInputRectangle(x, y);
			break;
		case "MyCircle":
			shape = newMyCircle(x, y);
			break;
		case "RoundRectangle":
			shape = newRoundRectangle(x, y);
			break;
		case "MyRectangle":
			shape = newMyRectangle(x, y);
			break;
		default:
			break;
		}
		drawController.regriste(shape);
		return shape;
	}
	public MyShape produceShapeByImage(String kind){
		return produceShapeByImage(kind, 300, 300);
	}
	public MyShape produceShapeByImage(String kind,double x,double y,double width,double height){
		MyShape shape=null;
		switch (kind) {
		case "RoundedRectangleImage":
			shape = newRoundRectangle(x, y);
			break;
		case "RectangleImage":
			shape = newMyRectangle(x, y);
			break;
		case "DecisionImage":
			shape = newDecision(x, y);
			break;
		case "InputRectangleImage":
			shape = newInputRectangle(x, y);
			break;
		case "CircularImage":
			shape = newMyCircle(x, y);
			break;
		case "CurvedRectangularImage":
			shape = newCurvedRectangle(x, y);
			break;
		default:
			break;
		}
		shape.setWidth(width);
		shape.setHeight(height);
		drawController.regriste(shape);
		return shape;
	}
	public MyShape produceShapeByImage(String kind,double x,double y){
		MyShape shape=null;
		switch (kind) {
		case "RoundedRectangleImage":
			shape = newRoundRectangle(x, y);
			break;
		case "RectangleImage":
			shape = newMyRectangle(x, y);
			break;
		case "DecisionImage":
			shape = newDecision(x, y);
			break;
		case "InputRectangleImage":
			shape = newInputRectangle(x, y);
			break;
		case "CircularImage":
			shape = newMyCircle(x, y);
			break;
		case "CurvedRectangularImage":
			shape = newCurvedRectangle(x, y);
			break;
		default:
			break;
		}
		drawController.regriste(shape);
		return shape;
	}
}
