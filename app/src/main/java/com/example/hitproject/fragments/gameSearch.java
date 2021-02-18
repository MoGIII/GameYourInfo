package com.example.hitproject.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hitproject.objects.Game;
import com.example.hitproject.R;
import com.example.hitproject.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link gameSearch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class gameSearch extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public gameSearch() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment searchGames.
     */
    // TODO: Rename and change types and number of parameters
    public static gameSearch newInstance(String param1, String param2) {
        gameSearch fragment = new gameSearch();
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
        View view =  inflater.inflate(R.layout.fragment_game_search, container, false);

        Button b = view.findViewById(R.id.searchGamesButton);
        //inner class
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Group all the search terms into a Game Instance and send to main for search
                Game game = new Game();
                
                TextView gameName = view.findViewById(R.id.searchNameText);
                String name = gameName.getText().toString();

                TextView gameGenre = view.findViewById(R.id.searchGenreText);
                String genre = gameGenre.getText().toString();

                TextView gamesDeveloper = view.findViewById(R.id.searchDeveloperText);
                String developer = gamesDeveloper.getText().toString();

                TextView gamesReleaseYear = view.findViewById(R.id.searchReleaseYearText);
                String releaseYear = gamesReleaseYear.getText().toString();

                if(!name.equals("")){
                    game.setName(name);
                }

                if(!genre.equals("")){
                    game.setGenre(genre);
                }

                if(!developer.equals("")){
                    game.setDeveloper(developer);
                }

                if(!releaseYear.equals("")){
                    game.setReleaseYear(releaseYear);
                }

                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.searchGames(game);
//

            }
        });
        return view;
    }
}