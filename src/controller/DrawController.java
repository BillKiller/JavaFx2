package controller;

import java.awt.List;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.plaf.synth.SynthStyle;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import model.MyLine;
import model.MyShape;

//ע������ܼ�Ҫ����Ϊ���Ĺܼң�Ȼ��������ܼҵ���������С�ܼң���¼��Ҫ״̬�͹���ͬ�������ϵ
public class DrawController {
	private AnchorPane drawingArea = null;
	private ArrayList<MyShape> list = new ArrayList<>();
	private MyShape workShape;

	// С�ܼ�
	private PropertyController propertyController;

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
		list.add(shape);
	}

	public void delete(MyShape shape) {
		int index = list.indexOf(shape);
		shape.delet();
		list.remove(index);
	}
	public void delete() {
		for(int i =0;i<list.size();i++){
			if(list.get(i).isSelected()){
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
	 * ������
	 */
	//���̼�����
	public void deletListener(){
		drawingArea.setOnKeyPressed(e->{
			System.out.println("kkk");
				if(e.getCode() == KeyCode.DELETE){
					delete();
				}
		});
	}
}
