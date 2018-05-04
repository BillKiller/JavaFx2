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

import org.omg.IOP.Codec;
import org.w3c.dom.ls.LSException;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import model.ConnectionInfo;
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
	private ArrayList<MyLine> listLine = new ArrayList<MyLine>();
	private Object workShape;
	private OperationStack operationStack = new OperationStack();
	private KeyBoardManager keyBoardManager;
	// 小管家
	private PropertyController propertyController;
	private Compiler compiler;
	// 正在拖动的线 和 最近的连接点
	private MyLine dragLine;
	private Circle nearPoint;
	// 连接点显示的最大距离
	private double maxDistance = 50;
	private double isChange;

	//
	private boolean isReStroing = false;
	private String copyClipBoard = null;
	public DrawController(AnchorPane drawArea) {
		drawingArea = drawArea;
	}
	// 连接线和图形

	public Compiler getCompiler() {
		return compiler;
	}

	public void setCompiler(Compiler compiler) {
		this.compiler = compiler;
	}

	public void connect(double x1, double y1, String type, MyLine line) {
		double minDistance = 100000;
		nearPoint = null;
		MyShape nearShape = null;
		int location = 0;
		String part = type;
		for (MyShape nowShape : list) {
			Circle[] circles = nowShape.getDrawPoints().getCircles();
			for (int i = 0; i < 4; i++) {
				Circle nowCircle = circles[i];
				nowCircle.setVisible(false);
				double x2, y2;
				x2 = nowCircle.getCenterX();
				y2 = nowCircle.getCenterY();
				double distance = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
				if (distance < maxDistance && distance < minDistance) {
					nearPoint = nowCircle;
					nearShape = nowShape;
					location = i;
					minDistance = distance;
				}
			}
		}
		if (nearPoint != null) {
			nearShape.addConnectionInfo(new ConnectionInfo(line, location, part));
			if (type.equals("end")) {
				line.endMove(nearPoint.getCenterX(), nearPoint.getCenterY());
				line.setTailLink(nearShape);
			} else if (type.equals("start")) {
				line.startMove(nearPoint.getCenterX(), nearPoint.getCenterY());
				line.setHeadLink(nearShape);
			}
		}
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

	public void addDrawArea() {
		int index = list.size() - 1;
		MyShape shape = (list.get(index));
		shape.getPane(drawingArea, this);
	}

	public void regriste(MyShape shape) {
		if (shape != null) {
			list.add(shape);
			addDrawArea();
			if (!isReStroing)
				saveChange();
		}
	}

	public void addLineDrawArea() {
		int index = listLine.size() - 1;
		MyLine shape = (listLine.get(index));
		shape.getPane(drawingArea, this);
	}

	public void regristeLine(MyLine shape) {
		if (shape != null) {
			listLine.add(shape);
			addLineDrawArea();
			if (!isReStroing)
				saveChange();
		}
	}

	public void delete(MyShape shape) {
		int index = list.indexOf(shape);
		shape.delet();
		list.remove(index);
	}

	public void delete() {
		boolean remain = false;// 由于删除会导致list元素变动所以为了安全，从第一元素接着找起
		while (true) {
			remain = false;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).isSelected()) {
					list.get(i).delet();
					list.remove(i);
					remain = true;
					break;
				}
			}
			if (!remain) {
				break;
			}
		}
		while (true) {
			remain = false;
			for (int i = 0; i < listLine.size(); i++) {
				if (listLine.get(i).isSelected()) {
					listLine.get(i).delete();
					listLine.remove(i);
					remain = true;
					break;
				}
			}
			if (!remain) {
				break;
			}
		}
		saveChange();
	}

	public void clearAllOnEdit() {
		if (!keyBoardManager.isCtrl()) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setISelected(false);
				list.get(i).getEditer().disapper();
			}
			for (int i = 0; i < listLine.size(); i++) {
				listLine.get(i).setSelected(false);
			}
		}
	}

	public Object workingShape() {
		Object shape = null;
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
		for (int i = 0; i < listLine.size(); i++) {
			if (listLine.get(i).isSelected()) {
				if (shape == null) {
					shape = listLine.get(i);
				} else {
					shape = null;
					break;
				}
			}
		}
		workShape = shape;
		return shape;
	}

	public void copyManager() {
		String code="";
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isSelected()) {
					code = code + list.get(i).toString();
			}
		}
		for (int i = 0; i < listLine.size(); i++) {
			if (listLine.get(i).isSelected()) {
				code = code + listLine.get(i).toString();
			}
		}
		copyClipBoard = code;
	}
	public void copy(){
		String code =getCode();
		if(copyClipBoard==null)return;
		code = code + copyClipBoard;
		isReStroing = true;
		compiler.getTextArea().setText(code);
		compiler.compireProduce(code);
		isReStroing = false;
		saveChange();

	}
	public String getCopyClipBoard() {
		return copyClipBoard;
	}

	public void setCopyClipBoard(String copyClipBoard) {
		this.copyClipBoard = copyClipBoard;
	}

	public void reset() {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).delet();
		}
		for (int i = 0; i < listLine.size(); i++) {
			listLine.get(i).delete();
		}
		listLine.clear();
		list.clear();
	}

	public KeyBoardManager getKeyBoardManager() {
		return keyBoardManager;
	}

	public void setKeyBoardManager() {
		this.keyBoardManager = new KeyBoardManager(this);
	}

	public AnchorPane getDrawingArea() {
		return drawingArea;
	}

	public void setDrawingArea(AnchorPane drawingArea) {
		this.drawingArea = drawingArea;
	}

	public void saveChange() {
		String code = getCode();
		operationStack.addOperation(code);
		System.out.println("i am hehe");
		compiler.getTextArea().setText(code);
	}

	public String getCode() {
		String code = "";
		for (int i = 0; i < list.size(); i++) {
			code = code + list.get(i).toString();
		}
		for (int i = 0; i < listLine.size(); i++) {
			code = code + listLine.get(i).toString();
		}
		return code;
	}

	public void restore() {
		isReStroing = true;
		String code = operationStack.restoreOperation();
		code = operationStack.getTop();
		compiler.getTextArea().setText(code);
		compiler.compireProduce(code);
		isReStroing = false;
	}
}
