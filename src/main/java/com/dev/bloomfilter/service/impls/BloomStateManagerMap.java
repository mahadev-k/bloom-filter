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
public class BloomStateManagerMap implements BloomStateManager {

    private final Map<String, BloomState> bloomStateMap;

    BloomStateManagerMap() {
        this.bloomStateMap = new ConcurrentHashMap<>();
    }

    @Override
    public void save(BloomState bloomState) {
        bloomStateMap.put(bloomState.getUniqueKey(), bloomState);
    }

    @Override
    public Optional<BloomState> getBloomState(String uniqueKey) {
        return Optional.ofNullable(bloomStateMap.get(uniqueKey));
    }

    @Override
    public BloomState findBloomState(String uniqueKey) throws BloomFilterException {
        return bloomStateMap.get(uniqueKey);
    }

    @Override
    public List<BloomState> getAllBloomState() {
        return (List<BloomState>) bloomStateMap.values();
    }

    @Override
    public void deleteBloomState(String uniqueKey) {
        bloomStateMap.remove(uniqueKey);
    }
}
