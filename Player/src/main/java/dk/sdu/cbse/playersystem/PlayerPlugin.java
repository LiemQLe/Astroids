package dk.sdu.cbse.playersystem;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;


public class PlayerPlugin implements IGamePluginService{

    private Entity player;

    public PlayerPlugin() {
        
    }

    @Override
    public void start(GameData gameData, World world) {
        // Add Player to the world
        player = createShip(gameData);
        world.addEntity(player);
    }

    public Entity createShip(GameData gameData) {
        
        Entity playerShip = new Player();
        playerShip.setSpeed(200f);
        playerShip.setPolygonCoordination(-5,-5,10,0,-5,5);
        playerShip.setX(gameData.getDisplayWidth() / 2);
        playerShip.setY(gameData.getDisplayHeight() / 2);
        System.out.println("Player ship created");
        return playerShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove Player from the world
        world.removeEntity(player);
    }
    
}
