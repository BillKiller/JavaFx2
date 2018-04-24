package model;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ToolBar extends VBox {
	private Button circleButton;
	public ToolBar(){
		circleButton = new Button("circle");
		this.getChildren().add(circleButton);
	}
	public Button getCircleButton(){
		return circleButton;
	}
}
