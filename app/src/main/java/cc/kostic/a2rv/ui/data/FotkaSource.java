package cc.kostic.a2rv.ui.data;

import java.util.ArrayList;
import java.util.List;

import cc.kostic.a2rv.ui.data.Fotka;

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
