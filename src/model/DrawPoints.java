package model;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class DrawPoints {
	private Double []list;
	private Circle[] points;
	public  DrawPoints(){
		points = new Circle[4];
		int cnt=0;
		for(int i =0;i<8;i+=2){
			points[cnt] = new Circle();
			points[cnt].setRadius(StandardNum.DRAW_POINTS_RADIUS);
			cnt++;
		}
	}
	public void updataLocation(double leftMidX,double leftMidY,double upMidX,double upMidY,double rightMidX,double rightMidY,double downMidX,double downMidY){
		Double[] list = {leftMidX,leftMidY,upMidX,upMidY,rightMidX,rightMidY,downMidX,downMidY};
		this.list =list;
		int cnt=0;
		for(int i=0;i<8;i+=2){
			points[cnt].setCenterX(list[i]);
			points[cnt].setCenterY(list[i+1]);
			cnt++;
		}
	}
	public void delPoint(Pane pane) {
		pane.getChildren().removeAll(points);
	}
	
	public Circle[] getCircles(){
		return points;
	}
	
	public void setAllVisiable(boolean state){
		for(int i =0;i<points.length;i++){
			points[i].setVisible(state);
		}
	}
}
