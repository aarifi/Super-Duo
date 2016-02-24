package it.jaschke.alexandria.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import it.jaschke.alexandria.R;
import it.jaschke.alexandria.adapters.BooksAdapter;
import it.jaschke.alexandria.data.BooksData;
import it.jaschke.alexandria.model.BooksModel;

/**
 * Created by AdonisArifi on 11.1.2016 - 2016 . alexandria
 */
public class FragmentListOfBooks extends Fragment implements LoaderManager.LoaderCallbacks<List<BooksModel>>, SearchView.OnQueryTextListener {
    private static final String TEXT_FRAGMENT = "TEXT_FRAGMENT";

    @Bind(R.id.recView_list_of_books)
    RecyclerView recView_list_of_books;

    private List<BooksModel> booksModelList;
    private BooksAdapter booksAdapter;
    private SearchView searchView;
    private String lastStringFromSearch;

    private MenuItem item;


    public static FragmentListOfBooks newInstance(String s) {


        FragmentListOfBooks mainFragment = new FragmentListOfBooks();
        Bundle bundle = new Bundle();
        bundle.putString(TEXT_FRAGMENT, s);
        mainFragment.setArguments(bundle);
        return mainFragment;

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list_of_books, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        getLoaderManager().initLoader(0, null, FragmentListOfBooks.this).forceLoad();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public Loader<List<BooksModel>> onCreateLoader(int id, Bundle args) {
        Loader<List<BooksModel>> booksModelLoader = new BooksLoader(getActivity());
        return booksModelLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<BooksModel>> loader, List<BooksModel> data) {

        if (data != null) {
            booksModelList = data;
            setDataToRecyclerView();
            if (lastStringFromSearch != null) {
                final List<BooksModel> filteredList = filter(booksModelList, lastStringFromSearch);
                booksAdapter.setFilter(filteredList);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<List<BooksModel>> loader) {

    }


    public static class BooksLoader extends AsyncTaskLoader<List<BooksModel>> {

        public BooksLoader(Context context) {
            super(context);
        }

        @Override
        public List<BooksModel> loadInBackground() {
            List<BooksModel> booksModelsList = BooksData.getBooksDataInstance(getContext()).getAll();
            return booksModelsList;
        }
    }

    public void setDataToRecyclerView() {
        booksAdapter = new BooksAdapter(booksModelList, getActivity());

        //Atach the adapter to the recyclerview to populate items
        recView_list_of_books.setAdapter(booksAdapter);
        recView_list_of_books.setLayoutManager(new LinearLayoutManager(getActivity()));
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recView_list_of_books.setLayoutManager(gridLayoutManager);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<BooksModel> filteredList = filter(booksModelList, newText);
        booksAdapter.setFilter(filteredList);
        lastStringFromSearch = newText;
        return true;
    }


    private List<BooksModel> filter(List<BooksModel> models, String query) {
        query = query.toLowerCase();

        final List<BooksModel> filteredModelList = new ArrayList<>();
        for (BooksModel model : models) {
            final String text = model.getTitle().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {

        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        item = menu.findItem(R.id.action_search);

        searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                booksAdapter.setFilter(booksModelList);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return true;
            }
        });


        super.onPrepareOptionsMenu(menu);
    }

 /*   @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_main, menu);

        item = menu.findItem(R.id.action_search);

        searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                booksAdapter.setFilter(booksModelList);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                item.collapseActionView();
                searchView.clearFocus();
                return true;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                searchView.onActionViewCollapsed();
                return false;
            }
        });

        //searchView.setIconifiedByDefault(false);
        super.onCreateOptionsMenu(menu, inflater);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    private void hideKeybord() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }
}
