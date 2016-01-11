package com.beefyolegames.beefyengine.phys_box2d.physics_objects;

import com.beefyolegames.beefyengine.phys_box2d.PhysWorld;

import org.jbox2d.dynamics.BodyType;

/**
 * Created with IntelliJ IDEA.
 * User: Leo
 * Date: 28/07/13
 * Time: 12:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class KinematicBox extends PhysicsBox {
    public KinematicBox(PhysWorld world, float x, float y, float width, float height) {
        super(BodyType.KINEMATIC, world, x, y, width, height);
    }
}
