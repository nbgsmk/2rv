package cc.kostic.a2rv.ui.data;

public class Fotka {
	private static int ID;

	private int id;
	private String naziv;
	private String cena;

	public Fotka(String naziv, String cena) {
		this.id = ID++;
		this.naziv = naziv;
		this.cena = cena;
	}

	public int getID() {
		return this.id;
	}

	public String getNaziv() {
		return naziv;
	}

	public String getCena() {
		return cena;
	}
}
