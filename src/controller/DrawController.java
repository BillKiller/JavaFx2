package controller;

import java.awt.List;
import java.awt.Shape;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DropTargetListener;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.Observable;
import java.util.function.ObjDoubleConsumer;

import javax.swing.plaf.synth.SynthStyle;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import model.MyLine;
import model.MyShape;

//注意这个管家要设置为最大的管家，然后在这个管家的下面设置小管家，记录必要状态和管理不同区域的联系
/*
 *总管家给属性栏提供一个工作的Shape（画图区域唯一处在编辑状态的Shape，如果有多个shape处在编辑状态那么我们修改右侧其实是没有意义的，所以只有当有且仅有一个的时候，
 *总管家给右侧属性栏管家提供一个Shape让属性管家显示属性和修改属性
 *
 */
public class DrawController {

	private AnchorPane drawingArea = null;
	private ArrayList<MyShape> list = new ArrayList<>();
	private MyShape workShape;
	// 小管家
	private PropertyController propertyController;
	// 正在拖动的线 和 最近的连接点
	private MyLine dragLine;
	private Circle nearPoint;
	// 连接点显示的最大距离
	private double maxDistance = 50;

	// 连接线和图形
	public void connect(double x1, double y1, String type) {
		double minDistance = 100000;
		nearPoint = null;
		for (MyShape nowShape : list) {
			for (Circle nowCircle : nowShape.getDrawPoints().getCircles()) {
				nowCircle.setVisible(false);
				double x2, y2;
				x2 = nowCircle.getCenterX();
				y2 = nowCircle.getCenterY();
				double distance = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
				if (distance < maxDistance && distance < minDistance) {
					nearPoint = nowCircle;
					minDistance = distance;
				}
			}
		}
		if (nearPoint != null) {
			if (type.equals("end")) {
				dragLine.endMove(nearPoint.getCenterX(), nearPoint.getCenterY());
			} else if (type.equals("start")) {
				dragLine.startMove(nearPoint.getCenterX(), nearPoint.getCenterY());
			}
		}
	}

	public void setDragLine(MyLine dragLine) {
		this.dragLine = dragLine;
	}

	public boolean isDraggingLine() {
		return dragLine != null;
	}

	public void setNearPoint(Circle nearPoint) {
		this.nearPoint = nearPoint;
	}

	public void checkDistanceToPoints(double x1, double y1) {
		for (MyShape nowShape : list) {
			for (Circle nowCircle : nowShape.getDrawPoints().getCircles()) {
				double x2, y2;
				x2 = nowCircle.getCenterX();
				y2 = nowCircle.getCenterY();
				double distance = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
				if (distance < maxDistance) {
					nowCircle.setVisible(true);
				} else {
					nowCircle.setVisible(false);
				}
			}
		}
	}

	public ArrayList<MyShape> getList() {
		return list;
	}

	public void setList(ArrayList<MyShape> list) {
		this.list = list;
	}

	public PropertyController getPropertyController() {
		return propertyController;
	}

	public void setPropertyController(PropertyController propertyController) {
		this.propertyController = propertyController;
	}

	public DrawController(AnchorPane drawArea) {
		drawingArea = drawArea;
		System.out.println(drawArea);

	}

	public void addDrawArea() {
		int index = list.size() - 1;
		MyShape shape = (list.get(index));
		shape.getPane(drawingArea, this);
	}

	public void regriste(MyShape shape) {
		if (shape != null)
			list.add(shape);
	}

	public void delete(MyShape shape) {
		int index = list.indexOf(shape);
		shape.delet();
		list.remove(index);
	}

	public void delete() {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isSelected()) {
				list.get(i).delet();
				System.out.println("hehe");
				list.remove(i);
			}
		}
	}

	public void clearAllOnEdit() {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setISelected(false);
			list.get(i).getEditer().disapper();
		}
	}

	public MyShape workingShape() {
		MyShape shape = null;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isSelected()) {
				if (shape == null) {
					shape = list.get(i);
				} else {
					shape = null;
					break;
				}
			}
		}
		workShape = shape;
		return shape;
	}

	/*
	 * 监听器
	 */
	// 键盘监听器
	public void deletListener() {
		drawingArea.setOnKeyPressed(e -> {
			System.out.println("kkk");
			if (e.getCode() == KeyCode.DELETE) {
				delete();
			}
		});
	}
}
