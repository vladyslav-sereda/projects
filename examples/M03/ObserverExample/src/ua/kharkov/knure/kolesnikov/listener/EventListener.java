package ua.kharkov.knure.kolesnikov.listener;

import ua.kharkov.knure.kolesnikov.event.Event;

/**
 * ��������� ��������� ������� Event.<br/>
 * (Observer - �������, ������� ��������� �� Observable)
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public interface EventListener {
	void eventOccured(Event event);
}
