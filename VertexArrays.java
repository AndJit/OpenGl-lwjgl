package net.sssempai;


import java.util.HashMap;

import static org.lwjgl.opengl.GL33.*;


public class VertexArrays {

    static HashMap<String, Integer> VAOs = new HashMap();


    private static void bind(int VAO){
        glBindVertexArray(VAO);
    }


    public static void addTriangle(String name, float[] vertex, boolean color, boolean texture){
        int VAO = glGenVertexArrays();
        int VBO = glGenBuffers();
        bind(VAO);
        glBindBuffer(GL_ARRAY_BUFFER, VBO);
        glBufferData(GL_ARRAY_BUFFER, vertex, GL_STATIC_DRAW);
        int stride;
        if (color && texture) stride = 8; else if (color && !texture) stride = 6; else stride = 3;
        if (vertex != null) {
            glVertexAttribPointer(0, 3, GL_FLOAT, false, stride * Float.BYTES, 0);
            glEnableVertexAttribArray(0);
            if (color) {
                glVertexAttribPointer(1, 3, GL_FLOAT, false, stride * Float.BYTES, 3 * Float.BYTES);
                glEnableVertexAttribArray(1);
            }
            if (texture) {
                glVertexAttribPointer(2, 2, GL_FLOAT, false, stride * Float.BYTES, 6 * Float.BYTES);
                glEnableVertexAttribArray(2);
            }
            VAOs.put(name, VAO);
        }
        bind(0);
    }

    public static void bindVAO(String s){
        bind(VAOs.get(s));
    }

}
