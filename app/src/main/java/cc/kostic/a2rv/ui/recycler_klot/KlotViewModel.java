package cc.kostic.a2rv.ui.recycler_klot;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class KlotViewModel extends ViewModel {

	private final MutableLiveData<String> mText = new MutableLiveData<>();

	public KlotViewModel() {
		mText.setValue("Klot recycler \n onClick -> ViewHolder konstruktor");

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
