package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.data.*;
import dk.sdu.cbse.commonbullet.BulletMarker;
import dk.sdu.cbse.commonbullet.IBullet;



public class Bullet extends Entity implements IBullet, BulletMarker {
    private Entity owner;

    @Override
    public Entity getOwner() {
        return owner;
    }

    public void setOwner(Entity owner) {
        this.owner = owner;
    }
}
