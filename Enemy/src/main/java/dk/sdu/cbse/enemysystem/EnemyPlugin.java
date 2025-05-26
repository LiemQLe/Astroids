package dk.sdu.cbse.enemysystem;

import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.data.Entity;

public class EnemyPlugin implements IGamePluginService {

    private Entity enemy;

    public EnemyPlugin() {
        
    }

    @Override
    public void start(GameData gameData, World world) {
        enemy = createEnemy(gameData);
        world.addEntity(enemy);
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove the enemy control system from the world
        
    }
    
    public Entity createEnemy(GameData gameData) {
        Entity enemyShip = new Enemy();
        enemyShip.setSpeed(100f);
        enemyShip.setPolygonCoordination(-5, -5, 10, 0, -5, 5);
        enemyShip.setX((Math.random() * gameData.getDisplayWidth() + 1 ));
        enemyShip.setY((Math.random() * gameData.getDisplayHeight() + 1));
        enemyShip.setRadius(enemyShip.calcRadius());
        enemyShip.setHealth(5);
        System.out.println("Enemy created at position: " + enemyShip.getX() + ", " + enemyShip.getY());
        return enemyShip;
    }
}
