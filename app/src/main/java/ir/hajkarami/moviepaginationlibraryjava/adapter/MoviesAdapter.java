package ir.hajkarami.moviepaginationlibraryjava.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;

import ir.hajkarami.moviepaginationlibraryjava.databinding.SingleMovieItemBinding;
import ir.hajkarami.moviepaginationlibraryjava.model.Movie;
import kotlinx.coroutines.CoroutineDispatcher;

public class MoviesAdapter extends PagingDataAdapter<Movie, MoviesAdapter.MovieViewHolder> {

    public static final int LOADING_ITEM = 0;
    public static final int MOVIE_ITEM = 1;

    RequestManager glide;

    public MoviesAdapter(@NonNull DiffUtil.ItemCallback<Movie> diffCallback,RequestManager glide) {
        super(diffCallback);
        this.glide = glide;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(SingleMovieItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }
    @Override
    public int getItemViewType(int position) {
        return position == getItemCount() ? LOADING_ITEM : MOVIE_ITEM;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = getItem(position);
        if (movie != null) {
            glide.load("https://image.tmdb.org/t/p/w500" + movie.getPosterPath())
                    .into(holder.binding.imageViewMovie);
            holder.binding.textViewRating.setText(String.valueOf(movie.getVoteAverage()));
        }

    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        SingleMovieItemBinding binding;

        public MovieViewHolder(@NonNull SingleMovieItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
