package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.data.Entity;

public class BulletPlugin {
    
    private Entity bullet;

    public BulletPlugin() {
        
    }

    public void setPosition(int x, int y) {
        bullet.setPosition(x, y);
    }
}
