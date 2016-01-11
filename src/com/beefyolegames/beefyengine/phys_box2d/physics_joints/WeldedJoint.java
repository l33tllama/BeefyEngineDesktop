package com.beefyolegames.beefyengine.phys_box2d.physics_joints;

import com.beefyolegames.beefyengine.framework.PhysicsObject;
import com.beefyolegames.beefyengine.framework.PhysicsWorld;
import com.beefyolegames.beefyengine.phys_box2d.PhysWorld;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.WeldJoint;
import org.jbox2d.dynamics.joints.WeldJointDef;

/**
 * Created by leo on 26/04/14.
 */
public class WeldedJoint extends PhysicsJointImpl {
    WeldJointDef weldJointDef;
    WeldJoint weldJoint;

    public WeldedJoint(PhysWorld world, boolean collisionsEnabled) {
        //super(world, anchorAx, anchorAy, anchorBx, anchorBy, collisionsEnabled);
        weldJointDef = new WeldJointDef();
        this.pixelsToMeters = world.getPixelsToMeters();
        weldJointDef.collideConnected = collisionsEnabled;
        jointCreated = false;
    }

    // ---- WeldedJoint-Specific Methods ---- //
    public void setDampingRatio(float dampingRatio){
        if(jointCreated){
            weldJoint.setDampingRatio(dampingRatio);
        } else {
            weldJointDef.dampingRatio = dampingRatio;
        }
    }

    public void setFrequencyHz(float frequencyHz){
        if(jointCreated){
            weldJoint.setFrequency(frequencyHz);
        } else {
            weldJointDef.frequencyHz = frequencyHz;
        }
    }

    public void setReferenceAngle(float referenceAngle){
        if(!jointCreated){
            weldJointDef.referenceAngle = referenceAngle;
        }

    }

    @Override
    public void createJoint(PhysicsWorld world, PhysicsObject parent, PhysicsObject child) {

        child.setWorldTransform(parent.getWorldX() + anchorAx + anchorBx,
                parent.getWorldY() + anchorAy + anchorBy, child.getAngleDeg());
        anchorAx = parent.getWorldX() + anchorAx;
        anchorAy = parent.getWorldY() + anchorAy;
        anchorBx = child.getWorldX() + anchorBx;
        anchorBy = child.getWorldY() + anchorBy;

        weldJointDef.initialize((Body)parent.getBody(), (Body)child.getBody(), new Vec2(anchorAx, anchorAy));
        //totally pro code ??
        weldJoint = (WeldJoint)((World)world.getWorld()).createJoint(weldJointDef);
    }

    @Override
    public void destroyJoint(PhysicsWorld world){
        ((World)world.getWorld()).destroyJoint(weldJoint);
    }
}
