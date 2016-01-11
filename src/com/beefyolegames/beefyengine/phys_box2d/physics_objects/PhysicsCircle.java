package com.beefyolegames.beefyengine.phys_box2d.physics_objects;


import com.beefyolegames.beefyengine.framework.PhysicsObject;
import com.beefyolegames.beefyengine.framework.PhysicsWorld;
import com.beefyolegames.beefyengine.phys_box2d.PhysWorld;
import com.beefyolegames.beefyengine.framework.PhysicsJoint;
import com.beefyolegames.beefyengine.phys_box2d.CollisionInfo;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

/**
 * Created with IntelliJ IDEA.
 * User: Leo
 * Date: 29/07/13
 * Time: 11:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class PhysicsCircle extends PhysicsObjectImpl {

    CircleShape circleShape;

    public PhysicsCircle(BodyType type, PhysWorld world, float x, float y, float radius) {
        this.pixelsToMeters = world.getPixelsToMeters();
        bodyDef = new BodyDef();
        bodyDef.position.set(x * (1.0f / pixelsToMeters), y * (1.0f / pixelsToMeters));
        bodyDef.type = type;
        body = world.getWorld().createBody(bodyDef);
        circleShape = new CircleShape();
        circleShape.m_radius = radius * (1.0f / pixelsToMeters);
        fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 1.0f;
        body.createFixture(fixtureDef);
        CollisionInfo myInfo = world.addNewObjectID();
        myID = myInfo.getMyId();
        body.setUserData(myInfo);
    }

    @Override
    public void addChildWithJoint(PhysicsWorld world, PhysicsObject child, PhysicsJoint joint){
        joint.createJoint(world, this, child);
    }
}
