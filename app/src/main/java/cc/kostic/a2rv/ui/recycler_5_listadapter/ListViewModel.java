package cc.kostic.a2rv.ui.recycler_5_listadapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import cc.kostic.a2rv.data.Fotka;
import cc.kostic.a2rv.data.FotkaSource;

public class ListViewModel extends ViewModel {

	private final MutableLiveData<String> mText = new MutableLiveData<>();
	private List<Fotka> fotke = new ArrayList<>();
	private final MutableLiveData<List<Fotka>> fotkeLive = new MutableLiveData<>();

	public ListViewModel() {
		mText.setValue("Recycler with DiffUtil");
		FotkaSource source = new FotkaSource();
		fotke = source.getData(200);
	}

	public LiveData<String> getText() {
		return mText;
	}

	public List<Fotka> getFotke() {
		return fotke;
	}

	public void deleteFotka(int position) {
		fotke.remove(position);
		fotkeLive.postValue(fotke);
	}

	public MutableLiveData<List<Fotka>> getFotkeLive() {
		fotkeLive.postValue(getFotke());
		return fotkeLive;
	}
}
