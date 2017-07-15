package ua.kharkov.knure.kolesnikov.event;

import ua.kharkov.knure.kolesnikov.EventSource;

/**
 * ������ �������. ���������� ������� ������ � ����� ������ ������� ��
 * ���������.
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public class Event {
	/**
	 * ��� ������� (��� ������������).
	 */
	private String name;

	/**
	 * �������� �������. ��� ������� �������� ������� ����� �������� �� ������
	 * ������� �������.
	 */
	private EventSource source;

	public Event(EventSource source, String name) {
		this.source = source;
		this.name = name;
	}

	/**
	 * ���������� �������� �������.
	 * 
	 * @return ������ EventSource ������� ������ ������ �������.
	 */
	public EventSource getSource() {
		return source;
	}

	/**
	 * ���������� ��� ������� (��� ������������).
	 * 
	 * @return ��� ������� �������.
	 */
	public String getName() {
		return name;
	}

}