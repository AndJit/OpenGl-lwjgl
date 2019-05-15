#version 330 core

in vec2 cords;
uniform sampler2D ourTexture;
out vec4 finalOutColor;

void main() {
    finalOutColor = texture(ourTexture, cords);
}
