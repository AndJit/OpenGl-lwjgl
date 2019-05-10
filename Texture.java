package net.sssempai;

import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.stb.STBImage.*;

public class Texture{


    public static int createTexture(String filepath, int p1,  int p2,  int p3,  int p4) {


        int width, height;
        stbi_set_flip_vertically_on_load(true);
        ByteBuffer buffer;
        try (MemoryStack memoryStack = MemoryStack.stackPush()){
            IntBuffer pWidth = memoryStack.mallocInt(1);
            IntBuffer pHeight = memoryStack.mallocInt(1);
            IntBuffer pChannels = memoryStack.mallocInt(1);
            buffer = stbi_load(filepath, pWidth, pHeight, pChannels, STBI_rgb);
            width = pWidth.get(0);
            height = pHeight.get(0);
        }


        int texture = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texture);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S,  p1);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T,  p2);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, p3);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, p4);


        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width, height, 0, GL_RGB, GL_UNSIGNED_BYTE, buffer);
        glGenerateMipmap(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, 0);



        return texture;
    }




}
