package com.beefyolegames.beefyengine.framework;

/**
 * Created by leo on 26/04/14.
 */
public interface PhysicsJoint {
    public void createJoint(PhysicsWorld world, PhysicsObject parent, PhysicsObject child);

    public void destroyJoint(PhysicsWorld world);
}
