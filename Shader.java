package net.sssempai;

import org.lwjgl.system.MemoryStack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.IntBuffer;
import java.util.HashMap;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.system.MemoryStack.stackPush;

public class Shader {

    static HashMap<String, Integer> shaders = new HashMap();
    public static int shader;

    public static void createShader(String vert, String frag, String name){
        int vertexShader = createNewShader(vert, GL_VERTEX_SHADER);
        int fragShader = createNewShader(frag, GL_FRAGMENT_SHADER);
        int shaderProgram = initShaderProgram(vertexShader, fragShader);
        shaders.put(name, shaderProgram);
    }


    private static String readShaderFile(String s){
        File file = new File(s);
        StringBuilder shaderSource = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                shaderSource.append(line).append("\n");
            }
            reader.close();
        } catch (Exception e) {
            System.err.println("Could not read file.");
            e.printStackTrace();
            System.exit(-1);
        }
        return shaderSource.toString();
    }

    private static int createNewShader(String filename, int version){
        int shader = glCreateShader(version);
        glShaderSource(shader, readShaderFile(filename));
        glCompileShader(shader);

        int success;
        try (MemoryStack stack = stackPush()){
            IntBuffer pname = stack.mallocInt(1);
            glGetShaderiv(shader, GL_COMPILE_STATUS, pname);
            success = pname.get(0);
        }
        if(success == 0) {
            System.out.println(glGetShaderInfoLog(shader, 512));
            System.out.println(filename);
            System.out.println("Error shader");
        }else {
            return shader;
        }
        return 0;
    }

    private static int initShaderProgram(int vert, int frag){
        int shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram, vert);
        glAttachShader(shaderProgram, frag);
        glLinkProgram(shaderProgram);

        int successShaderProgamm;
        try (MemoryStack stack = stackPush()){
            IntBuffer pname = stack.mallocInt(1);
            glGetProgramiv(shaderProgram, GL_LINK_STATUS, pname);
            successShaderProgamm = pname.get(0);
        }
        if (successShaderProgamm == 0){
            System.out.println(glGetProgramInfoLog(shaderProgram, 512));
            System.out.println("Error");
        }
        glDeleteShader(vert);
        glDeleteShader(frag);
        return shaderProgram;
    }

    public static void useShader(String s){
        shader = shaders.get(s);
        glUseProgram(shader);
    }
}
