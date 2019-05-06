package com.example.myloginapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,TaskContract.View{
    private static final int RC_SIGN_IN = 100;

    // 구글api클라이언트
    private GoogleSignInClient googleSignInClient;
    private GoogleApiClient mGoogleApiClient;
    // 파이어베이스 인증 객체 생성
    private FirebaseAuth mAuth;
    TaskContract.Presenter mainPresenter;
    // 구글  로그인 버튼
    //private SignInButton buttonGoogle;
    @BindView(R.id.guideEmail)
    TextView guideEmail;
    @BindView(R.id.writeEamil)
    EditText writeEmail;
    @BindView(R.id.guidePassword)
    TextView guidePassword;
    @BindView(R.id.writePassword)
    EditText writePassword;
    @BindView(R.id.logintButton)
    Button newMemberEnrollButton;
    @BindView(R.id.btn_googleSignIn)
    SignInButton buttonGoogle;
    @OnClick(R.id.logintButton)
    public void clickLogin(){
        String Id = writeEmail.getText().toString();
        String passWord = writePassword.getText().toString();
        mainPresenter.confirm(Id,passWord);


    }
    @OnClick(R.id.btn_googleSignIn)
    public void clickGoogleLogin(){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresenter = new MainPresenter(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
        mAuth = FirebaseAuth.getInstance();
        //buttonGoogle = findViewById(R.id.btn_googleSignIn);
        /*
        buttonGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent,RC_SIGN_IN);
            }
        });
        */
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            }
            else{
            }
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct){
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "인증 실패", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "구글 로그인 인증 성공", Toast.LENGTH_SHORT).show();
                            Intent afterlogin = new Intent(getApplicationContext(),MainscreenActivity.class);
                            startActivity(afterlogin);
                        }
                    }
                });
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }


    @Override
    public void showResult(int a) {
        if(a == 1) {
            Intent afterlogin = new Intent(getApplicationContext(), MainscreenActivity.class);
            startActivity(afterlogin);
            Toast.makeText(this,"로그인 성공공", Toast.LENGTH_LONG).show();
        }
        if(a == 0){
            Toast.makeText(this,"틀립니다", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void setPresenter(TaskContract.Presenter presenter) {

    }
}
