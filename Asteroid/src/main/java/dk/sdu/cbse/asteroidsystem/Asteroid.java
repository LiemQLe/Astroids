package dk.sdu.cbse.asteroidsystem;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.commonasteroid.AsteroidMarker;
import dk.sdu.cbse.commonasteroid.IAsteroid;

public class Asteroid extends Entity implements IAsteroid, AsteroidMarker {
    private int size;

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void splitAsteroid(GameData gameData, World world, Entity e) {
        Asteroid asteroid = (Asteroid) e;
        if (asteroid.getSize() >= 0) {

            if (asteroid.getSize() > 1) {
                double[] scaledCoords = scaleAsteroid(asteroid.getPolygonCoordinates(), 0.5);

                for (int i = 0; i < 2; i++) {
                    Asteroid newAsteroid = new Asteroid();
                    newAsteroid.setPolygonCoordination(scaledCoords);
                    if (asteroid.getSize() == 3) {

                        newAsteroid.setX(asteroid.getX() + (i == 0 ? 50 : -50));
                    }else if (asteroid.getSize() == 2) {
                        newAsteroid.setX(asteroid.getX() + (i == 0 ? 30 : -30));
                    } else {
                        newAsteroid.setX(asteroid.getX() + (i == 0 ? 20 : -20));
                    }
                    newAsteroid.setY(asteroid.getY());
                    newAsteroid.setRadius(newAsteroid.calcRadius());
                    newAsteroid.setSpeed(80);
                    newAsteroid.setDmg(5);
                    newAsteroid.setSize(asteroid.getSize() - 1);
                    double angle = Math.random() * 360;
                    // Ensure the new asteroid rotates in the opposite direction
                    newAsteroid.setRotation((i == 0 ? angle : (angle + 180) % 360));
                    world.addEntity(newAsteroid);
                }
            }

        }
        world.removeEntity(e);
    }

    public double[] scaleAsteroid(double[] coords, double scale) {
        double[] scaled = new double[coords.length];
        for (int i = 0; i < coords.length; i++) {
            scaled[i] = coords[i] * scale;
        }
        return scaled;
    }
}
