package cc.kostic.a2rv.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import cc.kostic.a2rv.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

	private FragmentHomeBinding binding;
	ActivityResultLauncher<Intent> someActivityResultLauncher;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		someActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
			@Override
			public void onActivityResult(ActivityResult result) {
				if (result.getResultCode() == Activity.RESULT_OK) {
					// There are no request codes
					Intent data = result.getData();
					Uri uri = data.getData();
					binding.iv2.setImageURI(uri);
					Log.d("TAG", "onActivityResult: " + uri);
				}
			}
		});

	}

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

		binding = FragmentHomeBinding.inflate(inflater, container, false);
		View root = binding.getRoot();

		final TextView textView = binding.textHome;
		homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

		binding.button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//Create Intent
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.setType("*/*");
				// intent.setType("image/jpg");
				intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
				//Launch activity to get result
				someActivityResultLauncher.launch(intent);
			}
		});
		return root;
	}



	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}
}
