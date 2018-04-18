package model;

import javafx.css.*;
import javafx.scene.shape.Rectangle;

public class RoundRectangle extends MyRectangle {
    Rectangle roundrectangle;
    public  RoundRectangle(){
        super();
    }
    public RoundRectangle(double x,double y,double width,double height){
        super(x,y,width,height);
        roundrectangle=((Rectangle)getShape());
        //roundrectangle.getStyleClass().add("RoundRectangle");
        roundrectangle.setStyle("-fx-arc-height: 20;-fx-arc-width: 20;");
    }
}
