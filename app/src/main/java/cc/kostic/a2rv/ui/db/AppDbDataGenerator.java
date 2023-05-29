package cc.kostic.a2rv.ui.db;

import java.util.ArrayList;
import java.util.List;


public class AppDbDataGenerator {

	public static final int strt = 1;
	public static final int MAXUR = 7;     // kreiranih uredjaja u bazi
	public static final int MAXDUG = 4;    // dugmica u svakom uredjaju

	public static final int DB_CREATIONDELAY = 500; // mS


	public static List<Slika> getDummyUredjajs(){
		List<Slika> lista = new ArrayList<>();
		for (int i = strt; i < MAXUR + 1; i++) {
			String n = Character.toString((char) (i + 65));

			Slika slika = new Slika();
			slika.setNaziv("" + i + "" + n);
			slika.setCena("111-" + i);
			lista.add(slika);
		}
		return lista;
	}





	public static void delay_inDebugMode() {
		try {
			if (BuildConfig.DEBUG) {
				Thread.sleep(DB_CREATIONDELAY);     // STOPSHIP
			}
		} catch (InterruptedException ignored) {
		}
	}
}
