package model;

import javafx.scene.text.Text;

public class Status extends Text {
	//这是状态框，用于拖动的时候显示坐标
	private double x;
	private double y;
	private boolean isDrag;
	private boolean isZoom;
	private boolean isRotate;
	//setter
	public void setDrag() {
		this.setText("x:"+x+","+"y:"+y);
	}
	public void setRelease(){
		this.setText("");
	}
	public void setZoom(boolean isZoom) {
		this.isZoom = isZoom;
	}
	public void setRotate(boolean isRotate) {
		this.isRotate = isRotate;
	}
	public void  Move(double x,double y){
		this.x=x;
		this.y=y+20;
		super.setX(this.x);
		super.setY(this.y);
	}
}
