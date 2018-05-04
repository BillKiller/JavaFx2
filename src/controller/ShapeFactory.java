package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import model.BrokenLine;
import model.CurvedRectangle;
import model.Decision;
import model.InputRectangle;
import model.MyCircle;
import model.MyLine;
import model.MyRectangle;
import model.MyShape;
import model.RoundRectangle;

public class ShapeFactory {
	private int countShapeID = 0;
	private AnchorPane drawingArea;
	private DrawController drawController;

	/*
	 * 工厂是多入口单出口的
	 */
	public ShapeFactory(AnchorPane drawingArea, DrawController drawController) {
		this.drawingArea = drawingArea;
		this.drawController = drawController;
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

	public MyLine newMyLine(double sx, double sy, double ex, double ey) {
		countShapeID++;
		return new MyLine(sx, sy, ex, ey);
	}

	public MyLine newDogLegLine(double sx, double sy, double ex, double ey) {
		countShapeID++;
		return new BrokenLine(sx, sy, ex, ey);
	}

	public DrawController getDrawController() {
		return drawController;
	}

	public void setDrawController(DrawController drawController) {
		this.drawController = drawController;
	}
	// -----------下面主要用于生成

	public void produce(String kind, double x, double y,double width,double height) {
		kind = kind.replaceAll("Image", "");
		if (kind.indexOf("Line") != -1) {
			System.out.println("Line" + kind.indexOf("Line"));
			produceMyLine(kind, x, y,width,height);
		} else {
			System.out.println("hehe"+kind);
			productMyShape(kind, x, y,width,height);
		}
	}
	public void produce(String kind, double x, double y) {
		kind = kind.replaceAll("Image", "");
		if (kind.indexOf("Line") != -1) {
			System.out.println("Line" + kind.indexOf("Line"));
			produceMyLine(kind, x, y);
		} else {
			System.out.println("hehe"+kind);
			productMyShape(kind, x, y);
		}
	}

	public MyShape productMyShape(String kind) {
		MyShape shape = productMyShape(kind, 300, 300);
		return shape;
	}

	public MyShape productMyShape(String kind, double x, double y, double width, double height) {
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

	public MyShape productMyShape(String kind, double x, double y) {
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
		case "Circular":
			shape = newMyCircle(x, y);
			break;
		case "RoundRectangle":
			shape = newRoundRectangle(x, y);
			break;
		case "Rectangle":
			shape = newMyRectangle(x, y);
			break;
		default:
			break;
		}
		// 生产完了的产品要交给管理者登记进入列表方便以后管理
		drawController.regriste(shape);
		return shape;
	}

	public MyLine produceMyLine(String kind, double x, double y) {
		MyLine shape = null;
		switch (kind) {
		case "StraightLineImage":
			shape = newMyLine(x, y, x, y + 100);
			break;
		case "BrokenLine":
			shape = newDogLegLine(x, y, x, y + 100);
			break;
		default:
			break;
		}
		drawController.regristeLine(shape);
		return shape;
	}
	public MyLine produceMyLine(String kind, double x, double y,double ex, double ey) {
		MyLine shape = null;
		switch (kind) {
		case "StraightLineImage":
			shape = newMyLine(x, y, ex, ey);
			break;
		case "BrokenLine":
			shape = newDogLegLine(x, y, ex, ey);
			break;
		default:
			break;
		}
		drawController.regristeLine(shape);
		return shape;
	}
}
