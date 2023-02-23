package com.dev.bloomfilter.service;

import com.dev.bloomfilter.exception.BloomFilterException;
import com.dev.bloomfilter.model.BloomState;

import java.util.List;
import java.util.Optional;

public interface BloomStateManager<T> {

    void save(T uniqueKey, BloomState bloomState);

    Optional<BloomState> getBloomState(T uniqueKey);

    BloomState findBloomState(T uniqueKey) throws BloomFilterException;

    List<BloomState> getAllBloomState();

    void deleteBloomState(T uniqueKey);

}
