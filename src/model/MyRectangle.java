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
        rectangle.setX(leftX);
    }
    public void setY(double y){
        rectangle.setY(leftY);
    }
    public void setWidth(double width){
        this.width=width;
        rectangle.setWidth(2*width);
    }
    public  void setHeight(double height){
        this.height=height;
        rectangle.setHeight(2*height);
    }
    @Override
    public void Move(double x,double y){
        super.Move(x,y);
        rectangle.setX(leftX);
        rectangle.setY(leftY);
    }
}
