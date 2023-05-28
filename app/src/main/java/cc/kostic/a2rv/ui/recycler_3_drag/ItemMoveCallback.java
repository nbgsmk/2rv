package cc.kostic.a2rv.ui.recycler_3_drag;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class ItemMoveCallback extends ItemTouchHelper.Callback {
	private final ItemTouchHelperContract mAdapter;

	public ItemMoveCallback(ItemTouchHelperContract adapter) {
		mAdapter = adapter;
	}

	@Override
	public boolean isLongPressDragEnabled() {
		return true;
	}

	@Override
	public boolean isItemViewSwipeEnabled() {
		return true;
	}



	@Override
	public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

	}

	@Override
	public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
		int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
		int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
		return makeMovementFlags(dragFlags, swipeFlags);
	}

	@Override
	public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
		mAdapter.onRowMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
		return true;
	}

	@Override
	public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {

		if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
			if (viewHolder instanceof RisajklerAdapter.FotkaHolder) {
				RisajklerAdapter.FotkaHolder myViewHolder= (RisajklerAdapter.FotkaHolder) viewHolder;
				mAdapter.onRowSelected(myViewHolder);
			}

		}

		super.onSelectedChanged(viewHolder, actionState);
	}
	@Override
	public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
		super.clearView(recyclerView, viewHolder);

		if (viewHolder instanceof RisajklerAdapter.FotkaHolder) {
			RisajklerAdapter.FotkaHolder myViewHolder= (RisajklerAdapter.FotkaHolder) viewHolder;
			mAdapter.onRowClear(myViewHolder);
		}
	}

	public interface ItemTouchHelperContract {

		void onRowMoved(int fromPosition, int toPosition);
		void onRowSelected(RisajklerAdapter.FotkaHolder myViewHolder);
		void onRowClear(RisajklerAdapter.FotkaHolder myViewHolder);

	}

}
