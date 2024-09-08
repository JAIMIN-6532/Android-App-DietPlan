package com.example.dietapp.Database.Callback;

import com.example.dietapp.Database.DataModel.User;
import com.google.firebase.database.DatabaseError;
import java.util.List;

public interface ReadDataCallback {
    void onDataLoaded(List<User> users);
    void onError(DatabaseError error);
}

