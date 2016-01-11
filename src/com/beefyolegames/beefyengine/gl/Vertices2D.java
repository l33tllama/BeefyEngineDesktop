package com.beefyolegames.beefyengine.gl;

import org.lwjgl.opengl.GL11;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by Leo Febey on 3/01/2016.
 */
public class Vertices2D {
    final boolean hasColour;
    final boolean hasTexCoords;
    final int vertexSize;
    final FloatBuffer vertices;
    private final ShortBuffer indices;

    public Vertices2D(int maxVertices, int maxIndicies, boolean hasColour, boolean hasTexCoords) {
        this.hasColour = hasColour;
        this.hasTexCoords = hasTexCoords;
        this.vertexSize = (2 + (hasColour ? 4 : 0) + (hasTexCoords ? 2 : 0)) * 4;

        ByteBuffer buffer = ByteBuffer.allocateDirect(maxVertices * vertexSize);
        buffer.order(ByteOrder.nativeOrder());
        vertices = buffer.asFloatBuffer();

        if (maxIndicies > 0) {
            buffer = ByteBuffer.allocateDirect(maxIndicies * Short.SIZE / 8);
            buffer.order(ByteOrder.nativeOrder());
            indices = buffer.asShortBuffer();
        } else {
            indices = null;
        }
    }

    public void setVertices(float[] vertices, int offset, int length) {
        this.vertices.clear();
        this.vertices.put(vertices, offset, length);
        this.vertices.flip();
    }

    public void setIndices(short[] indices, int offset, int length) {
        this.indices.clear();
        this.indices.put(indices, offset, length);
        this.indices.flip();
    }

    public void bind() {
        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        vertices.position(0);
        // TODO: test glVertexPointer stride value - size (eg 2 for 2D) * size of float?
        // either 2 * vertexSize or 2 * 4...
        // see http://wiki.lwjgl.org/wiki/Using_Vertex_Buffer_Objects_(VBO)
        GL11.glVertexPointer(2, vertexSize, 2 * vertexSize, vertices);

        if (hasColour) {
            GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
            vertices.position(0);
            GL11.glColorPointer(4, vertexSize, 2 * vertexSize, vertices);
        }
        if (hasTexCoords) {
            GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
            vertices.position(hasColour ? 6 : 2);
            GL11.glTexCoordPointer(2, vertexSize, 2 * vertexSize,vertices);
        }
    }

    public void draw(int primitiveType, int offset, int numVertices) {
        if (indices != null) {
            indices.position(offset);
            GL11.glDrawElements(primitiveType, indices);
            //GL11.glDrawElements();
        } else {
            GL11.glDrawArrays(primitiveType, offset, numVertices);
        }
    }

    public void unbind() {
        if (hasTexCoords) {
            GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
        }

        if (hasColour) {
            GL11.glDisableClientState(GL11.GL_COLOR_ARRAY);
        }
    }
}
