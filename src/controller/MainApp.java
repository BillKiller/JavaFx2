package controller;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.CurvedRectangle;
import model.Decision;
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
    }

    /**
     * 加载RootLayout.fxml
     */
    public void initRootLayout() {
        try {
	//将CSS文件加载到fxml文件中
	Application.setUserAgentStylesheet(getClass().getResource("/css/MyCSS.css")
                    .toExternalForm());
        	//将RootLayout.fxml加载到rootLayout成员变量中
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/controller/RootLayout.fxml"));
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
            loader.setLocation(MainApp.class.getResource("/controller/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            //用rootLayout初始化一个scene，放到stage上展示
            Scene scene = new Scene(rootLayout);
            ((ScrollPane)rootLayout.getCenter()).setOnKeyPressed(e->{
         	   System.out.println("as123");
            });
            primaryStage.setScene(scene);
            primaryStage.show();

            ((ScrollPane)rootLayout.getCenter()).requestFocus();

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
