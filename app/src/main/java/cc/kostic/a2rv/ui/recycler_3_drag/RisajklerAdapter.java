package cc.kostic.a2rv.ui.recycler_3_drag;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import cc.kostic.a2rv.R;


public class RisajklerAdapter extends RecyclerView.Adapter<RisajklerAdapter.FotkaHolder>
	implements ItemMoveCallback.ItemTouchHelperContract
{


	public interface Klik_listener {
		void onClickItem(View view, Fotka fotka, int position);
	}

	private final List<Fotka> lista;
	private Klik_listener klik_listener;

	public RisajklerAdapter(List<Fotka> fotke) {
		this.lista = fotke;
	}

	@NonNull
	@Override
	public FotkaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View v = inflater.inflate(R.layout.fotka, parent, false);
		FotkaHolder holder = new FotkaHolder(v);
		return holder;
	}

	@Override
	public void onBindViewHolder(@NonNull FotkaHolder holder, int position) {
		Fotka f = lista.get(position);
		holder.tv_naziv.setText(f.getNaziv());
		holder.tv_cena.setText(f.getCena());

	}

	@Override
	public int getItemCount() {
		return lista.size();
	}







	@Override
	public void onSwiped(int position, int swipeDirection) {
		lista.remove(position);
		notifyItemRemoved(position);
	}

	@Override
	public void onMove(int fromPosition, int toPosition) {
		if (fromPosition < toPosition) {
			for (int i = fromPosition; i < toPosition; i++) {
				Collections.swap(lista, i, i + 1);
			}
		} else {
			for (int i = fromPosition; i > toPosition; i--) {
				Collections.swap(lista, i, i - 1);
			}
		}
		notifyItemMoved(fromPosition, toPosition);
	}

	@Override
	public void onSelectedChanged(FotkaHolder myViewHolder) {
		myViewHolder.itemView.setBackgroundColor(Color.GRAY);

	}

	@Override
	public void onClearView(FotkaHolder myViewHolder) {
		myViewHolder.itemView.setBackgroundColor(Color.WHITE);

	}








	public class FotkaHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
		TextView tv_naziv;
		TextView tv_cena;

		public FotkaHolder(@NonNull View itemView) {
			super(itemView);
			tv_naziv = itemView.findViewById(R.id.tv_naziv);
			tv_cena = itemView.findViewById(R.id.tv_cena);

			// itemView.setOnClickListener(new View.OnClickListener() {
			// 	@Override
			// 	public void onClick(View view) {
			// 		int pos = getAdapterPosition();
			// 		Toast.makeText(itemView.getContext(), "klik pos " + pos, Toast.LENGTH_SHORT).show();
			// 	}
			// });

			itemView.setOnClickListener(this);

		}

		@Override
		public void onClick(View view) {
			if ( klik_listener != null){
				int position = getAdapterPosition();
				klik_listener.onClickItem(view, lista.get(position), position);
			}
		}

	}

	void setKlikIntf(Klik_listener listener){
		this.klik_listener = listener;
	}



}
