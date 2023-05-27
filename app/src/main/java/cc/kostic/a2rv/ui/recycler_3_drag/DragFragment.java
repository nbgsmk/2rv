package cc.kostic.a2rv.ui.recycler_3_drag;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cc.kostic.a2rv.databinding.Klot3DragSwipeBinding;

public class DragFragment extends Fragment implements RisajklerAdapter.Klik_listener {

	private Klot3DragSwipeBinding binding;

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		binding = Klot3DragSwipeBinding.inflate(inflater, container, false);
		DragViewModel model = new ViewModelProvider(this).get(DragViewModel.class);

		LifecycleOwner vlo = getViewLifecycleOwner();
		model.getText().observe(vlo, new Observer<String>() {
			@Override
			public void onChanged(String s) {
				binding.tvNaslov.setText(s);
			}
		});

		RecyclerView rv = binding.rv;
		RisajklerAdapter adapter = new RisajklerAdapter(model.getFotke());
		adapter.setKlikIntf(this);
		rv.setLayoutManager(new LinearLayoutManager(requireContext()));
		rv.setHasFixedSize(true);
		rv.setAdapter(adapter);

		return binding.getRoot();

	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}


	@Override
	public void onItemClick(View view, int position) {
		Toast.makeText(view.getContext(), "fragment: klik pos " + position, Toast.LENGTH_SHORT).show();
	}
}
