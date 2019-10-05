// Help
// Used to redirect wikihow.com
package android.task2.uttam.mycheckin;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;



public class Help extends AppCompatActivity {

    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_help);

        web = findViewById(R.id.webview);
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl("https://www.wikihow.com/Check-In-On-Facebook");

    }

    public static Intent newIntent(Context packageContent){

        Intent intent = new Intent(packageContent, Help.class);
        return intent;

    }

}
