package com.beefyolegames.beefyengine.phys_box2d.physics_joints;

import com.beefyolegames.beefyengine.framework.PhysicsObject;
import com.beefyolegames.beefyengine.framework.PhysicsWorld;
import com.beefyolegames.beefyengine.phys_box2d.PhysWorld;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.RevoluteJoint;
import org.jbox2d.dynamics.joints.RevoluteJointDef;

/**
 * Created by leo on 26/04/14.
 */
public class RevolvingJoint extends PhysicsJointImpl {
    RevoluteJoint revJoint;
    RevoluteJointDef revJointDef;

    public RevolvingJoint(PhysWorld world, boolean collisionsEnabled) {
        //super(world, anchorAx, anchorAy, anchorBx, anchorBy, collisionsEnabled);
        revJointDef = new RevoluteJointDef();
        pixelsToMeters = world.getPixelsToMeters();
        revJointDef.localAnchorA = new Vec2(this.anchorAx, this.anchorAy);
        revJointDef.localAnchorB = new Vec2(this.anchorBx, this.anchorBy);
        revJointDef.collideConnected = collisionsEnabled;
        jointCreated = false;
    }

    // ---- Revolving Joint Specific Methods ---- //
    /*
    Set and enable angle limits - upper and lower in degrees
     */
    public void setAndEnableLimit(float lowerAngleDeg, float upperAngleDeg){
        if(jointCreated){
            revJoint.enableLimit(true);
            revJoint.setLimits(lowerAngleDeg, upperAngleDeg);
        } else {
            revJointDef.enableLimit = true;
            revJointDef.lowerAngle = lowerAngleDeg * (360 / (float)Math.PI);
            revJointDef.upperAngle = upperAngleDeg * (360 / (float)Math.PI);
        }
    }

    public void disableLimit(){
        if(jointCreated){
            revJoint.enableLimit(false);
        } else {
            revJointDef.enableLimit = false;
        }
    }

    public float getJointAngle(){
        return revJoint.getJointAngle();
    }

    /*
    Set and enable motor speed (rads per second) and torque in newton Meters??? documentation fail..
     */
    public void setAndEnableMotor(float torqueNM, float speedDegPerSec){
        if(jointCreated){
            revJoint.enableMotor(true);
            revJoint.setMotorSpeed(speedDegPerSec);
            revJoint.setMaxMotorTorque(torqueNM);
        } else {
            revJointDef.enableMotor = true;
            revJointDef.motorSpeed = speedDegPerSec * (360 / (float)Math.PI);
            revJointDef.maxMotorTorque = torqueNM;
        }
    }

    public void disableMotor(){
        revJoint.enableMotor(false);
    }

    @Override
    public void createJoint(PhysicsWorld world, PhysicsObject parent, PhysicsObject child) {
        child.setWorldTransform(parent.getWorldX() + anchorAx - anchorBx, parent.getWorldY() + anchorAy - anchorBy, child.getAngleDeg());
        revJointDef.bodyA = (Body)parent.getBody();
        revJointDef.bodyB = (Body)child.getBody();
        //totally pro code ??
        revJoint = (RevoluteJoint)((World)world.getWorld()).createJoint(revJointDef);
        jointCreated = true;
    }

    @Override
    public void destroyJoint(PhysicsWorld world){
        ((World)world.getWorld()).destroyJoint(revJoint);
        jointCreated = false;
    }
}
