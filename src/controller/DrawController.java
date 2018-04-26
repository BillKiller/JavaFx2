package controller;

import java.awt.List;
import java.util.Observable;

import javax.swing.plaf.synth.SynthStyle;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import model.MyLine;
import model.MyShape;

public class DrawController {
	AnchorPane drawArea = null;
	public DrawController(AnchorPane drawArea) {
		this.drawArea=drawArea;
	}
//	public void setEndPointOn(MyShape targetShape,MyLine line,int x,int y) {
////		int index=drawArea.getChildren().indexOf((Object)targetShape);
//		
//	}
//	public void activeShape(MyShape nowShape) {
//		System.out.println(nowShape.getClass());
//		nowShape.removeFromPane(drawArea);
//		nowShape.getPane(drawArea, this);
////		System.out.println(nowShape.getFactoryID());
////		ObservableList<Node> children=drawArea.getChildren();
////		for(Node ch:children) {
////			
////		}
////		drawArea.getChildren().remove(nowShape.getShape());
////		nowShape.getEditer()
////		nowShape.getPane(pane, drawController)
//	}
}
