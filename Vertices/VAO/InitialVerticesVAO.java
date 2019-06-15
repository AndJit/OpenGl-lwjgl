package net.sssempai.Vertices.VAO;

public class InitialVerticesVAO implements VerticesVAO {


    public InitialVerticesVAO(float size){
        this.size = size;
        setVertices();
    }

    private float size;

    private float[] vertices = null;

    @Override
    public float getSize() {
        return size;
    }

    @Override
    public float[] getVertices() {
        return vertices;
    }

    @Override
    public void setVertices() {
        vertices = new float[]{
                -1f * size, -1f * size, -1f * size,// Верхний правый угол
                1f * size, -1f * size, -1f * size,// Нижний правый угол
                1f * size, 1f * size, -1f * size,// Верхний левый угол
                // Второй треугольник
                1f * size, 1f * size, -1f * size,  // Нижний правый угол
                -1f * size, 1f * size, -1f * size,// Нижний левый угол
                -1f * size, -1f * size, -1f * size,// Верхний левый угол
                //Третий треугольник
                -1f * size, -1f * size, 1f * size,
                1f * size, -1f * size, 1f * size,
                1f * size, 1f * size, 1f * size,
                //Четвертый треугольник
                1f * size, 1f * size, 1f * size,
                -1f * size, 1f * size, 1f * size,
                -1f * size, -1f * size, 1f * size,
                //Пятый треугольник
                -1f * size, 1f * size, 1f * size,
                -1f * size, 1f * size, -1f * size,
                -1f * size, -1f * size, -1f * size,
                // Шестой треугольник
                -1f * size, -1f * size, -1f * size,
                -1f * size, -1f * size, 1f * size,
                -1f * size, 1f * size, 1f * size,
                // Седьмой треугольник
                1f * size, 1f * size, 1f * size,
                1f * size, 1f * size, -1f * size,
                1f * size, -1f * size, -1f * size,
                // Восьмой треугольник
                1f * size, -1f * size, -1f * size,
                1f * size, -1f * size, 1f * size,
                1f * size, 1f * size, 1f * size,
                // Девятый треугольник
                -1f * size, -1f * size, -1f * size,
                1f * size, -1f * size, -1f * size,
                1f * size, -1f * size, 1f * size,
                // Десятый треугольник
                1f * size, -1f * size, 1f * size,
                -1f * size, -1f * size, 1f * size,
                -1f * size, -1f * size, -1f * size,
                // Одинадцатый треугольник
                -1f * size, 1f * size, -1f * size,
                1f * size, 1f * size, -1f * size,
                1f * size, 1f * size, 1f * size,
                // Двенадцатый треугольник
                1f * size, 1f * size, 1f * size,
                -1f * size, 1f * size, 1f * size,
                -1f * size, 1f * size, -1f * size,
        };
    }

    public static class NormalsVerticesVAO implements VerticesVAO{


        public NormalsVerticesVAO(float size){
            this.size = size;
            setVertices();
        }

        private float size;

        private float[] vertices = null;


        @Override
        public float getSize() {
            return size;
        }

        @Override
        public float[] getVertices() {
            return vertices;
        }

        @Override
        public void setVertices(){
            vertices = new float[]{
                    -1f * size, -1f * size, -1f * size,  0.0f,  0.0f, -1.0f,
                    1f * size, -1f * size, -1f * size,   0.0f,  0.0f, -1.0f,
                    1f * size, 1f * size, -1f * size,    0.0f,  0.0f, -1.0f,
                    1f * size, 1f * size, -1f * size,    0.0f,  0.0f, -1.0f,
                    -1f * size, 1f * size, -1f * size,   0.0f,  0.0f, -1.0f,
                    -1f * size, -1f * size, -1f * size,  0.0f,  0.0f, -1.0f,

                    -1f * size, -1f * size, 1f * size,   0.0f,  0.0f, 1.0f,
                    1f * size, -1f * size, 1f * size,    0.0f,  0.0f, 1.0f,
                    1f * size, 1f * size, 1f * size,     0.0f,  0.0f, 1.0f,
                    1f * size, 1f * size, 1f * size,     0.0f,  0.0f, 1.0f,
                    -1f * size, 1f * size, 1f * size,    0.0f,  0.0f, 1.0f,
                    -1f * size, -1f * size, 1f * size,   0.0f,  0.0f, 1.0f,

                    -1f * size, 1f * size, 1f * size,   -1.0f,  0.0f,  0.0f,
                    -1f * size, 1f * size, -1f * size,  -1.0f,  0.0f,  0.0f,
                    -1f * size, -1f * size, -1f * size, -1.0f,  0.0f,  0.0f,
                    -1f * size, -1f * size, -1f * size, -1.0f,  0.0f,  0.0f,
                    -1f * size, -1f * size, 1f * size,  -1.0f,  0.0f,  0.0f,
                    -1f * size, 1f * size, 1f * size,   -1.0f,  0.0f,  0.0f,

                    1f * size, 1f * size, 1f * size,    1.0f,  0.0f,  0.0f,
                    1f * size, 1f * size, -1f * size,   1.0f,  0.0f,  0.0f,
                    1f * size, -1f * size, -1f * size,  1.0f,  0.0f,  0.0f,
                    1f * size, -1f * size, -1f * size,  1.0f,  0.0f,  0.0f,
                    1f * size, -1f * size, 1f * size,   1.0f,  0.0f,  0.0f,
                    1f * size, 1f * size, 1f * size,    1.0f,  0.0f,  0.0f,

                    -1f * size, -1f * size, -1f * size, 0.0f, -1.0f,  0.0f,
                    1f * size, -1f * size, -1f * size,  0.0f, -1.0f,  0.0f,
                    1f * size, -1f * size, 1f * size,   0.0f, -1.0f,  0.0f,
                    1f * size, -1f * size, 1f * size,   0.0f, -1.0f,  0.0f,
                    -1f * size, -1f * size, 1f * size,  0.0f, -1.0f,  0.0f,
                    -1f * size, -1f * size, -1f * size, 0.0f, -1.0f,  0.0f,

                    -1f * size, 1f * size, -1f * size,  0.0f,  1.0f,  0.0f,
                    1f * size, 1f * size, -1f * size,   0.0f,  1.0f,  0.0f,
                    1f * size, 1f * size, 1f * size,    0.0f,  1.0f,  0.0f,
                    1f * size, 1f * size, 1f * size,    0.0f,  1.0f,  0.0f,
                    -1f * size, 1f * size, 1f * size,   0.0f,  1.0f,  0.0f,
                    -1f * size, 1f * size, -1f * size,  0.0f,  1.0f,  0.0f,
            };
        }

    }
    public static class TexturesNormalsVerticesVAO implements VerticesVAO{


        public TexturesNormalsVerticesVAO(float size){
            this.size = size;
            setVertices();
        }

        private float size;

        @Override
        public float getSize() {
            return size;
        }

        @Override
        public float[] getVertices() {
            return vertices;
        }

        @Override
        public void setVertices() {
            vertices = new float[]{
                    -1f * size, -1f * size, -1f * size,  0.0f,  0.0f, -1.0f, 0.0f, 0.0f,
                    1f * size, -1f * size, -1f * size,   0.0f,  0.0f, -1.0f, 1.0f, 0.0f,
                    1f * size, 1f * size, -1f * size,    0.0f,  0.0f, -1.0f, 1.0f, 1.0f,
                    1f * size, 1f * size, -1f * size,    0.0f,  0.0f, -1.0f, 1.0f, 1.0f,
                    -1f * size, 1f * size, -1f * size,   0.0f,  0.0f, -1.0f, 0.0f, 1.0f,
                    -1f * size, -1f * size, -1f * size,  0.0f,  0.0f, -1.0f, 0.0f, 0.0f,

                    -1f * size, -1f * size, 1f * size,   0.0f,  0.0f, 1.0f, 0.0f, 0.0f,
                    1f * size, -1f * size, 1f * size,    0.0f,  0.0f, 1.0f, 1.0f, 0.0f,
                    1f * size, 1f * size, 1f * size,     0.0f,  0.0f, 1.0f, 1.0f, 1.0f,
                    1f * size, 1f * size, 1f * size,     0.0f,  0.0f, 1.0f, 1.0f, 1.0f,
                    -1f * size, 1f * size, 1f * size,    0.0f,  0.0f, 1.0f, 0.0f, 1.0f,
                    -1f * size, -1f * size, 1f * size,   0.0f,  0.0f, 1.0f, 0.0f, 0.0f,

                    -1f * size, 1f * size, 1f * size,   -1.0f,  0.0f,  0.0f, 1.0f, 0.0f,
                    -1f * size, 1f * size, -1f * size,  -1.0f,  0.0f,  0.0f, 1.0f, 1.0f,
                    -1f * size, -1f * size, -1f * size, -1.0f,  0.0f,  0.0f, 0.0f, 1.0f,
                    -1f * size, -1f * size, -1f * size, -1.0f,  0.0f,  0.0f, 0.0f, 1.0f,
                    -1f * size, -1f * size, 1f * size,  -1.0f,  0.0f,  0.0f, 0.0f, 0.0f,
                    -1f * size, 1f * size, 1f * size,   -1.0f,  0.0f,  0.0f, 1.0f, 0.0f,

                    1f * size, 1f * size, 1f * size,    1.0f,  0.0f,  0.0f, 1.0f, 0.0f,
                    1f * size, 1f * size, -1f * size,   1.0f,  0.0f,  0.0f, 1.0f, 1.0f,
                    1f * size, -1f * size, -1f * size,  1.0f,  0.0f,  0.0f, 0.0f, 1.0f,
                    1f * size, -1f * size, -1f * size,  1.0f,  0.0f,  0.0f, 0.0f, 1.0f,
                    1f * size, -1f * size, 1f * size,   1.0f,  0.0f,  0.0f, 0.0f, 0.0f,
                    1f * size, 1f * size, 1f * size,    1.0f,  0.0f,  0.0f, 1.0f, 0.0f,

                    -1f * size, -1f * size, -1f * size, 0.0f, -1.0f,  0.0f, 0.0f, 1.0f,
                    1f * size, -1f * size, -1f * size,  0.0f, -1.0f,  0.0f, 1.0f, 1.0f,
                    1f * size, -1f * size, 1f * size,   0.0f, -1.0f,  0.0f, 1.0f, 0.0f,
                    1f * size, -1f * size, 1f * size,   0.0f, -1.0f,  0.0f, 1.0f, 0.0f,
                    -1f * size, -1f * size, 1f * size,  0.0f, -1.0f,  0.0f, 0.0f, 0.0f,
                    -1f * size, -1f * size, -1f * size, 0.0f, -1.0f,  0.0f, 0.0f, 1.0f,

                    -1f * size, 1f * size, -1f * size,  0.0f,  1.0f,  0.0f, 0.0f, 1.0f,
                    1f * size, 1f * size, -1f * size,   0.0f,  1.0f,  0.0f, 1.0f, 1.0f,
                    1f * size, 1f * size, 1f * size,    0.0f,  1.0f,  0.0f, 1.0f, 0.0f,
                    1f * size, 1f * size, 1f * size,    0.0f,  1.0f,  0.0f, 1.0f, 0.0f,
                    -1f * size, 1f * size, 1f * size,   0.0f,  1.0f,  0.0f, 0.0f, 0.0f,
                    -1f * size, 1f * size, -1f * size,  0.0f,  1.0f,  0.0f, 0.0f, 1.0f
            };
        }

        private float[] vertices = null;

    }
    public static class TexturesVerticesVAO implements VerticesVAO{


        public TexturesVerticesVAO(float size){
            this.size = size;
            setVertices();
        }

        private float size;

        @Override
        public float getSize() {
            return size;
        }

        private float[] vertices = null;
        @Override
        public float[] getVertices() {
            return vertices;
        }
        @Override
        public void setVertices() {
            vertices = new float[]{
                    -1f * size, -1f * size, -1f * size, 0.0f, 0.0f,
                    1f * size, -1f * size, -1f * size, 1.0f, 0.0f,
                    1f * size, 1f * size, -1f * size, 1.0f, 1.0f,
                    1f * size, 1f * size, -1f * size, 1.0f, 1.0f,
                    -1f * size, 1f * size, -1f * size, 0.0f, 1.0f,
                    -1f * size, -1f * size, -1f * size, 0.0f, 0.0f,

                    -1f * size, -1f * size, 1f * size, 0.0f, 0.0f,
                    1f * size, -1f * size, 1f * size, 1.0f, 0.0f,
                    1f * size, 1f * size, 1f * size, 1.0f, 1.0f,
                    1f * size, 1f * size, 1f * size, 1.0f, 1.0f,
                    -1f * size, 1f * size, 1f * size, 0.0f, 1.0f,
                    -1f * size, -1f * size, 1f * size, 0.0f, 0.0f,

                    -1f * size, 1f * size, 1f * size, 1.0f, 0.0f,
                    -1f * size, 1f * size, -1f * size, 1.0f, 1.0f,
                    -1f * size, -1f * size, -1f * size, 0.0f, 1.0f,
                    -1f * size, -1f * size, -1f * size, 0.0f, 1.0f,
                    -1f * size, -1f * size, 1f * size, 0.0f, 0.0f,
                    -1f * size, 1f * size, 1f * size, 1.0f, 0.0f,

                    1f * size, 1f * size, 1f * size, 1.0f, 0.0f,
                    1f * size, 1f * size, -1f * size, 1.0f, 1.0f,
                    1f * size, -1f * size, -1f * size, 0.0f, 1.0f,
                    1f * size, -1f * size, -1f * size, 0.0f, 1.0f,
                    1f * size, -1f * size, 1f * size, 0.0f, 0.0f,
                    1f * size, 1f * size, 1f * size, 1.0f, 0.0f,

                    -1f * size, -1f * size, -1f * size, 0.0f, 1.0f,
                    1f * size, -1f * size, -1f * size, 1.0f, 1.0f,
                    1f * size, -1f * size, 1f * size, 1.0f, 0.0f,
                    1f * size, -1f * size, 1f * size, 1.0f, 0.0f,
                    -1f * size, -1f * size, 1f * size, 0.0f, 0.0f,
                    -1f * size, -1f * size, -1f * size, 0.0f, 1.0f,

                    -1f * size, 1f * size, -1f * size, 0.0f, 1.0f,
                    1f * size, 1f * size, -1f * size, 1.0f, 1.0f,
                    1f * size, 1f * size, 1f * size, 1.0f, 0.0f,
                    1f * size, 1f * size, 1f * size, 1.0f, 0.0f,
                    -1f * size, 1f * size, 1f * size, 0.0f, 0.0f,
                    -1f * size, 1f * size, -1f * size, 0.0f, 1.0f
            };
        }

    }

}
