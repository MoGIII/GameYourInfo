package com.example.hitproject.fragments;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hitproject.objects.Game;
import com.example.hitproject.R;
import com.example.hitproject.activities.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link resultSearchGame#newInstance} factory method to
 * create an instance of this fragment.
 */
public class resultSearchGame extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private final String GAME = "game";

    public resultSearchGame() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment resultSearchGame.
     */
    // TODO: Rename and change types and number of parameters
    public static resultSearchGame newInstance(String param1, String param2) {
        resultSearchGame fragment = new resultSearchGame();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_result_search_game, container, false);

        Game game = (Game)this.getArguments().getSerializable(GAME);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("games");

        LinearLayout ll = (LinearLayout)view.findViewById(R.id.gameListBut);

            // Read from the database
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.

                    //Iterate over all the games and find the ones that are relevant for our search
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Game newGame = snapshot.getValue(Game.class);

                        String name = newGame.getName();

                        //Replace the search parameters to empty strings (empty string means all values are wanted)
                        if (game.getName().equals("")) {
                            newGame.setName("");
                        }
                        if (game.getGenre().equals("")) {
                            newGame.setGenre("");
                        }
                        if (game.getDeveloper().equals("")) {
                            newGame.setDeveloper("");
                        }
                        if ( game.getReleaseYear().equals("")) {
                            newGame.setReleaseYear("");
                        }

                        //Turn all search strings to upper case for case insensitive search
                        game.setName(game.getName().toUpperCase());
                        newGame.setName(newGame.getName().toUpperCase());

                        game.setGenre(game.getGenre().toUpperCase());
                        newGame.setGenre(newGame.getGenre().toUpperCase());

                        game.setDeveloper(game.getDeveloper().toUpperCase());
                        newGame.setDeveloper(newGame.getDeveloper().toUpperCase());

                        game.setReleaseYear(game.getReleaseYear().toUpperCase());
                        newGame.setReleaseYear(newGame.getReleaseYear().toUpperCase());

                        //search the strings in the game from the Database
                        if (newGame.getName().contains(game.getName()) && newGame.getGenre().contains(game.getGenre()) && newGame.getDeveloper().contains(game.getDeveloper()) && newGame.getReleaseYear().contains(game.getReleaseYear())) {

                            //If the search strings correspond to the game - dynamically create a button to the GameInfo
                            Button btn = createButton(view,name);
                            ll.addView(btn);//Add the button to the layout
                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    MainActivity mainActivity = (MainActivity) getActivity();
                                    mainActivity.loadGameInfo(snapshot.getKey());
                                }
                            });
                        }
                    }
                }
                @Override
                public void onCancelled(DatabaseError error) {
                    Toast.makeText(view.getContext(), "Error occurred while trying to connect the database",Toast.LENGTH_LONG).show();
                }
            });
        return view;
    }

    public Button createButton (View view, String name){
        Button btn = new Button(view.getContext());
        btn.setText(name);
        btn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18.f);
        //Style the button dynamically
        btn.setTextColor(Color.WHITE);
        btn.setBackgroundResource(R.drawable.buttonstyle);
        //Set Button parameters
        btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        return btn;
    }

}