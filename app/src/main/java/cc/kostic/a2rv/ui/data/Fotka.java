package cc.kostic.a2rv.ui.data;

public class Fotka {
	private String naziv;
	private String cena;

	public Fotka(String naziv, String cena) {
		this.naziv = naziv;
		this.cena = cena;
	}

	public String getNaziv() {
		return naziv;
	}

	public String getCena() {
		return cena;
	}
}
