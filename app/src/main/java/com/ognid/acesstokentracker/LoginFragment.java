package com.ognid.acesstokentracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;



public class LoginFragment extends Fragment {
    LoginButton loginButton;
    CallbackManager callbackManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        callbackManager = CallbackManager.Factory.create();
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends","user_photos");
        // If using in a fragment
        loginButton.setFragment(this);
        // Other app specific specialization

        return view;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }



}
