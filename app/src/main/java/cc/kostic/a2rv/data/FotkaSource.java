package cc.kostic.a2rv.data;

import java.util.ArrayList;
import java.util.List;

public class FotkaSource {

	public FotkaSource() {
	}

	public List<Fotka> getData(int count){
		List<Fotka> fotke = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			Fotka f = new Fotka("naziv " + i, "cena " + i);
			fotke.add(f);
		}
		return fotke;
	}
}
