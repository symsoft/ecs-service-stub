/*
 * Copyright Symsoft AB 1996-2015. All Rights Reserved.
 */
package se.symsoft.codecamp.myservice;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * Data container
 */

@DynamoDBTable(tableName=MyService.TABLE_NAME)
public class MyData {
    private String id;
    private String foo;  // This is our primary key, i.e DynamoDB hash key
    private int bar;

    @DynamoDBHashKey(attributeName="foo")
    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    public int getBar() {
        return bar;
    }

    public void setBar(int bar) {
        this.bar = bar;
    }
}
