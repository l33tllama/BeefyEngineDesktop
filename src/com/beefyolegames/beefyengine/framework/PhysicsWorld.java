package com.beefyolegames.beefyengine.framework;

/**
 * Created by leo on 26/04/14.
 */
public interface PhysicsWorld {
    public float getXOffset();

    public float getYOffset();

    public void setXOffset(float xOffset);

    public void setYOffset(float yOffset);

    public Object getWorld();

    public void step(float dt, int velocityIterations, int positionIterations);

    public int getPixelsToMeters();
}
