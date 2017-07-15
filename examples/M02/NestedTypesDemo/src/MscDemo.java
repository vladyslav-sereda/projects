
public class MscDemo {
	
	private void m() {
		// если убрать final будет ошибка компил€ции
		final int x = 7; // <== x
		
		// анонимный класс
		new Thread() {
			@Override
			public void run() {
				System.out.println(this + ", x = " + x); // <== x
			}
		}.start();
	}
	
	private class Internal {
		public Internal() {
			// печатаем текущий объект
			System.out.println(this);
			
			// печатаем объект внешнего класса
			System.out.println(MscDemo.this);
		}
	}
	
	public static void main(String[] args) {
		MscDemo o = new MscDemo();
		
		MscDemo.Internal internal = o.new Internal(); 
		System.out.println("~~~~~~~~~~~~~~~~~");
		
		o.m();
	}

}
