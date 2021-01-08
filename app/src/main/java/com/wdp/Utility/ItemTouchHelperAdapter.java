package com.wdp.Utility;

public interface ItemTouchHelperAdapter  {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}