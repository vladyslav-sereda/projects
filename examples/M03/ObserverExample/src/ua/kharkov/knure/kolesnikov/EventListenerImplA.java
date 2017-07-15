package ua.kharkov.knure.kolesnikov;

import ua.kharkov.knure.kolesnikov.event.Event;
import ua.kharkov.knure.kolesnikov.listener.EventListener;

/**
 * Слушатель событий.
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public class EventListenerImplA implements EventListener {

	@Override
	public void eventOccured(Event event) {
		System.out.println("EventListenerImplA#eventOccured: event--> "
				+ event.getName() + "; source --> " + event.getSource());
	}
}
