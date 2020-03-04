package com.example.orgame.helper;

import android.graphics.Canvas;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PizzaItemTouchHelperCallback extends ItemTouchHelper.Callback {

    public PizzaItemTouchHelperCallback() {
        super();
    }

    /**
     * Item operation callback to update UI and data source
     */
    private OnItemTouchCallbackListener onItemTouchCallbackListener;
    /**
     * Whether it can be dragged
     */
    private boolean isCanDrag = false;
    /**
     * Whether it can be swiped
     */
    private boolean isCanSwipe = false;

    public PizzaItemTouchHelperCallback(OnItemTouchCallbackListener onItemTouchCallbackListener) {
        this.onItemTouchCallbackListener = onItemTouchCallbackListener;
    }


    /**
     * Set whether it can be dragged
     *
     * @param canDrag
     */
    public void setDragEnable(boolean canDrag) {
        isCanDrag = canDrag;
    }

    /**
     * Set whether it can be swiped
     *
     * @param canSwipe is true, false
     */
    public void setSwipeEnable(boolean canSwipe) {
        isCanSwipe = canSwipe;
    }

    /**
     * Whether to enable item long-press dragging
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return isCanDrag;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return isCanSwipe;
    }

    /**
     * Get action ID
     * Action flag points: dragFlags and swipeFlags
     * dragFlags: the action flags of the list scrolling direction (such as vertical lists are up and down, horizontal lists are left and right)
     * wipeFlags: action flags that are vertical to the scrolling direction of the list (such as vertical lists are left and right, horizontal lists are up and down)
     */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            int dragFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        }
        return 0;
    }

    /**
     * Called when the item is dragged
     * @param recyclerView
     * @param viewHolder ViewHolder of the item being dragged
     * @param target ViewHolder of another item below the currently dragged item
     * @return Whether to be moved
     */
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        if (onItemTouchCallbackListener != null) {
            int srcPosition = viewHolder.getAdapterPosition();
            int targetPosition = target.getAdapterPosition();
            return onItemTouchCallbackListener.onMove(srcPosition, targetPosition);
        }

        return false;
    }

    /**
     * Triggered when the item slides out (vertical list is sideways, horizontal list is vertical)
     * @param viewHolder
     * @param direction Direction of sliding
     */
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        if (onItemTouchCallbackListener != null) {
            onItemTouchCallbackListener.onSwiped(viewHolder.getAdapterPosition());
        }
    }

    /**
     * Fired when the item's interactive animation ends
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

//    @Override
//    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
//                            @NonNull RecyclerView.ViewHolder viewHolder,
//                            float dX, float dY, int actionState, boolean isCurrentlyActive) {
//        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
//            float value = 1 - Math.abs(dX) / viewHolder.itemView.getWidth();
//            viewHolder.itemView.setAlpha(value);
//            viewHolder.itemView.setScaleY(value);
//        }
//    }



}
