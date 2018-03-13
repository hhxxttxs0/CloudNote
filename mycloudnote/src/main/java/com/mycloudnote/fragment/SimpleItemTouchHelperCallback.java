package com.mycloudnote.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class SimpleItemTouchHelperCallback extends  ItemTouchHelper.Callback {

    private onMoveAndSwipedListener mAdapter;
    public SimpleItemTouchHelperCallback(onMoveAndSwipedListener listener){
        mAdapter = listener;
    }
    /**这个方法是用来设置我们拖动的方向以及侧滑的方向的*/
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //如果是ListView样式的RecyclerView
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager){
            //设置拖拽方向为上下
            final int dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN;
            //设置侧滑方向为从左到右和从右到左都可以
            final int swipeFlags = ItemTouchHelper.START|ItemTouchHelper.END;
            //将方向参数设置进去
            return makeMovementFlags(dragFlags,swipeFlags);
        }else{//如果是GridView样式的RecyclerView
            //设置拖拽方向为上下左右
            final int dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN|
                    ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
            //不支持侧滑
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags,swipeFlags);
        }
    }
    /**当我们拖动item时会回调此方法*/
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//
//        //如果两个item不是一个类型的，我们让他不可以拖拽
//        if (viewHolder.getItemViewType() != target.getItemViewType()){
//            return false;
//        }
//        //回调adapter中的onItemMove方法
//        mAdapter.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return true;
    }
    /**当我们侧滑item时会回调此方法*/
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //回调adapter中的onItemDismiss方法
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }


    public interface onMoveAndSwipedListener {
        boolean onItemMove(int fromPosition , int toPosition);
        void onItemDismiss(int position);
    }
}
