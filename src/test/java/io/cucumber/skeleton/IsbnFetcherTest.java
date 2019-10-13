package io.cucumber.skeleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IsbnFetcherTest {

    @DisplayName("ISBN_DB_API_KEY available?")
    @Test
    public void testIsbnFetcherApiKey() {
        IsbnFetcher isbnFetcher = new IsbnFetcher();
        String isbnDbApiKey = "THE SAME SECURELY STORED STRING";
        assertEquals(isbnDbApiKey, isbnFetcher.ISBN_DB_API_KEY);
    }

    @DisplayName("API Request Map")
    @Test
    public void testIsbnDbArgs () {
        IsbnFetcher isbnFetcher = new IsbnFetcher();
        String isbn = "9781931499651";
        HashMap<String,String> testApiRequest = new HashMap<String,String>();

        String url = "https://api2.isbndb.com/book/" + isbn + "?with_prices=0";
        String accept = "application/json";
        String authorization = "SECURELY STORED STRING";
        String cacheControl = "no-cache";

        testApiRequest.put("url", url);
        testApiRequest.put("accept", accept);
        testApiRequest.put("authorization", authorization);
        testApiRequest.put("cacheControl", cacheControl);

        Map<String,String> apiRequest = isbnFetcher.isbnDbArgs(isbn);
        
        assertEquals(testApiRequest, apiRequest);
    }

    @DisplayName("Ternary conditional for handling null values returned from API response body")
    @Test
    public void testDetailValue() {
        IsbnFetcher isbnFetcher = new IsbnFetcher();
        assertEquals(isbnFetcher.bookDetailValue(null), "N/A");
        assertEquals(isbnFetcher.bookDetailValue("Hello, World!"), "Hello, World!");
    }

    @DisplayName("Collect Book Details if entry found in DB")
    @Test
    public void testCollectBookDetailsBookFoundTrue() {
        IsbnFetcher isbnFetcher = new IsbnFetcher();
        String isbn = "9781931499651";
        String boxLabel = "01 - Knitting Books";
        String apiResponseCode = "200";
        HashMap<String,String> inputApiResponseBodyBook = new HashMap<String,String>();
        HashMap<String,String> testApiResponseBodyBook = new HashMap<String,String>();
        
        String longTitle = "Knitting Vintage Socks";
        String author = "Nancy Bush";
        String publisher = "Interweave";
        String bindingType = "Spiral-bound";
        String pages = "128";
        String datePublished = "2005";

        // What the return value of the method should look contain
        testApiResponseBodyBook.put("isbn", isbn);
        testApiResponseBodyBook.put("boxLabel", boxLabel);
        testApiResponseBodyBook.put("apiResponseCode", apiResponseCode);
        testApiResponseBodyBook.put("longTitle", longTitle);
        testApiResponseBodyBook.put("author", author);
        testApiResponseBodyBook.put("publisher", publisher);
        testApiResponseBodyBook.put("bindingType", bindingType);
        testApiResponseBodyBook.put("pages", pages);
        testApiResponseBodyBook.put("datePublished", datePublished);

        // The contents of the apiResponseBodyBook HashMap, converted originally from JSON elsewhere
        inputApiResponseBodyBook.put("longTitle", longTitle);
        inputApiResponseBodyBook.put("author", author);
        inputApiResponseBodyBook.put("publisher", publisher);
        inputApiResponseBodyBook.put("bindingType", bindingType);
        inputApiResponseBodyBook.put("pages", pages);
        inputApiResponseBodyBook.put("datePublished", datePublished);

        assertEquals(testApiResponseBodyBook, isbnFetcher.collectDetailsBookFoundTrue(isbn, boxLabel, apiResponseCode, inputApiResponseBodyBook));
    }

    @DisplayName("Collect Book Details if entry NOT found in DB")
    @Test
    public void testCollectDetailsBookFoundFalse() {
        IsbnFetcher isbnFetcher = new IsbnFetcher();
        String isbn = "9781931499651";
        String boxLabel = "01 - Knitting Books";
        String apiResponseCode = "200";
        HashMap<String,String> testApiResponseBodyBook = new HashMap<>();

        String longTitle = "N/A";
        String author = "N/A";
        String publisher = "N/A";
        String bindingType = "N/A";
        String pages = "N/A";
        String datePublished = "N/A";

        // What the return value of the method should look contain
        testApiResponseBodyBook.put("isbn", isbn);
        testApiResponseBodyBook.put("boxLabel", boxLabel);
        testApiResponseBodyBook.put("apiResponseCode", apiResponseCode);
        testApiResponseBodyBook.put("longTitle", longTitle);
        testApiResponseBodyBook.put("author", author);
        testApiResponseBodyBook.put("publisher", publisher);
        testApiResponseBodyBook.put("bindingType", bindingType);
        testApiResponseBodyBook.put("pages", pages);
        testApiResponseBodyBook.put("datePublished", datePublished);

        assertEquals(testApiResponseBodyBook, isbnFetcher.collectDetailsBookFoundFalse(isbn, boxLabel, apiResponseCode));
    }


    //    THE MOST IMPORTANT METHOD OF ALL: THIS IS WHERE IT ALL COMES TOGETHER

    //    06: Make an API call to ISBNdb.com,
    //    Parse the API response (JSON response body),
    //    and return a Map containing the ISBN, Response Code, Box Label, and Book Details

    //    These details must include the following:
    //    IF the API Response code is "200" THEN invoke collectBookDetailsFoundTrue()
    //    ELSE invoke collectBookDetailsFoundFalse()

    //    NOTE: Sometimes the API Response is "200" but some fields in the body are null:
    //    Make sure that collectBookDetailsFoundTrue uses the bookDetailValue to account for null values

    @DisplayName("Book Details -- 200 and 404")
    @ParameterizedTest
    @ValueSource(strings = {"9781931499651", "661741006715"})
    void testCollectBookDetails(String isbn) {
        IsbnFetcher isbnFetcher = new IsbnFetcher();
        String boxLabel = "01 - Knitting Books";
        HashMap<String,String> bookDetails = isbnFetcher.collectBookDetails(isbn, boxLabel);

//        We know from testing:
//        ISBN "9781931499651" return "200",
//        ISBN "661741006715" return "200".

//        Therefore,
//        if the isbn is "9781931499651",
//        collectBookDetails should return the full complement of book details,
//        if the isbn is "661741006715",
//        collectBookDetails should return "N/A" for all the book detail values.

        String longTitle = "Knitting Vintage Socks";
        String author = "Nancy Bush";
        String publisher = "Interweave";
        String bindingType = "Spiral-bound";
        String pages = "128";
        String datePublished = "2005";


        if (isbn.matches("9781931499651")) {
            assertEquals("9781931499651", bookDetails.get("isbn"));
            assertEquals("200", bookDetails.get("apiResponseCode"));
            assertEquals(boxLabel, bookDetails.get("boxLabel"));
            assertEquals(longTitle, bookDetails.get("longTitle"));
            assertEquals(author, bookDetails.get("author"));
            assertEquals(publisher, bookDetails.get("publisher"));
            assertEquals(bindingType, bookDetails.get("bindingType"));
            assertEquals(pages, bookDetails.get("pages"));
            assertEquals(datePublished, bookDetails.get("datePublished"));

        } else if (isbn.matches("661741006715")) {
            assertEquals("661741006715", bookDetails.get("isbn"));
            assertEquals("404", bookDetails.get("apiResponseCode"));
            assertEquals(boxLabel, bookDetails.get("boxLabel"));
            assertEquals("N/A", bookDetails.get("longTitle"));
            assertEquals("N/A", bookDetails.get("author"));
            assertEquals("N/A", bookDetails.get("publisher"));
            assertEquals("N/A", bookDetails.get("bindingType"));
            assertEquals("N/A", bookDetails.get("pages"));
            assertEquals("N/A", bookDetails.get("datePublished"));
        }
    }
}
