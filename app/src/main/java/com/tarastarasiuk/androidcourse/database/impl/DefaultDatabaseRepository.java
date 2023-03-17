package com.tarastarasiuk.androidcourse.database.impl;

import static com.tarastarasiuk.androidcourse.constant.AppConstant.DatabaseDefinition.CREATE_TABLE_TRACKED_RESULT_QUERY;
import static com.tarastarasiuk.androidcourse.constant.AppConstant.DatabaseDefinition.DATABASE_NAME;
import static com.tarastarasiuk.androidcourse.constant.AppConstant.DatabaseDefinition.DATABASE_VERSION;
import static com.tarastarasiuk.androidcourse.constant.AppConstant.DatabaseDefinition.DATE_KEY;
import static com.tarastarasiuk.androidcourse.constant.AppConstant.DatabaseDefinition.DROP_TRACKED_RESULT_TABLE_QUERY;
import static com.tarastarasiuk.androidcourse.constant.AppConstant.DatabaseDefinition.ID_KEY;
import static com.tarastarasiuk.androidcourse.constant.AppConstant.DatabaseDefinition.RESULT_KEY;
import static com.tarastarasiuk.androidcourse.constant.AppConstant.DatabaseDefinition.SELECT_TRACKED_RESULT_PROJECTION;
import static com.tarastarasiuk.androidcourse.constant.AppConstant.DatabaseDefinition.TRACKED_RESULT_TABLE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import com.tarastarasiuk.androidcourse.database.DatabaseRepository;
import com.tarastarasiuk.androidcourse.model.TrackedResultModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DefaultDatabaseRepository extends SQLiteOpenHelper implements DatabaseRepository {

    private static DefaultDatabaseRepository dbInstance;

    public static synchronized DefaultDatabaseRepository getInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = new DefaultDatabaseRepository(context.getApplicationContext());
        }
        return dbInstance;
    }

    public DefaultDatabaseRepository(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TRACKED_RESULT_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL(DROP_TRACKED_RESULT_TABLE_QUERY);
            onCreate(db);
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    @Override
    public void createTrackedResult(TrackedResultModel trackedResultModel) {
        SQLiteDatabase writableDb = dbInstance.getWritableDatabase();

        writableDb.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(DATE_KEY, trackedResultModel.getDate().getTime());
            values.put(RESULT_KEY, trackedResultModel.getResult());

            writableDb.insertOrThrow(TRACKED_RESULT_TABLE, null, values);

            writableDb.setTransactionSuccessful();
        } catch (Exception e) {
            System.err.println("Error while trying to add tracked result to database");
        } finally {
            writableDb.endTransaction();
        }
    }

    @Override
    public List<TrackedResultModel> getAllTrackedResults() {
        List<TrackedResultModel> trackedResultModelList = new ArrayList<>();

        SQLiteDatabase readableDb = dbInstance.getReadableDatabase();
        Cursor cursor = readableDb.query(TRACKED_RESULT_TABLE, SELECT_TRACKED_RESULT_PROJECTION, null, null, null, null, null);

        while (cursor.moveToNext()) {
            TrackedResultModel trackedResultModel = retrieveTrackedResultModel(cursor);
            trackedResultModelList.add(trackedResultModel);
        }

        return trackedResultModelList;
    }

    @Override
    public void deleteAllTrackedResults() {
        SQLiteDatabase writableDb = dbInstance.getWritableDatabase();

        writableDb.beginTransaction();
        try {
            writableDb.delete(TRACKED_RESULT_TABLE, null, null);

            writableDb.setTransactionSuccessful();
        } catch (Exception e) {
            System.err.println("Error while trying to delete all posts and users");
        } finally {
            writableDb.endTransaction();
        }
    }

    @NonNull
    private TrackedResultModel retrieveTrackedResultModel(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndexOrThrow(ID_KEY));
        String result = cursor.getString(cursor.getColumnIndexOrThrow(RESULT_KEY));
        Date date = new Date(cursor.getLong(cursor.getColumnIndexOrThrow(DATE_KEY)));

        return new TrackedResultModel(id, result, date);
    }

}
