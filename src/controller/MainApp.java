package controller;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.CurseRectangle;
import model.Decisioin;
import model.InputRectangle;
import model.MyLine;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private AnchorPane DrawingArea;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Flow Graph Editor");

        initRootLayout();
//        MyCircle myCircle = new MyCircle();
//        myCircle.getPane(rootLayout);
//		MyRectangle myRectangle=new MyRectangle(500,500,200,100);
//		myRectangle.getPane(rootLayout);
//		RoundRectangle roundRectangle=new RoundRectangle(500,500,200,100);
//		roundRectangle.getPane(rootLayout);

    }

    /**
     * 加载RootLayout.fxml
     */
    public void initRootLayout() {
        try {
        	//将RootLayout.fxml加载到rootLayout成员变量中
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            //debug
//            MyLine myLine = new MyLine(500, 500,600,700);
//            myLine.getPane(rootLayout);
            //用rootLayout初始化一个scene，放到stage上展示
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initAttributeBox(){
    	try {
        	//将RootLayout.fxml加载到rootLayout成员变量中
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            //用rootLayout初始化一个scene，放到stage上展示
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