package cc.kostic.a2rv.ui.recycler_6_with_room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import cc.kostic.a2rv.R;
import cc.kostic.a2rv.data.Fotka;
import cc.kostic.a2rv.db.Slika;


public class RisajklerAdapter extends ListAdapter<Slika, RisajklerAdapter.SlikaViewHolder> {

	public interface Klik_listener {
		void onClickItem(View view, Slika slika, int position);
		boolean onLongClickItem(View view, Slika slika, int position);
	}

	private Klik_listener klik_listener;
	private DragHandlesListener dragHandlesListener;

	protected RisajklerAdapter(@NonNull DiffUtil.ItemCallback<Slika> diffCallback) {
		super(diffCallback);
	}

	void setKlikListener(RoomFragment listener){
		this.klik_listener = listener;
	}
	// void setDragListener(RoomFragment listener){
	// 	this.dragHandlesListener = listener;
	// }


	@NonNull
	@Override
	public SlikaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fotka_drag_handles, parent, false);
		return new SlikaViewHolder(view);
	}



	@Override
	public void onBindViewHolder(@NonNull SlikaViewHolder holder, int position) {
		Slika current = getItem(position);
		holder.bind(current.getNaziv(), current.getCena());
	}




	static class WordDiff extends DiffUtil.ItemCallback<Slika>{

		@Override
		public boolean areItemsTheSame(@NonNull Slika oldItem, @NonNull Slika newItem) {
			return oldItem == newItem;
		}

		@Override
		public boolean areContentsTheSame(@NonNull Slika oldItem, @NonNull Slika newItem) {
			return oldItem.getNaziv().equals(newItem.getNaziv());
		}
	}







	public class SlikaViewHolder extends RecyclerView.ViewHolder {
		private final TextView tv_naziv;
		private final TextView tv_cena;
		ImageView iv_drag;

		public SlikaViewHolder(@NonNull View itemView) {
			super(itemView);
			tv_naziv = itemView.findViewById(R.id.tv_naziv);
			tv_cena = itemView.findViewById(R.id.tv_cena);
			iv_drag = itemView.findViewById(R.id.iv_drag);

			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if ( klik_listener != null){
						int position = getAdapterPosition();
						klik_listener.onClickItem(view, getItem(position), position);
					}
				}
			});

			itemView.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View view) {
					if ( klik_listener != null){
						int position = getAdapterPosition();
						klik_listener.onLongClickItem(view, getItem(position), position);
					}
					return true;
				}
			});
		}


		public void bind(String naziv, String cena){
			tv_naziv.setText(naziv);
			tv_cena.setText(cena);
		}


		// SlikaViewHolder create(ViewGroup parent){
		// 	View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fotka_drag_handles, parent, false);
		// 	return new SlikaViewHolder(view);
		// }
	}






}
