package dk.sdu.cbse.common.data;

import java.util.UUID;

public class Entity {
    
    private final UUID ID;
    private int x;
    private int y;

    public Entity() {
        this.ID = UUID.randomUUID();
    }

    public String getID() {
        return ID.toString();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
