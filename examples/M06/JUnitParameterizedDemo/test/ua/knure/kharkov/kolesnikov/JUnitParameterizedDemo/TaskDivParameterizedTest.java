package ua.knure.kharkov.kolesnikov.JUnitParameterizedDemo;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


/**
 * Тестовый класс с параметрами для тестирования Task#div
 * 
 * @author Dmitry Kolesnikov
 * 
 */
@RunWith(Parameterized.class)
public class TaskDivParameterizedTest {

	/**
	 * Входное значение x метода Task#div
	 */
	private int passValueX;

	/**
	 * Входное значение y метода Task#div
	 */
	private int passValueY;

	/**
	 * Ожидаемое значение, которое должен вернуть метод Task#div
	 */
	private int expectedValue;

	/**
	 * Тип исключения, которое должен выбросить метод Task#div
	 */
	private Class<Throwable> expectedException;

	/**
	 * Поле, которое будет использовано в тестовых методах (объект класса Task).
	 */
	private Task task;

	/**
	 * Наличие данного конструктора обязательно.<br/>
	 * JUnit создает экземпляр класса для каждого набора параметров, передавая
	 * их в конструктор.
	 * 
	 * @param passValueX
	 * @param passValueY
	 * @param expectedValue
	 */
	public TaskDivParameterizedTest(int passValueX, int passValueY,
			int expectedValue, Class<Throwable> expectedException) {
		this.passValueX = passValueX;
		this.passValueY = passValueY;
		this.expectedValue = expectedValue;
		this.expectedException = expectedException;
	}

	/**
	 * Метод, который поддерживает механизм запуска параметризованных junit
	 * тестов.<br/>
	 * Наличие метода обязательно. Обязан быть аннотирован @Parameters<br/>
	 * Название метода - любое.
	 * 
	 * @return коллекцию (Collection), каждый элемент которой содержит массив
	 *         значений параметров, которые, в свою очередь, будут переданы в
	 *         конструктор.
	 */
	@Parameters
	public static Collection<?> getParameters() {
		Collection<Object[]> data = new ArrayList<Object[]>();
		data.add(new Object[] { 6, 3, 2, null });
		data.add(new Object[] { 7, 3, 2, null });
		data.add(new Object[] { 7, 0, 0, ArithmeticException.class });

		return data;
	}
	
	/**
	 * Метод поднятия глобальной фикстуры.<br/>
	 * Тестовая фикстура - тестовое окружение. Данный метод будет вызван один
	 * раз перед запуском всех тестовых методов.<br/>
	 */
	@BeforeClass
	public static void setUpGlobal() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
	}

	/**
	 * Метод поднятия тестовой фикстуры. Будет вызван по одному разу перед
	 * каждым вызовом каждого тестового метода.
	 */
	@Before
	public void setUp() {
		System.out.print("setUp");

		// перед вызовом каждого тестового метода
		// будет создан новый объект task
		task = new Task();
		System.out.println("\t-->\t" + task);
	}

	@Test
	public void testDiv() {
		System.out.println("\ttestDiv\t-->\t[" + passValueX + ", " + passValueY
				+ ", " + expectedValue + ", " + expectedException + "]");

		if (expectedException != null) {
			// функциональность негативного теста
			try {				
				// вызов метод должен привести к выбросу исключения
				// которое имеет тип expectedException.getName()
				task.div(passValueX, passValueY);
				
				// если метод отработал и выброса исключения не произошло
				// то тест не пройден ("фейлим" тест)
				Assert.fail("Expected exception: " + expectedException);
			} catch (Throwable t) {
				// если исключение произошло проверяем его тип
				// и в случае, если было выброшено исключение
				// но его тип не совпал с ожидаемым, то тест не пройден
				// ("фейлим" тест)
				if (t.getClass() != expectedException)
					Assert.fail("Expected exception: " + expectedException);
			}
		} else {
			// функциональность позитивных тестов
			int actual = task.div(passValueX, passValueY);
			Assert.assertEquals(expectedValue, actual);
		}
	}
}
