package com.beefyolegames.beefyengine.gl;

import com.beefyolegames.beefyengine.framework.FileIO;
import com.beefyolegames.beefyengine.impl.DesktopGame;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * Created by Leo Febey on 3/01/2016.
 */
public class Texture {
    FileIO fileIO;
    String fileName;
    int textureId;
    int minFilter;
    int magFilter;
    int width;
    int height;
    boolean mipmapped;
    boolean hasAlpha;

    public Texture(DesktopGame glGame, String fileName) {
        this(glGame, fileName, false, true);
    }

    public Texture(DesktopGame glGame, String fileName, boolean mipmapped, boolean hasAlpha) {
        this.fileIO = glGame.getFileIO();
        this.fileName = fileName;
        this.mipmapped = mipmapped;
        this.hasAlpha = hasAlpha;
        load();
    }

    public void load() {

        InputStream in = null;
        try {
            in = fileIO.readAsset(fileName);
            BufferedImage texImage = ImageIO.read(in);

            //TODO: mimmapping
            width = texImage.getWidth();
            height = texImage.getHeight();
            int[] pixels = new int[width * height];
            texImage.getRGB(0, 0, width, height, pixels, 0, texImage.getWidth());

            int bytesPerPixel = hasAlpha ? 4 : 3;

            ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * bytesPerPixel);

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int pixel = pixels[y * width + x];
                    buffer.put((byte) ((pixel >> 16) & 0xFF));     // Red component
                    buffer.put((byte) ((pixel >> 8) & 0xFF));      // Green component
                    buffer.put((byte) (pixel & 0xFF));               // Blue component
                    if (hasAlpha) buffer.put((byte) ((pixel >> 24) & 0xFF));    // Alpha component. Only for RGBA
                }
            }
            buffer.flip(); //FOR THE LOVE OF GOD DO NOT FORGET THIS
            // You now have a ByteBuffer filled with the color data of each pixel.
            // Now just create a texture ID and bind it. Then you can load it using
            // whatever OpenGL method you want, for example:
            textureId = GL11.glGenTextures();
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);

            //Set filters (and wrapping for certain devices)
            setFilters(GL11.GL_NEAREST, GL11.GL_NEAREST);

            //Send textel data to OpenGL
            if (hasAlpha) {
                GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, width, height, 0, GL11.GL_RGBA,
                        GL11.GL_UNSIGNED_BYTE, buffer);
            } else {
                GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, width, height, 0, GL11.GL_RGB,
                        GL11.GL_UNSIGNED_BYTE, buffer);
            }

            // Free memory?
            texImage.flush();
            buffer.clear();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                }
        }
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public void setFilters(int minFilter, int magFilter) {
        this.minFilter = minFilter;
        this.magFilter = magFilter;

        // Minification and Magnification types (usually GL_NEAREST)
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER,
                minFilter);
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER,
                magFilter);

        // Fix to allow for non power-of-two textures dimensions on devices such
        // as Nexus 7 with Tegra 3
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S,
                GL12.GL_CLAMP_TO_EDGE);
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T,
                GL12.GL_CLAMP_TO_EDGE);
    }

    public void bind() {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);
    }

    public void dispose() {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);
        int[] textureIds = {textureId};
        GL11.glDeleteTextures(textureId);
    }
}
