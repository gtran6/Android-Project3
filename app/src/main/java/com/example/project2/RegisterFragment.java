package com.example.project2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    Button loginButton, registerButton;
    EditText userName, userPassword, name, userHomeAddress, userWorkAddress;

    CallbackFragment callbackFragment;

    private RoomDatabaseUsers roomDatabaseUsers;
    private RoomDAO roomDAO;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.register_fragment, container, false);
        userName = view.findViewById(R.id.userName);
        userPassword = view.findViewById(R.id.userPassword);
        loginButton = view.findViewById(R.id.loginButton);
        registerButton = view.findViewById(R.id.registerButton);
        name = view.findViewById(R.id.name);
        userHomeAddress = view.findViewById(R.id.userHomeAddress);
        userWorkAddress = view.findViewById(R.id.userWorkAddress);

        roomDatabaseUsers = RoomDatabaseUsers.getInstance(getActivity().getApplicationContext());
        roomDAO = roomDatabaseUsers.getDAO();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RoomUsers roomUsers = new RoomUsers(0,
                        userName.getText().toString(),
                        userPassword.getText().toString(),
                        name.getText().toString(),
                        userHomeAddress.getText().toString(),
                        userWorkAddress.getText().toString());
                roomDAO.insert(roomUsers);
                Toast.makeText(getContext(), "User Registered", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public void setCallbackFragment(CallbackFragment callbackFragment) {
        this.callbackFragment = callbackFragment;
    }
}