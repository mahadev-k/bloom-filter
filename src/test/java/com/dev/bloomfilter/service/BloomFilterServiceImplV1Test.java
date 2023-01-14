package com.dev.bloomfilter.service;

import com.dev.bloomfilter.exception.BloomFilterException;
import com.dev.bloomfilter.hashes.Hash;
import com.dev.bloomfilter.hashes.HashFactory;
import com.dev.bloomfilter.model.BloomState;
import com.dev.bloomfilter.service.impls.BloomFilterServiceImplV1;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Slf4j
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BloomFilterServiceImplV1Test extends AbstractBloomServiceTest<String>{

    public static final String BLOOM_OUT = "Bloom-out";

    @BeforeAll
    public void setup() throws BloomFilterException {
        Collection<Hash<String>> hashes = generateHashes();
        this.bloomService = new BloomFilterServiceImplV1<>(this.bloomStateManagerMock, hashes);
    }

    @BeforeEach
    public void beforeEach() throws BloomFilterException {
        this.bloomState = buildBloom();
        Mockito.when(bloomStateManagerMock.findBloomState(Mockito.any())).thenReturn(bloomState);
    }

    @Test
    public void testWordsPresent() throws BloomFilterException {
        this.bloomService.add("Bloom", BLOOM_OUT);
        this.bloomService.add("Bloom1", BLOOM_OUT);
        this.bloomService.add("Qbrt", BLOOM_OUT);
        Assert.isTrue(bloomService.isPresent("Bloom", BLOOM_OUT), "Bloom should be present");
        Assert.isTrue(bloomService.isPresent("Bloom1", BLOOM_OUT), "Bloom1 should be present");
        Assert.isTrue(bloomService.isPresent("Qbrt", BLOOM_OUT), "Qbrt should be present");

        log.info("Bloom buckets [] {}", Arrays.toString(bloomState.getBuckets()));
    }

    @Test
    public void testWordNotPresent() throws BloomFilterException {
        this.bloomService.add("Bloom", BLOOM_OUT);
        this.bloomService.add("Bloom1", BLOOM_OUT);
        this.bloomService.add("Qbrt", BLOOM_OUT);
        Assert.isTrue(!bloomService.isPresent("Bloom2", BLOOM_OUT), "Bloom should  not be present");
        Assert.isTrue(!bloomService.isPresent("Bloom ", BLOOM_OUT), "Bloom1 should not be present");
        Assert.isTrue(!bloomService.isPresent("Qbrtt", BLOOM_OUT), "Qbrt should not be present");

        log.info("Bloom buckets [] {}", Arrays.toString(bloomState.getBuckets()));
    }

    private Collection<Hash<String>> generateHashes() {
        return List.of(
                HashFactory.StringHash.hash13,
                HashFactory.StringHash.hash19,
                HashFactory.StringHash.hash23
        );
    }

    private BloomState buildBloom() {
        return BloomState.builder()
                .buckets(new Boolean[100])
                .uniqueKey(BLOOM_OUT)
                .bucketLength(100)
                .build();
    }

}
