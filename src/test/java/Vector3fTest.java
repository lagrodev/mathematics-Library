import ru.vsu.math.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Vector3fTest {
    @Test
    public void testConstructorAndGetters() {
        Vector3f vector = new Vector3f(3, 4, 7);
        Assertions.assertEquals(3, vector.getX(), "Значение x должно быть 3.0");
        Assertions.assertEquals(4, vector.getY(), "Значение y должно быть 4.0");
        Assertions.assertEquals(7, vector.getZ(), "Значение y должно быть 7.0");
        Assertions.assertEquals(7, vector.getNum(2), "Значение y должно быть 7.0");

    }

    // Тестирование сложения векторов
    @Test
    public void testAddition() {
        Vector3f v1 = new Vector3f(1, 2, 3);
        Vector3f v2 = new Vector3f(3, 4, 7);
        Vector3f result = v1.add(v2);
        Assertions.assertEquals(new Vector3f(4, 6, 10), result, "Сложение векторов неверно");
    }

    // Тестирование вычитания векторов
    @Test
    public void testSubtraction() {
        Vector3f v1 = new Vector3f(5, 7, 8);
        Vector3f v2 = new Vector3f(2, 3, 3);
        Vector3f result = v1.sub(v2);
        Assertions.assertEquals(new Vector3f(3, 4, 5), result, "Вычитание векторов неверно");
    }

    // Тестирование умножения на скаляр
    @Test
    public void testMultiply() {
        Vector3f v = new Vector3f(2, -3, 10);
        float scalar = 4;
        v.multiply(scalar);
        Assertions.assertEquals(new Vector3f(8, -12, 40), v, "Умножение на скаляр неверно");
    }

    // Тестирование деления на скаляр
    @Test
    public void testDivide() {
        Vector3f v = new Vector3f(9.0f, -6.0f, 12);
        float scalar = 3.0f;
        v.divide(scalar);
        Assertions.assertEquals(new Vector3f(3.0f, -2.0f, 4), v, "Деление на скаляр неверно");
    }

    @Test
    public void testDivideByZero() {
        Vector3f v = new Vector3f(9.0f, -6.0f, 12);
        float scalar = 0.0f;
        Exception exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            v.divide(scalar);
        });
        Assertions.assertEquals("Деление на ноль", exception.getMessage(), "Исключение при делении на ноль неверно");
    }

    // доп геттеры для упрощения работы с матрицами, ибо, я не хочу делать switch кейс
    @Test
    void testGetNumInvalidNegativeIndex() {
        Vector3f vector = new Vector3f(1.0f, 2.0f, 3.0f);
        Exception exception = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> vector.getNum(4));
        Assertions.assertEquals("Invalid index: 4", exception.getMessage());
    }


    @Test
    void testSetNumInvalidNegativeIndex() {
        Vector3f vector = new Vector3f(1.0f, 2.0f, 3.0f);
        Exception exception = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> vector.setNum(4, 10));
        Assertions.assertEquals("Invalid index: 4", exception.getMessage());
    }

    @Test
    void testGetNumValidIndices() {
        Vector3f vector = new Vector3f(1.0f, 2.0f, 3.0f);
        Assertions.assertEquals(1.0f, vector.getNum(0), 1e-6f, "Component at index 0 should be x (1.0f)");
        Assertions.assertEquals(2.0f, vector.getNum(1), 1e-6f, "Component at index 1 should be y (2.0f)");
        Assertions.assertEquals(3.0f, vector.getNum(2), 1e-6f, "Component at index 2 should be z (3.0f)");
    }


    // Тест на размер... вектора, конечно
    @Test
    public void testLength() {
        Vector3f v = new Vector3f(3, 4, 10);
        float length = v.length();
        Assertions.assertEquals(Math.sqrt(125), length, 1e-6, "Длина вектора неверна");
    }

    // Тестирование нормализации ненулевого вектора
    @Test
    public void testNormalize() {
        Vector3f v = new Vector3f(1, 1, 1);
        v.normalize();
        Assertions.assertEquals(new Vector3f((float) (1 / Math.sqrt(3)), (float) (1 / Math.sqrt(3)), (float) (1 / Math.sqrt(3))), v, "Нормализация вектора неверна");
        Assertions.assertEquals(1, v.length(), 1e-6, "Нормализованный вектор должен иметь длину 1");
    }

    // Тестирование нормализации нулевого вектора
    @Test
    public void testNormalizeZeroVector() {
        Vector3f zeroVector = new Vector3f(0, 0, 0);
        zeroVector.normalize();
        Assertions.assertEquals(new Vector3f(0, 0, 0), zeroVector, "Нормализация нулевого вектора должна возвращать сам вектор");
    }

    // Тестирование скалярного произведения
    @Test
    public void testDotProduct() {
        Vector3f v1 = new Vector3f(-1, 2, -5);
        Vector3f v2 = new Vector3f(3, -3, -3);
        float dot = v1.dot(v2);
        Assertions.assertEquals(6, dot, 1e-6, "Скалярное произведение неверно");
    }

    @Test
    public void testCrossProduct() {
        Vector3f v1 = new Vector3f(-1, 2, -3);
        Vector3f v2 = new Vector3f(0, -4, 1);
        Vector3f result = v1.cross(v2);
        Assertions.assertEquals(new Vector3f(-10, 1, 4), result, "Скалярное произведение неверно");
    }

    @Test
    public void testEqualsAndHashCode() {
        Vector3f v1 = new Vector3f(2.5f, -7.1f, 14);
        Vector3f v2 = new Vector3f(2.5f, -7.1f, 14);
        Vector3f v3 = new Vector3f(2.5f, -7.2f, 17);

        // Проверка равенства
        Assertions.assertEquals(v1, v2, "Векторы с одинаковыми компонентами должны быть равны");
        Assertions.assertNotEquals(v1, v3, "Векторы с разными компонентами не должны быть равны");

        // Проверка хэш-кодов
        Assertions.assertEquals(v1.hashCode(), v2.hashCode(), "Хэш-коды равных объектов должны совпадать");
        Assertions.assertNotEquals(v1.hashCode(), v3.hashCode(), "Хэш-коды разных объектов не совпадают");
    }

    // Тестирование метода toString
    @Test
    public void testToString() {
        Vector3f v = new Vector3f(5.0f, -3.0f, 10);
        String expected = "Vector3f{x=5.0, y=-3.0, z=10.0}";
        Assertions.assertEquals(expected, v.toString(), "Метод toString возвращает неверное представление");
    }

    // доп тесты для сложения/умножения на 0 вектор
    @Test
    public void testOperationsWithZeroVector() {
        Vector3f zero = new Vector3f(0.0f, 0.0f, 0);
        Vector3f v = new Vector3f(1.0f, -1.0f, 9);

        // Сложение с нулевым вектором
        Vector3f addResult = v.add(zero);
        Assertions.assertEquals(v, addResult, "Сложение с нулевым вектором должно возвращать исходный вектор");

        // Вычитание нулевого вектора
        Vector3f subtractResult = v.sub(zero);
        Assertions.assertEquals(v, subtractResult, "Вычитание нулевого вектора должно возвращать исходный вектор");

        // Скалярное произведение с нулевым вектором
        float dot = v.dot(zero);
        Assertions.assertEquals(0.0f, dot, 1e-6, "Скалярное произведение с нулевым вектором должно быть 0");
    }
}
