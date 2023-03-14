package com.tarastarasiuk.androidcourse.fragment;

import static com.tarastarasiuk.androidcourse.constant.AppConstant.RESULT_FRAGMENT_ARG;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.tarastarasiuk.androidcourse.R;

public class ResultFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        Bundle args = getArguments();
        String result = args.getString(RESULT_FRAGMENT_ARG);

        TextView textView = view.findViewById(R.id.result);
        textView.setText(result);

        return view;
    }

}