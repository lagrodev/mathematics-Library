package ru.vsu.math;

import java.util.Objects;

// Пока так.

public class Vector3f extends AbstractVector {
    private static final int SIZE = 3;

    // Конструкторы + getters and setters
    public Vector3f(float... components) {
        super(components);
    }

    public Vector3f() {
        super();
    }

    public float[] getElements() {
        return components;
    }

    public void setElements(float[] components) {
        this.components = components;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getX() {
        return components[0];
    }

    public void setX(float x) {
        components[0] = x;
        calcLength();
    }

    public float getY() {
        return components[1];
    }

    public void setY(float y) {
        components[1] = y;
        calcLength();
    }

    public float getZ() {
        return components[2];
    }

    public void setZ(float z) {
        components[2] = z;
        calcLength();
    }


    @Override
    protected int getSize() {
        return SIZE;
    }

    @Override
    protected Vector3f instantiateVector(float[] elements) {
        return new Vector3f(elements);
    }

    @Override
    public void addV(AbstractVector other) {
        super.addV(other);
    }


    // Метод сложения векторов
    @Override
    public Vector3f add(AbstractVector other) {
        return (Vector3f) super.add(other);
    }

    // Метод вычитания векторов
    @Override
    public Vector3f sub(AbstractVector other) {
        return (Vector3f) super.sub(other);
    }

    @Override
    public void sub(AbstractVector first, AbstractVector second) {
        super.sub(first, second);
    }

    @Override
    public void subV(AbstractVector other) {
        super.subV((Vector3f) other);
    }


    @Override
    public float dot(AbstractVector other) {
        return super.dot(other);
    }

    // Метод векторного произведения
    public Vector3f cross(Vector3f other) {
        float x = this.components[1] * other.components[2] - this.components[2] * other.components[1];
        float y = this.components[2] * other.components[0] - this.components[0] * other.components[2];
        float z = this.components[0] * other.components[1] - this.components[1] * other.components[0];
        return new Vector3f(x, y, z);
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vector3f)) return false;
        Vector3f other = (Vector3f) obj;
        return Float.compare(components[0], other.components[0]) == 0 &&
                Float.compare(components[1], other.components[1]) == 0 &&
                Float.compare(components[2], other.components[2]) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(components[0], components[1], components[2]);
    }

    @Override
    public String toString() {
        return "Vector3f{" +
                "x=" + components[0] +
                ", y=" + components[1] +
                ", z=" + components[2] +
                '}';
    }
}