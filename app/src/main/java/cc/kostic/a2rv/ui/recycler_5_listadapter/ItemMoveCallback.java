package cc.kostic.a2rv.ui.recycler_5_listadapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;


public class ItemMoveCallback extends ItemTouchHelper.Callback {
	public interface ItemTouch_Interface {
		void onSwiped(int position, int swipeDirection);
		void onMove(int fromPosition, int toPosition);
		void onSelectedChanged(RisajklerAdapter.FotkaHolder myViewHolder);
		void onClearView(RisajklerAdapter.FotkaHolder myViewHolder);
	}

	private final ItemTouch_Interface listener;

	public ItemMoveCallback(ItemTouch_Interface listener) {
		this.listener = listener;
	}

	@Override
	public boolean isLongPressDragEnabled() {
		return false;
	}

	@Override
	public boolean isItemViewSwipeEnabled() {
		return true;
	}


	@Override
	public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
		int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
		int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;

		// int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END;
		// int swipeFlags = 0;
		return makeMovementFlags(dragFlags, swipeFlags);
	}

	@Override
	public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
		// item deleted from adapter. system will call onClearView
		int position = viewHolder.getAdapterPosition();
		listener.onSwiped(position, i);
	}


	@Override
	public boolean onMove(@NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
		// drag and drop starting. after returning true, system will call onMoved
		listener.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
		return true;
	}

	@Override
	public void onMoved(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, int fromPos, @NonNull RecyclerView.ViewHolder target, int toPos, int x, int y) {
		super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
		// after onMove returns true
	}

	@Override
	public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
		super.onSelectedChanged(viewHolder, actionState);
		// item selected an drag or swipe is about to start
		if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
			if (viewHolder instanceof RisajklerAdapter.FotkaHolder) {
				RisajklerAdapter.FotkaHolder myViewHolder= (RisajklerAdapter.FotkaHolder) viewHolder;
				listener.onSelectedChanged(myViewHolder);
			}
		}

	}
	@Override
	public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
		super.clearView(recyclerView, viewHolder);
		// inteaction with element is done and animation is completed
		if (viewHolder instanceof RisajklerAdapter.FotkaHolder) {
			RisajklerAdapter.FotkaHolder myViewHolder= (RisajklerAdapter.FotkaHolder) viewHolder;
			listener.onClearView(myViewHolder);
		}
	}

}