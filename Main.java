package net.sssempai;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Main {

    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");
        init();
        loop();
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }


    public static void main(String[] args) {
        new Main().run();
    }


    private long window;


    private void loop() {

        GL.createCapabilities();
        glViewport(0, 0, 600, 600);
        glEnable(GL_TEXTURE_2D);

        float[] cords = {
                -0.5f, -0.5f, 0.0f, 0.0f, 1.0f, 0.0f,
                0.5f, -0.5f, 0.0f, 0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f
        };

        String name = "superTr";

        VertexArrays.addTriangle(name, cords, true, false);
        Shader.createShader("forpolyf.glsl", "forpoly.glsl", name);

        while (!glfwWindowShouldClose(window)) {
            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            Shader.useShader(name);
            VertexArrays.bindVAO(name);
            glDrawArrays(GL_TRIANGLES,0,3);
            glfwSwapBuffers(window);
        }

        glBindVertexArray(0);
    }


    private void init() {

        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        window = glfwCreateWindow(600, 600, "Hello World!", NULL, NULL);
        if (window == NULL) throw new RuntimeException("Failed to create the GLFW window");

        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true);
            else if (key == GLFW_KEY_R && action == GLFW_RELEASE)
                glClearColor(255.0f, 0.0f, 0.0f, 0.0f);
            else if (key == GLFW_KEY_B && action == GLFW_RELEASE)
                glClearColor(0, 0, 0, 0.0f);
            else if (key == GLFW_KEY_S && action == GLFW_RELEASE)
                glViewport(-100, 0, 800, 600);
            else if  (key == GLFW_KEY_W && action == GLFW_RELEASE)
                glViewport(0, 0, 600, 600);

        });


        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(window, pWidth, pHeight);

            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            glfwGetFramebufferSize(window, pWidth, pHeight);

            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        }

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);

        glfwShowWindow(window);
    }



}