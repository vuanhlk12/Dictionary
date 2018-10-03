package BTLon;


import java.io.IOException;

import com.darkprograms.speech.translator.GoogleTranslate;

public class TranslatorExample {
	
	public static void main(String[] args) {

		
		try {
			System.out.println(GoogleTranslate.translate("en","vi", "hi there"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		Voice a = new Voice();
		a.setLine("Đờ mờ chính");
		a.speak();
		
	}
	
}
