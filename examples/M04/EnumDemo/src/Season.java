/**
 * Пример перечислимого типа (enum).<br/>
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public enum Season { // <=== файл Season.class
	SPRING(+10),

	SUMMER(+25) { // <=== файл Season$1.class
		/**
		 * Перекрываем метод {@link Season#toString()}.
		 */
		public String toString() {
			return super.toString() + " It's fine!";
		}
	},

	AUTUMN(+5), WINTER;

	private int t;

	/**
	 * Конструктор по умолчанию. Его использует элемент WINTER.
	 */
	Season() {
		this(0);
	}

	/**
	 * Несмотря на то, что модификатор уровня доступа не поставлен, уровень
	 * доступа конструкторов enum всегда private.<br/>
	 * Данный конструктор используют для инициализации все элементы кроме
	 * WINTER.
	 * 
	 * @param t
	 *            - температура сезона
	 */
	Season(int t) { // <=== уровень доступа private
		this.t = t;
	}

	/**
	 * Возвращает температуру сезона. Это общий метод для всех элементов enum.
	 * 
	 * @return значение температуры сезона.
	 */
	public int getT() {
		return t;
	}

	/**
	 * Перекрываем метод {@link Enum#toString()}
	 */
	public String toString() {
		return super.toString() + ", temperature = " + getT();
	}
}
