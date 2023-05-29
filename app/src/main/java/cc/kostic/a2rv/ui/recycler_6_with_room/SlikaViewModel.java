package cc.kostic.a2rv.ui.recycler_6_with_room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import cc.kostic.a2rv.db.Slika;
import cc.kostic.a2rv.db.SlikaRepo;

public class SlikaViewModel extends AndroidViewModel {

	private final MutableLiveData<String> mText = new MutableLiveData<>();
	private SlikaRepo repo;
	private final LiveData<List<Slika>> slike;

	public SlikaViewModel(@NonNull Application application) {
		super(application);
		mText.setValue("Room with DiffUtil");
		repo = new SlikaRepo(application);
		slike = repo.getSlike();
	}

	public LiveData<String> getText() {
		return mText;
	}


	LiveData<List<Slika>> getSlike(){
		return slike;
	}

	void insert(Slika slika){
		repo.insert(slika);
	}
}
