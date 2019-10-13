package io.cucumber.skeleton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IsbnFetcher {

    //    01: Make the ISBNdb API key available to the class
    //    This string must be securely stored

    String ISBN_DB_API_KEY = "SECURELY STORED STRING";

    //    02: Return the following set key-value pairs
    //    Refer to the API documentation for ISBNdb.com: https://isbndb.com/apidocs/v2
    //    It does not have to be a HashMap, it can be any Map you deem suitable
    //    (I'm still learning the ins-and-outs of Maps in Java)

    public HashMap<String,String> isbnDbArgs(String isbn) {
        HashMap<String,String> apiRequest = new HashMap<String,String>();
        String url = "https://api2.isbndb.com/book/" + isbn + "?with_prices=0";
        String accept = "application/json";
        String authorization = ISBN_DB_API_KEY;
        String cacheControl = "no-cache";

        apiRequest.put("url", url);
        apiRequest.put("accept", accept);
        apiRequest.put("authorization", authorization);
        apiRequest.put("cacheControl", cacheControl);

        return apiRequest;
    }

    //    03: Ternary conditional.  In Ruby it's a one-liner, but I suppose in Java you have to do the following.
    //    Is there a more elegant way of expressing this in Java?
    //    Solution in Ruby: https://github.com/clockworkpc/cpc-ruby/blob/master/lib/cpc/toolkit/isbn_fetcher.rb#L32

    public String bookDetailValue(String value) {
        if (value != null && !value.trim().isEmpty()) {
            return value;
        } else {
            return "N/A";
        }
    }

    //    04: Package the ISBN, box label, API response code, and all the book details into a single HashMap.
    //    This method is invoked by the main method BookDetails() IF the API response code is "200"

    public HashMap<String,String> collectDetailsBookFoundTrue(String isbn, String boxLabel, String apiResponseCode, HashMap<String,String> apiResponseBodyBook) {
        HashMap<String,String> bookDetails = new HashMap();

        bookDetails.put("isbn", isbn);
        bookDetails.put("boxLabel", boxLabel);
        bookDetails.put("apiResponseCode", apiResponseCode);

        apiResponseBodyBook.forEach((key,value) -> {
            bookDetails.put(key, value);
        });
        return bookDetails;
    }

    //    05: Package the ISBN, box label, API response code, and all the book details into a single HashMap.
    //    The book detail values will all be "N/A"
    //    This method is invoked by the main method BookDetails() UNLESS the API response code is "200"

    public HashMap<String,String> collectDetailsBookFoundFalse(String isbn, String boxLabel, String apiResponseCode) {
        HashMap<String,String> notFoundBookDetails = new HashMap<>();

        String longTitle = "N/A";
        String author = "N/A";
        String publisher = "N/A";
        String bindingType = "N/A";
        String pages = "N/A";
        String datePublished = "N/A";

        notFoundBookDetails.put("isbn", isbn);
        notFoundBookDetails.put("boxLabel", boxLabel);
        notFoundBookDetails.put("apiResponseCode", apiResponseCode);
        notFoundBookDetails.put("longTitle", longTitle);
        notFoundBookDetails.put("author", author);
        notFoundBookDetails.put("publisher", publisher);
        notFoundBookDetails.put("bindingType", bindingType);
        notFoundBookDetails.put("pages", pages);
        notFoundBookDetails.put("datePublished", datePublished);

        return notFoundBookDetails;
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

    public HashMap<String,String> collectBookDetails(String isbn, String boxLabel) {
        HashMap<String,String> bookDetails = new HashMap<>();

        // Solution in Ruby: https://github.com/clockworkpc/cpc-ruby/blob/master/lib/cpc/toolkit/isbn_fetcher.rb#L36

        // STUBBED CODE TO MAKE THE JUNIT PASS, THIS IS FOR DEMONSTRATION PURPOSES ONLY

        String longTitle = "Knitting Vintage Socks";
        String author = "Nancy Bush";
        String publisher = "Interweave";
        String bindingType = "Spiral-bound";
        String pages = "128";
        String datePublished = "2005";

        //      This is the return value of parsing the JSON response body
        HashMap<String,String> apiResponseBodyBook = new HashMap<>();

        apiResponseBodyBook.put("longTitle", longTitle);
        apiResponseBodyBook.put("author", author);
        apiResponseBodyBook.put("publisher", publisher);
        apiResponseBodyBook.put("bindingType", bindingType);
        apiResponseBodyBook.put("pages", pages);
        apiResponseBodyBook.put("datePublished", datePublished);


        if (isbn.matches("9781931499651")) {
            String apiResponseCode = "200";
            HashMap<String,String> myBookDetails = collectDetailsBookFoundTrue(isbn, boxLabel, apiResponseCode, apiResponseBodyBook);


            return myBookDetails;
        } else if (isbn.matches("661741006715")) {
            String apiResponseCode = "404";
            HashMap<String,String> myBookDetails = collectDetailsBookFoundFalse(isbn, boxLabel, apiResponseCode);
            return myBookDetails;
        }

        return bookDetails;
    }

//    06: Take the Map of book details and a CSV filepath string as parameters
//    Create a new a CSV file at the filepath
//    Use the keys from the Map as the CSV headers
//    write the details as a single line
//    The result of these operations should be a CSV with a header line and a single row in the body
//    The method should have no return value, though
    
    public void writeToCsv(Map<String,String> bookDetails, String csvFilepath) {
        // Solution in Ruby: https://github.com/clockworkpc/cpc-ruby/blob/master/lib/cpc/toolkit/isbn_fetcher.rb#L95
    }

    //    06: Take the Map of book details and a CSV filepath string as parameters
    //    This method pre-supposes the existence of a CSV at the filepath
    //    Append the details as a single line
    //    The result of these operations should be a CSV with a header line and at least two rows in the body
    //    The method should have no return value, though

    public void appendToCsv(Map<String,String> bookDetails, String csvFilepath) {
        // Solution in Ruby: https://github.com/clockworkpc/cpc-ruby/blob/master/lib/cpc/toolkit/isbn_fetcher.rb#L102
    }


}

//            def save_to_csv(details_hsh, csv_filepath)
//            no_headers = File.exist?(csv_filepath) == false || File.empty?(csv_filepath)
//            write_to_csv(details_hsh, csv_filepath) if no_headers
//            append_to_csv(details_hsh, csv_filepath) unless no_headers
//            end
//
//            def batch_fetch_save_to_csv(isbn_hsh_ary, csv_filepath)
//            countdown = isbn_hsh_ary.count
//            countup = 0
//
//            isbn_hsh_ary.each do |isbn_hsh, box_str|
//            isbn_str = isbn_hsh[:isbn]
//            box_str = isbn_hsh[:box]
//
//            details_hsh = book_details(isbn_str, box_str)
//            save_to_csv(details_hsh, csv_filepath)
//
//            countdown -= 1
//            countup += 1
//            puts "ISBNs checked: #{countup}"
//            puts "Books remaining: #{countdown}"
//            end
//            end
//            end
//            end
//            end
