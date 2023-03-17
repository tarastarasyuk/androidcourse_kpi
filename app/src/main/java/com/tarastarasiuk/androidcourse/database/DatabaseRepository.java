package com.tarastarasiuk.androidcourse.database;

import com.tarastarasiuk.androidcourse.model.TrackedResultModel;

import java.util.List;

public interface DatabaseRepository {

    void createTrackedResult(TrackedResultModel trackedResultModel);

    List<TrackedResultModel> getAllTrackedResults();

    void deleteAllTrackedResults();

}
