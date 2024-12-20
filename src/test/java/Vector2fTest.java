import ru.vsu.math.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Vector2fTest {
    @Test
    public void testConstructorAndGetters() {
        Vector2f vector = new Vector2f(3, 4);
        Assertions.assertEquals(3, vector.getX(), "Значение x должно быть 3.0");
        Assertions.assertEquals(4, vector.getY(), "Значение y должно быть 4.0");
    }

    // Тестирование сложения векторов
    @Test
    public void testAddition() {
        Vector2f v1 = new Vector2f(1, 2);
        Vector2f v2 = new Vector2f(3, 4);
        Vector2f result = v1.add(v2);
        Assertions.assertEquals(new Vector2f(4, 6), result, "Сложение векторов неверно");
    }

    // Тестирование вычитания векторов
    @Test
    public void testSubtraction() {
        Vector2f v1 = new Vector2f(5, 7);
        Vector2f v2 = new Vector2f(2, 3);
        Vector2f result = v1.sub(v2);
        Assertions.assertEquals(new Vector2f(3, 4), result, "Вычитание векторов неверно");
    }

    // Тестирование умножения на скаляр
    @Test
    public void testMultiply() {
        Vector2f v = new Vector2f(2, -3);
        float scalar = 4;
        v.multiply(scalar);
        Assertions.assertEquals(new Vector2f(8, -12), v, "Умножение на скаляр неверно");
    }

    // Тестирование деления на скаляр
    @Test
    public void testDivide() {
        Vector2f v = new Vector2f(9.0f, -6.0f);
        float scalar = 3.0f;
        v.divide(scalar);
        Assertions.assertEquals(new Vector2f(3.0f, -2.0f), v, "Деление на скаляр неверно");
    }

    // тест на ошибку при делении на 0
    @Test
    public void testDivideByZero() {
        Vector2f v = new Vector2f(1.0f, 2.0f);
        float scalar = 0.0f;
        Exception exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            v.divide(scalar);
        });
        Assertions.assertEquals("Деление на ноль", exception.getMessage(), "Исключение при делении на ноль неверно");
    }

    // Тест на длину... не вектора
    @Test
    public void testLength() {
        Vector2f v = new Vector2f(3, 4);
        float length = v.length();
        Assertions.assertEquals(5, length, 1e-6, "Длина вектора неверна");
    }

    // Тестирование нормализации ненулевого вектора
    @Test
    public void testNormalize() {
        Vector2f v = new Vector2f(3.0f, 4.0f);
        v.normalize();
        Assertions.assertEquals(new Vector2f(0.6f, 0.8f), v, "Нормализация вектора неверна");
        Assertions.assertEquals(1.0f, v.length(), 1e-6, "Нормализованный вектор должен иметь длину 1");
    }

    // Тестирование нормализации нулевого вектора
    @Test
    public void testNormalizeZeroVector() {
        Vector2f zeroVector = new Vector2f(0.0f, 0.0f);
        zeroVector.normalize();
        Assertions.assertEquals(new Vector2f(0, 0), zeroVector, "Нормализация нулевого вектора должна возвращать сам вектор");
    }

    // Тестирование скалярного произведения
    @Test
    public void testDotProduct() {
        Vector2f v1 = new Vector2f(1.0f, 3.0f);
        Vector2f v2 = new Vector2f(4.0f, -2.0f);
        float dot = v1.dot(v2);
        Assertions.assertEquals(-2.0f, dot, 1e-6, "Скалярное произведение неверно");
    }

    @Test
    public void testEqualsAndHashCode() {
        Vector2f v1 = new Vector2f(2.5f, -7.1f);
        Vector2f v2 = new Vector2f(2.5f, -7.1f);
        Vector2f v3 = new Vector2f(2.5f, -7.2f);

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
        Vector2f v = new Vector2f(5.0f, -3.0f);
        String expected = "Vector2f{x=5.0, y=-3.0}";
        Assertions.assertEquals(expected, v.toString(), "Метод toString возвращает неверное представление");
    }
    // доп тесты для сложения/умножения на 0 вектор
    @Test
    public void testOperationsWithZeroVector() {
        Vector2f zero = new Vector2f(0.0f, 0.0f);
        Vector2f v = new Vector2f(1.0f, -1.0f);

        // Сложение с нулевым вектором
        Vector2f addResult = v.add(zero);
        Assertions.assertEquals(v, addResult, "Сложение с нулевым вектором должно возвращать исходный вектор");

        // Вычитание нулевого вектора
        Vector2f subtractResult = v.sub(zero);
        Assertions.assertEquals(v, subtractResult, "Вычитание нулевого вектора должно возвращать исходный вектор");

        // Скалярное произведение с нулевым вектором
        float dot = v.dot(zero);
        Assertions.assertEquals(0.0f, dot, 1e-6, "Скалярное произведение с нулевым вектором должно быть 0");
    }
}