package net.sssempai;


import glm_.mat4x4.Mat4;
import glm_.vec3.Vec3;

import java.util.ArrayList;

import static glm_.Java.glm;

public class Cube {

    static ArrayList<Cube> cubes = new ArrayList();

    Mat4 properties;
    Mat4 position;

    public Cube(Vec3 position){
        this.position = glm.translate(new Mat4(), position);
    }

    public static void add(Cube cube){
        cubes.add(cube);
    }

    public void setProperties(Mat4 propertie){
        properties = propertie;
    }

    public Mat4 getProperties(){
        return properties;
    }

    public Mat4 getPosition(){
        return position;
    }

}
