package android.task2.uttam.mycheckin;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class LocationMap extends AppCompatActivity {

    WebView web2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_location);

        web2 = findViewById(R.id.webview2);
        web2.getSettings().setJavaScriptEnabled(true);
        web2.setWebViewClient(new WebViewClient());
        web2.loadUrl("https://www.google.com/map");

    }

    public static Intent newIntent(Context packageContent){

        Intent intent = new Intent(packageContent, Location.class);
        return intent;

    }

}
