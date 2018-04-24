package model;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public abstract class MyShape{
	// 中心坐标
	protected double x;
	protected double y;
	// 左上角坐标
	protected double leftX;
	protected double leftY;
	// 右下角坐标
	protected double rightX;
	protected double rightY;
	// 半长宽
	protected double width;
	protected double height;

	// 状态
	private boolean isDrag = false;
	private boolean isZoom = false;
	private boolean isSelected = false;
	// 内容
	private Shape shape;
	private Editer editer;
	private Status status;
	// 放缩RESIZE使用使用的常量
	private final static int minReSize = 5;
	private final static int[][] RESIZ_DRECTION = { { 0, -1, -1 }, { 1, -1, 0 }, { 2, -1, 1 }, { 3, 0, -1 },
			{ 4, 0, 0 }, { 5, 0, 1 }, { 6, 1, -1 }, { 7, 1, 0 }, { 8, 1, 1 } };
	private final static Cursor[] hand = { Cursor.NW_RESIZE, Cursor.W_RESIZE, Cursor.SW_RESIZE, Cursor.N_RESIZE,
			Cursor.MOVE, Cursor.S_RESIZE, Cursor.NE_RESIZE, Cursor.E_RESIZE, Cursor.SE_RESIZE };
	private Group pane;

	// --getter and setter
	public Shape getShape() {
		return this.shape;
	}

	public Status getStatus() {
		return status;
	}

	public void setMyShape(Shape shape) {
		this.shape = shape;
		this.status = new Status();
		this.editer = new Editer(this.x, this.x, height, width);
		pane = new Group();
		pane.setCursor(Cursor.CLOSED_HAND);
		pane.getChildren().add(shape);
		addListener();
	}

	public Editer getEditer() {
		return editer;
	}

	public void setEditer(Editer editer) {
		this.editer = editer;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getLeftX() {
		return leftX;
	}

	public void setLeftX(double leftX) {
		this.leftX = leftX;
	}

	public double getLeftY() {
		return leftY;
	}

	public void setLeftY(double leftY) {
		this.leftY = leftY;
	}

	// -----------constructor------------------------
	public MyShape(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		leftX = x - width;
		leftY = y - height;
		rightX = x + width;
		rightY = y + height;
	}

	public MyShape getPane(Pane pane) {
		editer.addEditer(pane);
		pane.getChildren().add(shape);
		return this;
	}

	/*
	 * ---listener --editer listener --cursor listener --resize listener --move
	 * listener
	 *
	 */
	public void addListener() {
		setOnDrag();
		setOnRealse();
		resizeCursorListener();
		resizeListener();
		moveHandListener();
	}

	public void setOnDrag() {
		shape.setOnMouseDragged(e -> {
			Move(e.getX(), e.getY());
			editer.show(x, y);
			editer.disapperCircle();
			isSelected = false;
		});
	}

	public void setOnRealse() {
		shape.setOnMouseClicked(e -> {
			status.setRelease();
			if (isSelected == false) {
				isSelected = true;
				editer.show(x, y);
			} else {
				isSelected = false;
				editer.disapper();
			}
		});
	}

	public void updateLocation(double x, double y) {
		this.x = x;
		this.y = y;
		this.rightX = x + width;
		this.rightY = y + height;
		this.leftX = x - width;
		this.leftY = y - height;
	}

	public void Move(double x, double y) {
		double posX = x - this.getShape().getParent().getLayoutX();
		double posY = y - this.getShape().getParent().getLayoutY();
		updateLocation(posX, posY);
	}

	/*
	 * Cursor listener for shape cursor will be changed to moveHand for mouse on
	 * editer(which is nine small circle) will be changed to RESIZEhand
	 *
	 */
	private void resizeCursorListener() {
		Point[] circles = editer.getCircles();
		for (int i = 0; i < circles.length; i++) {

			circles[i].setPosid(i);
			circles[i].setLeftX(RESIZ_DRECTION[i][0]);
			circles[i].setDirectionX(RESIZ_DRECTION[i][1]);
			circles[i].setDirectionY(RESIZ_DRECTION[i][2]);

			// 设置鼠标形状
			circles[i].setCursor(hand[i]);
		}
	}

	private void moveHandListener() {
		getShape().setCursor(Cursor.MOVE);
	}

	// Resize listener
	protected void resizeShape(int posId, double dx, double dy) {
		if (width + dx >= 0 && height + dy >= 0) {
			// 计算矩形的变换
			this.width = width + dx;
			this.height = height + dy;
			if (posId <= 1) {
				this.x = rightX - width;
				this.y = rightY - height;
			} else {
				if (posId == 2) {
					this.x = rightX - width;
					this.y = leftY + height;
				} else {
					if (posId == 3) {
						this.x = rightX - width;
						this.y = rightY - height;
					} else {
						if (posId == 6) {
							this.x = leftX + width;
							this.y = rightY - height;
						} else {
							this.x = leftX + width;
							this.y = leftY + height;
						}
					}
				}
			}
			// 生成变换效果
			updateLocation(this.x, this.y);
			getEditer().setHeight(height + 10);
			getEditer().setWidth(width + 10);
			// 矩形变换效果
			this.setX(leftX);
			this.setY(leftY);
			this.setWidth(width);
			this.setHeight(height);
			// 生成编辑框的变换效果
		}
	}

	private void resizeListener() {
		Circle[] circles = editer.getCircles();
		for (int i = 0; i < circles.length; i++) {
			circles[i].setOnMouseDragged(e -> {
				Point point = ((Point) (e.getSource()));
				double radius = point.getRadius();
				double ox = point.getCenterX();
				double oy = point.getCenterY();
				double dx = (e.getX() - ox);
				double dy = (e.getY() - oy);
				int posId = point.getPosid();
				dx = dx * ((Point) (e.getSource())).getDirectionX();
				dy = dy * ((Point) (e.getSource())).getDirectionY();
				dx = dx / 5;
				dy = dy / 5;

				int leftX = point.getLeftX();
				int leftY = point.getLeftY();

				if (radius * radius <= ((dx * dx) + dy * dy)) {
					resizeShape(posId, dx, dy);
					editer.show(this.x, this.y);
				}
			});
		}
	}
}
