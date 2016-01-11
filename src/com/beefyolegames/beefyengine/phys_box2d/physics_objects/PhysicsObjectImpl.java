package com.beefyolegames.beefyengine.phys_box2d.physics_objects;

import com.beefyolegames.beefyengine.framework.PhysicsObject;
import com.beefyolegames.beefyengine.framework.PhysicsWorld;
import com.beefyolegames.beefyengine.framework.PhysicsJoint;
import com.beefyolegames.beefyengine.phys_box2d.CollisionInfo;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

/**
 * Created with IntelliJ IDEA.
 * User: Leo
 * Date: 31/07/13
 * Time: 7:36 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class PhysicsObjectImpl implements PhysicsObject{
    BodyDef bodyDef;
    Body body;
    FixtureDef fixtureDef;
    int pixelsToMeters;
    BodyType type;
    boolean isContacting;
    int myID;
    //TODO: object removal with ID removal

    public void setContactingOn(){
        isContacting = true;
    }

    public void setContactingOff(){
        isContacting = false;
    }

    @Override
    public void setAngularDampening(float angularDampening) {
        body.m_angularDamping = angularDampening;
    }

    @Override
    public void setFixedRotation(boolean fixedRotation) {
        body.setFixedRotation(fixedRotation);
    }

    @Override
    public void setMass(float mass) {
        body.m_mass = mass;
    }

    @Override
    public void setSleepingAllowed(boolean sleepingAllowed) {
        body.setSleepingAllowed(sleepingAllowed);
    }

    @Override
    public void setAngularVelocity(float angularVelocity) {
        body.m_angularVelocity = angularVelocity;
    }

    @Override
    public void setTransform(float x, float y, float angle) {
        body.setLinearVelocity(new Vec2(0, 0));
        System.out.println("Setting a transform");
        body.setTransform(new Vec2(x * (1.0f / pixelsToMeters), y * (1.0f / pixelsToMeters)), angle);
    }

    @Override
    public void setWorldTransform(float x, float y, float angle){
        body.setTransform(new Vec2(x, y), angle);
    }

    @Override
    public void setFriction(float friction) {
        body.getFixtureList().setFriction(friction);
    }

    @Override
    public void setDensity(float density) {
        body.getFixtureList().setDensity(density);
    }

    @Override
    public void setRestitution(float restitution) {
        body.getFixtureList().setRestitution(restitution);
    }

    @Override
    public void setSensor(boolean sensor) {
        body.getFixtureList().setSensor(sensor);
        //TODO: what is a sensor..?
    }

    @Override
    public void setLinearVelocity(float vx, float vy) {
        body.setLinearVelocity(new Vec2(vx, vy));
    }

    @Override
    public float getX() {
        return body.getTransform().p.x * pixelsToMeters;
    }

    @Override
    public float getY() {
        return body.getTransform().p.y * pixelsToMeters;
    }

    @Override
    public float getScreenX(PhysicsWorld world) {
        return body.getTransform().p.x * pixelsToMeters + world.getXOffset();
    }

    @Override
    public float getScreenY(PhysicsWorld world) {
        return body.getTransform().p.y * pixelsToMeters + world.getYOffset();
    }

    @Override
    public float getWorldX() {
        return body.getTransform().p.x;
    }

    @Override
    public float getWorldY() {
        return body.getTransform().p.y;
    }

    @Override
    public float getAngleRad() {
        return body.getAngle() * (180 / (float) Math.PI);
    }

    @Override
    public float getAngleDeg() {
        return body.getAngle();
    }

    @Override
    public void setAwake(boolean awake){
        body.setAwake(awake);
    }

    @Override
    public float getLinearVelocityX(){
        return body.getLinearVelocity().x;
    }

    @Override
    public float getLinearVelocityY(){
        return body.getLinearVelocity().y;
    }

    @Override
    public float getAngularVelocity(){
        return body.getAngularVelocity();
    }

    @Override
    public Body getBody(){
        return body;
    }

    @Override
    public void applyForce(float fx, float fy, float posX, float posY){
        body.applyForce(new Vec2(fx, fy), new Vec2(posX, posY));
    }

    @Override
    public void addChildWithJoint(PhysicsWorld world, PhysicsObject child, PhysicsJoint joint){
        //To be overridden by other classes
    }

    @Override
    public CollisionInfo getMyCollisionInfo(){
        return (CollisionInfo)body.getUserData();
    }
    @Override
    public boolean checkCollision(PhysicsObject otherObject){
        return getMyCollisionInfo().amICollidingWith(otherObject.getMyCollisionInfo().getMyId());
    }
}