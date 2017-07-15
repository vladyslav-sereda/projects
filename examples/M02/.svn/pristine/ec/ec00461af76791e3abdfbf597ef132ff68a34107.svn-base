public class CreationDemo {

	public static void main(String[] args) {
		// создание объекта вложенного класса
		O.N nested = new O.N();
		
		// создание объекта внутреннего класса
		// обязательно нужен объект внешнего класса
		O o = new O();
		O.I internal = o.new I();		
	}
	
}

// класс верхнего уровня
// внешний класс по отношению к I и N
class O {

	// внутренний класс
	public class I {
	}

	// вложенный класс
	public static class N {
	}
	
}