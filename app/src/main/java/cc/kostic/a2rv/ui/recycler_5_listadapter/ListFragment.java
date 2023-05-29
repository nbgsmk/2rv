package cc.kostic.a2rv.ui.recycler_5_listadapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.Collections;
import java.util.List;

import cc.kostic.a2rv.databinding.Klot4DragHandlesBinding;
import cc.kostic.a2rv.data.Fotka;


public class ListFragment extends Fragment
		implements RisajklerAdapter.Klik_listener, DragHandlesListener {

	private Klot4DragHandlesBinding binding;
	ItemTouchHelper touchHelper;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		binding = Klot4DragHandlesBinding.inflate(inflater, container, false);

		RecyclerView rv = binding.rv;
		RisajklerAdapter adapter = new RisajklerAdapter();
		adapter.setKlikListener(this);
		adapter.setDragListener(this);
		// rv.setHasFixedSize(true);
		// rv.setItemAnimator(new DefaultItemAnimator());
		rv.setAdapter(adapter);

		int koji = 1;
		if (koji == 1) {
			GridLayoutManager glm = new GridLayoutManager(requireContext(), 3);
			rv.setLayoutManager(glm);
		} else if (koji == 2) {
			StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
			sglm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
			rv.setLayoutManager(sglm);
		}


		ListViewModel model = new ViewModelProvider(this).get(ListViewModel.class);

		LifecycleOwner vlo = getViewLifecycleOwner();
		model.getText().observe(vlo, new Observer<String>() {
			@Override
			public void onChanged(String s) {
				binding.tvNaslov.setText(s);
			}
		});
		model.getFotkeLive().observe(vlo, new Observer<List<Fotka>>() {
			@Override
			public void onChanged(List<Fotka> fotkas) {
				adapter.submitList(fotkas);
			}
		});


		ItemTouchHelper.Callback callback = new ItemMoveCallback(adapter, new ItemMoveCallback.ItemTouch_DragSwipe() {
			@Override
			public void onSwiped(int position, int swipeDirection) {
				model.deleteFotka(position);
				adapter.notifyItemRemoved(position);
			}

			@Override
			public void onMove(int fromPosition, int toPosition) {
				if (fromPosition < toPosition) {
					for (int i = fromPosition; i < toPosition; i++) {
						Collections.swap(model.getFotke(), i, i + 1);
					}
				} else {
					for (int i = fromPosition; i > toPosition; i--) {
						Collections.swap(model.getFotke(), i, i - 1);
					}
				}
				adapter.notifyItemMoved(fromPosition, toPosition);
			}
		});
		touchHelper = new ItemTouchHelper(callback);
		touchHelper.attachToRecyclerView(rv);

		return binding.getRoot();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}


	@Override
	public void onClickItem(View view, Fotka fotka, int position) {
		String msg = "fragment click: pos-" + position + ", item-" + fotka.getNaziv();
		Toast.makeText(view.getContext(), msg, Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onLongClickItem(View view, Fotka fotka, int position) {
		String msg = "fragment long click: pos-" + position + ", item-" + fotka.getNaziv();
		Toast.makeText(view.getContext(), msg, Toast.LENGTH_SHORT).show();
		return true;
	}


	// @Override
	// public void onRowMoved(int fromPosition, int toPosition) {
	//
	// }
	//
	// @Override
	// public void onRowSelected(RisajklerAdapter.FotkaHolder holder) {
	//
	// }
	//
	// @Override
	// public void onRowClear(RisajklerAdapter.FotkaHolder myViewHolder) {
	//
	// }


	@Override
	public void startDrag(RecyclerView.ViewHolder viewHolder) {
		touchHelper.startDrag(viewHolder);
	}


}
