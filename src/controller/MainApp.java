package controller;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.MyCircle;
import model.MyRectangle;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private FlowPane attributeBox;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Flow Graph Editor");

        initRootLayout();
        MyCircle myCircle = new MyCircle();
        myCircle.getPane(rootLayout);
		MyRectangle myRectangle=new MyRectangle(500,500,100,200);
		myRectangle.getPane(rootLayout);
    }

    /**
     * ����RootLayout.fxml
     */
    public void initRootLayout() {
        try {
        	//��RootLayout.fxml���ص�rootLayout��Ա������
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            //��rootLayout��ʼ��һ��scene���ŵ�stage��չʾ
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void initAttributeBox(){
    	try {
        	//��RootLayout.fxml���ص�rootLayout��Ա������
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            //��rootLayout��ʼ��һ��scene���ŵ�stage��չʾ
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

    public static void main(String[] args) {
        launch(args);
    }
}