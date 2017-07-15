package ua.kharkov.knure.kolesnikov;

import ua.kharkov.knure.kolesnikov.event.Event;
import ua.kharkov.knure.kolesnikov.listener.EventListener;

/**
 * Слушатель событий.
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public class EventListenerImplB implements EventListener {

	@Override
	public void eventOccured(Event event) {
		System.out.println("EventListenerImplB#eventOccured: event--> "
				+ event.getName() + "; source --> " + event.getSource());
	}
}
