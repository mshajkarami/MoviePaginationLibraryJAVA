package ir.hajkarami.moviepaginationlibraryjava.util;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import ir.hajkarami.moviepaginationlibraryjava.model.Movie;

public class MovieComparator extends DiffUtil.ItemCallback<Movie> {
    @Override
    public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
        return oldItem.getId() == newItem.getId();
    }
    @Override
    public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
        return oldItem.getId() == newItem.getId();
    }
}
