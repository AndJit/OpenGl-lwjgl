package net.sssempai;

import glm_.mat4x4.Mat4;
import glm_.vec3.Vec3;

import static glm_.Java.glm;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static org.lwjgl.glfw.GLFW.*;

public class Camera {

    private float lastX = 400; // Координаты мыши
    private float lastY = 300;// Координаты мыши
    private float pitch = 0.0f; //Угол
    private float yaw   = -90.0f; //Угол
    boolean firstMouse = true;

    Vec3 cameraFront = new Vec3(0f,0f,-1f);
    Vec3 up = new Vec3(0f,1f,0f);
    Vec3 cameraPos = new Vec3(0f,0f,3f);

    public Camera(long window){
        glfwSetCursorPosCallback(window, (window1, xpos, ypos) -> {
            if(firstMouse) { // Это чтобы курсор не скакал при первом движении
                lastX = (float) xpos;
                lastY = (float) ypos;
                firstMouse = false;
            }
            float xoffset = (float) (xpos - lastX);
            float yoffset = (float) (lastY - ypos);

            lastX = (float) xpos;
            lastY = (float) ypos;

            float sensitivity = 0.05f;
            xoffset *= sensitivity;
            yoffset *= sensitivity;
            pitch += yoffset;
            yaw   += xoffset;
            if(pitch > 89.0f) pitch =  89.0f; // Чтобы мы не могли разворачиваться на тучу градусов
            if(pitch < -89.0f) pitch = -89.0f;
            Vec3 front = new Vec3();
            front.setX((float) ( cos(Math.toRadians(yaw)) * cos(Math.toRadians(pitch)) ));
            front.setY((float) sin(Math.toRadians(pitch)));
            front.setZ((float) ( sin(Math.toRadians(yaw)) * cos(Math.toRadians(pitch)) ));
            cameraFront = front.normalize();
        });
    }

    public Mat4 getCamera(){
        Vec3 up = new Vec3(0f,1f,0f);
        return glm.lookAt(cameraPos, cameraPos.plus(cameraFront), up);
    }

    public void up(float speed){
        cameraPos = cameraPos.plus(cameraFront.times(speed));
    }

    public void down(float speed){
        cameraPos = cameraPos.minus(cameraFront.times(speed));
    }

    public void left(float speed){
        cameraPos = cameraPos.minus(cameraFront.cross(up).normalize().times(speed));
    }

    public void right(float speed){
        cameraPos = cameraPos.plus(cameraFront.cross(up).normalize().times(speed));
    }


}
