package ua.kharkov.knure.kolesnikov;

import java.util.ArrayList;
import java.util.List;

import ua.kharkov.knure.kolesnikov.event.Event;
import ua.kharkov.knure.kolesnikov.listener.EventListener;

/**
 * �������� �������.<br/>
 * (Observable - ������, �� ������� ���������)
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public class EventSource {
	/**
	 * ������ ���������� �������, ������� ���������� ������ EventSource.
	 */
	private List<EventListener> listeners;

	public EventSource() {
		listeners = new ArrayList<EventListener>();
	}

	/**
	 * ��������� ������ ��������� �������.
	 * 
	 * @param listener
	 *            ������, ������� ����� ������������ ������� �������
	 *            EventSource.
	 */
	public void add(EventListener listener) {
		listeners.add(listener);
	}

	/**
	 * ���������� ���� ���������� � ���, ��� ��������� �������.
	 */
	private void notifyListeners(Event event) {
		for (EventListener listener : listeners) {
			listener.eventOccured(event);
		}
	}

	/**
	 * ����� ������������ ������������� ������� �1
	 */
	public void fireEvent1() {
		Event event = new Event(this, "Event 1");
		notifyListeners(event);
	}

	/**
	 * ����� ������������ ������������� ������� �2
	 */
	public void fireEvent2() {
		Event event = new Event(this, "Event 2");
		notifyListeners(event);
	}

	@Override
	public String toString() {
		return "EventSource"; // ��� ������������
	}
}
