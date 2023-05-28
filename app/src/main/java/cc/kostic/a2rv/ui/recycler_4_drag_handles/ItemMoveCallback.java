package cc.kostic.a2rv.ui.recycler_4_drag_handles;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;


public class ItemMoveCallback extends ItemTouchHelper.Callback {
	private final ItemMoveCallback.ItemTouchHelperContract adapter;

	public ItemMoveCallback(ItemMoveCallback.ItemTouchHelperContract adapter) {
		this.adapter = adapter;
	}

	@Override
	public boolean isLongPressDragEnabled() {
		return false;
	}

	@Override
	public boolean isItemViewSwipeEnabled() {
		return false;
	}



	@Override
	public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
		// int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
		// int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;

		int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END;
		int swipeFlags = 0;
		return makeMovementFlags(dragFlags, swipeFlags);
	}

	@Override
	public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
		// item deleted from adapter. system will call onClearView
		int position = viewHolder.getAdapterPosition();
		adapter.onSwiped(position, i);
	}


	@Override
	public boolean onMove(@NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
		// drag and drop starting. after returning true, system will call onMoved
		adapter.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
		return true;
	}


	@Override
	public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
		super.onSelectedChanged(viewHolder, actionState);
		// item selected an drag or swipe is about to start
		if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
			if (viewHolder instanceof RisajklerAdapter.FotkaHolder) {
				RisajklerAdapter.FotkaHolder myViewHolder= (RisajklerAdapter.FotkaHolder) viewHolder;
				adapter.onSelectedChanged(myViewHolder);
			}

		}

	}
	@Override
	public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
		super.clearView(recyclerView, viewHolder);
		// inteaction with element is done and animation is completed
		if (viewHolder instanceof RisajklerAdapter.FotkaHolder) {
			RisajklerAdapter.FotkaHolder myViewHolder= (RisajklerAdapter.FotkaHolder) viewHolder;
			adapter.onClearView(myViewHolder);
		}
	}

	public interface ItemTouchHelperContract {
		void onSwiped(int position, int swipeDirection);
		void onMove(int fromPosition, int toPosition);
		void onSelectedChanged(RisajklerAdapter.FotkaHolder myViewHolder);
		void onClearView(RisajklerAdapter.FotkaHolder myViewHolder);

		// drag by handle instead od long click
		// void requestDrag(RecyclerView.ViewHolder viewHolder);

	}

}