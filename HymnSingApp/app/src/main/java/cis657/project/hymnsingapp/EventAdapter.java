package cis657.project.hymnsingapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cis657.project.hymnsingapp.EventFragment.OnListFragmentInteractionListener;
//import cis657.project.hymnsingapp.dummy.EventContent.EventItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Event} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private final List<Event> mValues;
    private final OnListFragmentInteractionListener mListener;

    public EventAdapter(List<Event> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        //System.out.println("\n\n\n\nMVALUES: "+mValues.size());
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.title.setText("Title: " + mValues.get(position).title);
        holder.location.setText("Location: " + mValues.get(position).location);
        holder.mDateTime.setText("Date: " + mValues.get(position).date);
        holder.time.setText("Time: " + mValues.get(position).time);

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
        public final TextView location;
        public final TextView time;
        public final TextView mDateTime;
        public Event mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            title = (TextView) view.findViewById(R.id.title);
            location = (TextView) view.findViewById(R.id.location);
            time = (TextView) view.findViewById(R.id.time);
            mDateTime = (TextView) view.findViewById(R.id.timestamp);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDateTime.getText() + "'";
        }
    }
}
