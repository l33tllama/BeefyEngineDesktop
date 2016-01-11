/**
 * Created by Leo on 7/06/13.
 *
 * A generic game object that other objects can inherit from.
 * has a static position and bounding rectangle (bound);
 *
 */
package com.beefyolegames.beefyengine.framework;

import com.beefyolegames.beefyengine.math.Vector2;
import com.beefyolegames.beefyengine.math.Rectangle;

public class GameObject {
    public final Vector2 position;
    public final Rectangle bounds;

    public GameObject(float x, float y, float width, float height) {
        this.position = new Vector2(x, y);
        this.bounds = new Rectangle(x - width / 2, y - height / 2, width,
                height);
    }
}
