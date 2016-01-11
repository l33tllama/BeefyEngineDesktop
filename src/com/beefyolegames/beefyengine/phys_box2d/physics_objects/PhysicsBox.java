package com.beefyolegames.beefyengine.phys_box2d.physics_objects;

import com.beefyolegames.beefyengine.framework.PhysicsObject;
import com.beefyolegames.beefyengine.framework.PhysicsWorld;
import com.beefyolegames.beefyengine.phys_box2d.PhysWorld;
import com.beefyolegames.beefyengine.framework.PhysicsJoint;
import com.beefyolegames.beefyengine.phys_box2d.CollisionInfo;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.*;

/**
 * Created with IntelliJ IDEA.
 * User: Leo
 * Date: 28/07/13
 * Time: 6:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class PhysicsBox extends PhysicsObjectImpl {

    PolygonShape polygonShape;

    public PhysicsBox(BodyType type, PhysWorld physWorld, float x, float y, float width, float height) {
        this.pixelsToMeters = physWorld.getPixelsToMeters();
        bodyDef = new BodyDef();
        bodyDef.position.set(x * (1.0f / pixelsToMeters), y * (1.0f / pixelsToMeters));
        bodyDef.type = type;
        body = physWorld.getWorld().createBody(bodyDef);
        polygonShape = new PolygonShape();
        polygonShape.setAsBox(width * (1.0f / (float) pixelsToMeters), height * (1.0f / (float) pixelsToMeters));
        fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 1.0f;
        body.createFixture(fixtureDef);
        CollisionInfo myInfo = physWorld.addNewObjectID();
        myID = myInfo.getMyId();
        body.setUserData(myInfo);
    }

    @Override
    public void addChildWithJoint(PhysicsWorld world, PhysicsObject child, PhysicsJoint joint){
        joint.createJoint(world, this, child);
    }
}
