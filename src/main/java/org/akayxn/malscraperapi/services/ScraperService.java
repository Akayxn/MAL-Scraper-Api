package org.akayxn.malscraperapi.services;


import java.util.Set;

public interface ScraperService<T> {

   public Set<T> scrape();
}
