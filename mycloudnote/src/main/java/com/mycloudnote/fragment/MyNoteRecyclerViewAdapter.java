package com.mycloudnote.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mycloudnote.fragment.NoteListFragment.OnListFragmentInteractionListener;
import com.example.mycloudnote.R;
import com.mycloudnote.net.context.AppContext;
import com.mycloudnote.net.pojo.response.Note;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Note} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyNoteRecyclerViewAdapter extends RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder>
        implements SimpleItemTouchHelperCallback.onMoveAndSwipedListener {

    private ArrayList<Note> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Context context;
    private AlertDialog.Builder noteDeleteDialogBuilder;
    private int position;

    public MyNoteRecyclerViewAdapter(Context context, ArrayList<Note> items, OnListFragmentInteractionListener listener) {
        this.context = context;
        mValues = items;
        mListener = listener;
        initDialog();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getTitle());
        holder.mContentView.setText(mValues.get(position).getContent());
        holder.mCreateTimeView.setText(sdf.format(mValues.get(position).getCreateTime()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);


                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //交换mItems数据的位置
        Collections.swap(mValues,fromPosition,toPosition);
        //交换RecyclerView列表中item的位置
        notifyItemMoved(fromPosition,toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        this.position = position;
        noteDeleteDialogBuilder.create().show();
    }

    public void changeDataSet(ArrayList<Note> data){
        mValues = data;
    }
    private void initDialog(){
        noteDeleteDialogBuilder = new AlertDialog.Builder(context);
        noteDeleteDialogBuilder.setTitle("提示" );
        noteDeleteDialogBuilder.setMessage("确认删除该笔记吗？");
        noteDeleteDialogBuilder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Note note = new Note();
                note = mValues.get(position);
                //删除mItems数据
                mValues.remove(position);
                //删除RecyclerView列表对应item
                notifyItemRemoved(position);

                AppContext.action.requestDeleteNote(note);
                dialog.dismiss();
            }
        });
        noteDeleteDialogBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }
//    public static String getSpecifiedDayAfter(String specifiedDay){
//        Calendar c = Calendar.getInstance();
//        Date date=null;
//        try {
//            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        c.setTime(date);
//        int day=c.get(Calendar.DATE);
//        c.set(Calendar.DATE,day+1);
//        String dayAfter=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
//        return dayAfter;
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mCreateTimeView;
        public Note mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.id_tv_note_content);
            mCreateTimeView = (TextView)view.findViewById(R.id.id_tv_note_create_time);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
