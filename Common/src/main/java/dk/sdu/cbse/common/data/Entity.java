package dk.sdu.cbse.common.data;

import java.util.UUID;

public class Entity {
    
    private final UUID ID;
    
    private double x;
    private double y;

    private float dx = 0;
    private float dy = 0;

    private double rotation;
    private double[] polygonCoordinates;

    private float speed;

    public Entity() {
        this.ID = UUID.randomUUID();
    }

    public String getID() {
        return ID.toString();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }


    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public void setVelocity(float dx, float dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotation() {
        return this.rotation;
    }

    public void setPolygonCoordination(double... coordinates) {
        this.polygonCoordinates = coordinates;
    }

    public double[] getPolygonCoordinates() {
        return this.polygonCoordinates;
    }

    public float getSpeed() {
        return speed;
    }
    
    public void setSpeed(float speed) {
        this.speed = speed;
    }

}
