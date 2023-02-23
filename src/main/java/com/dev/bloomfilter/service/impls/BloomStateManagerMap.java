package com.dev.bloomfilter.service.impls;

import com.dev.bloomfilter.exception.BloomFilterException;
import com.dev.bloomfilter.model.BloomState;
import com.dev.bloomfilter.service.BloomStateManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BloomStateManagerMap<T> implements BloomStateManager<T> {

    private final Map<T, BloomState> bloomStateMap;

    public BloomStateManagerMap() {
        this.bloomStateMap = new ConcurrentHashMap<>();
    }

    @Override
    public void save(T uniqueKey, BloomState bloomState) {
        bloomStateMap.put(uniqueKey, bloomState);
    }

    @Override
    public Optional<BloomState> getBloomState(T uniqueKey) {
        return Optional.ofNullable(bloomStateMap.get(uniqueKey));
    }

    @Override
    public BloomState findBloomState(T uniqueKey) throws BloomFilterException {
        return bloomStateMap.get(uniqueKey);
    }

    @Override
    public List<BloomState> getAllBloomState() {
        return (List<BloomState>) bloomStateMap.values();
    }

    @Override
    public void deleteBloomState(T uniqueKey) {
        bloomStateMap.remove(uniqueKey);
    }
}
