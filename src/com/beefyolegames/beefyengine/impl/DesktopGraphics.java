package com.beefyolegames.beefyengine.impl;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;

/**
 * Created by Leo Febey on 3/01/2016.
 */
public class DesktopGraphics {
    static GL11 gl;
    static GLFWVidMode vidMode;

    DesktopGraphics(){
        vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
    }

    public GL11 getGl(){
        return gl;
    }

    public int getWidth(){
        return vidMode.width();
    }

    public int getHeight(){
        return vidMode.height();
    }
}
