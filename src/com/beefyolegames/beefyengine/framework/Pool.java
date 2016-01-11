package com.beefyolegames.beefyengine.framework;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo Febey on 30/12/2015.
 */
public class Pool<T> {
    public interface PoolObjectFactory<T> {
        public T createObject();
    }

    // Storage for poled objects
    private final List<T> freeObjects;

    // Used to generate new instances of the type held by the class
    private final PoolObjectFactory<T> factory;

    // Maximum number of objects allowed in this pool (so we don't run out of memory)
    private final int maxsize;

    // Init
    public Pool(PoolObjectFactory<T> factory, int maxsize) {
        this.factory = factory;
        this.maxsize = maxsize;

        this.freeObjects = new ArrayList<T>(maxsize);
    }

    /* Returns a new object.
     * If the list is empty, it creates a new object. otherwise, more likely, it pulls an already existing object out
     * of the freeObjects (Pool) ArrayList
    */
    public T newObject() {
        T object = null;
        if (freeObjects.isEmpty()) {
            object = factory.createObject();
        } else {
            object = freeObjects.remove(freeObjects.size() - 1);
        }
        return object;
    }

    /* Re-insert objects that we no-longer use. if the list is full, the object won't be added and  will be
     left for the garbageCollector
     */
    public void free(T object) {
        if (freeObjects.size() < maxsize) {
            freeObjects.add(object);
        }
    }
}