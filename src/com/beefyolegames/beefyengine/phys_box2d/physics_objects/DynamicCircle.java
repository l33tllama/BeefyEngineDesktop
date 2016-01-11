package com.beefyolegames.beefyengine.phys_box2d.physics_objects;

import com.beefyolegames.beefyengine.phys_box2d.PhysWorld;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyType;

/**
 * Created with IntelliJ IDEA.
 * User: Leo
 * Date: 24/07/13
 * Time: 4:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class DynamicCircle extends PhysicsCircle {
    public DynamicCircle(PhysWorld world, float x, float y, float radius) {
        super(BodyType.DYNAMIC, world, x, y, radius);
    }
}
