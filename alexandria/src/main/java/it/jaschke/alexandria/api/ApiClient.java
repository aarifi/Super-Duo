package it.jaschke.alexandria.api;

import android.content.Context;

import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import it.jaschke.alexandria.model.ListResponseBooks;
import it.jaschke.alexandria.utils.Constants;
import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.client.Request;
import retrofit.client.Response;

/**
 * Created by AdonisArifi on 15.12.2015 - 2015 . alexandria
 */
public class ApiClient implements RequestInterceptor, Client {
    private static final String LOG = ApiClient.class.getSimpleName();
    private static ApiClient apiClientInstance;
    private static BooksApi booksApiInterfaceMethod;
    public String bookId = "";

    public static ApiClient getApiClientInstance(Context context) {
        if (apiClientInstance == null) {
            apiClientInstance = new ApiClient();
        }
        return apiClientInstance;
    }

    public BooksApi getBooksApiInterfaceMethod() {
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(60, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);
        if (booksApiInterfaceMethod == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setRequestInterceptor(this)
                    .setEndpoint(Constants.BASE_URL_GOOGLE_BOOKS_API)
                    .setErrorHandler(ErrorHandler.DEFAULT)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setClient(new OkClient(okHttpClient))
                    .build();
            booksApiInterfaceMethod = restAdapter.create(BooksApi.class);
        }

        return booksApiInterfaceMethod;
    }

    public ListResponseBooks getBook(String bookId) {
        ListResponseBooks listResponseBooks = null;
        try
        {
             listResponseBooks = getBooksApiInterfaceMethod().getBooks(bookId);
        }catch (RetrofitError error)
        {
            error.getMessage();
        }

        return listResponseBooks;
    }


    @Override
    public Response execute(Request request) throws IOException {
        return null;
    }

    @Override
    public void intercept(RequestFacade request) {

        request.addQueryParam("country", "US");
    }
}
