package view;

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
import javafx.scene.shape.Shape;
import model.MyLine;
import model.MyRectangle;
import model.MyShape;
import model.RoundRectangle;

public class RootLayoutController implements Initializable {
	@FXML
	private BorderPane rootLayout;
	@FXML
	private AnchorPane DrawingArea;
	@FXML
	private AnchorPane ShapeArea;

	@FXML
	private ImageView RoundedRectangleImage;
	@FXML
	private ImageView RectangleImage;
	@FXML
	private ImageView PrismImage;
	@FXML
	private ImageView ParallelogramImage;
	@FXML
	private ImageView CircularImage;
	@FXML
	private ImageView CurvedRectangularImage;

	DrawController drawController=new DrawController(DrawingArea);
	ShapeFactory shapeFactory=new ShapeFactory();
	String selectShape = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 初始化改变图形上面的鼠标图案
		RoundedRectangleImage.setCursor(Cursor.HAND);
		RectangleImage.setCursor(Cursor.HAND);
		PrismImage.setCursor(Cursor.HAND);
		ParallelogramImage.setCursor(Cursor.HAND);
		CircularImage.setCursor(Cursor.HAND);
		CurvedRectangularImage.setCursor(Cursor.HAND);
		Button button = new Button();
		MyLine myLine = new MyLine(500, 500, 300, 200);
		myLine.getPane(DrawingArea);
		// 绘图区域鼠标监听
		DrawingArea.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
						now.getPane(DrawingArea,drawController);
					} else if (selectShape.equals("RectangleImage")) {
						MyRectangle now = shapeFactory.newMyRectangle(x, y);
						now.getPane(DrawingArea,drawController);
						System.out.println(DrawingArea+"haha");
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
		ShapeArea.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("shape");
				if (event.getClickCount() == 2) {
					if (event.getTarget().getClass() == ImageView.class) {
						ImageView nowImage = (ImageView) event.getTarget();
						if (nowImage.getId().equals("RoundedRectangleImage")) {
							RoundRectangle now = shapeFactory.newRoundRectangle(DrawingArea.getWidth() / 5,
									DrawingArea.getHeight() / 5);
							now.getPane(DrawingArea,drawController);
						} else if (nowImage.getId().equals("RectangleImage")) {
							MyRectangle now = shapeFactory.newMyRectangle(DrawingArea.getWidth() / 5, DrawingArea.getHeight() / 5);
							now.getPane(DrawingArea,drawController);
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
