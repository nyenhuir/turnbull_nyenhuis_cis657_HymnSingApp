package cis657.project.hymnsingapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cis657.project.hymnsingapp.MakePlaylistFragment.OnListFragmentInteractionListener;
import cis657.project.hymnsingapp.dummy.PlaylistContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MakePlaylistAdapter extends RecyclerView.Adapter<MakePlaylistAdapter.ViewHolder> {

    private final List<Song> mValues;
    private final MakePlaylistFragment.OnListFragmentInteractionListener mListener;

    public MakePlaylistAdapter(List<Song> items, MakePlaylistFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        //System.out.println("\n\n\n\nMVALUES: "+mValues.size());
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_make_playlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.title.setText(mValues.get(position).title.toUpperCase());

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
        public Song mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            title = (TextView) view.findViewById(R.id.songTitle2);
        }

        @Override
        public String toString() {
            return super.toString() + " '" +  "'";
        }
    }
}
