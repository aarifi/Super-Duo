package it.jaschke.alexandria.api;

import it.jaschke.alexandria.model.ListResponseBooks;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by AdonisArifi on 15.12.2015 - 2015 . alexandria
 */
public interface BooksApi {

//https://www.googleapis.com/books/v1/volumes?q=isbn:9780553804577&country=US

    @GET("/volumes")
    ListResponseBooks getBooks(@Query("q") String bookId);

}
