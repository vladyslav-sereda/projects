/**
 * ������ ������������� ���� (enum).<br/>
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public enum Season { // <=== ���� Season.class
	SPRING(+10),

	SUMMER(+25) { // <=== ���� Season$1.class
		/**
		 * ����������� ����� {@link Season#toString()}.
		 */
		public String toString() {
			return super.toString() + " It's fine!";
		}
	},

	AUTUMN(+5), WINTER;

	private int t;

	/**
	 * ����������� �� ���������. ��� ���������� ������� WINTER.
	 */
	Season() {
		this(0);
	}

	/**
	 * �������� �� ��, ��� ����������� ������ ������� �� ���������, �������
	 * ������� ������������� enum ������ private.<br/>
	 * ������ ����������� ���������� ��� ������������� ��� �������� �����
	 * WINTER.
	 * 
	 * @param t
	 *            - ����������� ������
	 */
	Season(int t) { // <=== ������� ������� private
		this.t = t;
	}

	/**
	 * ���������� ����������� ������. ��� ����� ����� ��� ���� ��������� enum.
	 * 
	 * @return �������� ����������� ������.
	 */
	public int getT() {
		return t;
	}

	/**
	 * ����������� ����� {@link Enum#toString()}
	 */
	public String toString() {
		return super.toString() + ", temperature = " + getT();
	}
}
