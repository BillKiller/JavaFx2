package model;

import java.util.Set;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class MyLine extends Line {
	protected double startX;
	protected double startY;
	protected double endX;
	protected double endY;
	private Polygon triangle;
	private Line line;
	public MyLine(double startX,double startY,double endX,double endY){
		line = new Line(startX, startY, endX, endY);
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		triangle = new Polygon();
		setShape();

	}
	public void setShape(){

//		triangle.setRotate(angle*350);
	}

	public void getPane(Pane pane){
		pane.getChildren().add(line);
		pane.getChildren().add(triangle);
	}
}
