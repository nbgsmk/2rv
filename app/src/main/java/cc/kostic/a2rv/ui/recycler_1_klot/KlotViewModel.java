package cc.kostic.a2rv.ui.recycler_1_klot;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import cc.kostic.a2rv.ui.data.Fotka;
import cc.kostic.a2rv.ui.data.FotkaSource;

public class KlotViewModel extends ViewModel {

	private final MutableLiveData<String> mText = new MutableLiveData<>();

	public KlotViewModel() {
		mText.setValue("Klot recycler \n onClick -> adapter");

	}

	public LiveData<String> getText() {
		return mText;
	}

	public List<Fotka> getFotke() {
		FotkaSource source = new FotkaSource();
		List<Fotka> fotke = source.getData(200);
		return fotke;
	}
}
