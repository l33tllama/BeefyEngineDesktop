package com.beefyolegames.beefyengine.phys_box2d;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.contacts.Contact;

/**
 * Created by leo on 26/04/14.
 */
public class CollisionMonitor implements ContactListener{
    @Override
    public void beginContact(Contact contact) {

        CollisionInfo contactAInfo = (CollisionInfo)(contact.getFixtureA().getBody().getUserData());
        Integer contactAID = contactAInfo.getMyId();

        CollisionInfo contactBInfo = (CollisionInfo)(contact.getFixtureB().getBody().getUserData());
        Integer contactBID = contactBInfo.getMyId();

        contactAInfo.addOtherIDToList(contactBID);
        contactBInfo.addOtherIDToList(contactAID);

        contact.getFixtureA().getBody().setUserData(contactAInfo);
        contact.getFixtureB().getBody().setUserData(contactBInfo);

        System.out.println("Contact! IDA: " + contactAID + "IDB: " + contactBID);
    }

    @Override
    public void endContact(Contact contact) {
        CollisionInfo contactAInfo = (CollisionInfo)(contact.getFixtureA().getBody().getUserData());
        Integer contactAID = contactAInfo.getMyId();

        CollisionInfo contactBInfo = (CollisionInfo)(contact.getFixtureB().getBody().getUserData());
        Integer contactBID = contactBInfo.getMyId();

        contactAInfo.removeIDFromList(contactBID);
        contactBInfo.removeIDFromList(contactAID);

        contact.getFixtureA().getBody().setUserData(contactAInfo);
        contact.getFixtureB().getBody().setUserData(contactBInfo);

        System.out.println("Contact ended with IDA: " + contactAID + "IDB: " + contactBID);

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
