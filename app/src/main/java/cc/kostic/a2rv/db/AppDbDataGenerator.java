package cc.kostic.a2rv.db;

import java.util.ArrayList;
import java.util.List;


public class AppDbDataGenerator {

	public static final int strt = 1;
	public static final int MAXUR = 44;     // kreiranih uredjaja u bazi
	public static final int MAXDUG = 4;    // dugmica u svakom uredjaju

	public static final int DB_CREATIONDELAY = 500; // mS


	public static List<Slika> getDummySlike(){
		List<Slika> lista = new ArrayList<>();
		for (int i = strt; i < MAXUR + 1; i++) {
			String ch = Character.toString((char) (i + 65));

			String n = "naz-" + i;
			String c  = "cen-" + i;
			Slika slika = new Slika(n, c);
			// slika.setNaziv("" + i + "" + n);
			// slika.setCena("111-" + i);
			lista.add(slika);
		}
		return lista;
	}


}
