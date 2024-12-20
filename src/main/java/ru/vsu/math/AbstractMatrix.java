package ru.vsu.math;


/**
 * @author <a href="https://vk.com/v_zubkin">Мельник Василий</a>, ФКН 2 группа 2 курс<br>
 * <mark><b><i><u>"Каждый раз, когда вы пишите комментаний, поморщитесь и ощутите свою неудачу"</u></i></b> <br></mark>
 * <b><i><u>*никчемность</u></i></b> <br>
 */

public abstract class AbstractMatrix {
    protected float[][] elements;

    // Constructors
    public AbstractMatrix(float... array) {
        int size = this.getSize();
        if (array.length != size * size) {
            throw new IllegalArgumentException("Массив должен содержать ровно " + (size * size) + " элементов.");
        }
        this.elements = new float[size][size];
        int k = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++, k++) {
                this.elements[i][j] = array[k];
            }
        }
    }

    public AbstractMatrix(int one) {
        int size = this.getSize();
        this.elements = new float[size][size];
        for (int i = 0; i < size; i++) {
            this.elements[i][i] = 1;
        }
    }

    public AbstractMatrix() {
        int size = this.getSize();
        this.elements = new float[size][size];
    }

    public AbstractMatrix(float[][] array) {
        int size = this.getSize();
        if (array.length != size || array[0].length != size) {
            throw new IllegalArgumentException("Массив должен содержать ровно " + (size * size) + " элементов.");
        }
        this.elements = array;
    }


    public AbstractMatrix(AbstractMatrix elements) {
        float[][] el = elements.elements;
        if (el.length != getSize() && el[0].length != getSize()) {
            throw new IllegalArgumentException("Массив должен содержать ровно " + (getSize() * getSize()) + " элементов.");
        }
        this.elements = new float[getSize()][getSize()];
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                this.elements[i][j] = elements.getElement(i, j);
            }
        }
    }

    // всякое говно, которое, к сожалению, нужно :/
    protected abstract AbstractMatrix createInstance(float[] elements);

    protected abstract AbstractMatrix createInstance(float[][] elements);


    protected abstract AbstractMatrix createInstance();

    protected abstract AbstractVector instantiateVector();


    /**
     * какой длины матрица?
     * @return {@code int} - размер матрицы, скажем. 3 на 3, 4 на 4 и т.п.
     */
    protected abstract int getSize();

    /**
     * Метод для вычитания - возвращает новую матрицу
     *
     * @param other вектор с которым вычитания
     * @return {@code addMatrix(other)} новый вектор
     */
    public AbstractMatrix sub(AbstractMatrix other) {
        return subMatrix(other);
    }


    /**
     * Метод для вычитания обыгрывает всю логику)
     *
     * @param other вектор с которым вычитание делаем
     * @return {@code createInstance(a)} - создает новую матрицу
     */
    private AbstractMatrix subMatrix(AbstractMatrix other) {
        if (other == null || other.getSize() != this.getSize()) {
            throw new IllegalArgumentException("Матрицы должны иметь одинаковый размер.");
        }
        float[] a = new float[this.getSize() * this.getSize()];
        int k = 0;
        for (int i = 0; i < this.getSize(); i++) {
            for (int j = 0; j < this.getSize(); j++, k++) {
                a[k] = this.elements[i][j] - other.getElement(i, j);
            }
        }
        return createInstance(a);
    }
    /**
     * Метод для вычитания - меняет текущую матрицу
     *
     * @param other вектор с которым вычитания
     */
    public void subV(AbstractMatrix other) {
        AbstractMatrix abstractMatrix = subMatrix(other);
        this.elements = abstractMatrix.elements;
    }

    /**
     * Метод для транспонирования - изменяет текущую матрицу
     */
    public void transposition() {
        AbstractMatrix res = trans();
        this.elements = res.elements;
    }

    /**
     * Метод для транспонирования обыгрывает всю логику)
     *
     * @return {@code result} - создает новую матрицу
     */
    private AbstractMatrix trans() {
        AbstractMatrix result = createInstance(elements);
        for (int i = 0; i < getSize(); i++) {
            for (int j = i + 1; j < getSize(); j++) {
                float temp = elements[i][j];
                result.elements[i][j] = elements[j][i];
                result.elements[j][i] = temp;
            }
        }
        return result;
    }


    /**
     * Метод для транспонирования - возвращает новую матрицу
     *
     * @return {@code trans()} новая матрица
     */
    public AbstractMatrix transpositionNew() {
        return trans();
    }


    /**
     * Метод для сложения обыгрывает всю логику)
     *
     * @param other вектор с которым складываем
     * @return {@code createInstance(a)} - создает новую матрицу
     */
    private AbstractMatrix addMatrix(AbstractMatrix other) {
        if (other == null || other.getSize() != this.getSize()) {
            throw new IllegalArgumentException("Матрицы должны иметь одинаковый размер.");
        }
        float[] a = new float[this.getSize() * this.getSize()];
        int k = 0;
        for (int i = 0; i < this.getSize(); i++) {
            for (int j = 0; j < this.getSize(); j++, k++) {
                a[k] = this.elements[i][j] + other.getElement(i, j);
            }
        }
        return createInstance(a);
    }

    /**
     * Метод для сложения - возвращает новую матрицу
     *
     * @param other вектор с которым складываем
     * @return {@code addMatrix(other)} новый вектор
     */
    public AbstractMatrix add(AbstractMatrix other) {
        return addMatrix(other);
    }

    /**
     * Метод для сложения - меняет текущую матрицу
     *
     * @param other вектор с которым складываем
     */
    public void addV(AbstractMatrix other) {
        AbstractMatrix abstractMatrix = addMatrix(other);
        this.elements = abstractMatrix.elements;
    }

    /**
     * Я затрахался путаться, я умножаю матрицу на вектор, т.е. A*B = C
     * Где А - матрица, B- вектор {@code other} , С - вектор <p>
     * В теории, логику можно было бы кинуть в закрытый класс, хз, чисто рофла ради.. но мне так похуй уже
     *
     * @param other вектор
     * @return {@code result} новый вектор
     */
    public AbstractVector multiply(AbstractVector other) {
        if (other.components.length != this.elements[0].length) {
            throw new IllegalArgumentException("Кол-во строк должно быть равно кол-ву столбцов");
        }
        AbstractVector result = instantiateVector();
        for (int i = 0; i < getSize(); i++) {
            float res = 0;
            for (int j = 0; j < getSize(); j++) {
                res += (elements[i][j] * other.getNum(j));
            }
            result.setNum(i, res);
        }
        return result;
    }

    /**
     * Я затрахался путаться, я умножаю матрицу на вектор, т.е. A*B = C
     * Где А - матрица this, B - oter, С - вектор
     *
     * @param other матрица, умножаем справа
     */

    private AbstractMatrix mult(AbstractMatrix other) {
        if (other == null || other.getSize() != this.getSize()) {
            throw new IllegalArgumentException("Матрицы должны иметь одинаковый размер.");
        }
        AbstractMatrix result = createInstance();
        for (int i = 0; i < getSize(); i++) {
            for (int k = 0; k < getSize(); k++) {
                float res = 0;
                for (int j = 0; j < getSize(); j++) {
                    res += this.elements[i][j] * other.elements[j][k];
                }
                result.elements[i][k] = res;
            }
        }
        return result;
    }

    /**
     * Метод умножения матрицы на матриц, т.е. A*B = C
     * Где А - матрица this, B - oter, С - матрица
     *
     * @param other матрица, умножаем справа
     *              примечание - возвращаем ничего, т.е. меняем состояние текущей матрицы
     */
    public void multiply(AbstractMatrix other) {
        AbstractMatrix result = mult(other);
        this.elements = result.elements;
    }

    /**
     * Метод умножения матрицы на матриц, т.е. A*B = C
     * Где А - матрица this, B - oter, С - матрица
     *
     * @param other матрица, умножаем справа
     * @return {@code mult(other)} - новая матрица
     */
    public AbstractMatrix multiplyNew(AbstractMatrix other) {
        return mult(other);
    }

    /**
     * Считает детерминант - закрытый метод, т.к. я манал считать его с матрицами.... это надо так для подсчета обратной матрицы)
     *
     * @param elements элементы матрицы
     * @param size     длина матрицы (по факту, это можно не передавать... но кому не похуй)
     * @return {@code det} детерминант
     */
    private float determinant(float[][] elements, int size) {
        float det = 0;
        if (size == 1) {
            return elements[0][0];
        }
        if (size == 2) {
            return elements[0][0] * elements[1][1] - elements[0][1] * elements[1][0];
        }
        for (int col = 0; col < size; col++) {
            float sign = (col % 2 == 0) ? 1 : -1;
            float[][] minor = getMinorMatrix(elements, 0, col, size - 1);
            det += sign * elements[0][col] * determinant(minor, size - 1);
        }
        return det;
    }

    /**
     * Получение минорной матрицы, исключая указанную строку и столбец
     *
     * @param rowToExclude Строка для исключения
     * @param colToExclude Столбец для исключения
     * @return Минорная матрица
     */

    private float[][] getMinorMatrix(float[][] elements, int rowToExclude, int colToExclude, int newSize) {
        float[][] minorElements = new float[newSize][newSize];
        int n = elements.length;
        for (int i = 0; i < n; i++) {
            if (i == rowToExclude) continue;
            for (int j = 0; j < n; j++) {
                if (j == colToExclude) continue;
                int minorI = (i < rowToExclude) ? i : i - 1;
                int minorJ = (j < colToExclude) ? j : j - 1;
                minorElements[minorI][minorJ] = elements[i][j];
            }
        }
        return minorElements;
    }

    /**
     * возвращает детерминант
     *
     * @return {@code determinant} - детерминант нашей матрицы. считается в закрытом методе)
     */
    public float determinant() {
        return determinant(this.elements, getSize());
    }

    /**
     * Вычисление обратной матрицы - изменяет текущую матрицу, аккуратнее с этим)
     */
    public void inverseV() {
        AbstractMatrix a = inverse();
        this.elements = a.elements;
    }

    /**
     * Вычисление обратной матрицы
     *
     * @return Обратная матрица
     * @throws ArithmeticException если матрица вырождена и не имеет обратной
     */
    public AbstractMatrix inverse() {
        float det = determinant();
        if (det == 0) {
            throw new ArithmeticException("Матрица вырождена и не имеет обратной.");
        }
        float[][] tilda = computeTildaMatrix();
        float[] inverseElements = new float[getSize() * getSize()];
        int k = 0;
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++, k++) {
                inverseElements[k] = tilda[i][j] / det;
            }
        }
        return createInstance(inverseElements);
    }

    /**
     * Вычисление матрицы алгебраических дополнений для матрицы
     *
     * @return Матрица алгебраических дополнений
     */
    private float[][] computeDopMatrix() {
        int size = getSize();
        float[][] dopMatrix = new float[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                float sign = (i + j) % 2 == 0 ? 1 : -1;
                float minor = determinant(getMinorMatrix(elements, i, j, size - 1), size - 1);
                dopMatrix[i][j] = sign * minor;
            }
        }
        return dopMatrix;
    }

    /**
     * Вычисление матрицы тильда для матрицы
     *
     * @return Матрица присоединённых
     */

    private float[][] computeTildaMatrix() {
        float[][] cofactors = computeDopMatrix();
        float[][] tilda = new float[getSize()][getSize()];
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                tilda[j][i] = cofactors[i][j];
            }
        }
        return tilda;
    }


    public abstract float getElement(int row, int col);

    public abstract void setElement(int row, int col, float value);

    /**
     * да
     * s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1] поэтому мы умножаем на 31
     * <p>
     * Нахуй это надо?
     * предположим, у нас есть два массива:
     * <p>
     * Массив A: [1, 2, 3]
     * <p>
     * Массив B: [3, 2, 1]
     * </p>
     * Если мы просто суммируем хэш-коды элементов, то хэш-коды A и B будут одинаковыми, хотя массивы различаются порядком элементов.
     * <p>
     * Используя умножение на 31 (или другое число), мы вводим весовой коэффициент, который учитывает позицию элемента в структуре данных.
     * <p>
     * Таким образом, элементы на разных позициях вносят разный вклад в общий хэш-код, что помогает различать структуры с одинаковыми элементами, но в разных порядках.
     * <a href="https://stackoverflow.com/questions/299304/why-does-javas-hashcode-in-string-use-31-as-a-multiplier">что за 31</a>
     */

    @Override
    public int hashCode() {
        int result = 1;
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                result = 31 * result + Float.hashCode(elements[i][j]);
            }
        }
        return result;
    }


    /**
     * Хрень, чтобы матрицу правильно представлять
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getSize(); i++) { // Итерация по строкам
            for (int j = 0; j < getSize(); j++) { // Итерация по столбцам
                sb.append(String.format("%.2f\t", elements[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}