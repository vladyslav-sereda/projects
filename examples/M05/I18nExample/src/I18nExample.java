import java.util.Locale;
import java.util.ResourceBundle;

/**
 * ������������� ������ � ResourceBundle.<br/>
 * ������������� ��������������������, ������ (��������� � ������ �������)
 * ��������� ������ ��������, � ����� ������ (�����������) ������ � ������
 * hanger.<br/>
 * ��� ��������� ������� �������� ����� ������������ ������ <i>ResourceBundle
 * editor</i>.
 * 
 * @author Dmitry Kolesnikov
 */
public class I18nExample {
	public static void main(String[] args) throws Exception {
		Locale locale = new Locale("ru", "UA", "Kharkov");
		ResourceBundle rb = ResourceBundle.getBundle("resources", locale);
		System.out.println(rb.getString("hanger"));
	}
}
