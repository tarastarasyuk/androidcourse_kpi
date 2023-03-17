package com.tarastarasiuk.androidcourse.activity;

import static com.tarastarasiuk.androidcourse.util.DateFormatterUtil.formatDate;
import static com.tarastarasiuk.androidcourse.util.StringFormatterUtil.restrictTextLength;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tarastarasiuk.androidcourse.R;
import com.tarastarasiuk.androidcourse.database.DatabaseRepository;
import com.tarastarasiuk.androidcourse.database.impl.DefaultDatabaseRepository;
import com.tarastarasiuk.androidcourse.model.TrackedResultModel;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_history);
        tableLayout = findViewById(R.id.tableLayout);

        DatabaseRepository databaseRepository = DefaultDatabaseRepository.getInstance(this);
        List<TrackedResultModel> trackedResultList = databaseRepository.getAllTrackedResults();

        if (trackedResultList.isEmpty()) {
            showEmptyMessage();
        } else {
            trackedResultList.forEach(trackedResultModel -> addEntryToTable(trackedResultModel, tableLayout));
        }
    }

    private void addEntryToTable(TrackedResultModel trackedResultModel, TableLayout tableLayout) {
        TableRow row = new TableRow(this);

        TextView dateView = new TextView(this);
        dateView.setText(formatDate(trackedResultModel.getDate()));
        row.addView(dateView);

        TextView resultView = new TextView(this);
        resultView.setText(restrictTextLength(trackedResultModel.getResult()));
        row.addView(resultView);

        tableLayout.addView(row);
    }

    public void switchOnMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void clearDb(View view) {
        DefaultDatabaseRepository.getInstance(getApplicationContext()).deleteAllTrackedResults();
        Toast.makeText(getApplicationContext(), "All the TrackedResult data was deleted from database!", Toast.LENGTH_LONG).show();
        showEmptyMessage();
    }

    private void showEmptyMessage() {
        TextView textView = new TextView(this);
        textView.setText("No TrackedResult date found!");
        tableLayout.removeAllViews();
        tableLayout.addView(textView);
    }
}