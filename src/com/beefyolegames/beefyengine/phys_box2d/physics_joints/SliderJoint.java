package com.beefyolegames.beefyengine.phys_box2d.physics_joints;

import com.beefyolegames.beefyengine.framework.PhysicsObject;
import com.beefyolegames.beefyengine.framework.PhysicsWorld;
import com.beefyolegames.beefyengine.phys_box2d.PhysWorld;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.PrismaticJoint;
import org.jbox2d.dynamics.joints.PrismaticJointDef;

/**
 * Created by leo on 26/04/14.
 */
public class SliderJoint extends PhysicsJointImpl {
    PrismaticJointDef prismJointDef;
    PrismaticJoint prismJoint;

    float posX;
    float posY;
    float axisX;
    float axisY;

    boolean autoSlider;

    //For creating a Slider joint with an automatic axis that is the length between parent and child
    public SliderJoint(PhysWorld world, boolean collisionsEnabled){
        pixelsToMeters = world.getPixelsToMeters();
        prismJointDef = new PrismaticJointDef();
        prismJointDef.collideConnected = collisionsEnabled;
        jointCreated = false;
        autoSlider = true;
    }

    // ---- SliderJoint-Specific Methods ---- //

    public void setPositionAndAxis(float posX, float posY, float axisX, float axisY){
        this.posX = (1.0f / pixelsToMeters) * posX;
        this.posY = (1.0f / pixelsToMeters) * posY;
        this.axisX = (1.0f / pixelsToMeters) * axisX;
        this.axisY = (1.0f / pixelsToMeters) * axisY;
        autoSlider = false;
    }

    //Set slider limit
    public void setAndEnableLimit(float lower, float upper){
        if(jointCreated){
            prismJoint.enableLimit(true);
            prismJoint.setLimits(lower, upper);
        } else {
            prismJointDef.enableLimit = true;
            prismJointDef.lowerTranslation = lower;
            prismJointDef.upperTranslation = upper;
        }
    }

    public void disableLimit(){
        if(jointCreated){
            prismJoint.enableLimit(false);
        } else{
            prismJointDef.enableLimit = false;
        }
    }

    public void setAndEnableMotor(float torque, float speed){
        if(jointCreated){
            prismJoint.enableMotor(true);
            prismJoint.setMotorSpeed(speed);
        } else{
            prismJointDef.enableMotor = true;
            prismJointDef.maxMotorForce = torque;
            prismJointDef.motorSpeed = speed;
        }

    }

    public void disableMotor(){
        if(jointCreated){
            prismJoint.enableMotor(false);
        } else {
            prismJointDef.enableMotor = false;
        }
    }


    @Override
    public void createJoint(PhysicsWorld world, PhysicsObject parent, PhysicsObject child){
        child.setWorldTransform(parent.getWorldX() + anchorAx + anchorBx,
                parent.getWorldY() + anchorAy + anchorBy, child.getAngleDeg());
        if(autoSlider){
            //Get difference between child and parent and use for slider axis and position
            Vec2 diff = new Vec2(child.getWorldX(), child.getWorldY()).sub(new Vec2(parent.getWorldX(), parent.getWorldY()));
            float diffLength = diff.length();
            setAndEnableLimit(0, diffLength);
            Vec2 axis = new Vec2(diff.x / diffLength, diff.y / diffLength);
            Vec2 pos = new Vec2(parent.getWorldX(), parent.getWorldY());
            prismJointDef.initialize((Body)parent.getBody(), (Body)child.getBody(), pos, axis);
        } else{
            prismJointDef.initialize((Body)parent.getBody(), (Body)child.getBody(),new Vec2(posX, posY), new Vec2(axisX, axisY));
        }

        prismJoint = (PrismaticJoint)((World)world.getWorld()).createJoint(prismJointDef);
        jointCreated = true;

    }

    @Override
    public void destroyJoint(PhysicsWorld world){
        ((World)world.getWorld()).destroyJoint(prismJoint);
        jointCreated = false;
    }
}
