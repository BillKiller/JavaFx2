package controller;

import model.MyRectangle;
import model.RoundRectangle;

public class ShapeFactory {
	private int countShapeID = 0;
	public MyRectangle newMyRectangle(double x, double y) {
		countShapeID++;
		return new MyRectangle(x, y, countShapeID);
	}
	public RoundRectangle newRoundRectangle(double x, double y) {
		countShapeID++;
		return new RoundRectangle(x, y, countShapeID);
	}
}
