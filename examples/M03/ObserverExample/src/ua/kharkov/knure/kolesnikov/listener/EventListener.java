package ua.kharkov.knure.kolesnikov.listener;

import ua.kharkov.knure.kolesnikov.event.Event;

/**
 * Интерфейс слушателя события Event.<br/>
 * (Observer - объекты, которые наблюдают за Observable)
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public interface EventListener {
	void eventOccured(Event event);
}
