package com.beefyolegames.beefyengine.phys_box2d.physics_joints;

import com.beefyolegames.beefyengine.framework.PhysicsObject;
import com.beefyolegames.beefyengine.framework.PhysicsWorld;
import com.beefyolegames.beefyengine.phys_box2d.PhysWorld;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.WheelJoint;
import org.jbox2d.dynamics.joints.WheelJointDef;

/**
 * Created by leo on 26/04/14.
 */
public class SuspensionWheelJoint extends PhysicsJointImpl{
    WheelJointDef wheelJointDef;
    WheelJoint wheelJoint;

    float offsetX;
    float offsetY;

    public SuspensionWheelJoint(PhysWorld world, float offsetX, float offsetY, boolean collisionsEnabled) {
        //super(world, anchorAx, anchorAy, anchorBx, anchorBy, collisionsEnabled);
        pixelsToMeters = world.getPixelsToMeters();
        wheelJointDef = new WheelJointDef();
        this.offsetX = (1.0f / pixelsToMeters) * offsetX;
        this.offsetY = (1.0f / pixelsToMeters) * offsetY;
        wheelJointDef.collideConnected = collisionsEnabled;
        jointCreated = false;
    }

    // ---- WheelJoint-specific methods ---- //
    public void setAndEnableMotor(float torque, float speed){
        if(jointCreated){
            wheelJoint.enableMotor(true);
            wheelJoint.setMotorSpeed(speed);
            wheelJoint.setMaxMotorTorque(torque);
        } else {
            wheelJointDef.enableMotor = true;
            wheelJointDef.maxMotorTorque = torque;
            wheelJointDef.motorSpeed = speed;
        }
    }
    public void setDampingRatio(float dampingRatio){
        if(jointCreated){
            wheelJoint.setSpringDampingRatio(dampingRatio);
        } else {
            wheelJointDef.dampingRatio = dampingRatio;
        }
    }

    public float getMotorSpeed(){
        if(jointCreated){
            return wheelJoint.getMotorSpeed();
        } else {
            return 0;
        }
    }

    @Override
    public void createJoint(PhysicsWorld world, PhysicsObject parent, PhysicsObject child){
        child.setWorldTransform(parent.getWorldX() + offsetX + anchorAx + anchorBx,
                parent.getWorldY() + offsetY + anchorAy + anchorBy, child.getAngleDeg());

        Vec2 parentPos = new Vec2(parent.getWorldX() + anchorAx - anchorBx , parent.getWorldY() + anchorAy - anchorBy);
        Vec2 childPos = new Vec2(child.getWorldX(), child.getWorldY());
        Vec2 diff = parentPos.sub(childPos);
        float diffLength = diff.length();
        Vec2 axis = new Vec2(diff.x / diffLength, diff.y / diffLength);
        Vec2 pos = new Vec2(parent.getWorldX(), parent.getWorldY());
        wheelJointDef.initialize((Body)parent.getBody(), (Body)child.getBody(), pos, axis);

        wheelJoint = (WheelJoint)((World)world.getWorld()).createJoint(wheelJointDef);

        jointCreated = true;

    }

    @Override
    public void destroyJoint(PhysicsWorld world){
        ((World)world.getWorld()).destroyJoint(wheelJoint);
        jointCreated = false;
    }
}
