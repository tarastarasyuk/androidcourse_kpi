package com.tarastarasiuk.androidcourse.operation;

import com.tarastarasiuk.androidcourse.operation.impl.AdditionOperation;
import com.tarastarasiuk.androidcourse.operation.impl.DivisionOperation;
import com.tarastarasiuk.androidcourse.operation.impl.MultiplicationOperation;
import com.tarastarasiuk.androidcourse.operation.impl.SubtractionOperation;

public class OperationFactory {

    public static Operation extractOperation(String checkedRadioButtonId) {
        if (isTypeOf(OperationType.ADDITION, checkedRadioButtonId)) {
            return new AdditionOperation();
        } else if (isTypeOf(OperationType.SUBTRACTION, checkedRadioButtonId)) {
            return new SubtractionOperation();
        } else if (isTypeOf(OperationType.MULTIPLICATION, checkedRadioButtonId)) {
            return new MultiplicationOperation();
        } else if (isTypeOf(OperationType.DIVISION, checkedRadioButtonId)) {
            return new DivisionOperation();
        }
        throw new UnsupportedOperationException("Operation is not supported!");
    }

    private static boolean isTypeOf(OperationType operationType, String id) {
        return operationType.toString().equalsIgnoreCase(id);
    }

    private enum OperationType {
        ADDITION, DIVISION, MULTIPLICATION, SUBTRACTION
    }

}
