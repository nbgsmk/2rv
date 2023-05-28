package cc.kostic.a2rv.ui.recycler_4_drag_handles;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import cc.kostic.a2rv.R;
import cc.kostic.a2rv.ui.data.Fotka;

public class RisajklerAdapter extends RecyclerView.Adapter<RisajklerAdapter.FotkaHolder>
		implements ItemMoveCallback.ItemTouchHelperContract
{


	public interface Klik_listener {
		void onClickItem(View view, Fotka fotka, int position);
	}

	private final List<Fotka> lista;
	private RisajklerAdapter.Klik_listener klik_listener;
	private final StartDragListener startDragListener;
	public RisajklerAdapter(List<Fotka> fotke, StartDragListener startDragListener) {
		this.lista = fotke;
		this.startDragListener = startDragListener;
	}

	@NonNull
	@Override
	public RisajklerAdapter.FotkaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View v = inflater.inflate(R.layout.fotka_drag_handles, parent, false);
		RisajklerAdapter.FotkaHolder holder = new RisajklerAdapter.FotkaHolder(v);
		return holder;
	}

	@Override
	public void onBindViewHolder(@NonNull RisajklerAdapter.FotkaHolder holder, int position) {
		Fotka f = lista.get(position);
		holder.tv_naziv.setText(f.getNaziv());
		holder.tv_cena.setText(f.getCena());

		holder.iv_drag.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
					startDragListener.requestDrag(holder);
				}
				return false;
			}
		});
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
	public void onSelectedChanged(RisajklerAdapter.FotkaHolder myViewHolder) {
		myViewHolder.itemView.setBackgroundColor(Color.GRAY);

	}

	@Override
	public void onClearView(RisajklerAdapter.FotkaHolder myViewHolder) {
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

			// itemView.setOnClickListener(new View.OnClickListener() {
			// 	@Override
			// 	public void onClick(View view) {
			// 		int pos = getAdapterPosition();
			// 		Toast.makeText(itemView.getContext(), "klik pos " + pos, Toast.LENGTH_SHORT).show();
			// 	}
			// });

			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if ( klik_listener != null){
						int position = getAdapterPosition();
						klik_listener.onClickItem(view, lista.get(position), position);
					}
				}
			});



		}

		// @Override
		// public void onClick(View view) {
		// 	if ( klik_listener != null){
		// 		int position = getAdapterPosition();
		// 		klik_listener.onClickItem(view, lista.get(position), position);
		// 	}
		// }

	}

	void setKlikIntf(RisajklerAdapter.Klik_listener listener){
		this.klik_listener = listener;
	}



}
