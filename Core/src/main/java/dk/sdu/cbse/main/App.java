package dk.sdu.cbse.main;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello World!");
        primaryStage.show();
    }
}
