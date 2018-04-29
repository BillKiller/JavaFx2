package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.javafx.perf.PerformanceTracker.SceneAccessor;

import controller.DrawController;
import controller.ShapeFactory;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import model.CurvedRectangle;
import model.Decision;
import model.InputRectangle;
import model.MyCircle;
import model.MyLine;
import model.MyRectangle;
import model.MyShape;
import model.RoundRectangle;

public class RootLayoutController implements Initializable {
	@FXML
	private BorderPane rootLayout;
	@FXML
	private AnchorPane drawingArea;
	@FXML
	private AnchorPane shapeArea;

	@FXML
	private TextField textfield;
	@FXML
	private Button Button;
	@FXML
	private ImageView RoundedRectangleImage;
	@FXML
	private ImageView RectangleImage;
	@FXML
	private ImageView DecisionImage;
	@FXML
	private ImageView InputRectangleImage;
	@FXML
	private ImageView CircularImage;
	@FXML
	private ImageView CurvedRectangularImage;


	@FXML
	private TextField textFieldH;
	@FXML
	private TextField textFieldW;
	@FXML
	private TextField textFieldX;
	@FXML
	private TextField textFieldY;

	@FXML
	private TextArea textArea;

	@FXML
	private Button button2;

	private DrawController drawController;
	private PropertyController propertyController;
	ShapeFactory shapeFactory;
	String selectShape = null;


	@FXML
	public void onClick(){
		String kind=textfield.getText();
		MyShape myShape=shapeFactory.product(kind);
		if(myShape !=null){
			myShape.getPane(drawingArea, drawController);
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 初始化改变图形上面的鼠标图案
		RoundedRectangleImage.setCursor(Cursor.HAND);
		RectangleImage.setCursor(Cursor.HAND);
		DecisionImage.setCursor(Cursor.HAND);
		InputRectangleImage.setCursor(Cursor.HAND);
		CircularImage.setCursor(Cursor.HAND);
		CurvedRectangularImage.setCursor(Cursor.HAND);
		drawController=new DrawController(drawingArea);
		shapeFactory=new ShapeFactory(drawingArea,drawController);
		MyLine myLine = new MyLine(500, 500, 300, 200);
		myLine.getPane(drawingArea);

	    propertyController = new PropertyController(textFieldX,textFieldY,textFieldW,textFieldH,textArea);
	    System.out.println(button2);
	    propertyController.setButton(button2);
	    propertyController.edit();
	    drawController.setPropertyController(propertyController);
	    drawingArea.setOnKeyPressed(e->{
	    	System.out.println("asdasd");
	    });
//	    dccrawController.setScene(scene);

//	    \

		// 绘图区域鼠标监听

		drawingArea.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
//				System.out.println("draw");
				if (event.getClickCount() == 1 && selectShape != null) {
					double x, y;
					x = event.getX();
					y = event.getY();
					System.out.println(x + " " + y);
					MyShape myShape = shapeFactory.produceShapeByImage(selectShape, x, y);
					drawController.addDrawArea();
					selectShape = null;
				}
				if(event.getClickCount() ==1 && selectShape == null){
						drawController.getPropertyController().setWorkShape(drawController.workingShape());
						drawController.getPropertyController().update();
				}
				if(event.getClickCount()==2 && selectShape == null){
					drawController.clearAllOnEdit();
					drawController.getPropertyController().setWorkShape(null);
					drawController.getPropertyController().update();
				}
			}
		});

		// 图形区域鼠标监听
		shapeArea.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					if (event.getTarget().getClass() == ImageView.class) {
						int x,y;
						x=300;
						y=300;
						selectShape=((ImageView)event.getTarget()).getId();
						MyShape myShape = shapeFactory.produceShapeByImage(selectShape);
						drawController.addDrawArea();
						selectShape = null;
					}
				} else if (event.getClickCount() == 1) {
					if (event.getTarget().getClass() == ImageView.class) {
						ImageView nowImage = (ImageView) event.getTarget();
						selectShape = nowImage.getId();
					}
				}
			}
		});
	}
}
