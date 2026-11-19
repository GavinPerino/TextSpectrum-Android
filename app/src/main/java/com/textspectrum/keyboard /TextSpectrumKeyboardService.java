package com.textspectrum.keyboard;

import android.inputmethodservice.InputMethodService;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TextSpectrumKeyboardService extends InputMethodService {

    private WebView webView;
    private int currentColor = 0xFF000000;

    @Override
    public View onCreateInputView() {
        webView = new WebView(this);
        WebSettings s = webView.getSettings();
        s.setJavaScriptEnabled(true);
        // OFFLINE LOCKS - keeps your privacy policy true
        s.setBlockNetworkLoads(true);
        s.setBlockNetworkImage(true);
        s.setAllowFileAccess(true);
        s.setAllowContentAccess(false);

        webView.setWebViewClient(new WebViewClient());
        // Bridge: HTML can call AndroidKeyboard.sendKey("a")
        webView.addJavascriptInterface(new KeyboardBridge(), "AndroidKeyboard");
        // Load your local file, no internet
        webView.loadUrl("file:///android_asset/keyboard.html");
        return webView;
    }

    // This is what talks to WhatsApp, exactly like Gboard does
    public class KeyboardBridge {
        @JavascriptInterface
        public void sendKey(String text) {
            InputConnection ic = getCurrentInputConnection();
            if (ic != null) ic.commitText(text, 1);
        }
        @JavascriptInterface
        public void deleteKey() {
            InputConnection ic = getCurrentInputConnection();
            if (ic != null) ic.deleteSurroundingText(1, 0);
        }
        @JavascriptInterface
        public void enterKey() {
            InputConnection ic = getCurrentInputConnection();
            if (ic != null) ic.sendKeyEvent(new android.view.KeyEvent(
                android.view.KeyEvent.ACTION_DOWN,
                android.view.KeyEvent.KEYCODE_ENTER));
        }
        @JavascriptInterface
        public void setColor(String hex) {
            try { currentColor = (int)Long.parseLong(hex.replace("#",""), 16) | 0xFF000000; } catch (Exception e) {}
        }
    }

    @Override
    public void onDestroy() {
        if (webView != null) webView.destroy();
        super.onDestroy();
    }
}