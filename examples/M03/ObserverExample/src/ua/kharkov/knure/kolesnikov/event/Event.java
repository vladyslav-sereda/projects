package ua.kharkov.knure.kolesnikov.event;

import ua.kharkov.knure.kolesnikov.EventSource;

/**
 * Объект событие. Содержимое данного класса в общем случае зависит от
 * контекста.
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public class Event {
	/**
	 * Имя события (для демонстрации).
	 */
	private String name;

	/**
	 * Источник события. Как правило источник события можно получить по самому
	 * объекту событию.
	 */
	private EventSource source;

	public Event(EventSource source, String name) {
		this.source = source;
		this.name = name;
	}

	/**
	 * Возвращает источник события.
	 * 
	 * @return объект EventSource который вызвал данное событие.
	 */
	public EventSource getSource() {
		return source;
	}

	/**
	 * Возвращает имя события (для демонстрации).
	 * 
	 * @return имя данного события.
	 */
	public String getName() {
		return name;
	}

}