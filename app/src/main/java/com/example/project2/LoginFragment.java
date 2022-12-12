package com.example.project2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    Button loginButton, registerButton;
    EditText userName, userPassword;

    CallbackFragment callbackFragment;

    private RoomDatabaseUsers roomDatabaseUsers;
    private RoomDAO roomDAO;

    List<RoomUsers> roomUsersList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        userName = view.findViewById(R.id.userName);
        userPassword = view.findViewById(R.id.userPassword);
        loginButton = view.findViewById(R.id.loginButton);
        registerButton = view.findViewById(R.id.registerButton);

        roomDatabaseUsers = RoomDatabaseUsers.getInstance(getActivity().getApplicationContext());
        roomDAO = roomDatabaseUsers.getDAO();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roomUsersList = roomDAO.getAllUsers();
                //Log.i("gjhh", roomUsersList.get(9).getUserName());
                /*for (int i = 0; i < roomUsersList.size(); i++) {
                    RoomUsers roomUsers = roomUsersList.get(i);
                    //Log.i("jshfk", roomUsers.getUserName());
                    String uName = userName.getText().toString();
                    String uPassword = userPassword.getText().toString();
                    String rUserName = roomUsers.getUserName();
                    String rUserPassword = roomUsers.getUserPassword();

                    Log.i("size", String.valueOf(roomUsersList.size()));
                    if (uName.equals(rUserName) && uPassword.equals(rUserPassword)) {
                        Toast.makeText(getContext(), "User Login", Toast.LENGTH_SHORT).show();
                        Log.i("test", roomUsers.getUserHomeAddress());
                        Intent intent = new Intent(getActivity(), OrderActivity.class);
                        intent.putExtra("user-detail", roomUsers);
                        startActivity(intent);
                        break;
                    } else {
                        Toast.makeText(getContext(), "Error Login", Toast.LENGTH_SHORT).show();
                    }
                }*/
                for(RoomUsers user : roomUsersList) {
                    String uName = userName.getText().toString();
                    String uPassword = userPassword.getText().toString();
                    if (uName.equals(user.getUserName()) && uPassword.equals(user.getUserPassword())) {
                        Toast.makeText(getActivity(), "User Login", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), OrderActivity.class); //QR Scanner
                        startActivity(intent);

                        MainActivity.currentUser = userName.getText().toString();
                        return;

                    }
                }

                Toast.makeText(getActivity(), "Error Login", Toast.LENGTH_SHORT).show();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callbackFragment != null) {
                    callbackFragment.changeFragment();
                }
            }
        });
        return view;
    }

    public void setCallbackFragment(CallbackFragment callbackFragment) {
        this.callbackFragment = callbackFragment;
    }
}