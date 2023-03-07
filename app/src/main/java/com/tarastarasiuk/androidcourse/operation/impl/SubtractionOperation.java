package com.tarastarasiuk.androidcourse.operation.impl;

import com.tarastarasiuk.androidcourse.operation.Operation;

public class SubtractionOperation implements Operation {
    @Override
    public String performOperation(Double arg1, Double arg2) {
        return Double.toString(arg1 - arg2);
    }
}
