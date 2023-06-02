package com.example.tinyhouse.ui.Review;

import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tinyhouse.R;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    SQLiteDatabase db;
    List<Review> reviewList;

    public ReviewAdapter(List<Review> review, SQLiteDatabase _db) {

        reviewList= review;
        db = _db;


    }

    @NonNull
    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View cleanerItems = inflater.inflate(R.layout.review_item, parent, false);
        ReviewAdapter.ViewHolder holder = new ReviewAdapter.ViewHolder(cleanerItems);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Review review = reviewList.get(position);
        holder.txtReviewFromName.setText("- "+review.getUserName());
        holder.edtNamefor.setText(review.getUserNamefor());
        holder.txtReviewFromReview.setText(review.getReview());

    }


    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView edtNamefor, txtReviewFromName, txtReviewFromReview;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            txtReviewFromName = itemView.findViewById(R.id.txtReviewFromName);
            edtNamefor = itemView.findViewById(R.id.edtNamefor);
            txtReviewFromReview = itemView.findViewById(R.id.txtReviewFromReview);

        }
    }
}
