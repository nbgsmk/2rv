package cc.kostic.a2rv.ui.recycler_1_klot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cc.kostic.a2rv.databinding.Klot1FragmentBinding;


public class KlotFragment extends Fragment {

	private Klot1FragmentBinding binding;

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		binding = Klot1FragmentBinding.inflate(inflater, container, false);
		KlotViewModel model = new ViewModelProvider(this).get(KlotViewModel.class);

		LifecycleOwner vlo = getViewLifecycleOwner();
		model.getText().observe(vlo, new Observer<String>() {
			@Override
			public void onChanged(String s) {
				binding.tvNaslov.setText(s);
			}
		});

		RecyclerView rv = binding.rv;
		RisajklerAdapter adapter = new RisajklerAdapter(model.getFotke());
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
}
