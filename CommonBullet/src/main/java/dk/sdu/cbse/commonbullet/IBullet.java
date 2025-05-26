package dk.sdu.cbse.commonbullet;
import dk.sdu.cbse.common.data.Entity;


public interface IBullet  {
    /**
     * Gets the owner of the bullet.
     *
     * @return the owner entity
     */
    Entity getOwner();
}
