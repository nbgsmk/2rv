package cc.kostic.a2rv.ui.recycler_2_plus_listener;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import cc.kostic.a2rv.ui.data.Fotka;
import cc.kostic.a2rv.ui.data.FotkaSource;

public class ListenerViewModel extends ViewModel {

	private final MutableLiveData<String> mText = new MutableLiveData<>();

	public ListenerViewModel() {
		mText.setValue("Recycler \n onClick interface -> parent Fragment");
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
