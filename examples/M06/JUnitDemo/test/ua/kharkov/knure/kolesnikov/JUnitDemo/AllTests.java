package ua.kharkov.knure.kolesnikov.JUnitDemo;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Тестовый набор. Объединяет несколько тестовых классов.
 * 
 * @author Dmitry Kolesnikov
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ TaskTest1.class })
public class AllTests {
}
