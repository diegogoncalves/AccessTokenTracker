package com.ognid.acesstokentracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;


public class MainActivity extends ActionBarActivity {
    CallbackManager callbackManager;
    AccessTokenTracker accessTokenTracker;
    Fragment selection=new SelectionFragment2();
    Fragment login=new LoginFragment();
    String TAG=MainActivity.class.getSimpleName();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(this.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                Log.d(TAG,"onCurrentAccessTokenChanged");
                updateWithToken(currentAccessToken);

            }
        };
        setContentView(R.layout.activity_main);
    }



    
    
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"is tracking:"+accessTokenTracker.isTracking());

    }

    private void updateWithToken(AccessToken token) {
        if(token!=null)showFragment(selection);
        else showFragment(login);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        updateWithToken(AccessToken.getCurrentAccessToken());
    }

    void showFragment(Fragment f){
        getSupportFragmentManager().beginTransaction().replace(R.id.container,f).commit();
    }



}
