package com.beefyolegames.beefyengine.phys_box2d.physics_objects;

import com.beefyolegames.beefyengine.framework.PhysicsObject;
import com.beefyolegames.beefyengine.framework.PhysicsWorld;
import com.beefyolegames.beefyengine.phys_box2d.PhysWorld;
import com.beefyolegames.beefyengine.framework.PhysicsJoint;
import com.beefyolegames.beefyengine.phys_box2d.CollisionInfo;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
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
public class PhysicsShape extends PhysicsObjectImpl {

    PolygonShape polygonShape;
    Vec2[] verts;

    public PhysicsShape(BodyType type, PhysWorld world, float x, float y, int[] xs, int[] ys, int numVerts, int maxWidth, int maxHeight) {
        this.pixelsToMeters = world.getPixelsToMeters();
        bodyDef = new BodyDef();
        bodyDef.position.set(x * (1.0f / pixelsToMeters), y * (1.0f / pixelsToMeters));
        bodyDef.type = type;
        body = world.getWorld().createBody(bodyDef);
        polygonShape = new PolygonShape();

        verts = new Vec2[numVerts];
        //System.out.println("Num verts " + numVerts);
        //Convert positions from pixels to meters
        for(int i = 0; i < numVerts; i++){
            verts[i] = new Vec2();
            verts[i].set((-(maxWidth/2) + xs[i]) * (1.0f / pixelsToMeters), ( (maxHeight /2) - ys[i]) * (1.0f / pixelsToMeters));
            //System.out.println("Set b2d vertice x: "+ verts[i].x + ", y: " + verts[i].y);
        }

        polygonShape.set(verts, numVerts);
        fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
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

    @Override
    public boolean checkCollision(PhysicsObject other){
        return false;
        //ToDO: replace with working collisions ihateshapednet
        //if(org.jbox2d.common.)
    }
}
