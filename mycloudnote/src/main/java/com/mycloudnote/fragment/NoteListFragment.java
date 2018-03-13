package com.mycloudnote.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mycloudnote.R;

import static com.mycloudnote.net.context.MsgConstant.*;
import com.mycloudnote.net.context.AppContext;
import com.mycloudnote.net.pojo.response.Note;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class NoteListFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;


    private OnListFragmentInteractionListener mListener;
    private MyNoteRecyclerViewAdapter myNoteRecyclerViewAdapter;
    private ItemTouchHelper mItemTouchHelper;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NoteListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NoteListFragment newInstance(int columnCount) {
        NoteListFragment fragment = new NoteListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }
    private Handler notelistHandler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
//			dialog.dismiss();

            switch (msg.what)
            {
                case CREATENOTE_SUCCESS:
                    Log.i("Fragement","fragHandler");
                    myNoteRecyclerViewAdapter.changeDataSet(AppContext.noteList);
                    myNoteRecyclerViewAdapter.notifyDataSetChanged();
//                    myNoteRecyclerViewAdapter.notifyItemChanged(AppContext.noteList.size()-1,);
//                    myNoteRecyclerViewAdapter.notifyItemInserted(AppContext.noteList.size()-1);
                    break;

                case CREATENOTE_FAILURE:

                    break;
                case EDITNOTE_SUCCESS:
                    myNoteRecyclerViewAdapter.changeDataSet(AppContext.noteList);
                    myNoteRecyclerViewAdapter.notifyDataSetChanged();
                    break;
                case EDITNOTE_FAILURE:

                    break;

                default:

                    break;
            }
        };
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        AppContext.notelistHandler = notelistHandler;
    }

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_note_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
//            recyclerView.setAdapter(new MyNoteRecyclerViewAdapter(DummyContent.ITEMS, mListener));
            myNoteRecyclerViewAdapter = new MyNoteRecyclerViewAdapter(getContext(),AppContext.noteList, mListener);
            recyclerView.setAdapter(myNoteRecyclerViewAdapter);


            recyclerView.addItemDecoration(new SimplePaddingDecoration());

            //关联ItemTouchHelper和RecyclerView
            ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(myNoteRecyclerViewAdapter);
            mItemTouchHelper = new ItemTouchHelper(callback);
            mItemTouchHelper.attachToRecyclerView(recyclerView);
            Log.i("Fragement","oncreateView");
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Note item);
    }

    public class SimplePaddingDecoration extends RecyclerView.ItemDecoration {

        private int dividerHeight;

        public SimplePaddingDecoration() {
            dividerHeight = 1;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.bottom = dividerHeight;//类似加了一个bottom padding
        }

    }
}
