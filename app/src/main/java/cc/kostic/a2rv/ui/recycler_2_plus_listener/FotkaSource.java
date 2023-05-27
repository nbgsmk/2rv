package cc.kostic.a2rv.ui.recycler_2_plus_listener;

import java.util.ArrayList;
import java.util.List;

import cc.kostic.a2rv.ui.recycler_1_klot.Fotka;

public class FotkaSource {

	public FotkaSource() {
	}

	public List<cc.kostic.a2rv.ui.recycler_1_klot.Fotka> getData(int count){
		List<cc.kostic.a2rv.ui.recycler_1_klot.Fotka> fotke = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			cc.kostic.a2rv.ui.recycler_1_klot.Fotka f = new Fotka("naziv " + Character.toString((char) (i + 65)), "cena " + i);
			fotke.add(f);
		}
		return fotke;
	}
}
