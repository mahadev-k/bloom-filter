package com.dev.bloomfilter.service;

import com.dev.bloomfilter.exception.BloomFilterException;
import com.dev.bloomfilter.hashes.Hash;
import com.dev.bloomfilter.model.BloomState;

import java.util.Collection;
import java.util.Objects;

/**
 * This should have all the hashes for the bloom filter set
 * So we can Iterate and find the hash
 * BloomStateManager will help in getting and saving bloomStates.
 */
public abstract class AbstractBloomService<T> {

    protected final Collection<Hash<T>> hashes;
    protected final BloomStateManager bloomStateManager;
    private final int defaultArrayLength = 50;

    public AbstractBloomService(BloomStateManager bloomStateManager, Collection<Hash<T>> hashes){
        this.bloomStateManager = bloomStateManager;
        this.hashes = hashes;
    }

    protected Boolean[] getBuckets(BloomState bloomState) throws BloomFilterException {
        Boolean[] buckets = bloomState.getBuckets();
        if(Objects.isNull(buckets)){
            throw new BloomFilterException("Buckets not present");
        }
        return buckets;
    }

}
