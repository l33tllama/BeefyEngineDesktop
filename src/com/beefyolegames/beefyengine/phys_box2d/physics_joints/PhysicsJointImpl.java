package com.beefyolegames.beefyengine.phys_box2d.physics_joints;

import com.beefyolegames.beefyengine.framework.PhysicsObject;
import com.beefyolegames.beefyengine.framework.PhysicsWorld;
import com.beefyolegames.beefyengine.framework.PhysicsJoint;


import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.DistanceJoint;
import org.jbox2d.dynamics.joints.DistanceJointDef;

/**
 * Created by leo on 26/04/14.
 */
public class PhysicsJointImpl implements PhysicsJoint {
    //TODO: Gear and Friction joint, when needed
    boolean collisionsEnabled;
    boolean jointCreated;
    float anchorAx;
    float anchorAy;
    float anchorBx;
    float anchorBy;
    float pixelsToMeters;

    public void setAnchorA(float anchorAx, float anchorAy){
        this.anchorAx = (1.0f / pixelsToMeters) * anchorAx;
        this.anchorAy = (1.0f / pixelsToMeters) * anchorAy;
    }

    public void setAnchorB(float anchorBx, float anchorBy){
        this.anchorBx = (1.0f / pixelsToMeters) * anchorBx;
        this.anchorBy = (1.0f / pixelsToMeters) * anchorBy;
    }

    public void setCollisionsEnabled(boolean collisionsEnabled){
        this.collisionsEnabled = collisionsEnabled;
    }

    @Override
    public void createJoint(PhysicsWorld world, PhysicsObject parent, PhysicsObject child) {
        // to be overridden
    }

    @Override
    public void destroyJoint(PhysicsWorld world) {
        // to be overridden
    }
}
