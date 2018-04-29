package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MyRectangle extends  MyShape {
    private Rectangle rectangle;
    public MyRectangle(double x,double y,int id){
        this(x,y,100,100);
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
        rectangle.setWidth(2*width);
        updateRectangle();
    }
    public  void setHeight(double height){
    	this.height=height;
    	updateRectangle();
    	rectangle.setHeight(2*height);
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
}
