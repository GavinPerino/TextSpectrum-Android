package com.textspectrum.keyboard;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.View;
import android.view.inputmethod.InputConnection;
public class TextSpectrumKeyboardService extends InputMethodService implements KeyboardView.OnKeyboardActionListener {
    private KeyboardView kv; private Keyboard keyboard; private ColorStripView colorStrip;
    private int currentColor = 0xFF000000;
    private final int[] colors = {0xFFFFFFFF, 0xFFFF0000, 0xFFFFA500, 0xFFFFFF00, 0xFF00FF00, 0xFF0000FF, 0xFF4B0082, 0xFF8A2BE2};
    @Override public View onCreateInputView() {
        View root = getLayoutInflater().inflate(R.layout.keyboard_view, null);
        kv = root.findViewById(R.id.keyboardView);
        colorStrip = root.findViewById(R.id.colorStrip);
        keyboard = new Keyboard(this, R.xml.qwerty);
        kv.setKeyboard(keyboard); kv.setOnKeyboardActionListener(this);
        colorStrip.setColors(colors); colorStrip.setOnColorSelectedListener(c -> currentColor = c);
        return root;
    }
    @Override public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection(); if (ic==null) return;
        if(primaryCode==Keyboard.KEYCODE_DELETE){ ic.deleteSurroundingText(1,0); }
        else if(primaryCode==Keyboard.KEYCODE_DONE){ ic.sendKeyEvent(new android.view.KeyEvent(android.view.KeyEvent.ACTION_DOWN, android.view.KeyEvent.KEYCODE_ENTER)); }
        else { char code=(char)primaryCode; ic.commitText(String.valueOf(code),1); }
    }
    @Override public void onPress(int p){} @Override public void onRelease(int p){} @Override public void onText(CharSequence t){} @Override public void swipeLeft(){} @Override public void swipeRight(){} @Override public void swipeDown(){} @Override public void swipeUp(){}
}