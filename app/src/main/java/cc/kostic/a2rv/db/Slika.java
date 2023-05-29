package cc.kostic.a2rv.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "slika_tabela")
public class Slika {
	// private int uid;

	@PrimaryKey
	@NonNull
	@ColumnInfo(name = "naziv")
	private String naziv;

	@NonNull
	@ColumnInfo(name = "cena")
	private String cena;

	public Slika(@NonNull String naziv, @NonNull String cena) {
		this.naziv = naziv;
		this.cena = cena;
	}

	@NonNull
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(@NonNull String naziv) {
		this.naziv = naziv;
	}

	@NonNull
	public String getCena() {
		return cena;
	}

	public void setCena(@NonNull String cena) {
		this.cena = cena;
	}
}
