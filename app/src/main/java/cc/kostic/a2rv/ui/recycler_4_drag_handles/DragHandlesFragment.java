package cc.kostic.a2rv.ui.recycler_4_drag_handles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import cc.kostic.a2rv.databinding.Klot4DragHandlesBinding;
import cc.kostic.a2rv.ui.data.Fotka;


public class DragHandlesFragment extends Fragment
		implements
		RisajklerAdapter.Klik_listener
	,
		StartDragListener

{

	private Klot4DragHandlesBinding binding;
	ItemTouchHelper helper;


	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		binding = Klot4DragHandlesBinding.inflate(inflater, container, false);
		DragHandlesViewModel model = new ViewModelProvider(this).get(DragHandlesViewModel.class);

		LifecycleOwner vlo = getViewLifecycleOwner();
		model.getText().observe(vlo, new Observer<String>() {
			@Override
			public void onChanged(String s) {
				binding.tvNaslov.setText(s);
			}
		});

		RecyclerView rv = binding.rv;
		RisajklerAdapter adapter = new RisajklerAdapter(model.getFotke(), this);
		adapter.setHasStableIds(true);
		adapter.setKlikIntf(this);

		int koji = 1;
		if (koji==1) {
			GridLayoutManager glm = new GridLayoutManager(requireContext(), 2);
			rv.setLayoutManager(glm);
		} else if (koji==2) {
			StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
			sglm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
			rv.setLayoutManager(sglm);
		}

		rv.setHasFixedSize(true);
		rv.setItemAnimator(new DefaultItemAnimator());

		ItemTouchHelper.Callback callback = new ItemMoveCallback(adapter);
		helper = new ItemTouchHelper(callback);
		helper.attachToRecyclerView(rv);
		rv.setAdapter(adapter);

		return binding.getRoot();

	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}


	@Override
	public void onClickItem(View view, Fotka fotka, int position) {
		String msg = "fragmen click: pos-" + position + ", item-" + fotka.getNaziv();
		Toast.makeText(view.getContext(), msg, Toast.LENGTH_SHORT).show();

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
	public void requestDrag(RecyclerView.ViewHolder viewHolder) {
		helper.startDrag(viewHolder);
	}
}