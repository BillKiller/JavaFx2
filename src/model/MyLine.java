package model;

import javafx.scene.Cursor;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class MyLine extends Line {

	protected double startX;
	protected double startY;
	protected double endX;
	protected double endY;
	private double lastX;
	private double lastY;
	// Shape
	private Polygon triangle;
	private Line line;
	private Circle circle;
	// ״̬����
	private boolean isOnTheLine = false;

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

	public void getPane(Pane pane) {
		pane.getChildren().add(line);
		pane.getChildren().add(circle);
		pane.getChildren().add(triangle);
	}

	private void endMove(double x, double y) {
		// ĩ�˵��ƶ��Ǹ��ݾ���λ��
		endX = x - triangle.getParent().getLayoutX();
		endY = y - triangle.getParent().getLayoutY();
		setShape();
	}

	private void move(double dx, double dy) {
		// �˴��ƶ��Ǹ������λ�ƶ����Ǿ���λ��
		startX = startX + dx;
		startY = startY + dy;
		endX = endX + dx;
		endY = endY + dy;
		setShape();
	}

	private void startMove(double x, double y) {
		startX = x - triangle.getParent().getLayoutX();
		startY = y - triangle.getParent().getLayoutY();
		setShape();
	}

	private void startListening() {
		triangle.setCursor(Cursor.E_RESIZE);
		triangle.setOnMouseDragged(e -> {
			endMove(e.getX(), e.getY());
		});
//		triangle.setOnMouseReleased(e ->{
//			Shape nowShape=(Shape)e.getTarget();
//			
//		});
		circle.setCursor(Cursor.E_RESIZE);
		circle.setOnMouseDragged(e -> {
			startMove(e.getX(), e.getY());
		});
		/*
		 * ֱ�ߵ���ת�ͷ����Ƚϼ򵥾��Ǹ��������ε�λ�������е�����ֱ������ĩ��Ϊ��굱ǰλ�ü��� ֱ�ߵ�ƽ�ƱȽϸ��ӣ�����ʵ�����£�
		 * ��¼��꿪ʼ��λ�ã�������ƶ���line�����ʱ���¼�������겻�Ƴ���ô���ı���һ��λ�� ������ƶ���ʱ������µ��ƶ�λ�ã�dx =
		 * e.getX()-lastX,dy = getY()-lastY; ��������ƶ���λ�ƶ������˵������Ӧ��ƽ��
		 *
		 */
		line.setCursor(Cursor.HAND);
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
	}
}
