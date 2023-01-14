package com.dev.bloomfilter.hashes;

public interface Hash<T> {

    Integer hash(T object);

}
