import java.text.NumberFormat;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Demonstrates currency conversion and locale sensitive formatting.
 * 
 * @author Dmitry Kolesnikov
 * 
 */
public class Demo {

	/**
	 * Returns the converted currency value.
	 * 
	 * @param locale
	 *            locale of the country.
	 * @param amount
	 *            the amount of currency in UAH.
	 * @param conversion
	 *            currency conversion table (key = currency code ISO 4217, value
	 *            = the cost of the one currency unit in UAH).
	 * @return converted value
	 */
	public static String getCurrency(Locale locale, double amount,
			Map<String, Double> conversion) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
		Double alfa = conversion.get(Currency.getInstance(locale)
				.getCurrencyCode());
		if (alfa == null)
			throw new IllegalArgumentException(
					"Cannot find currency code for the given locale: " + locale);
		return nf.format(amount / alfa);
	}

	public static void main(String[] args) {
		// put value to currency conversion table
		Map<String, Double> conv = new HashMap<String, Double>();
		conv.put("UAH", 1.);
		conv.put("USD", 8.1936);
		conv.put("EUR", 11.05);
		conv.put("GBP", 13.1658);

		double amount = 3577;

		System.out.println(getCurrency(new Locale("uk", "UA"), amount, conv));
		System.out.println(getCurrency(new Locale("en", "US"), amount, conv));
		System.out.println(getCurrency(new Locale("fr", "FR"), amount, conv));

		// note! to see GBP sign set UTF-8 in console
		System.out.println(getCurrency(new Locale("en", "GB"), amount, conv));
	}

}