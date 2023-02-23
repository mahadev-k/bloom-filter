package com.dev.bloomfilter.service.impls;

import com.dev.bloomfilter.exception.BloomFilterException;
import com.dev.bloomfilter.model.BloomState;
import com.dev.bloomfilter.repository.BloomStateRepo;
import com.dev.bloomfilter.service.BloomStateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BloomStateManagerImpl implements BloomStateManager<String> {

    private final BloomStateRepo bloomStateRepo;

    @Autowired
    public BloomStateManagerImpl(BloomStateRepo bloomStateRepo) {
        this.bloomStateRepo = bloomStateRepo;
    }

    @Override
    public void save(String uniqueKey, BloomState bloomState) {
        bloomState.setUniqueKey(uniqueKey);
        this.save(bloomState);
    }

    public void save(BloomState bloomState) {
        bloomStateRepo.saveAndFlush(bloomState);
    }

    @Override
    public Optional<BloomState> getBloomState(String uniqueKey) {
        return this.bloomStateRepo.findByUniqueKey(uniqueKey);
    }

    @Override
    public BloomState findBloomState(String uniqueKey) throws BloomFilterException {
        return getBloomState(uniqueKey).orElseThrow(() ->
                new BloomFilterException("BloomState Not Present"));
    }

    @Override
    public List<BloomState> getAllBloomState() {
        return this.bloomStateRepo.findAll();
    }

    @Override
    public void deleteBloomState(String uniqueKey) {
        this.bloomStateRepo.deleteByUniqueKey(uniqueKey);
    }
}
