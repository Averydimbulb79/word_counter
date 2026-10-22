package sg.kithandkeen.wordcounter;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.webkit.JavascriptInterface;
import android.content.ClipboardManager;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.view.View;

public class MainActivity extends Activity {
    private WebView web;
    public class ClipboardBridge {
        @JavascriptInterface public String read() {
            ClipboardManager cb=(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
            if(cb!=null && cb.hasPrimaryClip()){
                ClipData data=cb.getPrimaryClip();
                if(data!=null && data.getItemCount()>0) {
                    CharSequence content=data.getItemAt(0).coerceToText(MainActivity.this);
                    return content==null?"":content.toString();
                }
            }
            return "";
        }
        @JavascriptInterface public void write(String value) {
            runOnUiThread(()->{
                ClipboardManager cb=(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                if(cb!=null) cb.setPrimaryClip(ClipData.newPlainText("Word Counter",value));
            });
        }
    }
    @Override public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.rgb(11,17,32));
        getWindow().setNavigationBarColor(Color.rgb(11,17,32));
        web=new WebView(this);
        web.setBackgroundColor(Color.rgb(11,17,32));
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setDomStorageEnabled(false);
        web.setWebViewClient(new WebViewClient());
        web.setWebChromeClient(new WebChromeClient());
        web.addJavascriptInterface(new ClipboardBridge(),"AndroidClipboard");
        setContentView(web);
        web.loadUrl("file:///android_asset/index.html");
    }
    @Override public void onBackPressed(){
        if(web.canGoBack()) web.goBack(); else super.onBackPressed();
    }
    @Override public void onDestroy(){
        web.removeJavascriptInterface("AndroidClipboard");
        web.destroy();
        super.onDestroy();
    }
}