package io.cucumber.skeleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @DisplayName("Ternary conditional for handling null values in API response body")
    @Test
    public void testDetailValue() {
        IsbnFetcher isbnFetcher = new IsbnFetcher();
        assertEquals(isbnFetcher.detailValue(null), "N/A");
        assertEquals(isbnFetcher.detailValue("Hello, World!"), "Hello, World!");
    }

    @DisplayName("Collect Book Details if entry found in DB")
    @Test
    public void testCollectBookDetailsBookFoundTrue () {
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

        testApiResponseBodyBook.put("isbn", isbn);
        testApiResponseBodyBook.put("boxLabel", boxLabel);
        testApiResponseBodyBook.put("apiResponseCode", apiResponseCode);
        testApiResponseBodyBook.put("longTitle", longTitle);
        testApiResponseBodyBook.put("author", author);
        testApiResponseBodyBook.put("publisher", publisher);
        testApiResponseBodyBook.put("bindingType", bindingType);
        testApiResponseBodyBook.put("pages", pages);
        testApiResponseBodyBook.put("datePublished", datePublished);

        inputApiResponseBodyBook.put("longTitle", longTitle);
        inputApiResponseBodyBook.put("author", author);
        inputApiResponseBodyBook.put("publisher", publisher);
        inputApiResponseBodyBook.put("bindingType", bindingType);
        inputApiResponseBodyBook.put("pages", pages);
        inputApiResponseBodyBook.put("datePublished", datePublished);

        assertEquals(testApiResponseBodyBook, isbnFetcher.collectDetailsBookFoundTrue(isbn, boxLabel, apiResponseCode, inputApiResponseBodyBook));
    }

    @DisplayName("VERY IMPORTANT: Check Book Details")
    @Test
    public void testBookDetails() {
        IsbnFetcher isbnFetcher = new IsbnFetcher();
        String isbn = "9781931499651";
        String boxLabel = "01 - Knitting Books";
        HashMap sampleBookDetails = isbnFetcher.bookDetails(isbn, boxLabel);
    }


}
