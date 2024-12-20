import ru.vsu.math.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class Matrix3fTest {


    @Test
    public void multiplay() {
        float[] elements = {
                1, 0, 3,
                0, 1, 0,
                3, 0, 1
        };
        Matrix3f matrix = new Matrix3f(elements);
        Vector3f vector3f = new Vector3f(2, 2, 2);
        Vector3f res = matrix.multiply(vector3f);
        Vector3f expected = new Vector3f(8, 2, 8);

        Assertions.assertEquals(expected, res, "Матрица должна быть создана из переданного массива");

    }


    @Test
    public void multiplayMat() {
        float[] elements = {
                1, 0, 3,
                0, 1, 0,
                3, 0, 1
        };
        Matrix3f matrix = new Matrix3f(elements);

        float[] element = {
                2, 3, 0,
                2, 0, 3,
                2, 1, 0
        };
        Matrix3f matrixB = new Matrix3f(element);

        matrix.multiply(matrixB);
        float[] res = {
                8, 6, 0,
                2, 0, 3,
                8, 10, 0
        };

        Matrix3f expected = new Matrix3f(res);

        Assertions.assertEquals(expected, matrix, "Матрица должна быть создана из переданного массива");

    }

    // Тестирование конструктора с правильным размером массива
    @Test
    public void testConstructorWithValidArray() {
        float[] elements = {
                1, 2, 3,
                4, 5, 6,
                7, 8, 9
        };
        Matrix3f matrix = new Matrix3f(elements);
        float[][] expected = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Assertions.assertArrayEquals(expected, matrix.getElements(), "Матрица должна быть создана из переданного массива");
    }


    @Test
    public void testGetElem() {
        float[] elements = {
                1, 2, 3,
                4, 5, 6,
                7, 8, 9
        };
        Matrix3f matrix = new Matrix3f(elements);
        float num = 3;
        Assertions.assertEquals(num, matrix.getElement(0, 2), "Матрица должна быть создана из переданного массива");
    }

    // Тестирование конструктора с неверным размером массива
    @Test
    public void testConstructorWithInvalidArray() {
        float[] elements = {1, 2, 3, 4}; // Неверная длина массива
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Matrix3f(elements);
        }, "Конструктор должен выбрасывать исключение при неверном размере массива");
    }

    // Тестирование конструктора, создающего единичную матрицу
    @Test
    public void testIdentityConstructor() {
        Matrix3f identity = new Matrix3f(1);
        float[][] expected = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        Assertions.assertArrayEquals(expected, identity.getElements(), "Конструктор с параметром 1 должен создавать единичную матрицу");
    }

    // Тестирование конструктора по умолчанию (создает нулевую матрицу)
    @Test
    public void testDefaultConstructor() {
        Matrix3f zero = new Matrix3f();
        float[][] expected = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        Assertions.assertArrayEquals(expected, zero.getElements(), "Конструктор по умолчанию должен создавать нулевую матрицу");
    }

    // Тестирование сложения матриц
    @Test
    public void testAdd() {
        float[] elementsA = {
                1, 2, 3,
                4, 5, 6,
                7, 8, 9
        };
        float[] elementsB = {
                9, 8, 7,
                6, 5, 4,
                3, 2, 1
        };
        Matrix3f A = new Matrix3f(elementsA);
        Matrix3f B = new Matrix3f(elementsB);
        Matrix3f expected = new Matrix3f(new float[]{
                10, 10, 10,
                10, 10, 10,
                10, 10, 10
        });
        Matrix3f result = A.add(B);
        Assertions.assertEquals(expected, result, "Сложение матриц неверно");
    }

    // Тестирование метода вычитания матриц
    @Test
    public void testSubtract() {
        float[] elementsA = {
                9, 8, 7,
                6, 5, 4,
                3, 2, 1
        };
        float[] elementsB = {
                1, 2, 3,
                4, 5, 6,
                7, 8, 9
        };
        Matrix3f A = new Matrix3f(elementsA);
        Matrix3f B = new Matrix3f(elementsB);
        Matrix3f expected = new Matrix3f(new float[]{
                8, 6, 4,
                2, 0, -2,
                -4, -6, -8
        });
        Matrix3f result = A.sub(B);
        Assertions.assertEquals(expected, result, "Вычитание матриц неверно");
    }

    // Тестирование умножения матрицы на вектор
    @Test
    public void testMultiplyWithVector() {
        float[] elements = {
                1, 8, 3,
                4, 5, 6,
                7, 8, 9
        };
        Matrix3f matrix = new Matrix3f(elements);
        Vector3f vector = new Vector3f(1, 0, -1);
        Vector3f expected = new Vector3f(-2, -2, -2);
        Vector3f result = matrix.multiply(vector);
        Assertions.assertEquals(expected, result, "Умножение матрицы на вектор неверно");
    }

    // Тестирование умножения матрицы на вектор с инвалидным типом
    @Test
    public void testMultiplyWithInvalidVector() {
        Vector2f invalidVector = new Vector2f(1, 2);
        Matrix3f matrix = new Matrix3f(new float[]{
                1, 0, 0,
                0, 1, 0,
                0, 0, 1
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            matrix.multiply(invalidVector);
        }, "Умножение на неверный тип вектора должно выбрасывать исключение");
    }

    // Тестирование умножения двух матриц
    @Test
    public void testMultiplyWithMatrix() {
        float[] elementsA = {
                1, 2, 3,
                4, 5, 6,
                7, 8, 9
        };
        float[] elementsB = {
                9, 8, 7,
                6, 5, 4,
                3, 2, 1
        };
        Matrix3f A = new Matrix3f(elementsA);
        Matrix3f B = new Matrix3f(elementsB);
        Matrix3f expected = new Matrix3f(new float[]{
                30, 24, 18,
                84, 69, 54,
                138, 114, 90
        });
        A.multiply(B);
        Assertions.assertEquals(expected, A, "Умножение матриц неверно");
    }

    // Тестирование умножения матрицы на матрицу с инвалидным типом
    @Test
    public void testMultiplyWithInvalidMatrix() {
        Matrix4f invalidMatrix = new Matrix4f(new float[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16});
        Matrix3f matrix = new Matrix3f(new float[]{
                1, 0, 0,
                0, 1, 0,
                0, 0, 1
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            matrix.multiply(invalidMatrix);
        }, "Умножение на неверный тип матрицы должно выбрасывать исключение");
    }

    // Тестирование транспонирования матрицы
    @Test
    public void testTransposition() {
        float[] elements = {
                1, 2, 3,
                4, 5, 6,
                7, 8, 9
        };
        Matrix3f matrix = new Matrix3f(elements);
        System.out.println();
        matrix.transposition();
        Matrix3f expected = new Matrix3f(new float[]{
                1, 4, 7,
                2, 5, 8,
                3, 6, 9
        });

        Assertions.assertEquals(expected, matrix, "Транспонирование матрицы выполнено неверно");
    }

    // Тестирование метода toString
    @Test
    public void testToString() {
        float[] elements = {
                1.5f, 2.25f, 3.75f,
                4.0f, 5.5f, 6.125f,
                7.375f, 8.625f, 9.875f
        };
        Matrix3f matrix = new Matrix3f(elements);
        String expected = "1,50\t2,25\t3,75\t\n4,00\t5,50\t6,13\t\n7,38\t8,63\t9,88\t\n";
        Assertions.assertEquals(expected, matrix.toString(), "Метод toString возвращает неверное представление матрицы");
    }

    // Тестирование равенства и hashCode
    @Test
    public void testEqualsAndHashCode() {
        Matrix3f A = new Matrix3f(new float[]{
                1, 2, 3,
                4, 5, 6,
                7, 8, 9
        });
        Matrix3f B = new Matrix3f(new float[]{
                1, 2, 3,
                4, 5, 6,
                7, 8, 9
        });
        Matrix3f C = new Matrix3f(new float[]{
                9, 8, 7,
                6, 5, 4,
                3, 2, 1
        });

        // Проверка равенства
        Assertions.assertEquals(A, B, "Матрицы с одинаковыми элементами должны быть равны");
        Assertions.assertNotEquals(A, C, "Матрицы с разными элементами не должны быть равны");

        // Проверка hashCode
        Assertions.assertEquals(A.hashCode(), B.hashCode(), "Хэш-коды равных матриц должны совпадать");
        Assertions.assertNotEquals(A.hashCode(), C.hashCode(), "Хэш-коды разных матриц не должны совпадать");
    }

    // Тестирование операций с нулевой матрицей
    @Test
    public void testOperationsWithZeroMatrix() {
        Matrix3f zero = new Matrix3f(); // Нулевая матрица
        Matrix3f A = new Matrix3f(new float[]{
                1, 2, 3,
                4, 5, 6,
                7, 8, 9
        });

        // Сложение с нулевой матрицей
        Matrix3f addResult = A.add(zero);
        Assertions.assertEquals(A, addResult, "Сложение с нулевой матрицей должно возвращать исходную матрицу");

        // Вычитание нулевой матрицы
        Matrix3f subtractResult = A.sub(zero);
        Assertions.assertEquals(A, subtractResult, "Вычитание нулевой матрицы должно возвращать исходную матрицу");

        // Умножение на нулевую матрицу
        A.multiply(zero);
        Matrix3f expected = new Matrix3f(); // Нулевая матрица
        Assertions.assertEquals(expected, A, "Умножение на нулевую матрицу должно возвращать нулевую матрицу");
    }

    // Тестирование умножения матрицы на саму себя
    @Test
    public void testMultiplyWithItself() {
        Matrix3f A = new Matrix3f(new float[]{
                2, 0, 1,
                3, 0, 0,
                5, 1, 1
        });
        Matrix3f expected = new Matrix3f(new float[]{
                9, 1, 3,
                6, 0, 3,
                18, 1, 6
        });
        A.multiply(A);
        Assertions.assertEquals(expected, A, "Умножение матрицы на саму себя выполнено неверно");
    }

    // Тестирование транспонирования транспонированной матрицы (должна вернуть исходную матрицу)
    @Test
    public void testDoubleTransposition() {
        float[] elements = {
                1, 2, 3,
                4, 5, 6,
                7, 8, 9
        };
        Matrix3f matrix = new Matrix3f(elements);
        matrix.transposition();
        matrix.transposition();
        Matrix3f expected = new Matrix3f(elements);
        Assertions.assertEquals(expected, matrix, "Двойное транспонирование должно вернуть исходную матрицу");
    }

    // Тестирование умножения матрицы на единичную матрицу
    @Test
    public void testMultiplyWithIdentityMatrix() {
        Matrix3f identity = new Matrix3f(1);
        Matrix3f A = new Matrix3f(new float[]{
                2, 4, 6,
                1, 3, 5,
                0, 7, 8
        });
        Matrix3f B = new Matrix3f(new float[]{
                2, 4, 6,
                1, 3, 5,
                0, 7, 8
        });
        A.multiply(identity);
        Assertions.assertEquals(B, A, "Умножение матрицы на единичную матрицу должно возвращать исходную матрицу");
    }

    // Тестирование умножения единичной матрицы на матрицу
    @Test
    public void testIdentityMultiplyWithMatrix() {
        Matrix3f identity = new Matrix3f(1);
        Matrix3f A = new Matrix3f(new float[]{
                2, 4, 6,
                1, 3, 5,
                0, 7, 8
        });
        identity.multiply(A);
        Assertions.assertEquals(A, identity, "Умножение единичной матрицы на матрицу должно возвращать исходную матрицу");
    }


    @Test
    void determinant1() {
        Matrix3f zeroMatrix = new Matrix3f();
        float det = zeroMatrix.determinant();
        Assertions.assertEquals(0.0f, det, "Определитель нулевой матрицы должен быть равен 0");
    }

    @Test
    void determinant2() {
        float[] elements = {
                1, 2, 3,
                0, 4, 5,
                1, 0, 6
        };
        Matrix3f matrix = new Matrix3f(elements);
        float det = matrix.determinant();
        Assertions.assertEquals(22.0f, det, 1e-5, "Определитель матрицы должен быть равен 22");
    }

    @Test
    void determinant3() {
        float[] elements = {
                2, 4, 2,
                1, 2, 1,
                3, 6, 3
        };
        Matrix3f singularMatrix = new Matrix3f(elements);
        float det = singularMatrix.determinant();
        Assertions.assertEquals(0.0f, det, 1e-5, "Определитель вырожденной матрицы должен быть равен 0");
    }

    @Test
    void determinant4() {
        float[] elements = {
                3, 8, 1,
                4, 6, 5,
                7, 2, 9
        };
        Matrix3f matrix = new Matrix3f(elements);
        float det = matrix.determinant();
        Assertions.assertEquals(90.0f, det, 1e-5, "Определитель матрицы должен быть равен 90");
    }

    @Test
    void determinant5() {
        Matrix3f identity = new Matrix3f(1);
        float det = identity.determinant();
        Assertions.assertEquals(1.0f, det, "Определитель единичной матрицы должен быть равен 1");
    }

    // Тестирование обратной матрицы для единичной матрицы
    @Test
    public void testInverse1() {
        Matrix3f identity = new Matrix3f(1);
        Matrix3f inverse = identity.inverse();


        assertMatrixEquals(identity, inverse);
    }

    protected void assertMatrixEquals(Matrix3f actual, Matrix3f expected) {
        float delta = 1e-5f;
        float[][] actualElements = actual.getElements();
        float[][] expectedElements = expected.getElements();

        Assertions.assertEquals(expectedElements.length, actualElements.length, "Размеры матриц не совпадают");

        for (int i = 0; i < expectedElements[0].length; i++) {
            for (int j = 0; j < expectedElements.length; j++) {
                Assertions.assertEquals(expectedElements[i][j], actualElements[i][j], delta,
                        "Элемент [" + (i / 4) + "][" + (j % 4) + "] матрицы отличается. Ожидалось: "
                                + expectedElements[i][j] + ", Получено: " + actualElements[i][j]);
            }
        }
    }

    // Тестирование обратной матрицы
    @Test
    public void testInverse2() {
        float[] elements = {
                1, 2, 3,
                0, 1, 4,
                5, 6, 0
        };
        Matrix3f matrix = new Matrix3f(elements);

        float[] expectedInverseElements = {
                -24, 18, 5,
                20, -15, -4,
                -5, 4, 1
        };
        Matrix3f expectedInverse = new Matrix3f(expectedInverseElements);

        Matrix3f inverse = matrix.inverse();


        assertMatrixEquals(expectedInverse, inverse);
    }

    // Тестирование обратной матрицы для вырожденной матрицы
    @Test
    public void testInverse3() {
        float[] singularElements = {
                1, 2, 3,
                4, 5, 6,
                7, 8, 9
        };
        Matrix3f singularMatrix = new Matrix3f(singularElements);

        Assertions.assertThrows(ArithmeticException.class, () -> {
            singularMatrix.inverse();
        }, "Обратная матрица для вырожденной матрицы должна выбрасывать ArithmeticException");
    }

    // Тестирование, что произведение матрицы и её обратной дает единичную матрицу
    @Test
    public void testMultiply2() {
        float[] elements = {
                2, -1, 0,
                -1, 2, -1,
                0, -1, 2
        };
        Matrix3f matrix = new Matrix3f(elements);
        Matrix3f inverse = matrix.inverse();
        matrix.multiply(inverse);
        Matrix3f identity = new Matrix3f(1);

        // Проверяем, что произведение матрицы и её обратной дает единичную матрицу
        assertMatrixEquals(identity, matrix);
    }

    // Тестирование, что обратная матрица обратной матрицы равна исходной матрице
    @Test
    public void testInverse4() {
        // Задание элементов исходной матрицы 3x3
        float[] elements = {
                3, 0, 2,
                2, 0, -2,
                0, 1, 1
        };
        Matrix3f matrix = new Matrix3f(elements);
        Matrix3f inverse = matrix.inverse();
        Matrix3f inverseOfInverse = inverse.inverse();

        assertMatrixEquals(inverseOfInverse, matrix);
    }

}
