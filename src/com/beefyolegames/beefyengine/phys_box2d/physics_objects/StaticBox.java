package com.beefyolegames.beefyengine.phys_box2d.physics_objects;

import com.beefyolegames.beefyengine.phys_box2d.PhysWorld;

import org.jbox2d.dynamics.*;

/**
 * Created with IntelliJ IDEA.
 * User: Leo
 * Date: 28/07/13
 * Time: 11:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class StaticBox extends PhysicsBox {

    public StaticBox(PhysWorld world, float x, float y, float width, float height) {
        super(BodyType.STATIC, world, x, y, width, height);
        System.out.print("Box top: " + ((y + height) / 2.0f) * ( 1.0f / pixelsToMeters));
    }
}
