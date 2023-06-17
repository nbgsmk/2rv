package cc.kostic.a2rv.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SlikaDao {
	@Query("SELECT * FROM slika_tabela ORDER BY naziv")
	LiveData<List<Slika>> getAll();

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insert(Slika slika);

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insert(List<Slika> slike);

	@Delete
	void delete(Slika slika);

	@Query("DELETE FROM slika_tabela")
	void deleteAll();
}
