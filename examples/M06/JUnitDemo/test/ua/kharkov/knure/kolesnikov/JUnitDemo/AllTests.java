package ua.kharkov.knure.kolesnikov.JUnitDemo;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * �������� �����. ���������� ��������� �������� �������.
 * 
 * @author Dmitry Kolesnikov
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ TaskTest1.class })
public class AllTests {
}
