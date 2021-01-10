package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Responsible for displaying data from the model into a row into a row in recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface OnClickListerner {
        void onItemClicked(int position);
    }


    public interface OnLongClickListener {
        void OnItemLongCLicked(int position);
    }


    List<String> items;
    OnLongClickListener longClickListener;
    OnClickListerner clickListerner;

    public ItemsAdapter(List<String> items,OnLongClickListener longClickListener,OnClickListerner clickListerner ) {
        this.items = items;
        this.longClickListener = longClickListener;
        this.clickListerner =  clickListerner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // Use layout inflator to inflate a view

        View todoView =  LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent,false);
        // Wrap it inside a view holder and return it

        return new ViewHolder(todoView);
    }
// Responsible for binding data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    // Grab the item at the position
    String item = items.get(position);
    // Bind the item into the specified view holder
        holder.bind(item);


    }
    // Tells the RV how many items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Container to provide easy access to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvItems;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItems = itemView.findViewById(android.R.id.text1);
        }

        // Update the view inside of the view holder with this data
        public void bind(String item) {
            tvItems.setText(item);
            tvItems.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListerner.onItemClicked(getAdapterPosition() );
                }
            });
            tvItems .setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // Notify the listener which position was long pressed
                    longClickListener.OnItemLongCLicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }

}
