package com.dev.bloomfilter.service;

import com.dev.bloomfilter.exception.BloomFilterException;
import com.dev.bloomfilter.model.BloomState;

import java.util.List;
import java.util.Optional;

public interface BloomStateManager {

    void save(BloomState bloomState);

    Optional<BloomState> getBloomState(String uniqueKey);

    BloomState findBloomState(String uniqueKey) throws BloomFilterException;

    List<BloomState> getAllBloomState();

    void deleteBloomState(String uniqueKey);

}
