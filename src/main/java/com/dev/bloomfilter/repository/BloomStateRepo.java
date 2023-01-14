package com.dev.bloomfilter.repository;

import com.dev.bloomfilter.model.BloomState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BloomStateRepo extends JpaRepository<BloomState, Long> {

    Optional<BloomState> findByUniqueKey(String uniqueKey);

    void deleteByUniqueKey(String uniqueKey);

}
