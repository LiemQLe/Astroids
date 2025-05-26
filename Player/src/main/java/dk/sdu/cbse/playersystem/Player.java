package dk.sdu.cbse.playersystem;

import dk.sdu.cbse.common.data.Entity;

public class Player extends Entity{
    double bulletCooldown = 0;
    
    double getBulletCooldown() {
        return bulletCooldown;
    }
    void setBulletCooldown(double bulletCooldown) {
        this.bulletCooldown = bulletCooldown;
    }
}
