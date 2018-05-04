package model;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.omg.CORBA.FloatSeqHelper;
import org.omg.PortableInterceptor.IORInfoOperations;

import com.sun.media.jfxmedia.control.VideoDataBuffer;

import controller.DrawController;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public abstract class MyShape {
	// ͼ�εĹ������
	protected int factoryID;
	// ���������������
	protected AnchorPane drawingArea;
	protected DrawController drawController;
	// ��������
	protected double x;
	protected double y;
	// ���Ͻ�����
	protected double leftX;
	protected double leftY;
	// ���½�����
	protected double rightX;
	protected double rightY;
	// �볤��
	protected double width;
	protected double height;

	// ����һ���ɼ����ı���������ֵ�ı仯�������Ǽ�¼MyShape��������û�з����ı䣬Ϊ�Ժ�Ĳ���ջ�ṩ����
	protected BooleanProperty booleanProperty;
	// ״̬
	private boolean isDrag = false;
	private boolean isZoom = false;
	private boolean isSelected = false;
	// ����
	protected Shape shape;
	private Editer editer;
	private Status status;
	private Text text;
	protected DrawPoints drawPoints;
	// ��¼������
	ArrayList<ConnectionInfo> connectionInfos=new ArrayList<>();
	// ����RESIZEʹ��ʹ�õĳ���
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

	public void setISelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public BooleanProperty getBooleanProperty() {
		return booleanProperty;
	}

	public void setBooleanProperty(BooleanProperty booleanProperty) {
		this.booleanProperty = booleanProperty;
	}

	public Status getStatus() {
		return status;
	}

	public DrawPoints getDrawPoints() {
		return this.drawPoints;
	}

	public void setMyShape(Shape shape) {
		this.shape = shape;
		this.status = new Status();
		this.editer = new Editer(this.x, this.x, height, width);
		this.booleanProperty = new SimpleBooleanProperty(false);
		this.text = new Text();
		this.text.setX(x);
		this.text.setY(y);
		this.drawPoints = new DrawPoints();
		createDrawPoints();
		shape.setFill(Color.WHITE);
		shape.setStroke(Color.BLACK);
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

	public int getFactoryID() {
		return factoryID;
	}

	@Override
	public boolean equals(Object obj) {
		return ((MyShape) obj).getFactoryID() == this.factoryID;
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

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}
	//���������Ϣ
	public void addConnectionInfo(ConnectionInfo info) {
		connectionInfos.add(info);
	}
	//ɾ��������Ϣ
	public void delConnectionInfo(MyLine line) {
		ConnectionInfo delInfo=null;
		for(ConnectionInfo info:connectionInfos) {
			if(info.getLine()==line) {
				System.out.println("delOK");
				delInfo=info;
			}
		}
		if(delInfo!=null)connectionInfos.remove(delInfo);
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

	// ɾ��������Ϊ�ṩdetele������
	public void delet() {
		drawingArea.getChildren().remove(shape);
		editer.delEditer(drawingArea);
		drawPoints.delPoint(drawingArea);
	}

	public void getPane(AnchorPane drawingArea, DrawController drawController) {
		editer.addEditer(drawingArea);
		drawingArea.getChildren().add(shape);
		drawingArea.getChildren().addAll(drawPoints.getCircles());
		drawPoints.setAllVisiable(false);
		// text���¼��������
		drawingArea.getChildren().add(text);
		this.drawController = drawController;
		this.drawingArea = drawingArea;
	}

	public void setToTop() {
		drawingArea.getChildren().remove(shape);
		editer.delEditer(drawingArea);
		drawPoints.delPoint(drawingArea);
		drawingArea.getChildren().remove(text);
		getPane(drawingArea, drawController);
	}

	/*
	 * ������϶���ʱ�����4����
	 *
	 */
	public void createDrawPoints() {
		double leftMidX = this.x - width;
		double leftMidY = this.y;
		double upMidX = this.x;
		double upMidY = this.y - height;
		double rightMidX = this.x + width;
		double rightMidY = this.y;
		double downMidX = this.x;
		double downMidY = this.y + height;
		drawPoints.updataLocation(leftMidX, leftMidY, upMidX, upMidY, rightMidX, rightMidY, downMidX, downMidY);
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
		// ��MyShape�����Է����ı��ʱ����Ӧ
		changeListener();
	}

	public void changeListener() {
		booleanProperty.addListener(e -> {
			if (booleanProperty.getValue() == false) {
				// ������巢���ı�˵����������ǵ�ǰ������Shape����ʱ�Ҳ����������ʾ���Shape������
				drawController.getPropertyController().setWorkShape(this);
				drawController.getPropertyController().update();
				drawController.saveChange();
			}
		});
	}

	public void setOnDrag() {
		shape.setOnMouseDragged(e -> {
			Move(e.getX(), e.getY());
			editer.show(x, y);
			editer.disapperCircle();
			isSelected = false;
		});
	}
//
//	public void setOnMouseEnter() {
//		shape.setOnDragDone(e -> {
//			System.out.println("go");
//			if (drawController.isDraggingLine()) {
//				System.out.println("gogogo");
//				drawController.setDragInShape(this);
//			}
//			e.setDropCompleted(true);
//			e.consume();
//		});
//	}
	public void setOnRealse() {
		shape.setOnMouseReleased(e -> {
			this.setToTop();
			this.booleanProperty.setValue(false);
			status.setRelease();
			if (isSelected == false) {
				drawController.clearAllOnEdit();
				isSelected = true;
				editer.show(x, y);
			} else {
				isSelected = false;
				editer.disapper();
			}
			System.out.println(toString());
		});
	}

	public void updateLocation(double x, double y) {
		this.x = x;
		this.y = y;
		this.rightX = x + width;
		this.rightY = y + height;
		this.leftX = x - width;
		this.leftY = y - height;
		createDrawPoints();
		getEditer().setHeight(height + 10);
		getEditer().setWidth(width + 10);
		if (isSelected)
			editer.show(x, y);
		update();
	}

	public void lineMove() {
		for(ConnectionInfo info:connectionInfos) {
			if(info.getConnectionPart().equals("end")) {
				double centerX,centerY;
				centerX=drawPoints.getCircles()[info.getLocation()].getCenterX();
				centerY=drawPoints.getCircles()[info.getLocation()].getCenterY();
				info.getLine().endMove(centerX,centerY);
			}else if(info.getConnectionPart().equals("start")){
				double centerX,centerY;
				centerX=drawPoints.getCircles()[info.getLocation()].getCenterX();
				centerY=drawPoints.getCircles()[info.getLocation()].getCenterY();
				info.getLine().startMove(centerX,centerY);
			}
		}
	}

	public void Move(double x, double y) {
		double posX = x - this.getShape().getParent().getLayoutX();
		double posY = y - this.getShape().getParent().getLayoutY();
		booleanProperty.setValue(true);
		updateLocation(posX, posY);
		lineMove();
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

			// ���������״
			circles[i].setCursor(hand[i]);
		}
	}

	private void moveHandListener() {
		getShape().setCursor(Cursor.MOVE);
	}

	// Resize listener
	protected void resizeShape(int posId, double dx, double dy) {
		if (width + dx >= 0 && height + dy >= 0) {
			// ������εı任
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
			// ���ɱ任Ч��
			updateLocation(this.x, this.y);
			getEditer().setHeight(height + 10);
			getEditer().setWidth(width + 10);
			// ���α任Ч��
			this.setX(x);
			this.setY(y);
			this.setWidth(width);
			this.setHeight(height);
			// ���ɱ༭��ı任Ч��
			booleanProperty.setValue(true);
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
			// �����ڷ�����ʱ���¼һ��״̬����ı�
			circles[i].setOnMouseReleased(e -> {
				this.booleanProperty.setValue(false);
				;
			});
		}
	}

	// ÿ���ƶ��ᵼ����������x,y�ı䣬Ϊ�˷�ֹtext��ͼ�η���ƫ��������Ҫ����text��λ��
	public void update() {
		int len = text.getText().length() * 5;
		text.setX(x - len);
		text.setY(y);
	}

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.00");
		String tostring = getClass().getSimpleName() + "(" + df.format(this.x) + "," + df.format(this.y) + "," + df.format(this.width) + ","
				+ df.format(this.height) + ")" + "[ " + text.getText() + " ]" + " ;\n";
		return tostring;
	}
}
