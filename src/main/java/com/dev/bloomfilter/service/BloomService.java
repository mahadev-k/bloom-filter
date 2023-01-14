package com.dev.bloomfilter.service;

import com.dev.bloomfilter.exception.BloomFilterException;
public interface BloomService<T> {

    void add(T object, String uniqueKey) throws BloomFilterException;

    boolean isPresent(T object, String uniqueKey) throws BloomFilterException;

}
