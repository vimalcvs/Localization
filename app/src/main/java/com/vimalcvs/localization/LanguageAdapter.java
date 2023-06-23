package com.vimalcvs.localization;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vimalcvs.localization.databinding.ItemLanguageBinding;

import java.util.List;

@SuppressLint("NotifyDataSetChanged")
public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder> {

    private final List<LangModel> mLanguageOptions;
    private int mSelectedPosition = -1;
    private OnItemClickListener mOnItemClickListener;

    public LanguageAdapter(List<LangModel> mLanguageOptions) {
        this.mLanguageOptions = mLanguageOptions;
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    @NonNull
    @Override
    public LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LanguageViewHolder(ItemLanguageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageViewHolder holder, int position) {
        LangModel langModel = mLanguageOptions.get(position);
        holder.binding.tvName.setText(langModel.name);

        holder.binding.cbCheck.setChecked(position == mSelectedPosition);
        holder.binding.cvCard.setOnClickListener(v -> {
            if (position != mSelectedPosition) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(langModel, position);
                }
                setSelectedPosition(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLanguageOptions.size();
    }

    public int getSelectedPosition() {
        return mSelectedPosition;
    }

    public void setSelectedPosition(int position) {
        mSelectedPosition = position;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(LangModel langModel, int position);
    }

    public static class LanguageViewHolder extends RecyclerView.ViewHolder {
        private final ItemLanguageBinding binding;

        public LanguageViewHolder(ItemLanguageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
