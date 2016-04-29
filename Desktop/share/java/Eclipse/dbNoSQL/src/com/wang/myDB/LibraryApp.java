package com.wang.myDB;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * Main program
 * @author wang
 *
 */
public class LibraryApp extends Application {
	/**
	 * Data fields
	 * @return
	 */
	private AlbumDAO buildDAO(){
		
		return new DerbyAlbumDAO();
	}
	

	private  Library buildModel(){
		return new Library(buildDAO());
	}

	private Controller buildController(Stage primaryStage){
		
		return new Controller(buildModel(), primaryStage);
	}

	/**
	 * Load the GUI
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(LibraryApp.class.getResource("/ui.fxml"));
		loader.setControllerFactory(t -> buildController(primaryStage));
		primaryStage.setTitle("Media Library");
		primaryStage.setScene(new Scene(loader.load(), 1300, 800));
		primaryStage.show();
		
		
		
	}
	

	public static void main(String[] args) throws Exception{
		
		launch(args);
		
		
	}

}
