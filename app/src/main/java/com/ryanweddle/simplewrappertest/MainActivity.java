package com.ryanweddle.simplewrappertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
import android.content.Context;

import com.ryanweddle.sparksdkwrapper.SparkCall;

public class MainActivity extends AppCompatActivity {

    private WebView myWebView;

    private Button mCallButton;
    private EditText mCallEdit;
    private EditText mTokenEdit;

    public static final String  CLASS_TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        myWebView = (WebView)findViewById(R.id.splash_webview);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.setWebChromeClient(new WebChromeClient());
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.addJavascriptInterface(new WebAppInterface(this),"Android");
        myWebView.loadUrl("file:///android_asset/www/index.html");


        /*
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCallButton = findViewById(R.id.button_call);
        mCallEdit = findViewById(R.id.text_callee);
        mTokenEdit = findViewById(R.id.text_jwt);

        mTokenEdit.setText(DevConstants.EXAMPLE_JWT);
        mCallEdit.setText(DevConstants.EXAMPLE_SPARKID);

        mCallButton.setOnClickListener(view -> {
            Log.i(CLASS_TAG, "Call Button Pressed");

            Intent intent = new Intent(MainActivity.this, SparkCall.class);
            intent.putExtra(SparkCall.INTENT_CALLEE, mCallEdit.getText().toString());
            intent.putExtra(SparkCall.INTENT_JWT, mTokenEdit.getText().toString());

            startActivity(intent);

        });
        */

    }

    @Override
    public void onBackPressed() {
        if(myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    //try WebAppInterface code here -

    public class WebAppInterface {
        Context mContext;

        /**
         * Instantiate the interface and set the context
         */
        WebAppInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void StartDemo (String jot, String uri)
        {
            //Toast.makeText(mContext,jot, Toast.LENGTH_SHORT).show();
            Toast.makeText(mContext,"Received JWT from AWS Lambda",Toast.LENGTH_SHORT).show();
            Log.i(CLASS_TAG, "Call Button Pressed");
            Log.i(CLASS_TAG, "The context is - "+ mContext);
            Intent intent = new Intent(mContext, SparkCall.class);
            intent.putExtra(SparkCall.INTENT_CALLEE, uri);
            intent.putExtra(SparkCall.INTENT_JWT, jot);
            Log.i(CLASS_TAG, "The intent is - "+ intent);
            /*
            EditText editText = (EditText) findViewById(R.id.dummy_view_text);
            String newmessage = editText.getText().toString();
            intent.putExtra(message, newmessage);
            */
            startActivity (intent);

        }

        /*
        @JavascriptInterface
        public void moveToNextScreen() {
            Intent intRef = new Intent(LoginActivity.this, SecondActivity.class);
            startActivity(intRef);
            //Log.e("banji", "Clicked");
        }
        */
    }
}