package ua.kharkov.knure.kolesnikov;

/**
 * Класс демонстрации работы с шаблоном Observer.<br/>
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public class Demo {
	public static void main(String[] args) {
		// источник событий
		EventSource source = new EventSource();

		// регистрация двух слушателей событий
		source.add(new EventListenerImplA());
		source.add(new EventListenerImplB());

		// моделирование возникновения события №1
		source.fireEvent1();
		System.out.println("~~~~~~~~");

		// моделирование возникновения события №2
		source.fireEvent2();
	}

}
