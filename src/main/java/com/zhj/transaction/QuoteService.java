package com.zhj.transaction;

import org.joda.time.DateTime;

/**
 * Created by zhj on 2018/10/10.
 */

public interface QuoteService {
    Quote getQuote();
    Quote getQuoteByDateTime(DateTime dateTime );
    void saveQuote(Quote quote);
    void updateQuote(Quote quote);
    void deteleQuote(Quote quote);
}