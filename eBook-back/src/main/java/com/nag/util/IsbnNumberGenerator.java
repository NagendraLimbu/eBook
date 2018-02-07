package com.nag.util;

import java.util.Random;

/**
 * Created by nagendra on 1/14/18.
 */
public class IsbnNumberGenerator implements NumberGenerator{
    @Override
    public String generateNumber() {
        return "123-345" + Math.abs(new Random().nextInt());
    }
}
