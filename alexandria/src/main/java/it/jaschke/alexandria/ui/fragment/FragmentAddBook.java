package it.jaschke.alexandria.ui.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.jaschke.alexandria.R;
import it.jaschke.alexandria.api.ApiClient;
import it.jaschke.alexandria.model.ListResponseBooks;
import it.jaschke.alexandria.ui.activity.AddBookActivity;
import it.jaschke.alexandria.utils.Constants;
import it.jaschke.alexandria.utils.SupportMethod;

/**
 * Created by AdonisArifi on 11.1.2016 - 2016 . alexandria
 */
public class FragmentAddBook extends Fragment implements LoaderManager.LoaderCallbacks<ListResponseBooks> {
    private static final String TEXT_FRAGMENT = "TEXT_FRAGMENT";

    @Bind(R.id.button_search)
    Button button_search;

    @Bind(R.id.edittext_isbn_number)
    EditText edittext_isbn_number;

    @Bind(R.id.coordinatorLayout)
    RelativeLayout coordiLayout;
    View rootView;

    public static FragmentAddBook newInstance(String s) {

        FragmentAddBook mainFragment = new FragmentAddBook();
        Bundle bundle = new Bundle();
        bundle.putString(TEXT_FRAGMENT, s);
        mainFragment.setArguments(bundle);
        return mainFragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_search_book, container, false);
        ButterKnife.bind(this, rootView);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.scan_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This is the callback method that the system will invoke when your button is
                // clicked. You might do this by launching another app or by including the
                //functionality directly in this app.
                // Hint: Use a Try/Catch block to handle the Intent dispatch gracefully, if you
                // are using an external app.
                //when you're done, remove the toast below.
                IntentIntegrator integrator = IntentIntegrator.forSupportFragment(FragmentAddBook.this);

                integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
                integrator.setPrompt("Please scan book ISBN barcode");
                integrator.setBeepEnabled(false);
                integrator.initiateScan();
            }
        });
        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStop() {
        super.onStop();
        ButterKnife.unbind(rootView);
        hideKeybord();
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.d("AddBook", "Cancelled scan");
            } else {
                Log.d("AddBook", "Scanned");
                edittext_isbn_number.setText(result.getContents());
                getLoaderManager().restartLoader(123, null, this).forceLoad();
            }

            // At this point we may or may not have a reference to the activity
        } else {
            Log.d("MainActivity", "Weird");
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public Loader<ListResponseBooks> onCreateLoader(int id, Bundle args) {
        String isbn_number = Constants.ISBN_STRING_KEY + edittext_isbn_number.getText().toString();
        Loader<ListResponseBooks> booksLoader = new BooksAsyncTask(getActivity(), isbn_number);
        return booksLoader;
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<ListResponseBooks> loader, ListResponseBooks data) {
        ListResponseBooks listResponseBooks = data;
        if (listResponseBooks != null ) {
            Intent intent = new Intent(getActivity(), AddBookActivity.class);
            Bundle bundle = new Bundle();
            intent.putExtra(Constants.LIST_RESPONS_BOOK_INTENT_KEY, listResponseBooks);
            intent.putExtra(Constants.FROM_WHERE_OPNE_BOOK, true);
            //  bundle.putParcelable(Constants.LIST_RESPONS_BOOK_INTENT_KEY, listResponseBooks);
            startActivity(intent);
            edittext_isbn_number.getText().clear();
        } else {
            Snackbar snackbar = Snackbar
                    .make(coordiLayout, getString(R.string.book_not_found), Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<ListResponseBooks> loader) {

    }


    public static class BooksAsyncTask extends AsyncTaskLoader<ListResponseBooks> {

        String bookId;

        public BooksAsyncTask(Context context, String bookid) {
            super(context);
            this.bookId = bookid;
        }

        @Override
        public ListResponseBooks loadInBackground() {
            ListResponseBooks listResponseBooks = null;
            try {
                listResponseBooks = ApiClient.getApiClientInstance(getContext()).getBook(bookId);

            } catch (Exception e) {
                e.getMessage();
            }


            return listResponseBooks;
        }
    }

    @OnClick(R.id.button_search)
    public void setOnClickButtonSearch() {
        if (SupportMethod.getSupportMethodInstance(getActivity()).IsConnectNetwork()) {
            String isbn_digits = edittext_isbn_number.getText().toString();
            //catch isbn10 numbers
            if (isbn_digits.length() == 13 && isbn_digits.startsWith("978")) {
                hideKeybord();
                getLoaderManager().restartLoader(123, null, this).forceLoad();

            }
            if (isbn_digits.length() < 13) {
                hideKeybord();
                Snackbar snackbar = Snackbar
                        .make(coordiLayout, getString(R.string.valid_isbn), Snackbar.LENGTH_LONG);
                snackbar.show();

                return;
            }
        } else {
            Snackbar snackbar = Snackbar
                    .make(coordiLayout, getString(R.string.not_internet), Snackbar.LENGTH_LONG);
            snackbar.show();
        }


    }


    private void hideKeybord() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }


}
