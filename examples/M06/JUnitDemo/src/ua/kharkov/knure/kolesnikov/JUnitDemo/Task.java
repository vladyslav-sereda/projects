package ua.kharkov.knure.kolesnikov.JUnitDemo;

/**
 *  ласс с бизнес логикой.
 * 
 * @author Dmitry Kolesnkov
 * 
 */
public class Task {

	/**
	 * ћетод бизнес логики.<br/>
	 * ¬ данном случае сложение двух чисел.
	 * 
	 * @param x
	 *            первое слагаемое.
	 * @param y
	 *            второе слагаемое.
	 * @return сумма слагаемых.
	 * 
	 */
	public int add(int x, int y) {
		int result = x + y;
		// result = x + y + 1; // <== try to uncomment this line!!!
		return result;
	}

	/**
	 * ћетод бизнес логики.<br/>
	 * ¬ данном случае деление двух <i>целых</i> чисел.<br/>
	 * ƒанный метод целесообразно тестировать, например, следующими значени€ми:
	 * (6, 3); (7, 3); (7, 0)
	 * 
	 * @param x
	 *            делимое.
	 * @param y
	 *            делитель.
	 * @return результат делени€ делимого на делитель
	 * @throws ArithmeticException
	 *             если делимое 0.
	 */
	public int div(int x, int y) {
		return x / y;
	}

	/**
	 * ћетод main может служить простейшим средство тестировани€
	 * функциональности класса.
	 */
	public static void main(String[] args) {
		Task t = new Task();
		System.out.println(t.div(3, 2));
	}

}