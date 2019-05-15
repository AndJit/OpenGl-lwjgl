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

    Mat4 project = glm.perspective(45f, 4.0f / 3.0f, 0.1f, 100f); //Наша матрица перспективы

    float deltaTime = 0.0f;
    float lastFrame = 0.0f;

    private void za_loop() {
        GL.createCapabilities();
        glViewport(0, 0, 1280, 960);
        glEnable(GL_DEPTH_TEST); // Это обязательно для проверки глубины!!!
        glEnable(GL_TEXTURE_2D);

        String name = "CubeV1";

        VertexArrays.setDefaultCube(name); //Создаем VAO для кубика
        Shader.createShader("cubeTestV.glsl","cubeTestF.glsl", name);
        int cubeTexture = Texture.createTexture("madoka.jpg", GL_REPEAT, GL_REPEAT, GL_LINEAR, GL_LINEAR);

        Cube cube1 = new Cube(new Vec3( 0.0f,  0.0f,  0.0f));
        Cube cube2 = new Cube(new Vec3( 2.0f,  0.0f, -5.0f));
        cube1.setProperties(glm.rotate(cube1.getPosition(), (float)Math.toRadians(-55f), new Vec3(1f, 0.3f, 0.5f)));

        Cube.add(cube1); // Добавляем в "глобальный" массив кубы для обязательной отрисовки
        Cube.add(cube2);

        while (!glfwWindowShouldClose(window)) {
            float currentFrame = (float) glfwGetTime();
            deltaTime = currentFrame - lastFrame;
            lastFrame = currentFrame; // FPS тип

            Mat4 view = camera.getCamera(); //Наша камера или матрица вида

            glfwPollEvents();
            keycall(); //Обработка кликов

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glActiveTexture(GL_TEXTURE0);
            glBindTexture(GL_TEXTURE_2D, cubeTexture);
            Shader.useShader(name);

            int mod = glGetUniformLocation(Shader.shader, "model");
            int proj = glGetUniformLocation(Shader.shader, "projection");
            int vi = glGetUniformLocation(Shader.shader, "view");
            glUniformMatrix4fv(proj, false, project.toFloatArray());
            glUniformMatrix4fv(vi, false, view.toFloatArray());
            /*
            * Класс Cube лишь добавляет свойства(матрицы поворота, позиция, scale) для куба, размеры уже зависят от VAO
            * */
            VertexArrays.bindVAO(name);
            cube2.setProperties(glm.rotate(cube2.getPosition(), (float) ( Math.toRadians(glfwGetTime() * -25.0f)), new Vec3(1f, 0.3f, 0.5f)));
            for (Cube cube : Cube.cubes){//Собственно отрисовуем
                Mat4 properties = cube.getProperties(); // Получаем свойства каждого кубика и...
                glUniformMatrix4fv(mod, false, properties.toFloatArray()); // ... перадем их в шейдер
                glDrawArrays(GL_TRIANGLES, 0, 36); // Отрисовуем куб (12 треугольников)
            }
            glfwSwapBuffers(window);
        }
        glBindVertexArray(0);
    }

    private boolean[] keys = new boolean[1024];
    public void keycall(){
        float speed = 10.0f * deltaTime;;
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