package dk.sdu.cbse.main;

import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.client.RestTemplate;

import static java.util.stream.Collectors.toList;

import java.lang.module.Configuration;
import java.lang.module.ModuleFinder;
import java.nio.file.Paths;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;   
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;

/**
 * Hello world!
 *
 */
public class Game {

    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Pane gameWindow = new Pane();
    private Text destroyedAsteroids= new Text(10, 20, "Destroyed asteroids:");

    public void start(Stage primaryStage) {

        
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        gameWindow.getChildren().add(destroyedAsteroids);

        Scene scene = new Scene(gameWindow);
        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, true);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, true);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, true);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, true);
            }
        });

        scene.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, false);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, false);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, false);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, false);
            }
        });

        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePluginService : pluginServices) {
            iGamePluginService.start(gameData, world);
        }
        // Lookup all Entity Processing Services using ServiceLoader
        for (Entity entity : world.getEntities()) {
            Polygon polygon = new Polygon(entity.getPolygonCoordinates());
            polygons.put(entity, polygon);
            gameWindow.getChildren().add(polygon);
        }
        render();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Asteroids");
        primaryStage.show();

    }

    private void update() {
        for (IEntityProcessingService entityProcessorService : entityProcessingServices) {
            entityProcessorService.process(gameData, world);
        }
        for (IPostEntityProcessingService postEntityProcessorService : postEntityProcessingServices) {
            postEntityProcessorService.process(gameData, world);
        }
    }

    private void render() {
        new AnimationTimer() {
            private long lastTime = 0;

            @Override
            public void handle(long now) {

                if (lastTime == 0) {
                    lastTime = now;
                    return;
                }

                double delta = (now - lastTime) / 1_000_000_000.0; // Convert nanos to seconds
                gameData.setDelta(delta);

                update();
                draw();
                gameData.getKeys().update();

                lastTime = now;

                // Update the destroyed asteroids count
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    String playerScore = restTemplate.getForObject("http://localhost:8080/score/player", String.class);
                    destroyedAsteroids.setText("Destroyed asteroids: " + playerScore);
                } catch (Exception e) {
                    destroyedAsteroids.setText("Score unavailable");
                    System.err.println("Failed to fetch score: " + e.getMessage());
                }
                
            }

        }.start();
    }

    private void draw() {

        for (Entity entity : polygons.keySet()) {
            if (!world.getEntities().contains(entity)) {
                Polygon removedPolygon = polygons.get(entity);
                polygons.remove(entity);
                gameWindow.getChildren().remove(removedPolygon);
            }
        }

        for (Entity entity : world.getEntities()) {
            Polygon polygon = polygons.get(entity);
            // Draw new entities
            if (polygon == null) {
                polygon = new Polygon(entity.getPolygonCoordinates());

                polygons.put(entity, polygon);
                gameWindow.getChildren().add(polygon);
            }
            // Set the color of the polygon based on the entity type
            if (entity.getClass().getSimpleName().equals("Player")) {
                polygon.setFill(Color.LIGHTBLUE);
                polygon.setStroke(Color.BLUE);
            } else if (entity.getClass().getSimpleName().equals("Bullet")) {
                polygon.setFill(Color.YELLOW);
                polygon.setStroke(Color.GOLD);
            } else if (entity.getClass().getSimpleName().equals("Asteroid")) {
                polygon.setFill(null);
                polygon.setStroke(Color.SLATEGRAY);
            } else if (entity.getClass().getSimpleName().equals("Enemy")) {
                polygon.setFill(Color.RED);
                polygon.setStroke(Color.DARKRED);
            }

            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());
            polygon.setRotate(entity.getRotation());
        }

    }

    private ModuleLayer getModuleLayer() {
        ModuleFinder moduleFinder = ModuleFinder.of(Paths.get("layer2-packages"));
        ModuleLayer parentLayer = ModuleLayer.boot();
        List<String> moduleList = moduleFinder.findAll()
                .stream()
                .map(m -> m.descriptor()
                        .name())
                .collect(toList());

        Configuration cf = parentLayer.configuration()
                .resolve(moduleFinder, ModuleFinder.of(), moduleList);
        ModuleLayer myl = parentLayer.defineModulesWithOneLoader(cf, ClassLoader.getSystemClassLoader());
        return myl;
    }

    private List<IGamePluginService> pluginServices;
    private List<IEntityProcessingService> entityProcessingServices;
    private List<IPostEntityProcessingService> postEntityProcessingServices;

    // Add setters for Spring to inject
    public void setPluginServices(List<IGamePluginService> pluginServices) {
        this.pluginServices = pluginServices;
    }

    public void setEntityProcessingServices(List<IEntityProcessingService> entityProcessingServices) {
        this.entityProcessingServices = entityProcessingServices;
    }

    public void setPostEntityProcessingServices(List<IPostEntityProcessingService> postEntityProcessingServices) {
        this.postEntityProcessingServices = postEntityProcessingServices;
    }

}
