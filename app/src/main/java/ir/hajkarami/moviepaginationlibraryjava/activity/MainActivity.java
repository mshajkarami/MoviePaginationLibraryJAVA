package ir.hajkarami.moviepaginationlibraryjava.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import ir.hajkarami.moviepaginationlibraryjava.R;
import ir.hajkarami.moviepaginationlibraryjava.adapter.MovieLoadStateAdapter;
import ir.hajkarami.moviepaginationlibraryjava.adapter.MoviesAdapter;
import ir.hajkarami.moviepaginationlibraryjava.databinding.ActivityMainBinding;
import ir.hajkarami.moviepaginationlibraryjava.util.GridSpace;
import ir.hajkarami.moviepaginationlibraryjava.util.MovieComparator;
import ir.hajkarami.moviepaginationlibraryjava.util.Utils;
import ir.hajkarami.moviepaginationlibraryjava.viewmodel.MovieViewModel;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    MovieViewModel mainActivityViewModel;
    ActivityMainBinding binding;
    MoviesAdapter moviesAdapter;

    @Inject
    RequestManager mRequestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        if (Utils.API_KEY == null || Utils.API_KEY.isEmpty()) {
            Toast.makeText(this, "Error in API Key", Toast.LENGTH_SHORT).show();
        }
        moviesAdapter = new MoviesAdapter(new MovieComparator(), mRequestManager);
        mainActivityViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        initRecyclerViewAndAdapter();

        mainActivityViewModel.moviePagingDataFlowable.subscribe(moviePagingData -> {
            moviesAdapter.submitData(getLifecycle(), moviePagingData);
        });
    }

    private void initRecyclerViewAndAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        binding.recyclerView.setLayoutManager(gridLayoutManager);
        binding.recyclerView.addItemDecoration(new GridSpace(2, 20, true));
        binding.recyclerView.setAdapter(moviesAdapter.withLoadStateFooter(
                new MovieLoadStateAdapter(view ->
                        moviesAdapter.retry()
                )));
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return moviesAdapter.getItemViewType(position) == MoviesAdapter.MOVIE_ITEM ? 1 : 2;
            }
        });
    }
}