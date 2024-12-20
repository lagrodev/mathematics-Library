package ru.vsu.math;

import java.util.Objects;

public class Vector4f extends AbstractVector {
    private static final int SIZE = 4;

    // Конструкторы + getters and setters
    public Vector4f(float... components) {
        super(components);
    }

    public Vector4f() {
        super();
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

    public float getW() {
        return components[3];
    }

    public void setW(float w) {
        components[3] = w;
        calcLength();
    }


    @Override
    public void subV(AbstractVector other) {
        super.subV((Vector4f) other);
    }

    @Override
    protected int getSize() {
        return SIZE;
    }

    @Override
    protected Vector4f instantiateVector(float[] elements) {
        return new Vector4f(elements);
    }


    @Override
    public void addV(AbstractVector other) {
        super.addV((Vector4f) other);
    }


    @Override
    public Vector4f add(AbstractVector other) {
        return (Vector4f) super.add(other);
    }

    @Override
    public Vector4f sub(AbstractVector other) {
        return (Vector4f) super.sub(other);
    }

    @Override
    public void sub(AbstractVector first, AbstractVector second) {
        super.sub(first, second);
    }

    @Override
    public float dot(AbstractVector other) {
        return super.dot(other);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vector4f)) return false;
        Vector4f other = (Vector4f) obj;
        return Float.compare(components[0], other.components[0]) == 0 &&
                Float.compare(components[1], other.components[1]) == 0 &&
                Float.compare(components[2], other.components[2]) == 0 &&
                Float.compare(components[3], other.components[3]) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(components[0], components[1], components[2], components[3]);
    }

    @Override
    public String toString() {
        return "Vector4f{" +
                "x=" + components[0] +
                ", y=" + components[1] +
                ", z=" + components[2] +
                ", w=" + components[3] +
                '}';
    }
}