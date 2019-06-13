package cis657.project.hymnsingapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cis657.project.hymnsingapp.ShowPlaylistFragment.OnListFragmentInteractionListener;
import cis657.project.hymnsingapp.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ShowPlaylistAdapter extends RecyclerView.Adapter<ShowPlaylistAdapter.ViewHolder> {

    private final List<String> mValues;
    private final ShowPlaylistFragment.OnListFragmentInteractionListener mListener;

    public ShowPlaylistAdapter(List<String> items, ShowPlaylistFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        //System.out.println("\n\n\n\nMVALUES: "+mValues.size());
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_show_playlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.title.setText(mValues.get(position));

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
        if(mValues==null) return 0;
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        public final TextView title;
        public String mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            title = (TextView) view.findViewById(R.id.songTitle);
        }

        @Override
        public String toString() {
            return super.toString() + " '" +  "'";
        }
    }
}
