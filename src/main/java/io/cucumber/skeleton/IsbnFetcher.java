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
    //    It does not have to be a Map, it can be a HashMap or anything else that suits
    //    (I'm still learning the ins-and-outs of Maps in Java)

    public Map<String,String> isbnDbArgs(String isbn) {
        Map<String,String> apiRequest = null;
        String url = "https://api2.isbndb.com/book/" + isbn + "?with_prices=0";
        String accept = "application/json";
        String authorization = ISBN_DB_API_KEY;
        String cacheControl = "no-cache";

        return apiRequest;
    }

    //    03: Ternary conditional.  In Ruby it's a one-liner, but I suppose in Java you have to do the following.
    //    Solution in Ruby: https://github.com/clockworkpc/cpc-ruby/blob/master/lib/cpc/toolkit/isbn_fetcher.rb#L32

    public String detailValue(String value) {
        if (value != null && !value.trim().isEmpty()) {
            return value;
        } else {
            return "N/A";
        }
    }

    //    04: Make an API call to ISBNdb.com,
    //    Parse the API response (JSON response body)
    //    and return a Map containing book's details

    //    These details must include the following:

    //    isbn => simply include the isbn String that is passed to the method
    //    boxLabel => simply include the boxLabel String that is passed to the method
    //    responseCode => the API response code

    // IF the API Response code is "200" THEN:

    //    long_title => value of "long_title" key in the API response body
    //    author = FIRST VALUE FROM ENUMERABLE VALUE of "authors" key in the API response body
    //    publisher = value of "publisher" key in the API response body
    //    binding_type = value of "binding" key in the API response body
    //    pages = value of "pages" key in the API response body
    //    date_published = value of "date_published" key in the API response body

    // ELSE IF the API response code is NOT "200" THEN:

    //    long_title => "N/A"
    //    author = "N/A"
    //    publisher = "N/A"
    //    binding_type = "N/A"
    //    pages = "N/A"
    //    date_published = "N/A"

    public Map<String,String> bookDetails(String isbn, String boxLabel) {
        Map<String,String> myBookDetails = null;
        // Solution in Ruby: https://github.com/clockworkpc/cpc-ruby/blob/master/lib/cpc/toolkit/isbn_fetcher.rb#L36
        return myBookDetails;
    }

    //  05: Return a Map wherein all the values that would come from the API response are simply "N/A"
    //  The response code will always be "200" if this method is called, but it must not be hard-coded
    //  Refactor this code if there is a better way to do it

    public Map<String,String> detailsNotFound(String isbn, String boxLabel, String responseCode) {
        Map<String,String> notFoundBookDetails = null;

        String longTitle = "N/A";
        String author = "N/A";
        String publisher = "N/A";
        String bindingType = "N/A";
        String pages = "N/A";
        String datePublished = "N/A";

        notFoundBookDetails.put("long_title", longTitle);
        notFoundBookDetails.put("author", author);
        notFoundBookDetails.put("publisher", publisher);
        notFoundBookDetails.put("bindingType", bindingType);
        notFoundBookDetails.put("pages", pages);
        notFoundBookDetails.put("datePublished", datePublished);

        // Solution in Ruby: https://github.com/clockworkpc/cpc-ruby/blob/master/lib/cpc/toolkit/isbn_fetcher.rb#L60
        return notFoundBookDetails;
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
