package controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

public class KeyBoardManager {
	private boolean isCtrl;
	private boolean isShift;
	private boolean isAlt;

	private DrawController drawController;
	private AnchorPane drawingArea;
	public KeyBoardManager(DrawController drawController) {
			this.drawController = drawController;
			this.drawingArea = drawController.getDrawingArea();
			addListener();
	}
	public void addListener(){
		deletListener();
		drawingArea.setOnKeyReleased(e->{
			this.isAlt=false;
			this.isCtrl=false;
			this.isShift = false;
		});
	}
	public void deletListener() {
		drawingArea.setOnKeyPressed(e -> {
			if(e.isControlDown()){
				this.isCtrl = true;
			}
			if (e.getCode() == KeyCode.DELETE) {
				drawController.delete();
			}
			if(e.getCode() == KeyCode.ESCAPE){
				drawController.clearAllOnEdit();
				drawController.setCopyClipBoard(null);
			}
			if(isCtrl&&e.getCode() == KeyCode.Z){
				drawController.restore();
			}
			if(isCtrl&&e.getCode() == KeyCode.C){
				drawController.copyManager();
			}
			if(isCtrl&&e.getCode() == KeyCode.V){
				drawController.copy();
			}
		});
	}
	public boolean isCtrl() {
		return isCtrl;
	}
	public void setCtrl(boolean isCtrl) {
		this.isCtrl = isCtrl;
	}
	public boolean isShift() {
		return isShift;
	}
	public void setShift(boolean isShift) {
		this.isShift = isShift;
	}
	public boolean isAlt() {
		return isAlt;
	}
	public void setAlt(boolean isAlt) {
		this.isAlt = isAlt;
	}

}
