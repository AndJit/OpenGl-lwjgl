package net.sssempai;



import glm_.mat4x4.*;
import glm_.vec3.Vec3;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static glm_.Java.glm;
import static java.lang.Math.sin;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Main {



    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");
        init();
        za_loop();
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private long window;

    public static void main(String[] args) {
        new Main().run();
    }

    private void za_loop() {



        GL.createCapabilities();
        glViewport(0, 0, 1280, 960);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_2D);

        String name = "CubeV1";

        Vec3[] cubePositions = new Vec3[]{
            new Vec3( 0.0f,  0.0f,  0.0f),
            new Vec3( 2.0f,  5.0f, -15.0f),
            new Vec3(-1.5f, -2.2f, -2.5f),
            new Vec3(-3.8f, -2.0f, -12.3f),
            new Vec3( 2.4f, -0.4f, -3.5f),
            new Vec3(-1.7f,  3.0f, -7.5f),
            new Vec3( 1.3f, -2.0f, -2.5f),
            new Vec3( 1.5f,  2.0f, -2.5f),
            new Vec3( 1.5f,  0.2f, -1.5f),
            new Vec3(-1.3f,  1.0f, -1.5f)
        };


        VertexArrays.addCube(name);

        Shader.createShader("cubeTestV.glsl","cubeTestF.glsl", name);
        int cubeTexture = Texture.createTexture("madoka.jpg", GL_REPEAT, GL_REPEAT, GL_LINEAR, GL_LINEAR);

        Mat4 project = glm.perspective(45f, 4.0f / 3.0f, 0.1f, 100f);

        Cube cube1 = new Cube(new Vec3( 0.0f,  0.0f,  0.0f));
        cube1.setProperties(glm.rotate(cube1.getPosition(), (float)Math.toRadians(-55f), new Vec3(1f, 0.3f, 0.5f)));

        Cube.add(cube1);

        while (!glfwWindowShouldClose(window)) {
            Mat4 view = glm.translate( new Mat4(), new Vec3(x, y, z));
            glfwPollEvents();
            keycall(clamp, keys);
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glActiveTexture(GL_TEXTURE0);
            glBindTexture(GL_TEXTURE_2D, cubeTexture);

            Shader.useShader(name);

            int mod = glGetUniformLocation(Shader.shader, "model");
            int proj = glGetUniformLocation(Shader.shader, "projection");
            int vi = glGetUniformLocation(Shader.shader, "view");

            glUniformMatrix4fv(proj, false, project.toFloatArray());
            glUniformMatrix4fv(vi, false, view.toFloatArray());
            VertexArrays.bindVAO(name);
            cube1.setProperties(glm.rotate(cube1.getPosition(), (float) ( Math.toRadians(glfwGetTime() * 25.0f)), new Vec3(1f, 0.3f, 0.5f)));
            for (Cube cube : Cube.cubes){
                Mat4 properties = cube.getProperties();
                glUniformMatrix4fv(mod, false, properties.toFloatArray());
                glDrawArrays(GL_TRIANGLES, 0, 36);
            }
//            glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);
            glfwSwapBuffers(window);

        }/**/
        glBindVertexArray(0);


    }

    static float z = -3.0f;
    static float x = 0.0f;
    static float y = 0.0f;
    private boolean clamp = false;
    private int keys;

    private void init() {

        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(1280, 960, "Куб есть - можно поесть!", NULL, NULL);
        if (window == NULL) throw new RuntimeException("Failed to create the GLFW window");

        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true);
            else if (key == GLFW_KEY_R && action == GLFW_RELEASE)
                glClearColor(255.0f, 0.0f, 0.0f, 0.0f);
            else if (key == GLFW_KEY_B && action == GLFW_RELEASE)
                glClearColor(0, 0, 0, 0.0f);

            else if (key == GLFW_KEY_W && action == GLFW_PRESS) {clamp = true; keys = GLFW_KEY_W;  }
            else if (key == GLFW_KEY_S && action == GLFW_PRESS){clamp = true;  keys = GLFW_KEY_S;  }
            else if (key == GLFW_KEY_A && action == GLFW_PRESS){clamp = true;  keys = GLFW_KEY_A;  }
            else if (key == GLFW_KEY_D && action == GLFW_PRESS){clamp = true;  keys = GLFW_KEY_D;  }
            else if (key == GLFW_KEY_SPACE && action == GLFW_PRESS){clamp = true;  keys = GLFW_KEY_SPACE;  }
            else if (key == GLFW_KEY_LEFT_SHIFT && action == GLFW_PRESS){clamp = true;  keys = GLFW_KEY_LEFT_SHIFT;  }

            else if  (key == GLFW_KEY_W && action == GLFW_RELEASE){clamp = false; }
            else if  (key == GLFW_KEY_S && action == GLFW_RELEASE){clamp = false; }
            else if  (key == GLFW_KEY_D && action == GLFW_RELEASE){clamp = false; }
            else if  (key == GLFW_KEY_A && action == GLFW_RELEASE){clamp = false; }
            else if  (key == GLFW_KEY_LEFT_SHIFT && action == GLFW_RELEASE){clamp = false; }
            else if  (key == GLFW_KEY_SPACE && action == GLFW_RELEASE){clamp = false; }

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

    public void keycall(boolean clamp, int keys){
        if (clamp){
            switch (keys) {
                case GLFW_KEY_W: z += 0.1f; break;
                case GLFW_KEY_S: z -= 0.1f; break;
                case GLFW_KEY_A: x += 0.1f; break;
                case GLFW_KEY_D: x -= 0.1f; break;
                case GLFW_KEY_LEFT_SHIFT: y += 0.1f; break;
                case GLFW_KEY_SPACE: y -= 0.1f; break;
            }
        }
    }




}