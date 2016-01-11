package com.beefyolegames.beefyengine.phys_box2d;

import com.beefyolegames.beefyengine.framework.PhysicsWorld;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 26/04/14.
 */
public class PhysWorld implements PhysicsWorld{
    private World world;
    private int pixelsToMeters;
    private float xOffset;
    private float yOffset;
    private float world_width;
    private float world_height;
    private List<Integer> registeredObjectIDs;
    private CollisionMonitor collisionMonitor;

    public PhysWorld(float width, float height, float startX, float startY, float gravX, float gravY, int pixelsToMeters){
        world = new World(new Vec2(gravX, gravY));
        this.xOffset = startX;
        this.yOffset = startY;
        this.world_width = width;
        this.world_height = height;
        this.pixelsToMeters = pixelsToMeters;
        registeredObjectIDs = new ArrayList<Integer>();
        collisionMonitor = new CollisionMonitor();
        world.setContactListener(collisionMonitor);
    }
    public CollisionInfo addNewObjectID(){

        if(registeredObjectIDs.size() == 0){
            registeredObjectIDs.add(1);
        } else {
            registeredObjectIDs.add(registeredObjectIDs.get(registeredObjectIDs.size() - 1) + 1);
        }
        System.out.println("Added new object with ID " + registeredObjectIDs.get(registeredObjectIDs.size() - 1));

        return new CollisionInfo(registeredObjectIDs.get(registeredObjectIDs.size() - 1));
    }

    public void removeObjectID(int objectID){
        for(Integer obj : registeredObjectIDs){
            if(obj.intValue() == objectID){
                registeredObjectIDs.remove(obj);
            }
        }
    }

    public void setSleepingAllowed(boolean sleepingAllowed){
        world.setSleepingAllowed(sleepingAllowed);
    }

    @Override
    public float getXOffset() {
        return xOffset;
    }

    @Override
    public float getYOffset() {
        return yOffset;
    }

    @Override
    public void setXOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    @Override
    public void setYOffset(float yOffset) {
        this.yOffset = yOffset;
    }

    @Override
    public World getWorld(){
        return world;
    }

    @Override
    public void step(float dt, int velocityIterations, int positionIterations) {
        world.step(dt, velocityIterations, positionIterations);
    }

    @Override
    public int getPixelsToMeters() {
        return pixelsToMeters;
    }
}
