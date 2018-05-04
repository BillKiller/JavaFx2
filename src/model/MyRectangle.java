package model;

import java.text.DecimalFormat;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MyRectangle extends  MyShape {
    private Rectangle rectangle;
    public MyRectangle(double x,double y,int id){
        this(x,y,100,50);
        this.factoryID=id;
    }
    public MyRectangle(double x,double y,double width,double height){
        super(x,y,width,height);
        this.rectangle=new Rectangle(x-width,y-height,2*width,2*height);
        this.rectangle.setFill(Color.WHITE);
        this.rectangle.setStroke(Color.BLACK);
        super.setMyShape(this.rectangle);
    }
    public void setX(double x){
    	this.x =x;
    	 updateRectangle();
        rectangle.setX(x-width);
    }
    public void setY(double y){
        this.y = y;
        updateRectangle();
    	rectangle.setY(y-height);
    }
    public void setWidth(double width){
        this.width=width;
        this.x = leftX+width;
    	this.y = leftY+height;
        rectangle.setWidth(2*width);
        updateRectangle();
    }
    public  void setHeight(double height){
    	this.height=height;
    	this.x = leftX+width;
    	this.y = leftY+height;
    	rectangle.setHeight(2*height);
        updateRectangle();
    }
    @Override
    public void Move(double x,double y){
        super.Move(x,y);
        rectangle.setX(x-width);
        rectangle.setY(y-height);
    }
    public void updateRectangle(){
    		this.leftX = x -width;
    		this.leftY = y - height;
    		updateLocation(this.x, this.y);
    }
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.000");
		String tostring = getClass().getSimpleName() + "(" + df.format(this.x) + "," + df.format(this.y) + "," + df.format(this.width) + ","
				+ df.format(this.height) + ")" + "[ " + text.getText() + " ]" + " ;\n";
		return tostring;
	}
}
