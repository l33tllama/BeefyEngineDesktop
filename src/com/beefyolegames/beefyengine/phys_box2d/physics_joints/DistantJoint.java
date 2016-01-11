package com.beefyolegames.beefyengine.phys_box2d.physics_joints;


import com.beefyolegames.beefyengine.framework.PhysicsObject;
import com.beefyolegames.beefyengine.framework.PhysicsWorld;
import com.beefyolegames.beefyengine.phys_box2d.PhysWorld;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.DistanceJoint;
import org.jbox2d.dynamics.joints.DistanceJointDef;

/**
 * Created with IntelliJ IDEA.
 * User: Leo
 * Date: 15/08/13
 * Time: 9:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class DistantJoint extends PhysicsJointImpl {

    private DistanceJoint distJoint;
    private DistanceJointDef distJointDef;

    private float offsetX;
    private float offsetY;

    public DistantJoint(PhysWorld world, float offsetX, float offsetY, boolean collisionsEnabled) {
        distJointDef = new DistanceJointDef();
        this.pixelsToMeters = world.getPixelsToMeters();
        this.offsetX = (1.0f / pixelsToMeters) * offsetX;
        this.offsetY = (1.0f / pixelsToMeters) * offsetY;
        distJointDef.collideConnected = collisionsEnabled;
        jointCreated = false;
    }

    // ---- DistantJoint Specific Methods ---- //
    public void setDampingRatio(float dampingRatio){
        if(jointCreated){
            distJoint.setDampingRatio(dampingRatio);
        } else {
            distJointDef.dampingRatio = dampingRatio;
        }
    }

    public void setFrequencyHz(float frequencyHz){
        if(jointCreated){
            distJoint.setFrequency(frequencyHz);
        } else {
            distJointDef.frequencyHz = frequencyHz;
        }

    }

    public void setDistance(float distance){
        if(jointCreated){
            distJoint.setLength(distance * (1.0f / pixelsToMeters));
        } else{
            distJointDef.length = distance;
        }

    }

    @Override
    public void createJoint(PhysicsWorld world, PhysicsObject parent, PhysicsObject child) {

        child.setWorldTransform(parent.getWorldX() + offsetX + anchorAx + anchorBx,
                parent.getWorldY() + offsetY + anchorAy + anchorBy, child.getAngleDeg());
        anchorAx = parent.getWorldX() + anchorAx;
        anchorAy = parent.getWorldY() + anchorAy;
        anchorBx = child.getWorldX() + anchorBx;
        anchorBy = child.getWorldY() + anchorBy;

        distJointDef.initialize((Body)parent.getBody(), (Body)child.getBody(), new Vec2(anchorAx, anchorAy), new Vec2(anchorBx, anchorBy));
        //totally pro code ??
        distJoint = (DistanceJoint)((World)world.getWorld()).createJoint(distJointDef);
        jointCreated = true;
    }

    @Override
    public void destroyJoint(PhysicsWorld world){
        ((World)world.getWorld()).destroyJoint(distJoint);
        jointCreated = false;
    }
}
