package model;

import javafx.scene.shape.Polygon;

public class InputRectangle extends MyPolygon{
	public InputRectangle(double x, double y, double width, double height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		polygon = new Polygon();
		setShape();
		setMyShape(polygon);
	}

	@Override
	public void  setShape()
	 {
			double upLeftX = this.x;
			double upLeftY = this.y-height;
			double upRightX = this.x+width;
			double upRightY = upLeftY;
			double downLeftX = this.x-width;
			double downLeftY = this.y + height;
			double downRightX = this.x;
			double downRightY = this.y+height;
			Double []list={upLeftX,upLeftY,upRightX,upRightY,downRightX,downRightY,downLeftX,downLeftY};
			polygon.getPoints().setAll(list);
	}
}
