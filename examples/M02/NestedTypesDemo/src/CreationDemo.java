public class CreationDemo {

	public static void main(String[] args) {
		// �������� ������� ���������� ������
		O.N nested = new O.N();
		
		// �������� ������� ����������� ������
		// ����������� ����� ������ �������� ������
		O o = new O();
		O.I internal = o.new I();		
	}
	
}

// ����� �������� ������
// ������� ����� �� ��������� � I � N
class O {

	// ���������� �����
	public class I {
	}

	// ��������� �����
	public static class N {
	}
	
}