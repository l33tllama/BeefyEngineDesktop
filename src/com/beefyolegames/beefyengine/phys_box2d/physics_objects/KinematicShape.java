package com.beefyolegames.beefyengine.phys_box2d.physics_objects;

import com.beefyolegames.beefyengine.phys_box2d.PhysWorld;

import org.jbox2d.dynamics.BodyType;

/**
 * Created with IntelliJ IDEA.
 * User: Leo
 * Date: 28/07/13
 * Time: 12:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class KinematicShape extends PhysicsShape {
    public KinematicShape(PhysWorld world, float x, float y, int[] xs, int[] ys, int numVerts, int maxWidth, int maxHeight) {
        super(BodyType.KINEMATIC, world, x, y, xs, ys, numVerts, maxWidth, maxHeight);
    }
}
