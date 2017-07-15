package ua.kharkov.knure.kolesnikov;

import java.util.ArrayList;
import java.util.List;

import ua.kharkov.knure.kolesnikov.event.Event;
import ua.kharkov.knure.kolesnikov.listener.EventListener;

/**
 * Источник событий.<br/>
 * (Observable - объект, за которым наблюдают)
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public class EventSource {
	/**
	 * Список слушателей событий, который генерирует данный EventSource.
	 */
	private List<EventListener> listeners;

	public EventSource() {
		listeners = new ArrayList<EventListener>();
	}

	/**
	 * Добавляет нового слушателя события.
	 * 
	 * @param listener
	 *            объект, который будет прослушивать события данного
	 *            EventSource.
	 */
	public void add(EventListener listener) {
		listeners.add(listener);
	}

	/**
	 * Уведомляет всех слушателей о том, что произошло событие.
	 */
	private void notifyListeners(Event event) {
		for (EventListener listener : listeners) {
			listener.eventOccured(event);
		}
	}

	/**
	 * Метод моделирующий возникновение события №1
	 */
	public void fireEvent1() {
		Event event = new Event(this, "Event 1");
		notifyListeners(event);
	}

	/**
	 * Метод моделирующий возникновение события №2
	 */
	public void fireEvent2() {
		Event event = new Event(this, "Event 2");
		notifyListeners(event);
	}

	@Override
	public String toString() {
		return "EventSource"; // для демонстрации
	}
}
