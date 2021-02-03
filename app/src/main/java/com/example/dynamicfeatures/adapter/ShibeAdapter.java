package com.example.dynamicfeatures.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dynamicfeatures.databinding.ItemImageBinding;

import java.util.List;

/**
 * Step 1: Extend RecyclerView.Adapter<>
 * Step 2: Create custom ViewHolder class and extend RecyclerView.ViewHolder
 * Step 3: Create constructor matching super [use Alt + Enter] for our CustomViewHolder
 * Step 4: Pass custom ViewHolder into the RecyclerView.Adapter<PASS_IT_HERE>
 * Step 5: Implement the Adapter methods: onCreateViewHolder, onBindViewHolder, getItemCount [use Alt + Enter]
 * Step 6: Pass list to the Adapter using Constructor or any other way
 * Step 7: Pass the list size to getItemCount as the return value
 * Step 8: setup onCreateViewHolder by inflating the layout using LayoutInflater or ViewBinding
 * Ex: LayoutInflater.from(parent.getContext()).inflate(R.layout.my_layout_name, parent, false);
 * Ex: MyItemBinding.inflate(layoutInflater, parent, false);
 * Step 9: Finish onCreateViewHolder setup by passing the inflated view to the customViewHolder class and returning it
 * Step 10: Setup views in your custom view holder class
 * Step 11 Setup onBindViewHolder, retrieve the item from list using the provided position and
 * use it to load the ItemView
 */
public class ShibeAdapter extends RecyclerView.Adapter<ShibeAdapter.ShibeViewHolder> {
    private final List<String> urls;

    public ShibeAdapter(List<String> urls) {
        this.urls = urls;
    }

    @NonNull
    @Override
    public ShibeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemImageBinding binding = ItemImageBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ShibeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShibeViewHolder holder, int position) {
        String url = urls.get(position);
        holder.setUrl(url);
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    static class ShibeViewHolder extends RecyclerView.ViewHolder {

        private final ItemImageBinding binding;

        public ShibeViewHolder(@NonNull ItemImageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setUrl(String url) {
            binding.tvUrl.setText(url);
        }

    }
}