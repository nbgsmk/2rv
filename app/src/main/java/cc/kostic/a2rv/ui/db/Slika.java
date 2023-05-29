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

}
