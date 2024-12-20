package ru.vsu.math;


/**
 * @author <a href="https://vk.com/v_zubkin">Мельник Василий</a>, ФКН 2 группа 2 курс<br>
 * <mark><b><i><u>"Каждый раз, когда вы пишите комментаний, поморщитесь и ощутите свою неудачу"</u></i></b> <br></mark>
 * <b><i><u>*никчемность</u></i></b> <br>
 */
// todo навести красоту, сейчас мне впадлу...
public abstract class AbstractVector {
    protected float[] components;
    protected float length;

    /**
     * Конструктор для создания вектора с заданными компонентами. <p>
     * <p>
     * т.е. можно создавать как AbstractVector(1,2,3,4 и т.п.), так и AbstractVector(int[]{1,2,3,4 и т.п.})
     * </p>
     */

    public AbstractVector(float... components) {
        if (components.length != getSize()) {
            throw new IndexOutOfBoundsException("Неверная длина");
        }
        this.components = components;
        calcLength();
    }

    /**
     * Конструктор для создания нулевого вектора.
     */
    public AbstractVector() {
        components = new float[getSize()];
        for (int i = 0; i < getSize(); i++) {
            this.components[i] = 0;
        }
        calcLength();
    }


    /**
     * Вычисляет длину вектора и сохраняет её.
     */
    protected void calcLength() {
        float res = 0;
        for (int i = 0; i < getSize(); i++) {
            res += (components[i] * components[i]);
        }
        length = (float) Math.sqrt(res);
    }

    protected abstract int getSize();

    protected abstract AbstractVector instantiateVector(float[] elements);

    // Вычисление длины вектора ... пол факту, тут надо поставить каклкДлины, на всякий, но опять же... мне впадлу
    public float length() {
        calcLength();
        return length;
    }


    /**
     * Складывает текущий вектор с другим вектором.
     *
     * @param other Другой вектор для сложения.
     * @return Новый вектор, являющийся суммой двух векторов.
     * @throws IllegalArgumentException если тип другого вектора не Vector2f.
     */
    public AbstractVector add(AbstractVector other) {
        return addVector(other);
    }
    /**
     * Общий метод складывания вектора с другим вектором.
     *
     * @param other Другой вектор для сложения.
     * @return Новый вектор, являющийся суммой двух векторов.
     * @throws IllegalArgumentException если тип другого вектора не Vector2f.
     */
    private AbstractVector addVector(AbstractVector other) {
        equalsLength(other);
        float[] res = new float[getSize()];
        for (int i = 0; i < getSize(); i++) {
            res[i] = this.components[i] + other.components[i];
        }
        return instantiateVector(res);
    }


    /**
     * Складывает текущий вектор с другим вектором.
     *
     * @param other Другой вектор для сложения.
     *              <p>
     *              Ничего не возвращает
     *              </p>
     * @throws IllegalArgumentException если тип другого вектора не Vector2f.
     */
    public void addV(AbstractVector other) {
        this.components = addVector(other).components;
    }

    /**
     * Общий метод вычисления разницы. Возвращает новый вектор
     *
     * @param other Другой вектор для вычитания.
     * @return Новый вектор, являющийся разностью двух векторов.
     * @throws IllegalArgumentException если тип другого вектора не Vector2f.
     */
    private AbstractVector subVector(AbstractVector other) {
        equalsLength(other);
        float[] res = new float[getSize()];
        for (int i = 0; i < getSize(); i++) {
            res[i] = this.components[i] - other.components[i];
        }
        return instantiateVector(res);
    }

    /**
     * Вычитает из текущего вектора другой вектор.
     *
     * @param other Другой вектор для вычитания.
     * @return Новый вектор, являющийся разностью двух векторов.
     * @throws IllegalArgumentException если тип другого вектора не Vector2f.
     */
    public AbstractVector sub(AbstractVector other) {
        return subVector(other);
    }

    /**
     * вычитание с изменением состояния текущего вектора
     *
     * @param other
     */
    public void subV(AbstractVector other) {
        this.components = subVector(other).components;
    }

    // я дол сих пор хз. что это...
    // todo понять, что это нахуй такое и надо ли оно :? ... Хз что это, но пусть будет.. мало ли
    public void sub(AbstractVector first, AbstractVector second) {
        equalsLength(first);
        equalsLength(second);
        for (int i = 0; i < getSize(); i++) {
            this.components[i] = first.components[i] - second.components[i];
        }
    }


    /**
     * Умножает текущий вектор на скаляр. Изменяет состояние текущего вектора
     *
     * @param scalar Скаляр для умножения.
     */
    public void multiply(float scalar) {
        for (int i = 0; i < components.length; i++) {
            this.components[i] *= scalar;
        }
        calcLength();
    }
    /**
     * Умножает текущий вектор на скаляр. Создает новый вектор
     * @return {@code res} - новый вектор
     * @param scalar Скаляр для умножения.
     */

    public AbstractVector multiplyV(float scalar) {
        AbstractVector res = instantiateVector(this.components);
        res.multiply(scalar);
        return res;
    }


    /**
     * Делит текущий вектор на скаляр.
     *
     * @param scalar Скаляр для деления.
     * @throws ArithmeticException если скаляр равен нулю.
     */
    public void divide(float scalar) {
        if (scalar == 0) {
            throw new ArithmeticException("Деление на ноль");
        }
        for (int i = 0; i < components.length; i++) {
            this.components[i] /= scalar;
        }
        calcLength();
    }

    /**
     * Делит текущий вектор на скаляр. Создает новый вектор. Как работает? Для {@code res} вызывают войдовский
     * метод divide(scalar) и его и возвращаем)
     * @return {@code res} - новый вектор
     * @param scalar Скаляр для умножения.
     */
    public AbstractVector divideV(float scalar) {
        AbstractVector res = instantiateVector(this.components);
        res.divide(scalar);
        return res;
    }


    /**
     * Вычисляет скалярное произведение текущего вектора с другим вектором.
     *
     * @param other Другой вектор для скалярного произведения.
     * @return Значение скалярного произведения.
     * @throws IllegalArgumentException если тип другого вектора не Vector2f.
     */
    public float dot(AbstractVector other) {
        equalsLength(other);
        float res = 0;
        for (int i = 0; i < getSize(); i++) {
            res += (this.components[i] * other.components[i]);
        }
        return res;
    }

    /**
     * <p>Нормализует вектор.</p> При этом изменяет состояние текущего вектора. из-за этого нельзя делать пару приколюх...
     * рекомендую использовать 2й метод (в целом, вам решать что делать)
     * <p>Если длина вектора равна нулю, метод ничего не делает.</p>
     */
    public void normalize() {
        calcNormalize();
    }
    /**
     * <p>Нормализует вектор.</p>
     * @return {@code res} - новый вектор. Благодаря этому можно вот так играться с векторами:
     * <p>vector = vector1.add(vector2).normalizeV() </p>
     * p.s. это не факт, я не тестил...
     * <p>Если длина вектора равна нулю, метод ничего не делает.</p>
     */
    public AbstractVector normalizeV() {
        AbstractVector res = instantiateVector(this.components);
        res.normalize();
        return res;
    }

    /**
     * Возвращает компонент вектора по индексу.
     *
     * @param a Индекс компонента (0 для x, 1 для y, 2 для z).
     * @return Значение компонента.
     * @throws IndexOutOfBoundsException если индекса не существует.
     */

    public float getNum(int a) {
        if (a < 0 || a >= components.length) {
            throw new IndexOutOfBoundsException("Invalid index: " + a);
        }
        return components[a];
    }

    /**
     * Заменяет значения компоненты вектора по индексу.
     *
     * @param a   Индекс компонента (0 для x, 1 для y, 2 для z и т.д).
     * @param num Новое число для переменной
     * @throws IndexOutOfBoundsException если индекса не существует.
     */

    public void setNum(int a, float num) {
        if (a < 0 || a >= components.length) {
            throw new IndexOutOfBoundsException("Invalid index: " + a);
        }
        components[a] = num;
        calcLength();
    }



    /**
     * Проверка, что нам скармливают одинаковые вектора... говорят, что можно уйти от этого к полиморфизму,
     * если знаешь как - напиши мне, ахах
     *
     * @param other  Вектор который нам дают схавать
     * @throws IndexOutOfBoundsException если разная длина.
     */

    private void equalsLength(AbstractVector other) {
        if (this.components.length != other.components.length) {
            throw new IndexOutOfBoundsException("Разная длина");
        }
    }

    /**
     * Логика нормализации)
     */
    private void calcNormalize() {
        calcLength();
        if (length == 0) {
            return;
        }
        divide(length);
    }

}