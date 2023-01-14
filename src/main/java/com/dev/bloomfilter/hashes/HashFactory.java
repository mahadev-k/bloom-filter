package com.dev.bloomfilter.hashes;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HashFactory {
    private static final int mod = 24593;

    public static class StringHash {

        public static Hash<String> hash13 = (word) -> generateHash(word, 13, mod);
        public static Hash<String> hash19 = (word) -> generateHash(word, 19, mod);
        public static Hash<String> hash23 = (word) -> generateHash(word, 23, mod);

    }


    public static Integer generateHash(String word, int d, int mod) {

        int hash = 0;
        int n = word.length();
        try {
            for(int i=0; i<n; i++) {
                hash += (hash*d)%mod - (Character.valueOf(word.charAt(i)).hashCode());
                hash %= mod;
            }
        } catch (Exception e){
            log.error("Error occurred while hashing", e);
        }


        return hash;
    }

}
