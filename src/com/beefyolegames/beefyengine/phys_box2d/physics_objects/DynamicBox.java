package com.beefyolegames.beefyengine.phys_box2d.physics_objects;

import com.beefyolegames.beefyengine.phys_box2d.PhysWorld;

import org.jbox2d.dynamics.*;

/**
 * Created with IntelliJ IDEA.
 * User: Leo
 * Date: 24/07/13
 * Time: 4:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class DynamicBox extends PhysicsBox {
    public DynamicBox(PhysWorld world, float x, float y, float width, float height) {
        super(BodyType.DYNAMIC, world, x, y, width, height);
    }
}
