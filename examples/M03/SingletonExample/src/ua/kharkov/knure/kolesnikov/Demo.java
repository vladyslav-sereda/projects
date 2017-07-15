package ua.kharkov.knure.kolesnikov;

public class Demo {
	public static void main(String[] args) {
		Singleton s = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		System.out.println(s == s2);
		
		SingletonWOLI w = SingletonWOLI.getInstance();
		SingletonWOLI w2 = SingletonWOLI.getInstance();
		System.out.println(w == w2);
		
		SingletonEnum e = SingletonEnum.INSTANCE;
		SingletonEnum e2 = SingletonEnum.INSTANCE;
		System.out.println(e == e2);
	}

}
