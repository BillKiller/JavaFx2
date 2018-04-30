package model;

import javafx.css.*;
import javafx.scene.shape.Rectangle;

public class RoundRectangle extends MyRectangle {
    Rectangle roundrectangle;
    public  RoundRectangle(double x,double y,int id){
    	this(x,y,100,50);
    	this.factoryID=id;
    }
    public RoundRectangle(double x,double y,double width,double height){
        super(x,y,width,height);
        roundrectangle=((Rectangle)getShape());
        roundrectangle.setStyle("-fx-arc-height: 50;-fx-arc-width: 50;");
    }
}
