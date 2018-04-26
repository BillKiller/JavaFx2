package controller;

import model.CurvedRectangle;
import model.Decision;
import model.InputRectangle;
import model.MyCircle;
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
}
