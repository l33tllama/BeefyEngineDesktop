package com.beefyolegames.beefyengine.impl;

import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

/**
 * Created by Leo Febey on 3/01/2016.
 */
public class DesktopKeyHandler extends GLFWKeyCallback {

    public static boolean[] keys = new boolean[65536];

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        keys[key] = action != GLFW_RELEASE;
    }

    public static boolean isKeyDown(int keyCode){
        return keys[keyCode];
    }

    //TODO: get key by string/char
}
