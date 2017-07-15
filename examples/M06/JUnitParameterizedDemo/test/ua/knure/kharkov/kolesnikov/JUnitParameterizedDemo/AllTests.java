package ua.knure.kharkov.kolesnikov.JUnitParameterizedDemo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Тестовый набор. Объединяет несколько тестовых классов.
 * 
 * @author Dmitry Kolesnikov
 * 
 */
@RunWith(Suite.class)
@SuiteClasses({ TaskAddParameterizedTest.class, TaskDivParameterizedTest.class })
public class AllTests {

}
