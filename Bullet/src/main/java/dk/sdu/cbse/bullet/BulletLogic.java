package dk.sdu.cbse.bullet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.commonbullet.BulletSpawnerInterface;

public class BulletLogic implements IEntityProcessingService, BulletSpawnerInterface {

   @Override
public void process(GameData gameData, World world) {
    double delta = gameData.getDelta();

    for (Entity bullet : world.getEntities(Bullet.class)) {
        System.out.println(delta);
        //move the bullet in the direction it is facing
        double dx = Math.cos(Math.toRadians(bullet.getRotation())) * bullet.getSpeed();
        double dy = Math.sin(Math.toRadians(bullet.getRotation())) * bullet.getSpeed();
        bullet.setX(bullet.getX() + dx * delta );
        bullet.setY(bullet.getY() + dy * delta );

        if (bullet.getX() < 0 || bullet.getX() > gameData.getDisplayWidth() ||
            bullet.getY() < 0 || bullet.getY() > gameData.getDisplayHeight()) {
            world.removeEntity(bullet);
        }
    }
}


    @Override
    public Entity createBullet(Entity e, GameData gameData) {
        Bullet bullet = new Bullet();
        bullet.setSpeed(600f);
        bullet.setPosition(e.getX(), e.getY());
        bullet.setRotation(e.getRotation());
        bullet.setPolygonCoordination(1, -1, 1, 1, -1, 1, -1, -1);
        return bullet;

    }
    
}
