package view;

import java.awt.Image;
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
import javafx.scene.control.Button;
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

	DrawController drawController=new DrawController(drawingArea);
	ShapeFactory shapeFactory=new ShapeFactory();
	String selectShape = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 初始化改变图形上面的鼠标图案
		RoundedRectangleImage.setCursor(Cursor.HAND);
		RectangleImage.setCursor(Cursor.HAND);
		DecisionImage.setCursor(Cursor.HAND);
		InputRectangleImage.setCursor(Cursor.HAND);
		CircularImage.setCursor(Cursor.HAND);
		CurvedRectangularImage.setCursor(Cursor.HAND);
		
		MyLine myLine = new MyLine(500, 500, 300, 200);
		myLine.getPane(drawingArea);
		
		// 绘图区域鼠标监听
		drawingArea.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("draw");
				if (event.getClickCount() == 1 && selectShape != null) {
					double x, y;
					x = event.getX();
					y = event.getY();
					System.out.println(x + " " + y);
					// 未完成：根据selectShape，在图片中心创建相应图形
					if (selectShape. equals("RoundedRectangleImage")) {
						RoundRectangle now = shapeFactory.newRoundRectangle(x, y);
						now.getPane(drawingArea,drawController);
					} else if (selectShape.equals("RectangleImage")) {
						MyRectangle now = shapeFactory.newMyRectangle(x, y);
						now.getPane(drawingArea,drawController);
					}else if(selectShape.equals("DecisionImage")) {
						Decision now = shapeFactory.newDecision(x,y);
						now.getPane(drawingArea, drawController);
					}else if(selectShape.equals("InputRectangleImage")) {
						InputRectangle now =shapeFactory.newInputRectangle(x,y);
						now.getPane(drawingArea, drawController);
					}else if(selectShape.equals("CircularImage")) {
						MyCircle now =shapeFactory.newMyCircle(x, y);
						now.getPane(drawingArea, drawController);
					}else if(selectShape.equals("CurvedRectangularImage")) {
						CurvedRectangle now = shapeFactory.newCurvedRectangle(x,y);
						now.getPane(drawingArea, drawController);
					}
					selectShape = null;
				}
//				System.out.println(event.getTarget().getClass());
//				if(event.getTarget() instanceof Shape) {
//					System.out.println("????????");
//					System.out.println(((MyShape)event.getTarget()).getFactoryID());
//					drawController.activeShape((MyShape)event.getTarget());
//				}
			}
		});

		// 图形区域鼠标监听
		shapeArea.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("shape");
				if (event.getClickCount() == 2) {
					if (event.getTarget().getClass() == ImageView.class) {
						int x,y;
						x=300;
						y=300;
						selectShape=((ImageView)event.getTarget()).getId();
						if (selectShape. equals("RoundedRectangleImage")) {
							RoundRectangle now = shapeFactory.newRoundRectangle(x, y);
							now.getPane(drawingArea,drawController);
						} else if (selectShape.equals("RectangleImage")) {
							MyRectangle now = shapeFactory.newMyRectangle(x, y);
							now.getPane(drawingArea,drawController);
						}else if(selectShape.equals("DecisionImage")) {
							Decision now = shapeFactory.newDecision(x,y);
							now.getPane(drawingArea, drawController);
						}else if(selectShape.equals("InputRectangleImage")) {
							InputRectangle now =shapeFactory.newInputRectangle(x,y);
							now.getPane(drawingArea, drawController);
						}else if(selectShape.equals("CircularImage")) {
							MyCircle now =shapeFactory.newMyCircle(x, y);
							now.getPane(drawingArea, drawController);
						}else if(selectShape.equals("CurvedRectangularImage")) {
							CurvedRectangle now = shapeFactory.newCurvedRectangle(x,y);
							now.getPane(drawingArea, drawController);
						}
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
