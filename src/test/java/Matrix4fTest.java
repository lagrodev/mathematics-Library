import ru.vsu.math.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Matrix4fTest {

    // Тестирование конструктора с правильным размером массива
    @Test
    public void testConstructorWithValidArray() {
        float[] elements = {
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16
        };
        Matrix4f matrix = new Matrix4f(elements);
        float[][] expected = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        Assertions.assertArrayEquals(expected, matrix.getElements(),
                "Матрица должна быть создана из переданного массива");
    }

    @Test
    public void testGetElem() {
        float[] elements = {
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16
        };
        Matrix4f matrix = new Matrix4f(elements);
        float num = 3;
        Assertions.assertEquals(num, matrix.getElement(0, 2), "Матрица должна быть создана из переданного массива");
    }

    // Тестирование конструктора с неверным размером массива
    @Test
    public void testConstructorWithInvalidArray() {
        float[] elements = {1, 2, 3, 4, 5};
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Matrix4f(elements);
        }, "Конструктор должен выбрасывать исключение при неверном размере массива");
    }

    // Тестирование конструктора, создающего единичную матрицу
    @Test
    public void testIdentityConstructor() {
        Matrix4f identity = new Matrix4f(1);
        float[][] expected = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        Assertions.assertArrayEquals(expected, identity.getElements(),
                "Конструктор с параметром 1 должен создавать единичную матрицу");
    }

    // Тестирование конструктора по умолчанию (создает нулевую матрицу)
    @Test
    public void testDefaultConstructor() {
        Matrix4f zero = new Matrix4f();
        float[][] expected = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        Assertions.assertArrayEquals(expected, zero.getElements(),
                "Конструктор по умолчанию должен создавать нулевую матрицу");
    }

    // Тестирование метода сложения матриц
    @Test
    public void testAdd() {
        float[] elementsA = {
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16
        };
        float[] elementsB = {
                16, 15, 14, 13,
                12, 11, 10, 9,
                8, 7, 6, 5,
                4, 3, 2, 1
        };
        Matrix4f A = new Matrix4f(elementsA);
        Matrix4f B = new Matrix4f(elementsB);
        Matrix4f expected = new Matrix4f(new float[]{
                17, 17, 17, 17,
                17, 17, 17, 17,
                17, 17, 17, 17,
                17, 17, 17, 17
        });
        Matrix4f result = A.add(B);
        Assertions.assertEquals(expected, result, "Сложение матриц неверно");
    }

    // Тестирование метода вычитания матриц
    @Test
    public void testSubtract() {
        float[] elementsA = {
                16, 15, 14, 13,
                12, 11, 10, 9,
                8, 7, 6, 5,
                4, 3, 2, 1
        };
        float[] elementsB = {
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16
        };
        Matrix4f A = new Matrix4f(elementsA);
        Matrix4f B = new Matrix4f(elementsB);
        Matrix4f expected = new Matrix4f(new float[]{
                15, 13, 11, 9,
                7, 5, 3, 1,
                -1, -3, -5, -7,
                -9, -11, -13, -15
        });
        Matrix4f result = A.sub(B);
        Assertions.assertEquals(expected, result, "Вычитание матриц неверно");
    }

    // Тестирование метода умножения матрицы на вектор
    @Test
    public void testMultiplyWithVector() {
        float[] elements = {
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16
        };
        Matrix4f matrix = new Matrix4f(elements);
        Vector4f vector = new Vector4f(1, 0, -1, 2);
        Vector4f expected = new Vector4f(
                1 * 1 + 2 * 0 + 3 * (-1) + 4 * 2,  // 1 + 0 -3 + 8 = 6
                5 * 1 + 6 * 0 + 7 * (-1) + 8 * 2,  // 5 + 0 -7 + 16 = 14
                9 * 1 + 10 * 0 + 11 * (-1) + 12 * 2, // 9 + 0 -11 +24 = 22
                13 * 1 + 14 * 0 + 15 * (-1) + 16 * 2 // 13 +0 -15 +32 = 30
        );
        Vector4f result = matrix.multiply(vector);
        Assertions.assertEquals(expected, result, "Умножение матрицы на вектор неверно");
    }

    // Тестирование умножения матрицы на вектор с инвалидным типом
    @Test
    public void testMultiplyWithInvalidVector() {
        Vector3f invalidVector = new Vector3f(1, 2, 3);
        Matrix4f matrix = new Matrix4f(new float[]{
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            matrix.multiply(invalidVector);
        }, "Умножение на неверный тип вектора должно выбрасывать исключение");
    }

    // Тестирование метода умножения матриц
    @Test
    public void testMultiplyWithMatrix() {
        float[] elementsA = {
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16
        };
        float[] elementsB = {
                16, 15, 14, 13,
                12, 11, 10, 9,
                8, 7, 6, 5,
                4, 3, 2, 1
        };
        Matrix4f A = new Matrix4f(elementsA);
        Matrix4f B = new Matrix4f(elementsB);
        Matrix4f expected = new Matrix4f(new float[]{
                (1 * 16 + 2 * 12 + 3 * 8 + 4 * 4),
                (1 * 15 + 2 * 11 + 3 * 7 + 4 * 3),
                (1 * 14 + 2 * 10 + 3 * 6 + 4 * 2),
                (1 * 13 + 2 * 9 + 3 * 5 + 4 * 1),

                (5 * 16 + 6 * 12 + 7 * 8 + 8 * 4),
                (5 * 15 + 6 * 11 + 7 * 7 + 8 * 3),
                (5 * 14 + 6 * 10 + 7 * 6 + 8 * 2),
                (5 * 13 + 6 * 9 + 7 * 5 + 8 * 1),

                (9 * 16 + 10 * 12 + 11 * 8 + 12 * 4),
                (9 * 15 + 10 * 11 + 11 * 7 + 12 * 3),
                (9 * 14 + 10 * 10 + 11 * 6 + 12 * 2),
                (9 * 13 + 10 * 9 + 11 * 5 + 12 * 1),

                (13 * 16 + 14 * 12 + 15 * 8 + 16 * 4),
                (13 * 15 + 14 * 11 + 15 * 7 + 16 * 3),
                (13 * 14 + 14 * 10 + 15 * 6 + 16 * 2),
                (13 * 13 + 14 * 9 + 15 * 5 + 16 * 1)
        });
        A.multiply(B);
        Assertions.assertEquals(expected, A, "Умножение матриц неверно");
    }

    // Тестирование умножения матрицы на матрицу с инвалидным типом
    @Test
    public void testMultiplyWithInvalidMatrix() {
        Matrix3f invalidMatrix = new Matrix3f(new float[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        Matrix4f matrix = new Matrix4f(new float[]{
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            matrix.multiply(invalidMatrix);
        }, "Умножение на неверный тип матрицы должно выбрасывать исключение");
    }

    // Тестирование метода транспонирования матрицы
    @Test
    public void testTransposition() {
        float[] elements = {
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16
        };
        Matrix4f matrix = new Matrix4f(elements);
        matrix.transposition();
        Matrix4f expected = new Matrix4f(new float[]{
                1, 5, 9, 13,
                2, 6, 10, 14,
                3, 7, 11, 15,
                4, 8, 12, 16
        });
        Assertions.assertEquals(expected, matrix, "Транспонирование матрицы выполнено неверно");
    }

    // Тестирование метода toString
    @Test
    public void testToString() {
        float[] elements = {
                1.5f, 2.25f, 3.75f, 4.0f,
                5.5f, 6.125f, 7.375f, 8.625f,
                9.875f, 10.5f, 11.25f, 12.75f,
                13.9f, 14.8f, 15.65f, 16.55f
        };
        Matrix4f matrix = new Matrix4f(elements);
        String expected = "1,50\t2,25\t3,75\t4,00\t\n" +
                "5,50\t6,13\t7,38\t8,63\t\n" +
                "9,88\t10,50\t11,25\t12,75\t\n" +
                "13,90\t14,80\t15,65\t16,55\t\n";
        Assertions.assertEquals(expected, matrix.toString(), "Метод toString возвращает неверное представление матрицы");
    }

    // Тестирование метода equals и hashCode
    @Test
    public void testEqualsAndHashCode1() {
        Matrix4f A = new Matrix4f(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16
        });
        Matrix4f B = new Matrix4f(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16
        });
        Matrix4f C = new Matrix4f(new float[]{
                16, 15, 14, 13,
                12, 11, 10, 9,
                8, 7, 6, 5,
                4, 3, 2, 1
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
        Matrix4f zero = new Matrix4f(); // Нулевая матрица
        Matrix4f A = new Matrix4f(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16
        });

        // Сложение с нулевой матрицей
        Matrix4f addResult = A.add(zero);
        Assertions.assertEquals(A, addResult, "Сложение с нулевой матрицей должно возвращать исходную матрицу");

        // Вычитание нулевой матрицы
        Matrix4f subtractResult = A.sub(zero);
        Assertions.assertEquals(A, subtractResult, "Вычитание нулевой матрицы должно возвращать исходную матрицу");

        // Умножение на нулевую матрицу
        A.multiply(zero);
        Matrix4f expected = new Matrix4f(); // Нулевая матрица
        Assertions.assertEquals(expected, A, "Умножение на нулевую матрицу должно возвращать нулевую матрицу");
    }

    // Тестирование умножения матрицы на саму себя
    @Test
    public void testMultiplyWithItself1() {
        Matrix4f A = new Matrix4f(new float[]{
                2, 0, 1, 3,
                4, 1, 0, 2,
                5, 2, 1, 0,
                3, 0, 2, 1
        });
        Matrix4f expected = new Matrix4f(new float[]{
                (2 * 2 + 0 * 4 + 1 * 5 + 3 * 3),
                (2 * 0 + 0 * 1 + 1 * 2 + 3 * 0),
                (2 * 1 + 0 * 0 + 1 * 1 + 3 * 2),
                (2 * 3 + 0 * 2 + 1 * 0 + 3 * 1),

                (4 * 2 + 1 * 4 + 0 * 5 + 2 * 3),
                (4 * 0 + 1 * 1 + 0 * 2 + 2 * 0),
                (4 * 1 + 1 * 0 + 0 * 1 + 2 * 2),
                (4 * 3 + 1 * 2 + 0 * 0 + 2 * 1),

                (5 * 2 + 2 * 4 + 1 * 5 + 0 * 3),
                (5 * 0 + 2 * 1 + 1 * 2 + 0 * 0),
                (5 * 1 + 2 * 0 + 1 * 1 + 0 * 2),
                (5 * 3 + 2 * 2 + 1 * 0 + 0 * 1),

                (3 * 2 + 0 * 4 + 2 * 5 + 1 * 3),
                (3 * 0 + 0 * 1 + 2 * 2 + 1 * 0),
                (3 * 1 + 0 * 0 + 2 * 1 + 1 * 2),
                (3 * 3 + 0 * 2 + 2 * 0 + 1 * 1)
        });
        A.multiply(A);
        Assertions.assertEquals(expected, A, "Умножение матрицы на саму себя выполнено неверно");
    }


    // Тестирование умножения матрицы на единичную матрицу
    @Test
    public void testMultiplyWithIdentityMatrix() {
        Matrix4f identity = new Matrix4f(1);
        Matrix4f A = new Matrix4f(new float[]{
                2, 4, 6, 8,
                1, 3, 5, 7,
                0, 2, 4, 6,
                3, 6, 9, 12
        });
        Matrix4f B = new Matrix4f(new float[]{
                2, 4, 6, 8,
                1, 3, 5, 7,
                0, 2, 4, 6,
                3, 6, 9, 12
        });
        A.multiply(identity);
        Assertions.assertEquals(A, B, "Умножение матрицы на единичную матрицу должно возвращать исходную матрицу");
    }

    // Тестирование умножения единичной матрицы на матрицу
    @Test
    public void testIdentityMultiplyWithMatrix() {
        Matrix4f identity = new Matrix4f(1);
        Matrix4f A = new Matrix4f(new float[]{
                2, 4, 6, 8,
                1, 3, 5, 7,
                0, 2, 4, 6,
                3, 6, 9, 12
        });
        Matrix4f B = new Matrix4f(new float[]{
                2, 4, 6, 8,
                1, 3, 5, 7,
                0, 2, 4, 6,
                3, 6, 9, 12
        });
        identity.multiply(A);
        Assertions.assertEquals(A, B, "Умножение единичной матрицы на матрицу должно возвращать исходную матрицу");
    }


    // Тестирование методов сложения и вычитания с нулевой матрицей
    @Test
    public void testAddSubtractWithZeroMatrix() {
        Matrix4f zero = new Matrix4f(); // Нулевая матрица
        Matrix4f A = new Matrix4f(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16
        });

        // Сложение с нулевой матрицей
        Matrix4f addResult = A.add(zero);
        Assertions.assertEquals(A, addResult, "Сложение с нулевой матрицей должно возвращать исходную матрицу");

        // Вычитание нулевой матрицы
        Matrix4f subtractResult = A.sub(zero);
        Assertions.assertEquals(A, subtractResult, "Вычитание нулевой матрицы должно возвращать исходную матрицу");
    }

    // Тестирование умножения матрицы на единичную матрицу с обеих сторон
    @Test
    public void testMultiplyWithIdentityMatrixBothSides() {
        Matrix4f identity = new Matrix4f(1);
        Matrix4f A = new Matrix4f(new float[]{
                2, 4, 6, 8,
                1, 3, 5, 7,
                0, 2, 4, 6,
                3, 6, 9, 12
        });
        Matrix4f B = new Matrix4f(new float[]{
                2, 4, 6, 8,
                1, 3, 5, 7,
                0, 2, 4, 6,
                3, 6, 9, 12
        });

        // Умножение A * Identity
        A.multiply(identity);
        Assertions.assertEquals(A, B, "Умножение матрицы на единичную матрицу должно возвращать исходную матрицу");

        // Умножение Identity * A
        identity.multiply(A);
        Assertions.assertEquals(A, B, "Умножение единичной матрицы на матрицу должно возвращать исходную матрицу");
    }

    // Тестирование операций с разными типами матриц
    @Test
    public void testAddWithInvalidMatrix() {
        Matrix3f invalidMatrix = new Matrix3f(new float[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        Matrix4f A = new Matrix4f(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            A.add(invalidMatrix);
        }, "Сложение с неверным типом матрицы должно выбрасывать исключение");
    }

    @Test
    public void testSubtractWithInvalidMatrix() {
        Matrix3f invalidMatrix = new Matrix3f(new float[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        Matrix4f A = new Matrix4f(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            A.sub(invalidMatrix);
        }, "Вычитание с неверным типом матрицы должно выбрасывать исключение");
    }

    // Тестирование умножения матрицы на нулевую матрицу
    @Test
    public void testMultiplyWithZeroMatrix() {
        Matrix4f zero = new Matrix4f(); // Нулевая матрица
        Matrix4f A = new Matrix4f(new float[]{
                2, 4, 6, 8,
                1, 3, 5, 7,
                0, 2, 4, 6,
                3, 6, 9, 12
        });
        A.multiply(zero);
        Matrix4f expected = new Matrix4f(); // Нулевая матрица
        Assertions.assertEquals(expected, A, "Умножение на нулевую матрицу должно возвращать нулевую матрицу");
    }

    // Тестирование транспонирования транспонированной матрицы (должна вернуть исходную матрицу)
    @Test
    public void testDoubleTransposition() {
        float[] elements = {
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16
        };
        Matrix4f matrix = new Matrix4f(elements);
        matrix.transposition();
        matrix.transposition();
        Matrix4f expected = new Matrix4f(elements);
        Assertions.assertEquals(expected, matrix, "Двойное транспонирование должно вернуть исходную матрицу");
    }


    // Тестирование методов сложения, вычитания и скалярного произведения с нулевой матрицей и нулевым вектором
    @Test
    public void testOperationsWithZeroMatrixAndZeroVector() {
        Matrix4f zeroMatrix = new Matrix4f(); // Нулевая матрица
        Matrix4f A = new Matrix4f(new float[]{
                2, 4, 6, 8,
                1, 3, 5, 7,
                0, 2, 4, 6,
                3, 6, 9, 12
        });

        // Сложение с нулевой матрицей
        Matrix4f addResult = A.add(zeroMatrix);
        Assertions.assertEquals(A, addResult, "Сложение с нулевой матрицей должно возвращать исходную матрицу");

        // Вычитание нулевой матрицы
        Matrix4f subtractResult = A.sub(zeroMatrix);
        Assertions.assertEquals(A, subtractResult, "Вычитание нулевой матрицы должно возвращать исходную матрицу");

        // Умножение на нулевую матрицу
        A.multiply(zeroMatrix);
        Matrix4f expected = new Matrix4f(); // Нулевая матрица
        Assertions.assertEquals(expected, A, "Умножение на нулевую матрицу должно возвращать нулевую матрицу");
    }

    // Тестирование метода toString с нулевой матрицей
    @Test
    public void testToStringWithZeroMatrix() {
        Matrix4f zeroMatrix = new Matrix4f(); // Нулевая матрица
        String expected = "0,00\t0,00\t0,00\t0,00\t\n" +
                "0,00\t0,00\t0,00\t0,00\t\n" +
                "0,00\t0,00\t0,00\t0,00\t\n" +
                "0,00\t0,00\t0,00\t0,00\t\n";
        Assertions.assertEquals(expected, zeroMatrix.toString(),
                "Метод toString для нулевой матрицы должен возвращать матрицу из нулей");
    }

    // Тестирование операций с разными типами векторов и матриц
    @Test
    public void testMultiplyWithDifferentVectorTypes() {
        Vector3f invalidVector = new Vector3f(1, 2, 3);
        Matrix4f matrix = new Matrix4f(new float[]{
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            matrix.multiply(invalidVector);
        }, "Умножение на неверный тип вектора должно выбрасывать исключение");
    }

    @Test
    public void testAddAndSubtractWithDifferentMatrixTypes() {
        Matrix3f invalidMatrix = new Matrix3f(new float[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        Matrix4f A = new Matrix4f(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16
        });

        // Добавление с неверным типом матрицы
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            A.add(invalidMatrix);
        }, "Сложение с неверным типом матрицы должно выбрасывать исключение");

        // Вычитание с неверным типом матрицы
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            A.sub(invalidMatrix);
        }, "Вычитание с неверным типом матрицы должно выбрасывать исключение");
    }

    // Тестирование скалярного произведения


    // Тестирование метода equals и hashCode для матриц с разными данными
    @Test
    public void testPartialEquals() {
        Matrix4f A = new Matrix4f(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16
        });
        Matrix4f B = new Matrix4f(new float[]{
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 17 // 1н измененный элемент
        });
        Assertions.assertNotEquals(A, B, "Матрицы с хотя бы одним отличающимся элементом не должны быть равны");
    }

    @Test
    void determinant1() {
        Matrix4f identity = new Matrix4f(1);
        float det = identity.determinant();
        Assertions.assertEquals(1.0f, det, "Определитель единичной матрицы должен быть равен 1");
    }

    @Test
    void determinant2() {
        Matrix4f zeroMatrix = new Matrix4f();
        float det = zeroMatrix.determinant();
        Assertions.assertEquals(0.0f, det, "Определитель нулевой матрицы должен быть равен 0");
    }

    @Test
    void determinant3() {
        float[] elements = {
                5, -2, 2, 7,
                1, 0, 0, 3,
                -3, 1, 5, 0,
                3, -1, -9, 4
        };
        Matrix4f matrix = new Matrix4f(elements);
        float det = matrix.determinant();
        Assertions.assertEquals(88.0f, det, 1e-5, "Определитель матрицы должен быть равен 88");
    }

    @Test
    void determinant4() {
        float[] elements = {
                1, 2, 3, 4,
                2, 4, 6, 8,
                3, 6, 9, 12,
                4, 8, 12, 16
        };
        Matrix4f singularMatrix = new Matrix4f(elements);
        float det = singularMatrix.determinant();
        Assertions.assertEquals(0.0f, det, 1e-5, "Определитель вырожденной матрицы должен быть равен 0");
    }

    @Test
    void determinant5() {
        float[] elements = {
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16
        };
        // матрица вырождена
        Matrix4f matrix = new Matrix4f(elements);
        float det = matrix.determinant();
        Assertions.assertEquals(0.0f, det, 1e-5, "Определитель матрицы должен быть равен 0");
    }

    // Тестирование обратной матрицы для единичной матрицы
    @Test
    public void testInverse1() {
        Matrix4f identity = new Matrix4f(1);
        Matrix4f inverse = identity.inverse();

        assertMatrixEquals(identity, inverse);
    }

    private void assertMatrixEquals(Matrix4f actual, Matrix4f expected) {
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
                1, 2, 3, 4,
                2, 3, 1, 2,
                1, 1, 1, -1,
                1, 0, -2, -6
        };
        Matrix4f matrix = new Matrix4f(elements);
        Matrix4f expectedInverse = new Matrix4f(new float[]{
                22, -6, -26, 17,
                -17, 5, 20, -13,
                -1, 0, 2, -1,
                4, -1, -5, 3
        });
        Matrix4f inverse = matrix.inverse();

        assertMatrixEquals(expectedInverse, inverse);
    }

    // Тестирование обратной матрицы для вырожденной матрицы
    @Test
    public void testInverse3() {
        float[] singularElements = {
                1, 2, 3, 4,
                2, 4, 6, 8,
                3, 6, 9, 12,
                4, 8, 12, 16
        };
        Matrix4f singularMatrix = new Matrix4f(singularElements);
        Assertions.assertThrows(ArithmeticException.class, () -> {
            singularMatrix.inverse();
        }, "Обратная матрица для вырожденной матрицы должна выбрасывать ArithmeticException");
    }

    // Тестирование, что произведение матрицы и её обратной дает единичную матрицу
    @Test
    public void testMultiply2() {
        float[] elements = {
                4, 7, 2, 3,
                0, 5, 0, 4,
                0, 0, 3, 0,
                0, 0, 0, 2
        };
        Matrix4f matrix = new Matrix4f(elements);
        Matrix4f inverse = matrix.inverse();
        matrix.multiply(inverse);
        Matrix4f identity = new Matrix4f(1);

        // Проверяем, что произведение матрицы и её обратной дает единичную матрицу
        Assertions.assertEquals(identity, matrix, "Произведение матрицы и её обратной должно быть единичной матрицей");
    }

    // Тестирование, что обратная матрица обратной матрицы равна исходной матрице
    @Test
    public void testInverse4() {
        float[] elements = {
                1, 2, 3, 4,
                2, 3, 1, 2,
                1, 1, 1, -1,
                1, 0, -2, -6
        };
        Matrix4f matrix = new Matrix4f(elements);
        Matrix4f inverse = matrix.inverse();
        Matrix4f inverseOfInverse = inverse.inverse();

        assertMatrixEquals(inverseOfInverse, matrix);
    }
}