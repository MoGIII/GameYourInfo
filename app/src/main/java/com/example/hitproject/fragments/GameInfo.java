package com.example.hitproject.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hitproject.objects.Game;
import com.example.hitproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameInfo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameInfo extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final String GAMEID = "gameId";
    private Game game;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public GameInfo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameInfo.
     */
    // TODO: Rename and change types and number of parameters
    public static GameInfo newInstance(String param1, String param2) {
        GameInfo fragment = new GameInfo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_info, container, false);

        String key = this.getArguments().getString(GAMEID);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("games").child(key);

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                game = dataSnapshot.getValue(Game.class);

                //Write the info from the DB to the TextViews
                TextView gameName = view.findViewById(R.id.gameInfoNameText);
                gameName.append(" " + game.getName());

                TextView gameYearOfRelease = view.findViewById(R.id.gameInfoReleaseYearText);
                gameYearOfRelease.append(" " + game.getReleaseYear());

                TextView gameGenre = view.findViewById(R.id.gameInfoGenreText);
                gameGenre.append(" " + game.getGenre());

                TextView gamePlatform = view.findViewById(R.id.gameInfoPlatformText);
                gamePlatform.append(" " + game.getPlatform());

                TextView gameDeveloper = view.findViewById(R.id.gameInfoDeveloperText);
                gameDeveloper.append(" " + game.getDeveloper());

                TextView gameDistributor = view.findViewById(R.id.gameInfoDistributorText);
                gameDistributor.append(" " + game.getDistributor());

                TextView gameSummary = view.findViewById(R.id.gameInfoDescriptionText);
                gameSummary.append(game.getDescription());

                //Create a button to view the trailer
                Button trailer = view.findViewById(R.id.gameInfoTrailerButton);

                trailer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Allow to display the trailer in Youtube
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse(game.getTrailer()));
                        startActivity(intent);
                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(view.getContext(),R.string.failed_db_connection ,Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}