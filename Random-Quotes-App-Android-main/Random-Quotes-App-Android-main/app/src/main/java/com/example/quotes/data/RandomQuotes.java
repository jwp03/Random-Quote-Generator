package com.example.quotes.data;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

public class RandomQuotes implements Serializable {
  private String quote;

  private String author;

  private Integer id;

  public String getQuote() {
    return this.quote;
  }

  public void setQuote(String quote) {
    this.quote = quote;
  }

  public String getAuthor() {
    return this.author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
