package dk.sdu.cbse.common.data;

import java.util.UUID;

public class Entity {
    
    private final UUID ID;
    
    private double x;
    private double y;
    private double rotation;
    private double[] polygonCoordinates;

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

    public void setRotation(double rotation){
        this.rotation = rotation;
    }

    public double getRotation(){
        return this.rotation;
    }

    public void setPolygonCoordination(double... coordinates){
        this.polygonCoordinates = coordinates;
    }

    public double[] getPolygonCoordinates(){
        return this.polygonCoordinates;
    }

}
