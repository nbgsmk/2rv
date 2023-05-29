package cc.kostic.a2rv.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SlikaRepo {

	private SlikaDao slikaDao;
	private LiveData<List<Slika>> slike;

	public SlikaRepo(Application application) {
		AppDb appDb = AppDb.getInstance(application);
		slikaDao = appDb.slikaDao();
		slike = slikaDao.getAll();
	}


	public LiveData<List<Slika>> getSlike(){
		return slike;
	}

	public void insert(Slika slika){

	}
}
