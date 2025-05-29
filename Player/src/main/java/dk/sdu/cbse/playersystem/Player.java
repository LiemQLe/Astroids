package dk.sdu.cbse.playersystem;

import dk.sdu.cbse.commonplayer.IPlayer;
import dk.sdu.cbse.common.data.Entity;

public class Player extends Entity implements IPlayer{
    double bulletCooldown = 0;
    
    double getBulletCooldown() {
        return bulletCooldown;
    }
    void setBulletCooldown(double bulletCooldown) {
        this.bulletCooldown = bulletCooldown;
    }
}
