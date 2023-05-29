package cc.kostic.a2rv.ui.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Slika {
	@PrimaryKey
	public int uid;

	@ColumnInfo(name = "naziv")
	public String naziv;

	@ColumnInfo(name = "cena")
	public String cena;

	public Slika() {
		this.naziv = naziv;
		this.cena = cena;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getCena() {
		return cena;
	}

	public void setCena(String cena) {
		this.cena = cena;
	}
}
