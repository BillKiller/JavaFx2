package model;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

public class CurvedRectangle extends MyPolygon {

	public CurvedRectangle(double x, double y,int id) {
		super(x, y, 100,50);
		this.factoryID=id;
		polygon = new Polygon();
		setMyShape(polygon);
		setShape();
	}

	@Override
	public void setShape() {
		leftX = this.x-width;
		leftY = this.y -height;
		double downLeftX = leftX;
		double downLeftY = this.y + 3.0 * height / 4;
		double upLeftX = leftX;
		double upLeftY = leftY;
		double upRightX = this.x + width;
		double upRightY = this.y - height;
		double downRightX = this.x + width;
		double downRightY = this.y + height;

		//
		double x1 = downLeftX + width / 2;
		double y = downLeftY;
		double x2 = this.x + width / 2;
		double radius = width / 2;

		double A = height / 4;

		double dx = 2 * width / (StandardNum.ARC_NUM);
		double p = Math.PI / width;
		double x = 0;
		Double[] list = new Double[2 * StandardNum.ARC_NUM + 6];
		for (int i = 0; i <= 2 * StandardNum.ARC_NUM; i += 2) {
			y = A * Math.sin(p * x);
			list[i] = downLeftX + x;
			list[i + 1] = downLeftY + y;
			x = x + dx;
		}
		list[2 * StandardNum.ARC_NUM + 2] = upRightX;
		list[2 * StandardNum.ARC_NUM + 3] = upRightY;
		list[2 * StandardNum.ARC_NUM + 4] = upLeftX;
		list[2 * StandardNum.ARC_NUM + 5] = upLeftY;
		polygon.getPoints().setAll(list);
	}

}
