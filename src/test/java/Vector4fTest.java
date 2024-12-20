import ru.vsu.math.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Vector4fTest {

    @Test
    public void testConstructorAndGetters() {
        Vector4f vector = new Vector4f(1.0f, 2.0f, 3.0f, 4.0f);
        Assertions.assertEquals(1.0f, vector.getX(), "Значение x должно быть 1.0");
        Assertions.assertEquals(2.0f, vector.getY(), "Значение y должно быть 2.0");
        Assertions.assertEquals(3.0f, vector.getZ(), "Значение z должно быть 3.0");
        Assertions.assertEquals(4.0f, vector.getW(), "Значение w должно быть 4.0");
    }

    @Test
    public void testAddition() {
        Vector4f v1 = new Vector4f(1.0f, 2.0f, 3.0f, 4.0f);
        Vector4f v2 = new Vector4f(4.0f, 3.0f, 2.0f, 1.0f);
        Vector4f result = v1.add(v2);
        Vector4f expected = new Vector4f(5.0f, 5.0f, 5.0f, 5.0f);
        Assertions.assertEquals(expected, result, "Сложение векторов неверно");
    }

    @Test
    public void testSubtraction() {
        Vector4f v1 = new Vector4f(5.0f, 7.0f, 9.0f, 11.0f);
        Vector4f v2 = new Vector4f(1.0f, 2.0f, 3.0f, 4.0f);
        Vector4f result = v1.sub(v2);
        Vector4f expected = new Vector4f(4.0f, 5.0f, 6.0f, 7.0f);
        Assertions.assertEquals(expected, result, "Вычитание векторов неверно");
    }

    @Test
    public void testMultiply() {
        Vector4f v = new Vector4f(2.0f, -3.0f, 4.0f, -5.0f);
        float scalar = 3.0f;
        v.multiply(scalar);
        Vector4f expected = new Vector4f(6.0f, -9.0f, 12.0f, -15.0f);
        Assertions.assertEquals(expected, v, "Умножение на скаляр неверно");
    }

    @Test
    public void testMultiplyByZero() {
        Vector4f v = new Vector4f(2.0f, -3.0f, 4.0f, -5.0f);
        float scalar = 0.0f;
        v.multiply(scalar);
        Assertions.assertEquals(0, v.getX(), 1e-6, "Длина вектора неверна");
        Assertions.assertEquals(0, v.getY(), 1e-6, "Длина вектора неверна");
        Assertions.assertEquals(0, v.getZ(), 1e-6, "Длина вектора неверна");
        Assertions.assertEquals(0, v.getW(), 1e-6, "Длина вектора неверна");

    }

    @Test
    public void testDivide() {
        Vector4f v = new Vector4f(8.0f, -6.0f, 12.0f, -4.0f);
        float scalar = 2.0f;
        v.divide(scalar);
        Vector4f expected = new Vector4f(4.0f, -3.0f, 6.0f, -2.0f);
        Assertions.assertEquals(expected, v, "Деление на скаляр неверно");
    }

    @Test
    public void testDivideByZero() {
        Vector4f v = new Vector4f(8.0f, -6.0f, 12.0f, -4.0f);
        float scalar = 0.0f;
        Exception exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            v.divide(scalar);
        });
        Assertions.assertEquals("Деление на ноль", exception.getMessage(), "Исключение при делении на ноль неверно");
    }

    @Test
    public void testLength() {
        Vector4f v = new Vector4f(1.0f, 2.0f, 2.0f, 1.0f);
        float length = v.length();
        Assertions.assertEquals((float) Math.sqrt(10), length, 1e-6, "Длина вектора неверна");
    }

    @Test
    public void testNormalize() {
        Vector4f v = new Vector4f(2.0f, 0.0f, 0.0f, 0.0f);
        v.normalize();
        Vector4f expected = new Vector4f(1.0f, 0.0f, 0.0f, 0.0f);
        Assertions.assertEquals(expected, v, "Нормализация вектора неверна");
        Assertions.assertEquals(1.0f, v.length(), 1e-6, "Нормализованный вектор должен иметь длину 1");
    }

    @Test
    public void testNormalizeZeroVector() {
        Vector4f zeroVector = new Vector4f(0.0f, 0.0f, 0.0f, 0.0f);
        zeroVector.normalize();
        Vector4f expected = new Vector4f(0.0f, 0.0f, 0.0f, 0.0f);
        Assertions.assertEquals(expected, zeroVector, "Нормализация нулевого вектора должна возвращать сам вектор");
    }

    @Test
    public void testDotProduct() {
        Vector4f v1 = new Vector4f(1.0f, 2.0f, 3.0f, 4.0f);
        Vector4f v2 = new Vector4f(-1.0f, 0.5f, 2.0f, 3.0f);
        float dot = v1.dot(v2);
        float expected = (1.0f * -1.0f) + (2.0f * 0.5f) + (3.0f * 2.0f) + (4.0f * 3.0f); // -1 + 1 + 6 + 12 = 18
        Assertions.assertEquals(18.0f, dot, 1e-6, "Скалярное произведение неверно");
    }

    @Test
    public void testEqualsAndHashCode() {
        Vector4f v1 = new Vector4f(1.5f, -2.5f, 3.0f, 4.5f);
        Vector4f v2 = new Vector4f(1.5f, -2.5f, 3.0f, 4.5f);
        Vector4f v3 = new Vector4f(1.5f, -2.5f, 3.1f, 4.5f);

        // Проверка равенства
        Assertions.assertEquals(v1, v2, "Векторы с одинаковыми компонентами должны быть равны");
        Assertions.assertNotEquals(v1, v3, "Векторы с разными компонентами не должны быть равны");

        // Проверка хэш-кодов
        Assertions.assertEquals(v1.hashCode(), v2.hashCode(), "Хэш-коды равных объектов должны совпадать");
        Assertions.assertNotEquals(v1.hashCode(), v3.hashCode(), "Хэш-коды разных объектов не совпадают");
    }

    @Test
    public void testToString() {
        Vector4f v = new Vector4f(5.0f, -3.0f, 10.0f, 2.0f);
        String expected = "Vector4f{x=5.0, y=-3.0, z=10.0, w=2.0}";
        Assertions.assertEquals(expected, v.toString(), "Метод toString возвращает неверное представление");
    }

    @Test
    public void testOperationsWithZeroVector() {
        Vector4f zero = new Vector4f(0.0f, 0.0f, 0.0f, 0.0f);
        Vector4f v = new Vector4f(1.0f, -1.0f, 9.0f, 5.0f);

        // Сложение с нулевым вектором
        Vector4f addResult = v.add(zero);
        Assertions.assertEquals(v, addResult, "Сложение с нулевым вектором должно возвращать исходный вектор");

        // Вычитание нулевого вектора
        Vector4f subtractResult = v.sub(zero);
        Assertions.assertEquals(v, subtractResult, "Вычитание нулевого вектора должно возвращать исходный вектор");

        // Скалярное произведение с нулевым вектором
        float dot = v.dot(zero);
        Assertions.assertEquals(0.0f, dot, 1e-6, "Скалярное произведение с нулевым вектором должно быть 0");
    }
}