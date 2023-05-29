package cc.kostic.a2rv.ui.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SlikaDao {
	@Query("SELECT * FROM slika")
	List<Slika> getAll();

	@Query("SELECT * FROM slika WHERE naziv IN (:nazivi)")
	List<Slika> loadAllByNaziv(int[] nazivi);

	@Query("SELECT * FROM slika WHERE naziv LIKE :first")
	Slika findByName(String first);

	@Insert
	void insertAll(Slika... slike);

	@Insert
	void insert(List<Slika> slike);

	@Delete
	void delete(Slika slika);

}
