package dk.sdu.cbse.main;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public class App extends Application {

    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ModuleConfig.class);
        
        // If bean names get printed bean are registered correctly
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        Game game = context.getBean(Game.class);
        game.start(primaryStage);
        context.close();
    }
    
}
