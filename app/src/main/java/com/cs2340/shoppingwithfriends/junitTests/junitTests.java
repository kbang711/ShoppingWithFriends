package com.cs2340.shoppingwithfriends.junitTests;

import android.test.InstrumentationTestCase;

/**
 * Created by jli on 3/29/15.
 */
public class junitTests extends InstrumentationTestCase {
    public void test() throws Exception {
        int x = 1;
        int y = 1;
        assertEquals(x,y);
    }
}
