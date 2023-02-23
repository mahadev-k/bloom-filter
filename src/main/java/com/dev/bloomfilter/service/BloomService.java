package com.dev.bloomfilter.service;

import com.dev.bloomfilter.exception.BloomFilterException;
public interface BloomService<T> {

    /**
     * Helps you in adding an object to the bloom table.
     * Once added you can query if the object is present or not.
     * @param object
     * @param uniqueKey
     * @throws BloomFilterException
     */
    void add(T object, String uniqueKey) throws BloomFilterException;

    /**
     * The added object would be present in the table as well as this can give you false positive data
     * So if this is true then there are fair chances the data is there.
     * Else the data is not at all present in the table.
     *
     * Ex - Add valid mail ids to the bloom table.
     * Now the mail id is termed as present carry out the txn with the db.
     * If the mail id is not present then - probably user has to sign up once more.
     *
     * Now, searching for a single mail id with millions of users comes at a cost for higher traffic systems
     * So, this will help you to avoid unnecessary searches for a non-existent mail id.
     * Why ? If there is a script running to attack the login servers this will prove to be a good candidate in
     * keeping our db in chill mode 🤔
     * @param object
     * @param uniqueKey
     * @return
     * @throws BloomFilterException
     */
    boolean isPresent(T object, String uniqueKey) throws BloomFilterException;

}
