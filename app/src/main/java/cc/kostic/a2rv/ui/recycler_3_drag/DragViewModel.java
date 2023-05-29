package cc.kostic.a2rv.ui.recycler_3_drag;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import cc.kostic.a2rv.data.Fotka;
import cc.kostic.a2rv.data.FotkaSource;


public class DragViewModel extends ViewModel {

	private final MutableLiveData<String> mText = new MutableLiveData<>();

	public DragViewModel() {
		mText.setValue("ItemTouchHelper \n drag by long click \n swipe to delete");
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
