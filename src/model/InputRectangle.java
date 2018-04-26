package model;

import javafx.scene.shape.Polygon;

public class InputRectangle extends MyPolygon{
	public InputRectangle(double x, double y,int id) {
		super(x, y, 100,50);
		this.factoryID=id;
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
