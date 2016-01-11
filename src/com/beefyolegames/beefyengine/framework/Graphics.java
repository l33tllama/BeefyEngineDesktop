package com.beefyolegames.beefyengine.framework;

/**
 * Created by Leo Febey on 30/12/2015.
 */
public interface Graphics {
    public void clear(int color);

    public void drawPixel(int x, int y, int color);

    public void drawLine(int x, int y, int x2, int y2, int color);

    public void drawRect(int x, int y, int width, int height, int color);

    public int getWidth();

    public int getHeight();
}
