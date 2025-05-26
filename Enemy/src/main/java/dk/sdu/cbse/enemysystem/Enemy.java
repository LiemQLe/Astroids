package dk.sdu.cbse.enemysystem;
import dk.sdu.cbse.common.data.Entity;

public class Enemy extends Entity {
    double bulletCooldown = 0;
    
    double getBulletCooldown() {
        return bulletCooldown;
    }
    void setBulletCooldown(double bulletCooldown) {
        this.bulletCooldown = bulletCooldown;
    }
}
