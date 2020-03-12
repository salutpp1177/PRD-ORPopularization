package com.example.orgame.helper;

public interface OnItemTouchCallbackListener {

    /**
     * When an item is deleted by sliding
     * @param adapterPosition
     */
    void onSwiped(int adapterPosition);

    /**
     * Called back when two Item positions are swapped
     * @param srcPosition The position of the dragged item
     * @param targetPosition The position of the item
     * @return The operation should return true, otherwise it returns false
     */
    boolean onMove(int srcPosition, int targetPosition);


    /**
     * called back when the item's interactive animation ends
     */
    public void clearView();


}
