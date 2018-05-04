package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.TextArea;

public class Compiler {
	private ShapeFactory shapeFactory;
	private DrawController drawController;
	private String code;
	private TextArea textArea;
	public TextArea getTextArea() {
		return textArea;
	}
	public void setTextArea(TextArea textArea) {
		this.textArea = textArea;
	}
	public Double[] getRound(String shape){
		Double []list=new Double[4];
		Pattern pattern = Pattern.compile("(?<=\\()[^\\)]+");
		Matcher matcher = pattern.matcher(shape);
		if(matcher.find()){
			String temp = matcher.group();
			String tt[]=temp.split(",");
			for(int i =0;i<tt.length;i++){
				list[i] = Double.valueOf(tt[i]);
//				System.out.println(list[i]);
			}
		}
		return list;
	}
	public String getText(String shape){
		Pattern pattern = Pattern.compile("(?<=\\[)[^\\]]+");
		Matcher matcher = pattern.matcher(shape);
		String temp = null;
		if(matcher.find()){
			temp = matcher.group();
		}
		return temp;
	}
	public String getShapeType(String shape){
		Pattern pattern = Pattern.compile("[^\\(]+");
		Matcher matcher = pattern.matcher(shape);
		String temp = null;
		if(matcher.find()){
			temp = matcher.group();
		}
		return temp;
	}
	public String [] getItem(){
		String []item = code.split(";");
		return item;
	}
	public void compireProduce(String code){
		this.code = code;
		drawController.reset();
		String items[]=getItem();
		for(int i =0;i<items.length;i++){
			items[i]=items[i].trim();

			if(items[i]==null&&items[i].length()==0)continue;
			Double list[] = getRound(items[i]);
			this.shapeFactory.produce(getShapeType(items[i]),list[0],list[1]);
		}
	}
	public ShapeFactory getShapeFactory() {
		return shapeFactory;
	}
	public void setShapeFactory(ShapeFactory shapeFactory) {
		this.shapeFactory = shapeFactory;
		this.drawController=shapeFactory.getDrawController();
	}
}
