package com.dev.bloomfilter.service.impls;

import com.dev.bloomfilter.exception.BloomFilterException;
import com.dev.bloomfilter.hashes.Hash;
import com.dev.bloomfilter.model.BloomState;
import com.dev.bloomfilter.service.AbstractBloomService;
import com.dev.bloomfilter.service.BloomService;
import com.dev.bloomfilter.service.BloomStateManager;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

public class BloomFilterServiceImplV1<T> extends AbstractBloomService<T> implements BloomService<T> {


    public BloomFilterServiceImplV1(BloomStateManager bloomStateManager, Collection<Hash<T>> hashes) {
        super(bloomStateManager, hashes);
    }

    @Override
    public void add(T object, String uniqueKey) throws BloomFilterException {
        BloomState bloomState = bloomStateManager.findBloomState(uniqueKey);
        Boolean[] buckets = getBuckets(bloomState);
        Integer bucketLength = bloomState.getBucketLength();
        for(var hash:hashes){
            Integer hashedValue = hash.hash(object)%bucketLength;
            hashedValue = (hashedValue + bucketLength)%bucketLength;
            buckets[hashedValue] = true;
        }
        bloomStateManager.save(bloomState);
    }

    @Override
    public boolean isPresent(T object, String uniqueKey) throws BloomFilterException {
        BloomState bloomState = bloomStateManager.findBloomState(uniqueKey);
        Boolean[] buckets = getBuckets(bloomState);
        Integer bucketLength = bloomState.getBucketLength();
        for(var hash:hashes) {
            Integer hashedValue = hash.hash(object)%bucketLength;
            hashedValue = (hashedValue + bucketLength)%bucketLength;
            if (Objects.isNull(buckets[hashedValue]) || buckets[hashedValue] == Boolean.FALSE) {
                return false;
            }
        }
        return true;
    }
}
