package model;

import controller.DrawController;
import javafx.scene.Cursor;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class MyLine extends Line {
	// ͼ�εĹ������
	protected int factoryID;
	// ���������������
	protected AnchorPane drawingArea; 
	protected DrawController drawController;
	// ������Ϣ
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
	//���ӵ�ͼ��  
	protected MyShape headLinkShape;
	protected MyShape tailLinkShape;	
	// ״̬����
	protected boolean isOnTheLine = false;
	
	public MyLine() {}
	public MyLine(double startX, double startY, double endX, double endY) {
		line = new Line(startX, startY, endX, endY);
		circle = new Circle();
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
		drawingArea.getChildren().remove(line);
		drawingArea.getChildren().remove(circle);
		drawingArea.getChildren().remove(triangle);
		getPane(drawingArea, drawController);
	}

	public void endMove(double x, double y) {
		// ĩ�˵��ƶ��Ǹ��ݾ���λ��
		endX = x - triangle.getParent().getLayoutX();
		endY = y - triangle.getParent().getLayoutY();
		setShape();
	}

	protected void move(double dx, double dy) {
		// �˴��ƶ��Ǹ������λ�ƶ����Ǿ���λ��
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

	protected void startListening() {
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
		});
		circle.setOnMouseDragged(e -> {
			drawController.checkDistanceToPoints(e.getX(), e.getY());
			startMove(e.getX(), e.getY());
		});
		circle.setOnMouseReleased(e -> {
			this.setToTop();
			if(headLinkShape!=null)headLinkShape.delConnectionInfo(this);
			drawController.connect(e.getX(),e.getY(),"start",this);
		});
		/*
		 * ֱ�ߵ���ת�ͷ����Ƚϼ򵥾��Ǹ��������ε�λ�������е�����ֱ������ĩ��Ϊ��굱ǰλ�ü��� ֱ�ߵ�ƽ�ƱȽϸ��ӣ�����ʵ�����£�
		 * ��¼��꿪ʼ��λ�ã�������ƶ���line�����ʱ���¼�������겻�Ƴ���ô���ı���һ��λ�� ������ƶ���ʱ������µ��ƶ�λ�ã�dx =
		 * e.getX()-lastX,dy = getY()-lastY; ��������ƶ���λ�ƶ������˵������Ӧ��ƽ��
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
		});
		line.setOnMouseReleased(e->{
			this.setToTop();
			if(headLinkShape!=null)headLinkShape.delConnectionInfo(this);
			if(tailLinkShape!=null)tailLinkShape.delConnectionInfo(this);
		});
	}
}
