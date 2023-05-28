package cc.kostic.a2rv.ui.recycler_4_drag_handles;

import androidx.recyclerview.widget.RecyclerView;

public interface StartDragListener {
	void requestDrag(RecyclerView.ViewHolder viewHolder);
}
