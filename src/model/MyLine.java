package model;

import java.text.DecimalFormat;

import controller.DrawController;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Cursor;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class MyLine extends Line {


	public double getSX() {
		return startX;
	}
	public void setSX(double startX) {
		this.startX = startX;
	}
	public double getSY() {
		return startY;
	}
	public void setSY(double startY) {
		this.startY = startY;
	}
	public double getEX() {
		return endX;
	}
	public void setEX(double endX) {
		this.endX = endX;
	}
	public double getEY() {
		return endY;
	}
	public void setEY(double endY) {
		this.endY = endY;
	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	// 图形的工厂编号
	protected int factoryID;
	// 所在区域及其管理者
	protected AnchorPane drawingArea;
	protected DrawController drawController;
	// 坐标信息
	protected double startX;
	protected double startY;
	protected double endX;
	protected double endY;
	protected double lastX;
	protected double lastY;
	// Shape
	protected Polygon triangle;
	protected Line line;
	protected Circle circle;
	//连接的图形
	protected MyShape headLinkShape;
	protected MyShape tailLinkShape;
	// 状态变量
	protected boolean isOnTheLine = false;
	protected boolean isSelected;
	protected BooleanProperty booleanProperty;

	public MyLine() {
		this.booleanProperty = new SimpleBooleanProperty(false);
	}
	public MyLine(double startX, double startY, double endX, double endY,int factoryID) {
		this(startX,startY,endX,endY);
		this.factoryID=factoryID;
	}
	public MyLine(double startX, double startY, double endX, double endY) {
		line = new Line(startX, startY, endX, endY);
		circle = new Circle();
		this.booleanProperty = new SimpleBooleanProperty(false);
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		circle.setCenterX(startX);
		circle.setCenterY(startY);
		circle.setRadius(3);
		line.setStrokeWidth(3);
		triangle = new Polygon();
		setShape();
		startListening();
	}
	public void setHeadLink(MyShape shape) {
		this.headLinkShape=shape;
	}
	public void setTailLink(MyShape shape) {
		this.tailLinkShape=shape;
	}
	public Circle getCircle() {
		return this.circle;
	}
	public Line getLine() {
		return this.line;
	}
	public Polygon getTriangle() {
		return this.triangle;
	}
	public BooleanProperty getBooleanProperty() {
		return booleanProperty;
	}

	public void setBooleanProperty(BooleanProperty booleanProperty) {
		this.booleanProperty = booleanProperty;
	}
	public void setShape() {
		double dx = endX - startX;
		double dy = endY - startY;
		double k = 1 / Math.sqrt(dx * dx + dy * dy);
		double u = (double) Math.sqrt(3) * StandardNum.TRIANBLE_LEN / (Math.sqrt(dx * dx + dy * dy));
		double v = (double) StandardNum.TRIANBLE_LEN / Math.sqrt(dx * dx + dy * dy);
		double mX = endX - u * dx;
		double mY = endY - u * dy;

		double aX = v * dy + mX;
		double aY = v * (-1 * dx) + mY;

		double bX = mX - v * dy;
		double bY = mY - v * (-1 * dx);
		double endX = this.endX + 5 * k * dx;
		double endY = this.endY + 5 * k * dy;
		Double[] list = { aX, aY, endX, endY, bX, bY };
		triangle.getPoints().setAll(list);
		circle.setCenterX(startX);
		circle.setCenterY(startY);
		line.setStartX(startX);
		line.setStartY(startY);
		line.setEndX(this.endX);
		line.setEndY(this.endY);
		isSelected = true;
		booleanProperty.setValue(true);

	}
//	public void setToShape(Double[] list){
//
//	}

	public void getPane(AnchorPane drawingArea, DrawController drawController) {
		drawingArea.getChildren().add(line);
		drawingArea.getChildren().add(circle);
		drawingArea.getChildren().add(triangle);
		this.drawingArea = drawingArea;
		this.drawController = drawController;
	}

	public void setToTop() {
		delete();
		getPane(drawingArea, drawController);
	}

	public void endMove(double x, double y) {
		// 末端的移动是根据绝对位置
		endX = x - triangle.getParent().getLayoutX();
		endY = y - triangle.getParent().getLayoutY();
		setShape();
	}

	protected void move(double dx, double dy) {
		// 此处移动是根据相对位移而不是绝对位置
		startX = startX + dx;
		startY = startY + dy;
		endX = endX + dx;
		endY = endY + dy;
		setShape();
	}

	public void startMove(double x, double y) {
		startX = x - triangle.getParent().getLayoutX();
		startY = y - triangle.getParent().getLayoutY();
		setShape();
	}
	public void delete(){
		drawingArea.getChildren().remove(line);
		drawingArea.getChildren().remove(circle);
		drawingArea.getChildren().remove(triangle);
	}
	public void changeListener() {
		booleanProperty.addListener(e -> {
			if (booleanProperty.getValue() == false) {
				// 如果物体发生改变说明这个物体是当前工作的Shape，此时右侧的属性栏显示这个Shape的属性
				drawController.getPropertyController().setWorkShape(this);
				drawController.getPropertyController().update();
				drawController.saveChange();
			}
		});
	}
	protected void startListening() {
		changeListener();
		triangle.setCursor(Cursor.HAND);
		circle.setCursor(Cursor.HAND);
		line.setCursor(Cursor.MOVE);
		triangle.setOnMouseDragged(e -> {
			e.setDragDetect(true);
			drawController.checkDistanceToPoints(e.getX(), e.getY());
			endMove(e.getX(), e.getY());
		});
		triangle.setOnMouseReleased(e -> {
			this.setToTop();
			if(tailLinkShape!=null)tailLinkShape.delConnectionInfo(this);
			drawController.connect(e.getX(),e.getY(),"end",this);
			booleanProperty.setValue(false);
		});
		circle.setOnMouseDragged(e -> {
			drawController.checkDistanceToPoints(e.getX(), e.getY());
			startMove(e.getX(), e.getY());
		});
		circle.setOnMouseReleased(e -> {
			this.setToTop();
			booleanProperty.setValue(false);
			if(headLinkShape!=null)headLinkShape.delConnectionInfo(this);
			drawController.connect(e.getX(),e.getY(),"start",this);
		});
		/*
		 * 直线的旋转和放缩比较简单就是根据三角形的位置来进行调整，直接设置末端为鼠标当前位置即可 直线的平移比较复杂，具体实现如下：
		 * 记录鼠标开始的位置，当鼠标移动到line里面的时候记录，如果鼠标不移出那么不改变上一次位置 当鼠标移动的时候产生新的移动位置，dx =
		 * e.getX()-lastX,dy = getY()-lastY; 根据鼠标移动的位移对两个端点进行相应的平移
		 */
		line.setOnMouseEntered(e -> {
			if (!isOnTheLine) {
				lastX = e.getX();
				lastY = e.getY();
				isOnTheLine = true;
			}
		});
		line.setOnMouseExited(e -> {
			isOnTheLine = false;
		});
		line.setOnMouseDragged(e -> {
			double dx = e.getX() - lastX;
			double dy = e.getY() - lastY;
			lastX = e.getX();
			lastY = e.getY();
			move(dx, dy);
			isSelected = false;
		});
		line.setOnMouseReleased(e->{
			this.setToTop();
			if(headLinkShape!=null)headLinkShape.delConnectionInfo(this);
			if(tailLinkShape!=null)tailLinkShape.delConnectionInfo(this);
			if (isSelected == false) {
				drawController.clearAllOnEdit();
				isSelected = true;
			} else {
				isSelected = false;
			}
		});
	}
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.00");
		String tostring = getClass().getSimpleName() + "(" + df.format(this.startX) + "," + df.format(startY) + "," + df.format(endX) + ","
				+ df.format(endY) + ")" + "[ " +" " + " ]" + " ;\n";
		return tostring;
	}
}
