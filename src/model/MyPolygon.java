package model;

import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

public abstract class MyPolygon extends MyShape {

	protected Polygon polygon;

	public MyPolygon(double x, double y, double width, double height) {
		super(x, y, width, height);
		polygon = new Polygon();
	    setMyShape(polygon);
	}

	public void setX(double x) {
		this.x = x;
		setShape();
	}

	public void setY(double y) {
		this.y = y;
		setShape();
	}

	public void setWidth(double width) {
		this.width = width;
		setShape();
	}

	public void setHeight(double height) {
		this.height = height;
		setShape();
	}

	@Override
	public void Move(double x, double y) {
		super.Move(x, y);
		setShape();
	}

	public abstract void setShape();
}
