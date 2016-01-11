package com.beefyolegames.beefyengine.impl;

import com.beefyolegames.beefyengine.framework.*;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * Created by Leo Febey on 30/12/2015.
 */
public class DesktopGame implements Game {
    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback keyCallback;

    private long window;

    private int temp_width = 800;
    private int temp_height = 600;

    private long lastFrame;
    private long lastFPS;

    DesktopGraphics glGraphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    Screen screen;
    GLGameState state = GLGameState.Initialized;

    public DesktopGraphics getGLGraphics() {
        return glGraphics;
    }

    @Override
    public Input getInput() {
        return null;
    }

    @Override
    public FileIO getFileIO() {
        return null;
    }

    @Override
    public Graphics getGraphics() {
        return null;
    }

    @Override
    public Audio getAudio() {
        return null;
    }

    @Override
    public void setScreen(Screen screen) {

    }

    @Override
    public Screen getCurrentScreen() {
        return null;
    }

    @Override
    public Screen getStartScreen() {
        return null;
    }


    enum GLGameState{
        Initialized, Running, Paused, Finished, Idle
    }

    private void _glfwInit(){
        // setup error callback.
        // will print error msg in System.err
        glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));

        if(glfwInit() != GLFW_TRUE)
            throw new IllegalStateException("Unable to init GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        window = glfwCreateWindow(temp_width, temp_height, "LWGJL Window", NULL, NULL);

        if (window == NULL)
            throw new RuntimeException("Failed to create GLFW window");

        // setup key callback.. TODO: move to separate class?
        glfwSetKeyCallback(window, keyCallback = new DesktopKeyHandler());

        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        // Center the window
        glfwSetWindowPos(
                window,
                (vidMode.width() - temp_width) / 2,
                (vidMode.height() - temp_height) / 2
        );

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);

        // enable v-sync
        glfwSwapInterval(1);

        glfwShowWindow(window);
    }
    private long getTime() {
        return (long)(GLFW.glfwGetTime() * 1000);
    }

    private int getDelta() {
        long time = getTime();
        int delta = (int) (time - lastFrame);
        lastFrame = time;

        return delta;
    }

    private void glfwLoop(){

        // critical for setting up GLFW's OpenGL context
        GL.createCapabilities();

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        while(glfwWindowShouldClose(window) == GLFW_FALSE){
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            glfwSwapBuffers(window);  // swap the color buffers

            glfwPollEvents();

            if(DesktopKeyHandler.isKeyDown(GLFW_KEY_ESCAPE)) {
                glfwSetWindowShouldClose(window, GLFW_TRUE);
            }
            int delta = getDelta();
            // TODO: update when we have a screen
            screen.update(delta);
        }

    }

    private void init(){
        //TODO: intitialise framework..
        glGraphics = new DesktopGraphics();
        input = new DesktopInput();
        fileIO = new DesktopFileIO();
        audio = new DesktopAudio();

        if(state == GLGameState.Initialized){
            screen = getStartScreen();
        }

        getDelta();
        lastFPS = getTime();
        state = GLGameState.Running;

    }

    public void run(){
        try{
            // initialise GLFW window
            _glfwInit();

            // load game engine stuff
            init();

            // main loop
            glfwLoop();

            // release window and window callbacks
            glfwDestroyWindow(window);
            keyCallback.release();

            state = GLGameState.Finished;

        } finally {
            glfwTerminate();
            errorCallback.release();
        }
    }

    public DesktopGame(){
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");
        _glfwInit();
        run();
    }
}
