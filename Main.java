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
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glBindTexture;
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

    Camera camera;

    public static void main(String[] args) {
        new Main().run();
    }

    Mat4 project = glm.perspective(70f, 4.0f / 3.0f, 0.1f, 100f); //Наша матрица перспективы

    float deltaTime = 0.0f;
    float lastFrame = 0.0f;


    private void za_loop() {
        GL.createCapabilities();
        glViewport(0, 0, 1280, 960);
        glEnable(GL_DEPTH_TEST); // Это обязательно для проверки глубины!!!
        glEnable(GL_TEXTURE_2D);

        String name = "CubeV1";
        String lamps = "lamps";
        String diffuse = "dCube";
//        String diffuseTexrure = "dCubeTexture";

        VertexArrays.createCustomCube(lamps, 0.5f, false, false, true);
        VertexArrays.createCustomCube(diffuse, 0.5f, false, false, true);
//        VertexArrays.createCustomCube(name, 0.5f, false, true, false);
//        VertexArrays.createCustomCube(diffuseTexrure, 0.5f, false, false, true);

        Shader.createShader("cubeTestV.glsl","cubeTestF.glsl", name);
        Shader.createShader("constantLightV.glsl", "constantLightF.glsl", lamps);
        Shader.createShader("DiffuseV.glsl", "DiffuseF.glsl", diffuse);
//        Shader.createShader("DiffiseTextureV.glsl", "DiffiseTextureF.glsl", diffuseTexrure);

//        int texture = Texture.createTexture("madoka.jpg", GL_REPEAT, GL_REPEAT, GL_LINEAR, GL_LINEAR);

        Vec3 lightPos = new Vec3(1.2f, 1f, 2f);

        Cube lamp = new Cube(lightPos, 0);
        lamp.setProperties(glm.scale(lamp.getProperties(), new Vec3(0.2f)));
        Cube.add(lamp);



        for (int i = 0; i < 10000; i++) {
            Cube cube = new Cube(new Vec3(i,0,0), 0);
            Cube.add(cube);
        }
        for (int i = 0; i < 10000; i++) {
            Cube cube = new Cube(new Vec3(0,0, i), 0);
            Cube.add(cube);
        }
        for (int i = 0; i < 10000; i++) {
            Cube cube = new Cube(new Vec3(0,i,0), 0);
            Cube.add(cube);
        }

        while (!glfwWindowShouldClose(window)) {
            float currentFrame = (float) glfwGetTime();
            deltaTime = currentFrame - lastFrame;
            lastFrame = currentFrame; // FPS тип

            Mat4 view = camera.getCamera(); //Наша камера или матрица вида

            glfwPollEvents();

            keycall();

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            Shader.useShader(lamps);
            int modd = glGetUniformLocation(Shader.shader, "model");
            int projj = glGetUniformLocation(Shader.shader, "projection");
            int vii = glGetUniformLocation(Shader.shader, "view");

            glUniformMatrix4fv(projj, false, project.toFloatArray());
            glUniformMatrix4fv(vii, false, view.toFloatArray());
            VertexArrays.bindVAO(lamps);
            glUniformMatrix4fv(modd, false, lamp.properties.toFloatArray());
            glDrawArrays(GL_TRIANGLES, 0, 36);


            Shader.useShader(diffuse);
            int proj = glGetUniformLocation(Shader.shader, "projection");
            int vi = glGetUniformLocation(Shader.shader, "view");
            int mod = glGetUniformLocation(Shader.shader, "model");

            int lightPosLoc = glGetUniformLocation(Shader.shader, "lightPos");
            int objectColor = glGetUniformLocation(Shader.shader, "objectColor");
            int cameraPos = glGetUniformLocation(Shader.shader, "cameraPos");

            glUniform3f(cameraPos, camera.cameraPos.getX(), camera.cameraPos.getY(), camera.cameraPos.getZ());
            glUniform3f(lightPosLoc, lightPos.getX(), lightPos.getY(), lightPos.getZ());
            glUniform3f(objectColor, 1.0f, 0.5f, 0.31f);

            glUniformMatrix4fv(proj, false, project.toFloatArray());
            glUniformMatrix4fv(vi, false, view.toFloatArray());

            VertexArrays.bindVAO(diffuse);
            drawCubes(mod);
            glfwSwapBuffers(window);

        }

        glBindVertexArray(0);
    }

    public void drawCubes(int mod){
        Cube.cubes.forEach(cube -> {
            glBindTexture(GL_TEXTURE_2D, cube.getTexture());
            Mat4 properties = cube.getProperties();
            glUniformMatrix4fv(mod, false, properties.toFloatArray());
            glDrawArrays(GL_TRIANGLES, 0, 36);
        });
    }

    private boolean[] keys = new boolean[1024];
    public void keycall(){
        float speed = 10.0f * deltaTime;
        if (keys[GLFW_KEY_W]) camera.up(speed);
        if (keys[GLFW_KEY_S]) camera.down(speed);
        if (keys[GLFW_KEY_A]) camera.left(speed);
        if (keys[GLFW_KEY_D]) camera.right(speed);
        if (keys[GLFW_KEY_F11]);
    }

    private long window;

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

        glfwSetMouseButtonCallback(window, (window1, button, action, mods) -> {
            int cubeTexture = Texture.createTexture("madoka.jpg", GL_REPEAT, GL_REPEAT, GL_LINEAR, GL_LINEAR);
            int cubeTexture1 = Texture.createTexture("texture_1.png", GL_REPEAT, GL_REPEAT, GL_LINEAR, GL_LINEAR);
            if (button == GLFW_MOUSE_BUTTON_1 && action == GLFW_RELEASE){
                Cube cube = new Cube(new Vec3( camera.cameraPos),cubeTexture);
                cube.setProperties(glm.rotate(cube.getPosition(), (float)Math.toRadians(-55f), new Vec3(1f, 0.3f, 0.5f)));
                Cube.add(cube);
            }else if (button == GLFW_MOUSE_BUTTON_2 && action == GLFW_RELEASE){
                Cube cube = new Cube(new Vec3( camera.cameraPos),cubeTexture1);
                cube.setProperties(glm.rotate(cube.getPosition(), (float)Math.toRadians(glfwGetTime() * -55f), new Vec3(1f, 0.3f, 0.5f)));
                Cube.add(cube);
            }else if (button == GLFW_MOUSE_BUTTON_5 && action == GLFW_RELEASE){
                glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
            }else if (button == GLFW_MOUSE_BUTTON_4 && action == GLFW_RELEASE){
                glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
            }
        });

        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE ) {
                glfwSetWindowShouldClose(window, true);
            }
            else if (key == GLFW_KEY_R && action == GLFW_RELEASE) {
                glClearColor(255.0f, 0.0f, 0.0f, 0.0f);
            }
            else if (key == GLFW_KEY_B && action == GLFW_RELEASE) {
                glClearColor(0, 0, 0, 0.0f);
            }
            if(action == GLFW_PRESS) keys[key] = true;
            else if(action == GLFW_RELEASE) keys[key] = false;

        });


        camera = new Camera(window); // Инициализирую тут, потому что в конструкторе обработчик. Особой роли эта инициализация не играет
        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);

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