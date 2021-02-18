package com.example.hitproject.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hitproject.objects.Person;
import com.example.hitproject.R;
import com.example.hitproject.activities.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserInfoFragment extends Fragment {
    
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final String BUNDLEKEY = "KEY";
    private Person oldPerson;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment userLogin.
     */
    // TODO: Rename and change types and number of parameters
    public static UserInfoFragment newInstance(String param1, String param2) {
        UserInfoFragment fragment = new UserInfoFragment();
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
        View view =  inflater.inflate(R.layout.fragment_user_info, container, false);
        String key = this.getArguments().getString(BUNDLEKEY);// get bundle from MainActivity

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(key);
        // Read from the database
        Button update = view.findViewById(R.id.updateProfileButton);
        Button search = view.findViewById(R.id.gameSearchButton);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                //Display user data in the relevant TextView
                oldPerson = dataSnapshot.getValue(Person.class);
                TextView userEmail = view.findViewById(R.id.emailTextInfo);
                userEmail.append(" " + oldPerson.getEmail());
                TextView userAddress = view.findViewById(R.id.addressTextInfo);
                userAddress.append(" " + oldPerson.getAddress());
                TextView userPhone = view.findViewById(R.id.phoneTextInfo);
                userPhone.append(" " + oldPerson.getPhone());
                TextView userDate = view.findViewById(R.id.dateTextInfo);
                userDate.append(" " + oldPerson.getBirthDate());
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(view.getContext(),R.string.failed_db_connection ,Toast.LENGTH_LONG).show();
            }
        });

        //Go to UpdateUserInfo fragment
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.loadUserUpdate(oldPerson);
            }
        });

        //Go to gameSearch Fragment
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.loadSearchGames();
            }
        });
        return view;
    }
}