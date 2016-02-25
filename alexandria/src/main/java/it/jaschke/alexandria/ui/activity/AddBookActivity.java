package it.jaschke.alexandria.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import it.jaschke.alexandria.R;
import it.jaschke.alexandria.data.BooksData;
import it.jaschke.alexandria.model.BooksModel;
import it.jaschke.alexandria.model.ImageLinks;
import it.jaschke.alexandria.model.IndustryIdentifiersModel;
import it.jaschke.alexandria.model.ListResponseBooks;
import it.jaschke.alexandria.model.ResponsItemsBook;
import it.jaschke.alexandria.model.VolumeInfo;
import it.jaschke.alexandria.utils.Constants;

/**
 * Created by AdonisArifi on 11.1.2016 - 2016 . alexandria
 */
public class AddBookActivity extends AppCompatActivity {

    @Bind(R.id.image_add_book)
    ImageView imageView_add_book;

    @Bind(R.id.txt_add_book_title)
    TextView txt_add_book_title;

    @Bind(R.id.txt_add_book_authors)
    TextView txt_add_book_authors;

    @Bind(R.id.txt_add_book_description_value)
    TextView txt_add_book_description_value;

    @Bind(R.id.txt_add_book_publishedDate_value)
    TextView txt_add_book_publishedDate_value;

    @Bind(R.id.txt_add_book_publisher_value)
    TextView txt_add_book_publisher_value;

    @Bind(R.id.txt_add_book_categories_value)
    TextView txt_add_book_categories_value;

    ListResponseBooks listResponseBooks;
    List<String> authorModel;
    BooksModel booksModel = new BooksModel();
    List<String> categoriesModel;
    ImageLinks imageLinks;
    List<ResponsItemsBook> responsItemsBook;
    VolumeInfo volumeInfo;
    List<IndustryIdentifiersModel> industryIdentifiersModels;
    Bundle bundle;
    private boolean where_from_open_book;

    private BooksData booksData;
    private BooksModel booksModelLocal;

    @Bind(R.id.linear_layout_fragment_add)
    LinearLayout linear_layout_fragment_add;

    private View myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_book);

        booksData = new BooksData(getApplicationContext());
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bundle = this.getIntent().getExtras();
        where_from_open_book = bundle.getBoolean(Constants.FROM_WHERE_OPNE_BOOK);

        if (where_from_open_book) {
            setupDataFromOnline();

        } else {
            setupDataFromOffline();
        }
    }

    private void setupDataFromOffline() {
        booksModelLocal = (BooksModel) bundle.getParcelable(Constants.LIST_RESPONS_BOOK_INTENT_KEY);

        //set data to view
        if (booksModelLocal.getImageLinks() != null) {
            Glide.with(this).load(booksModelLocal.getImageLinks()).into(imageView_add_book);

        }
        txt_add_book_title.setText(booksModelLocal.getTitle());
        txt_add_book_publisher_value.setText(booksModelLocal.getPublisher());
        txt_add_book_publishedDate_value.setText(booksModelLocal.getPublishedDate());
        txt_add_book_authors.setText(booksModelLocal.getAuthor());
        txt_add_book_description_value.setText(booksModelLocal.getDescription());
        txt_add_book_categories_value.setText(booksModelLocal.getCategories());
    }

    public void setupDataFromOnline() {
        listResponseBooks = (ListResponseBooks) bundle.getParcelable(Constants.LIST_RESPONS_BOOK_INTENT_KEY);
        responsItemsBook = listResponseBooks.getItems();
        volumeInfo = responsItemsBook.get(0).getVolumeInfo();
        authorModel = volumeInfo.getAuthors();
        categoriesModel = volumeInfo.getCategories();
        imageLinks = volumeInfo.getImageLinks();
        industryIdentifiersModels = volumeInfo.getIndustryIdentifiers();


        //set data to view
        if (imageLinks != null) {
            Glide.with(this).load(imageLinks.getThumbnail())
                    .error(R.drawable.content_thumbnail)
                    .into(imageView_add_book);
        }

        txt_add_book_title.setText(volumeInfo.getTitle());
        txt_add_book_publisher_value.setText(volumeInfo.getPublisher());
        txt_add_book_publishedDate_value.setText(volumeInfo.getPublishedDate());
        txt_add_book_authors.setText(setAuthors());
        txt_add_book_description_value.setText(volumeInfo.getDescription());
        txt_add_book_categories_value.setText(setCategories());

        //set data for save local
        if (industryIdentifiersModels.size() == 2) {
            String identifier = industryIdentifiersModels.get(1).getIdentifier().length() > 6 ?
                    industryIdentifiersModels.get(1).getIdentifier().substring(industryIdentifiersModels.get(1).getIdentifier().length() - 6)
                    : industryIdentifiersModels.get(1).getIdentifier();
            booksModel.setId(identifier);

        } else {
            String identifier = industryIdentifiersModels.get(0).getIdentifier().length() > 6 ?
                    industryIdentifiersModels.get(0).getIdentifier().substring(industryIdentifiersModels.get(0).getIdentifier().length() - 6)
                    : industryIdentifiersModels.get(0).getIdentifier();
            booksModel.setId(identifier);

        }
        booksModel.setTitle(volumeInfo.getTitle());
        booksModel.setAuthor(setAuthors());
        if (imageLinks != null) {
            booksModel.setImageLinks(imageLinks.getThumbnail());
        }

        booksModel.setPublisher(volumeInfo.getPublisher());
        booksModel.setPublishedDate(volumeInfo.getPublishedDate());
        booksModel.setDescription(volumeInfo.getDescription());
        booksModel.setCategories(setCategories());


    }

    private String setCategories() {
        String categories = "";
        for (String model : categoriesModel) {
            categories = categories + model + "\n"
            ;
        }
        return categories;
    }

    private String setAuthors() {
        String authors = "";
        for (String model : authorModel) {
            authors = authors + model + "\n"
            ;
        }
        return authors;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_book, menu);
        if (where_from_open_book) {
            menu.getItem(1).setVisible(false);
        } else {
            menu.getItem(0).setVisible(false);

        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_book) {
            BooksData.getBooksDataInstance(getApplicationContext()).registerOrUpdate(booksModel);
            Snackbar snackbar = Snackbar
                    .make(linear_layout_fragment_add, getString(R.string.book_save), Snackbar.LENGTH_LONG);
            snackbar.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                    finish();
                }
            }, Constants.ACTIVITY_DISPLAY_LENGTH_ON_SAVE);
        }
        if (id == R.id.action_delete_book) {
            int bookId = 0;
            try {
                bookId = Integer.parseInt(booksModelLocal.getId());
            } catch (NumberFormatException e) {

            }
            BooksData.getBooksDataInstance(getApplicationContext()).deleteBook(bookId);
            Snackbar snackbar = Snackbar
                    .make(linear_layout_fragment_add, getString(R.string.book_delete), Snackbar.LENGTH_LONG);
            snackbar.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                    finish();
                }
            }, Constants.ACTIVITY_DISPLAY_LENGTH_ON_SAVE);
        }

        return super.onOptionsItemSelected(item);
    }
}
