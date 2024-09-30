//package com.example.dietapp.Database;
//
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//
//
//import com.example.dietapp.Database.Callback.FindByModel;
//import com.example.dietapp.Database.Callback.InsertModel;
//import com.example.dietapp.Database.Callback.ReadDataCallback;
//import com.example.dietapp.Database.DataModel.User;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.concurrent.CountDownLatch;
//
//public class DataManager {
//    private static final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//    private static final DatabaseReference databaseReference = firebaseDatabase.getReference("User");
//
//    public static void InsertData(@NonNull User user, @NonNull InsertModel callback) {
//        HashMap<String, Object> dataset = new HashMap<>();
//        Log.d("TAG", "InsertData:1111111 ");
//        dataset.put("Name", user.getName());
//        dataset.put("Email", user.getEmail());
//        dataset.put("Password", user.getPassword());
//
//        String key = databaseReference.push().getKey();
//        dataset.put("Key", key);
//
//        if (key != null) {
//            databaseReference.child(key).setValue(dataset).addOnCompleteListener(task -> {
//                if(task.isComplete()){
//                    Log.d("hgsxb", "InsertData: taskjcbsb ");
//                    callback.onComplete();
//                } else if (task.isCanceled()) {
//                    callback.onError();
//                }
//            });
//        } else {
//            callback.onError();
//        }
//    }
//
//
//
//    // ASYNC CODE
//    /** @noinspection MismatchedQueryAndUpdateOfCollection*/
//    public static void ReadData(@NonNull ReadDataCallback callback) {
//        List<User> allUser = new ArrayList<>();
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
//                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
//                    // Convert the snapshot into a user object
//                    User userData = snapshot.getValue(User.class);
//                    allUser.add(userData);
//                }
//                callback.onDataLoaded(allUser);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                callback.onError(error);
//            }
//        });
//    }
//
//    // ASYNC CODE
//    public static void FindByGmail(@NonNull String email, @NonNull String password, @NonNull FindByModel callback) {
//        Query query = databaseReference.orderByChild("Email").equalTo(email);
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                boolean successful = false;
//                if (dataSnapshot.exists()) {
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                        User userData = snapshot.getValue(User.class);
//                        if (userData != null && email.equals(userData.getEmail()) && password.equals(userData.getPassword())) {
//                            successful = true;
//                            callback.onSuccess(userData);  // Notify the caller with the user data
//                            break; // We found a match, no need to check further
//                        }
//                    }
//                }
//                if (!successful) {
//                    callback.onFailure();  // Notify the caller that no matching user was found
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                callback.onFailure();  // Notify the caller that the operation was cancelled or failed
//                Log.w("Firebase", "Failed to fetch data.", databaseError.toException());
//            }
//        });
//    }
//
//    public static void FindByName(@NonNull String name, @NonNull String password, @NonNull FindByModel callback) {
//        Query query = databaseReference.orderByChild("Name").equalTo(name);
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                boolean successful = false;
//                if (dataSnapshot.exists()) {
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                        User userData = snapshot.getValue(User.class);
//                        if (userData != null && name.equals(userData.getName()) && password.equals(userData.getPassword())) {
//                            successful = true;
//                            callback.onSuccess(userData);  // Notify the caller with the user data
//                            break; // We found a match, no need to check further
//                        }
//                    }
//                }
//                if (!successful) {
//                    callback.onFailure();  // Notify the caller that no matching user was found
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                callback.onFailure();  // Notify the caller that the operation was cancelled or failed
//                Log.w("Firebase", "Failed to fetch data.", databaseError.toException());
//            }
//        });
//    }
//
//
//}

package com.example.dietapp.Database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.dietapp.Database.Callback.FindByModel;
import com.example.dietapp.Database.Callback.InsertModel;
import com.example.dietapp.Database.Callback.ReadDataCallback;
import com.example.dietapp.Database.DataModel.User;
import com.example.dietapp.Database.DataModel.UserDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataManager {
    private static final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private static final DatabaseReference userReference = firebaseDatabase.getReference("User");
    private static final DatabaseReference userDetailsReference = firebaseDatabase.getReference("UserDetails");

    // Insert a new user into the database
    public static void InsertData(@NonNull User user, @NonNull InsertModel callback) {
        HashMap<String, Object> dataset = new HashMap<>();
        dataset.put("name", user.getName());
        dataset.put("email", user.getEmail());
        dataset.put("password", user.getPassword());
        dataset.put("isFormSubmit",user.isFormSubmit());

        String key = userReference.push().getKey();
        dataset.put("key", key);

        if (key != null) {
            userReference.child(key).setValue(dataset).addOnCompleteListener(task -> {
                if (task.isComplete()) {
                    callback.onComplete();
                } else if (task.isCanceled()) {
                    callback.onError();
                }
            });
        } else {
            callback.onError();
        }
    }

    // Insert user details (for first-time registration)
    public static void insertUserDetails(@NonNull UserDetails userDetails, @NonNull InsertModel callback) {
        HashMap<String, Object> dataset = new HashMap<>();

        dataset.put("name",userDetails.getName());
        dataset.put("age",userDetails.getAge());
        dataset.put("gender",userDetails.getGender());
        dataset.put("weight",userDetails.getWeight());
        dataset.put("height",userDetails.getHeight());
        dataset.put("tweight",userDetails.getTargetWeight());
        dataset.put("userKey",userDetails.getUserkey());

        String key = userReference.push().getKey();
        dataset.put("key", key);

        assert key != null;
        userDetailsReference.child(key).setValue(dataset).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                userReference.child((userDetails.getUserkey())).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            User user = dataSnapshot.getValue(User.class);
                            if (user != null) {
                                user.setFormSubmit(true);
                                userReference.child(user.getKey()).setValue(user);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                callback.onComplete();
            } else {
                callback.onError();
            }
        });
    }

    // Retrieve user details by userId
    public static void getUserDetails(@NonNull String userId, @NonNull FindByModel callback) {
        userDetailsReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    UserDetails userDetails = dataSnapshot.getValue(UserDetails.class);
                    if (userDetails != null) {
                        callback.onSuccess(userDetails);
                    } else {
                        callback.onFailure();
                    }
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onFailure();
            }
        });
    }


    public static void FindByUserkey(@NonNull String key, @NonNull FindByModel callback) {
        Query query = userDetailsReference.orderByChild("userkey").equalTo(key);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // key exists, so return success
                    callback.onSuccess(null);
                } else {
                    // key does not exist, so return failure
                    callback.onFailure();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onFailure();
                Log.w("Firebase", "Failed to fetch data.", databaseError.toException());
            }
        });
    }


    // Read all users (ASYNC CODE)
    public static void ReadData(@NonNull ReadDataCallback callback) {
        List<User> allUser = new ArrayList<>();

        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    User userData = snapshot.getValue(User.class);
                    allUser.add(userData);
                }
                callback.onDataLoaded(allUser);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onError(error);
            }
        });
    }

//    // Find by Gmail
//    public static void FindByGmail(@NonNull String email, @NonNull String password, @NonNull FindByModel callback) {
//        Query query = userReference.orderByChild("Email").equalTo(email);
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                boolean successful = false;
//                if (dataSnapshot.exists()) {
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                        User userData = snapshot.getValue(User.class);
//                        if (userData != null && email.equals(userData.getEmail()) && password.equals(userData.getPassword())) {
//                            successful = true;
//                            callback.onSuccess(userData);
//                            break;
//                        }
//                    }
//                }
//                if (!successful) {
//                    callback.onFailure();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                callback.onFailure();
//                Log.w("Firebase", "Failed to fetch data.", databaseError.toException());
//            }
//        });
//    }
// Check if the email exists
public static void FindByGmail(@NonNull String email, @NonNull FindByModel callback) {
    Query query = userReference.orderByChild("email").equalTo(email);
    query.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                // Email exists, so return success
                callback.onSuccess(null);
            } else {
                // Email does not exist, so return failure
                callback.onFailure();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            callback.onFailure();
            Log.w("Firebase", "Failed to fetch data.", databaseError.toException());
        }
    });
}

    public static void CheckLoginCredentials(@NonNull String email, @NonNull String password, @NonNull FindByModel callback) {
        // Query the database for the email
        Query query = userReference.orderByChild("email").equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean loginSuccess = false;

                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User user = snapshot.getValue(User.class);

                        // Check if the retrieved user has the same password
                        if (user != null && user.getPassword().equals(password)) {
                            loginSuccess = true;
                            callback.onSuccess(user); // If credentials are correct, call onSuccess
                            break;
                        }
                    }
                }

                if (!loginSuccess) {
                    // If login fails, call onFailure
                    callback.onFailure();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any cancellation or database errors
                callback.onFailure();
                Log.w("Firebase", "Failed to check login credentials.", databaseError.toException());
            }
        });
    }




    public static void checkUserDetails(String userId, FindByModel callback) {
        DatabaseReference userDetailsRef = firebaseDatabase.getReference("UserDetails").child(userId);

        userDetailsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // User details are found, pass the data back through the callback
                    UserDetails userDetails = dataSnapshot.getValue(UserDetails.class);
                    callback.onSuccess(userDetails);
                } else {
                    // No user details found
                    callback.onFailure();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onFailure();
            }
        });
    }


    // Find by Name
    public static void FindByName(@NonNull String name, @NonNull String password, @NonNull FindByModel callback) {
        Query query = userReference.orderByChild("name").equalTo(name);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean successful = false;
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User userData = snapshot.getValue(User.class);
                        if (userData != null && name.equals(userData.getName()) && password.equals(userData.getPassword())) {
                            successful = true;
                            callback.onSuccess(userData);
                            break;
                        }
                    }
                }
                if (!successful) {
                    callback.onFailure();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onFailure();
                Log.w("Firebase", "Failed to fetch data.", databaseError.toException());
            }
        });
    }
}
