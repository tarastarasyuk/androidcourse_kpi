package com.tarastarasiuk.androidcourse;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tarastarasiuk.androidcourse.exception.OperationPerformException;
import com.tarastarasiuk.androidcourse.operation.Operation;
import com.tarastarasiuk.androidcourse.operation.OperationFactory;

public class MainActivity extends AppCompatActivity {

    private EditText editText1, editText2, resultEditText;
    private RadioGroup operationRadioGroup;
    private Button calculateButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        resultEditText = findViewById(R.id.result);

        operationRadioGroup = findViewById(R.id.operation_group);

        calculateButton = findViewById(R.id.calculate_button);
        calculateButton.setOnClickListener(v -> calculate(editText1, editText2, operationRadioGroup, resultEditText));
    }

    private void calculate(EditText editText1, EditText editText2, RadioGroup operationRadioGroup, EditText resultEditText) {
        // cast to double
        Double editText1Number = Double.valueOf(editText1.getText().toString());
        Double editText2Number = Double.valueOf(editText2.getText().toString());

        // extract operation type
        String checkoutRadioButtonId = getCheckedRadioButtonId(operationRadioGroup);
        Operation operation = OperationFactory.extractOperation(checkoutRadioButtonId);

        try {
            // perform operation
            String result = operation.performOperation(editText1Number, editText2Number);
            // set result
            resultEditText.setText(result);
        } catch (OperationPerformException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private String getCheckedRadioButtonId(RadioGroup radioGroup) {
        return getResources().getResourceEntryName(radioGroup.getCheckedRadioButtonId());
    }

}
