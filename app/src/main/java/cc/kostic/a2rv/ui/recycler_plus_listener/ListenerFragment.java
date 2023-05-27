package cc.kostic.a2rv.ui.recycler_plus_listener;

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

import cc.kostic.a2rv.databinding.KlotPlusListenerBinding;

public class ListenerFragment extends Fragment implements cc.kostic.a2rv.ui.recycler_plus_listener.RisajklerAdapter.Klik_listener {

	private KlotPlusListenerBinding binding;

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		binding = KlotPlusListenerBinding.inflate(inflater, container, false);
		ListenerViewModel model = new ViewModelProvider(this).get(ListenerViewModel.class);

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
