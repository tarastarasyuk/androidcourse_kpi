package com.tarastarasiuk.androidcourse.operation.impl;

import com.tarastarasiuk.androidcourse.exception.OperationPerformException;
import com.tarastarasiuk.androidcourse.operation.Operation;

public class DivisionOperation implements Operation {
    @Override
    public String performOperation(Double arg1, Double arg2) throws RuntimeException {
        if (arg2 == 0) {
            throw new OperationPerformException("Division by 0 is not allowed! Change your second parameter!");
        }
        return Double.toString(arg1 / arg2);
    }
}
