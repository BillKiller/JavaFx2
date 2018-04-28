package model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class Editer {
	//这个类是修改框类，有8个点，当鼠标啊放在上面的时候，会相应改变鼠标形状
	private Point []circles;
	private double x;
	private double y;
	private double width;
	private double height;
	private Line []lines;
	private int OnClick=0;
	public Point[] getCircles(){
		return this.circles;
	}
	public Editer(double x,double y,double height,double width){
			this.x=x;
			this.y=y;
			this.height=height+10;
			this.width=width+10;
			initLine(x,y);
			initCicrle(x,y);
			disapperCircle();
			disapperLine();
	}

	//getter--and --setter

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}

	public int getOnClick() {
		return OnClick;
	}

	public void setOnClick(int onClick) {
		OnClick = onClick;
	}
	//init start

	private  void initCicrle(double x,double y){
		//这些圆是根据中心坐标实现的
		circles =new Point[9];
		for(int i =0;i<9;i++){
			circles[i] = new Point(x,y,5);
			circles[i].setFill(Color.BLUE);
		}
	}
	private void initLine(double x,double y){
		lines = new Line[8];
		lines[0]=new Line(x,y,x+width,y);
		lines[1]=new Line(x,y,x+2*width,y);
		lines[2]=new Line(x,y,x,y+height);
		lines[3]=new Line(x,y,x,y+2*height);
		lines[4]=new Line(x,y+2*height,x+width,y+2*height);
		lines[5]=new Line(x+width,y+2*height,x+2*width,y+2*height);
		lines[6]=new Line(x+2*width,y,x+2*width,y+height);
		lines[7]=new Line(x+2*width,y+height,x+2*width,y+2*height);
	}
	private void lineMove(double x,double y) {
		x=x-width;
		y=y-height;
		double pos[][]={{x,y,x+width,y},{x,y,x+2*width,y},{x,y,x,y+height},{x,y,x,y+2*height},{x,y+2*height,x+width,y+2*height},{x+width,y+2*height,x+2*width,y+2*height},{x+2*width,y,x+2*width,y+height},{x+2*width,y+height,x+2*width,y+2*height}};
		for(int i =0;i<8;i++) {
			lines[i].setStartX(pos[i][0]);
			lines[i].setStartY(pos[i][1]);
			lines[i].setEndX(pos[i][2]);
			lines[i].setEndY(pos[i][3]);
		}
	}
	public void  addEditer(Pane pane){
		pane.getChildren().addAll(circles);
		pane.getChildren().addAll(lines);
	}
	public void delEditer(Pane pane) {
		pane.getChildren().removeAll(circles);
		pane.getChildren().removeAll(lines);
	}
	//init end
	public void  show(double x,double y){
		lineMove(x,y);
		for(int i =0;i<3;i++){
			for(int j =0;j<3;j++){
				circles[3*i+j].setCenterX(x+width*(i-1));
				circles[3*i+j].setCenterY(y+height*(j-1));
				circles[3*i+j].setRadius(5);
				if(i==1&&j==1){
					circles[3*i+j].setRadius(0);
				}
			}
		}

	}
	public void  disapperCircle(){
		for(int i =0;i<3;i++){
			for(int j =0;j<3;j++){
				circles[3*i+j].setRadius(0);
			}
		}
	}
	public void disapperLine(){
		for(int i =0;i<8;i++){
			lines[i].setStartX(0);
			lines[i].setStartY(0);
			lines[i].setEndX(0);
			lines[i].setEndY(0);
		}
	}
	public void disapper(){
		disapperCircle();
		disapperLine();
	}
}
