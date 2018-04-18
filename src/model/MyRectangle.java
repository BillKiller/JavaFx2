package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MyRectangle extends  MyShape {
    private Rectangle rectangle;
    public MyRectangle(){
        this(500,500,50,50);
    }
    public MyRectangle(double x,double y,double width,double height){
        super(x+width/2,y+height/2,width/2,height/2);
        this.rectangle=new Rectangle(x,y,width,height);
        this.rectangle.setFill(Color.WHITE);
        this.rectangle.setStroke(Color.BLACK);
        super.setMyShape(this.rectangle);
    }
    public void setX(double x){
        rectangle.setX(x);
    }
    public void setY(double y){
        rectangle.setY(y);
    }
    public void setWidth(double width){
        this.width=width;
        rectangle.setWidth(width*2);
    }
    public  void setHeight(double height){
        this.height=height;
        rectangle.setHeight(height*2);
    }
    @Override
    public void Move(double x,double y){
        super.Move(x,y);
        rectangle.setX(leftX);
        rectangle.setY(leftY);
    }
	@Override
	public void setSize() {
		// TODO Auto-generated method stub
		
	}
}
