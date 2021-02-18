package com.example.hitproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;

import com.example.hitproject.objects.Game;
import com.example.hitproject.fragments.GameInfo;
import com.example.hitproject.objects.Person;
import com.example.hitproject.R;
import com.example.hitproject.fragments.UpdateUserInfo;
import com.example.hitproject.fragments.UserInfoFragment;
import com.example.hitproject.fragments.gameSearch;
import com.example.hitproject.fragments.resultSearchGame;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private final String KEY = "UID"; //Key of passed argument
    private final String BUNDLEKEY = "KEY";
    private final String PERSON = "person";
    private final String GAME = "game";
    private final String GAMEID = "gameId";
    private FragmentManager manager = getSupportFragmentManager();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String key = getIntent().getStringExtra(KEY);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(key);

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Person value = dataSnapshot.getValue(Person.class);
                TextView userName = findViewById(R.id.greetingText);
                userName.setText("Welcome " + value.getName());
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        Bundle bundle = new Bundle();//create bundle to sent to fragment
        bundle.putString(BUNDLEKEY,key);
        Fragment fragment = manager.findFragmentById(R.id.fregment_container);

        //If no fragment was assigned before
        if (fragment == null) {
            fragment = new UserInfoFragment();
            fragment.setArguments(bundle);
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.fregment_container, fragment, "0").commit();
        }
    }

    //replace the existing fragment to userUpdate
    public void loadUserUpdate(Person oldPerson) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(PERSON, oldPerson);
        Fragment fragment = new UpdateUserInfo();
        fragment.setArguments(bundle);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fregment_container, fragment, "0").addToBackStack(null).commit();

    }

    //replace the existing fragment to searchGames
    public void loadSearchGames() {
        Fragment fragment = new gameSearch();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fregment_container, fragment, "0").addToBackStack(null).commit();

    }

    //update user info after info was changed
    public void userUpdate(Person person) {
        String key = getIntent().getStringExtra(KEY);
        Bundle bundle = new Bundle();//create bundle to sent to fragment
        bundle.putString(BUNDLEKEY,key);
        Fragment fragment = new UserInfoFragment();
        fragment.setArguments(bundle);
        // update with new value in database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(key);
        myRef.setValue(person);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fregment_container,fragment,"0").addToBackStack(null).commit();

    }

    //replace the existing fragment to resultSearchGames
    public void searchGames(Game game) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(GAME, game);
        Fragment fragment = new resultSearchGame();
        fragment.setArguments(bundle);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fregment_container, fragment, "0").addToBackStack(null).commit();
        
    }

    //replace the existing fragment to gameInfo
    public void loadGameInfo(String id) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(GAMEID, id);
        Fragment fragment = new GameInfo();
        fragment.setArguments(bundle);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fregment_container, fragment, "0").addToBackStack(null).commit();
    }
}