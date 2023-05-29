package cc.kostic.a2rv.ui.recycler_4_drag_handles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import cc.kostic.a2rv.ui.data.Fotka;
import cc.kostic.a2rv.ui.data.FotkaSource;

public class DragHandlesViewModel extends ViewModel {

	private final MutableLiveData<String> mText = new MutableLiveData<>();

	public DragHandlesViewModel() {
		mText.setValue("Recycler ItemTouchHelper \n drag with HANDLES");
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
