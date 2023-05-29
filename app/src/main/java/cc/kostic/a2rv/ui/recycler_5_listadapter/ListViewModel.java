package cc.kostic.a2rv.ui.recycler_5_listadapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.nio.file.attribute.FileTime;
import java.util.List;

import cc.kostic.a2rv.ui.data.Fotka;
import cc.kostic.a2rv.ui.data.FotkaSource;

public class ListViewModel extends ViewModel {

	private final MutableLiveData<String> mText = new MutableLiveData<>();
	private final MutableLiveData<List<Fotka>> fotkeLive = new MutableLiveData<>();

	public ListViewModel() {
		mText.setValue("Recycler with DiffUtil");
	}

	public LiveData<String> getText() {
		return mText;
	}

	public List<Fotka> getFotke() {
		FotkaSource source = new FotkaSource();
		List<Fotka> fotke = source.getData(200);
		return fotke;
	}

	public MutableLiveData<List<Fotka>> getFotkeLive() {
		fotkeLive.postValue(getFotke());
		return fotkeLive;
	}
}
