package com.example.hitproject.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hitproject.objects.Person;
import com.example.hitproject.R;
import com.example.hitproject.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateUserInfo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateUserInfo extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final String PERSON = "person";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UpdateUserInfo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment userUpdate.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateUserInfo newInstance(String param1, String param2) {
        UpdateUserInfo fragment = new UpdateUserInfo();
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
        View view =  inflater.inflate(R.layout.fragment_update_user_info, container, false);
        Person person = (Person) this.getArguments().getSerializable(PERSON);
        Button b = view.findViewById(R.id.updateUserButton);
        //inner class
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Group all the update terms into a Person Instance and send to main for update
                TextView userAddress = view.findViewById(R.id.addressUpdText);
                String address = userAddress.getText().toString();
                if(!address.equals("")){
                    person.setAddress(address);
                }
                TextView userPhone = view.findViewById(R.id.phoneUpdText);
                String phone = userPhone.getText().toString();
                if(!phone.equals("")){
                    person.setPhone(phone);
                }
                TextView userDate = view.findViewById(R.id.dateUpdText);
                String date = userDate.getText().toString();
                if(!date.equals("")){
                    person.setBirthDate(date);
                }
                TextView userName = view.findViewById(R.id.personNameUpdText);
                String name = userName.getText().toString();
                if(!name.equals("")){
                    person.setName(name);
                }
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.userUpdate(person);
            }
        });
        return view;
    }
}