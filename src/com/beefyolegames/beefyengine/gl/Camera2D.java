package com.beefyolegames.beefyengine.gl;

import com.beefyolegames.beefyengine.framework.Game;
import com.beefyolegames.beefyengine.impl.DesktopGame;
import com.beefyolegames.beefyengine.impl.DesktopGraphics;
import com.beefyolegames.beefyengine.math.Vector2;
import org.lwjgl.opengl.GL11;

/**
 * Created by Leo Febey on 3/01/2016.
 */
public class Camera2D {
    public final Vector2 position;
    //public final Vec2 position_b2d;
    public float zoom;
    public final float frustumWidth;
    public final float frustumHeight;
    DesktopGraphics glGraphics;

    public Camera2D(DesktopGame game, float frustumWidth, float frustumHeight) {
        this.glGraphics = game.getGLGraphics();
        this.frustumWidth = frustumWidth;
        this.frustumHeight = frustumHeight;
        this.position = new Vector2(frustumWidth / 2, frustumHeight / 2);
        //this.position_b2d = new Vec2(frustumWidth / 2, frustumHeight / 2);
        this.zoom = 1.0f;
    }

    public void setViewportAndMatrices() {
        GL11.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(position.x - frustumWidth * zoom / 2,
                position.x + frustumWidth * zoom / 2,
                position.y - frustumHeight * zoom / 2,
                position.y + frustumHeight * zoom / 2,
                1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
    }

    public void touchToWorld(Vector2 touch) {
        touch.x = (touch.x / (float) glGraphics.getWidth()) * frustumWidth * zoom;
        touch.y = (1 - touch.y / (float) glGraphics.getHeight()) * frustumHeight * zoom;
        touch.add(position).sub(frustumWidth * zoom / 2, frustumHeight * zoom / 2);
    }

}
