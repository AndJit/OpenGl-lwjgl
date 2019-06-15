package net.sssempai;


import net.sssempai.Vertices.VAO.InitialVerticesVAO;

import java.util.HashMap;

import static org.lwjgl.opengl.GL33.*;


public class VertexArrays {

    static HashMap<String, Integer> VAOs = new HashMap();


    private static void bind(int VAO){
        glBindVertexArray(VAO);
    }


    public static void addTriangle(String name, float[] vertex, boolean color, boolean texture, boolean light){
        int VAO = glGenVertexArrays();
        int VBO = glGenBuffers();
        bind(VAO);
        glBindBuffer(GL_ARRAY_BUFFER, VBO);
        glBufferData(GL_ARRAY_BUFFER, vertex, GL_STATIC_DRAW);
        int stride = 0;
        if (color) {
            stride = 6;
            if (texture){
                stride = 8;
                if (light) stride = 11;
            }
        }else if (texture && !color) {
            stride = 5;
            if (light) stride = 8;
        }else{
            stride = 3;
            if (light) stride = 6;
        }

        if (vertex != null) {
            glVertexAttribPointer(0, 3, GL_FLOAT, false, stride * Float.BYTES, 0);
            glEnableVertexAttribArray(0);
            if (color) {
                glVertexAttribPointer(1, 3, GL_FLOAT, false, stride * Float.BYTES, (stride - 3) * Float.BYTES);
                glEnableVertexAttribArray(1);
            }
            if (texture) {
                glVertexAttribPointer(2, 2, GL_FLOAT, false, stride * Float.BYTES, (stride - 2) * Float.BYTES);
                glEnableVertexAttribArray(2);
            }
            if (light){
                glVertexAttribPointer(3, 3, GL_FLOAT, false, stride * Float.BYTES, (stride - 3) * Float.BYTES);
                glEnableVertexAttribArray(3);
            }

            VAOs.put(name, VAO);
        }
        bind(0);
    }

    public static void addTriangleEBO(String name, float[] vertex, float[] inc, boolean color, boolean texture, boolean light){
        int VAO = glGenVertexArrays();
        int VBO = glGenBuffers();
        int EBO = glGenBuffers();
        bind(VAO);
        glBindBuffer(GL_ARRAY_BUFFER, VBO);
        glBufferData(GL_ARRAY_BUFFER, vertex, GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, EBO);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, inc, GL_STATIC_DRAW);
        int stride;
        if (color) {
            stride = 6;
            if (texture){
                stride = 8;
                if (light) stride = 11;
            }
        }else if (texture && !color) {
            stride = 5;
            if (light) stride = 8;
        }else{
            stride = 3;
            if (light) stride = 6;
        }
        if (vertex != null) {
            glVertexAttribPointer(0, 3, GL_FLOAT, false, stride * Float.BYTES, 0);
            glEnableVertexAttribArray(0);
            if (color) {
                glVertexAttribPointer(1, 3, GL_FLOAT, false, stride * Float.BYTES, (stride - 3) * Float.BYTES);
                glEnableVertexAttribArray(1);
            }
            if (texture) {
                glVertexAttribPointer(2, 2, GL_FLOAT, false, stride * Float.BYTES, (stride - 2) * Float.BYTES);
                glEnableVertexAttribArray(2);
            }
            if (light){
                glVertexAttribPointer(3, 3, GL_FLOAT, false, stride * Float.BYTES, (stride - 3) * Float.BYTES);
                glEnableVertexAttribArray(3);
            }

            VAOs.put(name, VAO);
        }
        bind(0);
    }

    public static void setDefaultCube(String name){
        InitialVerticesVAO.TexturesVerticesVAO texturesVerticesVAO = new InitialVerticesVAO.TexturesVerticesVAO(0.5f);
        VertexArrays.addTriangle(name, texturesVerticesVAO.getVertices(), false, true, false);
    }

    public static void createCustomCube(String name, float size, boolean color, boolean texture, boolean light) {
        float[] vertices = null;
        InitialVerticesVAO.NormalsVerticesVAO normalsVertices = new InitialVerticesVAO.NormalsVerticesVAO(size);
        InitialVerticesVAO.TexturesVerticesVAO texturesVertices = new InitialVerticesVAO.TexturesVerticesVAO(size);
        InitialVerticesVAO.TexturesNormalsVerticesVAO texturesNormalsVertices = new InitialVerticesVAO.TexturesNormalsVerticesVAO(size);
        if (vertices == null) {
            if (texture) {
                vertices = texturesVertices.getVertices();
                if (light) {
                    vertices = texturesNormalsVertices.getVertices();
                }
            }
        }
        if (vertices == null) {
            if (light) {
                vertices = normalsVertices.getVertices();
            }
        }
        VertexArrays.addTriangle(name, vertices, color, texture, light);
    }
    

    public static void bindVAO(String s){
        bind(VAOs.get(s));
    }

}
