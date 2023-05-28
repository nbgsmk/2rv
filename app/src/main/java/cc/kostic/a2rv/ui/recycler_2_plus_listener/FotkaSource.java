package cc.kostic.a2rv.ui.recycler_2_plus_listener;

import java.util.ArrayList;
import java.util.List;


public class FotkaSource {

	public FotkaSource() {
	}

	public List<Fotka> getData(int count){
		List<Fotka> fotke = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			Fotka f = new Fotka("naziv " + Character.toString((char) (i + 65)), "cena " + i);
			fotke.add(f);
		}
		return fotke;
	}
}
