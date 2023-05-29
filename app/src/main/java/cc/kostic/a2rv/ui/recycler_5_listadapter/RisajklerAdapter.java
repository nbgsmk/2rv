package cc.kostic.a2rv.ui.recycler_5_listadapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

public class RisajklerAdapter extends ListAdapter<Fotka, RisajklerAdapter.FotkaHolder>
		implements ItemMoveCallback.ItemTouch_Interface {

	public interface Klik_listener {
		void onClickItem(View view, Fotka fotka, int position);
		boolean onLongClickItem(View view, Fotka fotka, int position);
	}

	// private final List<Fotka> lista;
	private Klik_listener klik_listener;
	private DragHandlesListener dragHandlesListener;

	public RisajklerAdapter(){
		super(RisajklerAdapter.DIFF_CALLBACK);
	}

	void setKlikListener(ListFragment listener){
		this.klik_listener = listener;
	}
	void setDragListener(ListFragment listener){
		this.dragHandlesListener = listener;
	}

	@NonNull
	@Override
	public FotkaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View v = inflater.inflate(R.layout.fotka_drag_handles, parent, false);
		FotkaHolder holder = new FotkaHolder(v);
		return holder;
	}

	@Override
	public void onBindViewHolder(@NonNull FotkaHolder holder, int position) {
		Fotka f = getItem(position);
		holder.tv_naziv.setText(f.getNaziv());
		holder.tv_cena.setText(f.getCena());

		holder.iv_drag.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
					dragHandlesListener.startDrag(holder);
				}
				return false;
			}
		});
	}





	// @Override
	// public void onSwiped(int position, int swipeDirection) {
	// 	// getCurrentList().remove(position);
	// 	notifyItemRemoved(position);
	// }

	@Override
	public void onMove(int fromPosition, int toPosition) {
		// if (fromPosition < toPosition) {
		// 	for (int i = fromPosition; i < toPosition; i++) {
		// 		Collections.swap(getCurrentList(), i, i + 1);
		// 	}
		// } else {
		// 	for (int i = fromPosition; i > toPosition; i--) {
		// 		Collections.swap(getCurrentList(), i, i - 1);
		// 	}
		// }
		// notifyItemMoved(fromPosition, toPosition);
	}

	@Override
	public void onSelectedChanged(FotkaHolder myViewHolder) {
		myViewHolder.itemView.setBackgroundColor(Color.GRAY);

	}

	@Override
	public void onClearView(FotkaHolder myViewHolder) {
		myViewHolder.itemView.setBackgroundColor(Color.WHITE);

	}



	public class FotkaHolder extends RecyclerView.ViewHolder {
		TextView tv_naziv;
		TextView tv_cena;
		ImageView iv_drag;

		public FotkaHolder(@NonNull View itemView) {
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

	}



	public static final DiffUtil.ItemCallback<Fotka> DIFF_CALLBACK = new DiffUtil.ItemCallback<Fotka>() {
		@Override
		public boolean areItemsTheSame(@NonNull Fotka oldFotka, @NonNull Fotka newFotka) {
			// User properties may have changed if reloaded from the DB, but ID is fixed
			return oldFotka.getID() == newFotka.getID();
		}



		@Override
		public boolean areContentsTheSame(@NonNull Fotka oldFotka, @NonNull Fotka newFotka) {
			// NOTE: if you use equals, your object must properly override Object#equals()
			// Incorrectly returning false here will result in too many animations.
			if (oldFotka.getNaziv().equalsIgnoreCase(newFotka.getNaziv())
				&& oldFotka.getCena().equalsIgnoreCase(newFotka.getCena())) {
				return true;
			} else {
				return false;
			}
		}


	};

}
