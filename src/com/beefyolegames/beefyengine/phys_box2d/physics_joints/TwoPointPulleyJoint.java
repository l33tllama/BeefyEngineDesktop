package com.beefyolegames.beefyengine.phys_box2d.physics_joints;

import com.beefyolegames.beefyengine.framework.PhysicsObject;
import com.beefyolegames.beefyengine.framework.PhysicsWorld;
import com.beefyolegames.beefyengine.phys_box2d.PhysWorld;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.PulleyJoint;
import org.jbox2d.dynamics.joints.PulleyJointDef;

/**
 * Created by leo on 26/04/14.
 */
public class TwoPointPulleyJoint extends PhysicsJointImpl{
    PulleyJointDef pulleyJointDef;
    PulleyJoint pulleyJoint;

    float pAx;
    float pAy;
    float pBx;
    float pBy;

    public TwoPointPulleyJoint(PhysWorld world, float pulleyOffsetAx, float pulleyOffsetAy, float pulleyOffsetBx, float pulleyOffsetBy, boolean collisionsEnabled) {
        //super(world, anchorAx, anchorAy, anchorBx, anchorBy, collisionsEnabled);
        pulleyJointDef = new PulleyJointDef();
        this.pixelsToMeters = world.getPixelsToMeters();
        pAx = (1.0f / pixelsToMeters ) * pulleyOffsetAx;
        pAy = (1.0f / pixelsToMeters) * pulleyOffsetAy;
        pBx = (1.0f / pixelsToMeters) * pulleyOffsetBx;
        pBy = (1.0f / pixelsToMeters) * pulleyOffsetBy;
        pulleyJointDef.collideConnected = collisionsEnabled;
        jointCreated = false;
    }

    // --- TwoPointPulleyJoint-Specifi methods ---- //

    public void setRatio(float ratio){
        if(!jointCreated){
            pulleyJointDef.ratio = ratio;
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
        pAx = parent.getWorldX() + pAx;
        pAy = parent.getWorldY() + pAy;
        pBx = child.getWorldX() + pBx;
        pBy = child.getWorldY() + pBy;

        pulleyJointDef.initialize((Body)parent.getBody(), (Body)child.getBody(), new Vec2(pAx, pAy), new Vec2(pBx, pBy), new Vec2(anchorAx, anchorAx), new Vec2(anchorBx, anchorBy), pulleyJointDef.ratio);
        //totally pro code ??
        pulleyJoint = (PulleyJoint)((World)world.getWorld()).createJoint(pulleyJointDef);
        jointCreated = true;
    }

    @Override
    public void destroyJoint(PhysicsWorld world){
        ((World)world.getWorld()).destroyJoint(pulleyJoint);
        jointCreated = false;
    }
}
