package com.beefyolegames.beefyengine.framework;

import com.beefyolegames.beefyengine.phys_box2d.CollisionInfo;

/**
 * Created by leo on 26/04/14.
 */
public interface PhysicsObject {
    //TODO: add all getters/setters

    public float getWorldX();

    public float getWorldY();

    public float getX();

    public float getY();

    public float getScreenX(PhysicsWorld world);

    public float getScreenY(PhysicsWorld world);

    public float getAngleRad();

    public float getAngleDeg();

    public float getLinearVelocityX();

    public float getLinearVelocityY();

    public void setAngularDampening(float angularDampening);

    public void setFixedRotation(boolean fixedRotation);

    public void setMass(float mass);

    public void setSleepingAllowed(boolean sleepingAllowed);

    public void setAngularVelocity(float angularVelocity);

    public void setTransform(float x, float y, float angle);

    public void setWorldTransform(float x, float y, float angle);

    public void setFriction(float friction);

    public void setDensity(float density);

    public void setRestitution(float restitution);

    public void setSensor(boolean sensor);

    public void setLinearVelocity(float vx, float vy);

    public void setAwake(boolean awake);

    public void applyForce(float fx, float fy, float posX, float posY);

    public float getAngularVelocity();

    public Object getBody();

    public CollisionInfo getMyCollisionInfo();

    public void addChildWithJoint(PhysicsWorld world, PhysicsObject child, PhysicsJoint joint);

    public boolean checkCollision(PhysicsObject otherObject);
}
