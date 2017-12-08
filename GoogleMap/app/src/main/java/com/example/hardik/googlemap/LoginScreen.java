package com.example.hardik.googlemap;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginScreen extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    GoogleApiClient mGoogleApiClient;
    TextView pname,pgivenname,pemail,pid;
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener mAuthListener;
    private int RC_SIGN_IN=9001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        auth=FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!=null){
                    Log.d("Auth","user logged in"+user.getEmail());
                }
                else {
                    Log.d("Auth","user logged out");
                }
            }
        };
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.default_web_client_id))
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
      /*  pname= (TextView) findViewById(R .id.tvPersonName);
        pgivenname= (TextView) findViewById(R.id.tvPersonGivenName);
        pemail= (TextView) findViewById(R.id.tvPersonEmail);
        pid= (TextView) findViewById(R.id.tvPersonId);


        // Build a GoogleApiClient with access to the Google Sign-In API and the
// options specified by gso.
      */

    }

    public void onLogin(View view) {
        Intent i=new Intent(LoginScreen.this,MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void signIn(View view) {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {

        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            firebaseAuth(acct);
            updateinfo(acct);
        } else {
            // Signed out, show unauthenticated UI.
            Toast.makeText(this,"Cannot get Details",Toast.LENGTH_SHORT);
        }
    }

    private void firebaseAuth(GoogleSignInAccount acct) {
        AuthCredential credetial= GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        auth.signInWithCredential(credetial).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("Auth","sign in with credentials"+task.isSuccessful());
            }
        });
    }

    private void updateinfo(GoogleSignInAccount acct) {
        String personName = acct.getDisplayName();
        String personGivenName = acct.getGivenName();

        String personEmail = acct.getEmail();
        String personId = acct.getId();
        pname.setText(personName);
        pgivenname.setText(personGivenName);
        pemail.setText(personEmail);
        pid.setText(personId);
    }
}
