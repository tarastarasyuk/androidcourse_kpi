package com.tarastarasiuk.androidcourse.fragment;

import static android.text.TextUtils.isEmpty;
import static com.tarastarasiuk.androidcourse.constant.AppConstant.RESULT_FRAGMENT_ARG;
import static java.util.Objects.isNull;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.tarastarasiuk.androidcourse.R;
import com.tarastarasiuk.androidcourse.activity.listener.OnFragmentInteractionListener;
import com.tarastarasiuk.androidcourse.database.DatabaseRepository;
import com.tarastarasiuk.androidcourse.database.impl.DefaultDatabaseRepository;
import com.tarastarasiuk.androidcourse.exception.OperationPerformException;
import com.tarastarasiuk.androidcourse.model.TrackedResultModel;
import com.tarastarasiuk.androidcourse.operation.Operation;
import com.tarastarasiuk.androidcourse.operation.OperationFactory;
import com.tarastarasiuk.androidcourse.util.FragmentUtil;

import java.util.Date;

public class FormFragment extends Fragment {

    private EditText editText1, editText2;
    private RadioGroup operationRadioGroup;
    private static final String REQUIRED_FIELDS_ERROR_MSG = "All fields are required, please fill them in";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editText1 = view.findViewById(R.id.editText1);
        editText2 = view.findViewById(R.id.editText2);

        operationRadioGroup = view.findViewById(R.id.operation_group);

        Button calculateButton = view.findViewById(R.id.calculate_button);
        calculateButton.setOnClickListener(v -> calculate());
    }

    public void calculate() {
        boolean validationResult = validateInputData(editText1, editText2, operationRadioGroup);

        if (validationResult) {
            Double editText1Number = Double.valueOf(editText1.getText().toString());
            Double editText2Number = Double.valueOf(editText2.getText().toString());

            String checkoutRadioButtonId = getCheckedRadioButtonId(operationRadioGroup);
            Operation operation = OperationFactory.extractOperation(checkoutRadioButtonId);

            try {
                String result = operation.performOperation(editText1Number, editText2Number);

                sendResultToResultFragment(result, getActivity().getSupportFragmentManager(),
                        ((OnFragmentInteractionListener) getActivity()).getFragmentPlaceholderViewId());

                trackResultToDb(getContext(), result);
            } catch (OperationPerformException e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void trackResultToDb(Context context, String result) {
        DatabaseRepository DatabaseRepository = DefaultDatabaseRepository.getInstance(context);
        DatabaseRepository.createTrackedResult(new TrackedResultModel(result, new Date()));
    }

    private boolean validateInputData(EditText editText1, EditText editText2, RadioGroup operationRadioGroup) {
        if (isEmpty(editText1.getEditableText()) || isEmpty(editText2.getEditableText()) || isNull(operationRadioGroup.getCheckedRadioButtonId())) {
            Toast.makeText(getContext(), REQUIRED_FIELDS_ERROR_MSG, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void sendResultToResultFragment(String result, FragmentManager fragmentManager, int containerViewId) {
        Bundle args = new Bundle();
        args.putString(RESULT_FRAGMENT_ARG, result);
        ResultFragment resultFragment = new ResultFragment();
        resultFragment.setArguments(args);
        FragmentUtil.replaceFragmentFor(fragmentManager, resultFragment, containerViewId);
    }

    private String getCheckedRadioButtonId(RadioGroup radioGroup) {
        return getResources().getResourceEntryName(radioGroup.getCheckedRadioButtonId());
    }
}