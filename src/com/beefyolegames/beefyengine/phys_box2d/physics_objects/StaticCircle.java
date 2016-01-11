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
public class StaticCircle extends PhysicsCircle{
    public StaticCircle(PhysWorld world, float x, float y, float radius) {
        super(BodyType.STATIC, world, x, y, radius);
    }
}
