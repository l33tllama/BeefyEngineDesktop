package com.beefyolegames.beefyengine.impl;

import org.lwjgl.glfw.GLFWCursorPosCallback;

/**
 * Created by Leo Febey on 12/01/2016.
 */
public class DesktopMouseHandler extends GLFWCursorPosCallback {

    int x, y;

    @Override
    public void invoke(long window, double xpos, double ypos) {
        x = (int)Math.floor(xpos);
        y = (int)Math.floor(ypos);

    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
