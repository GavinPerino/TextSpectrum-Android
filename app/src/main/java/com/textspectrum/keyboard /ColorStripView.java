package com.textspectrum.keyboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ColorStripView extends View {
    private Paint paint = new Paint();
    private int[] colors = {0xFFFFFFFF, 0xFFFF0000, 0xFFFF8800, 0xFFFFFF00, 0xFF00FF00, 0xFF0000FF, 0xFF4B0082, 0xFF8A2BE2};

    private OnColorSelectedListener listener;

    public interface OnColorSelectedListener {
        void onColorSelected(int color);
    }

    public ColorStripView(Context c, AttributeSet a) {
        super(c, a);
        setOnTouchListener((v, e) -> {
            if (listener != null) {
                listener.onColorSelected(getColorAt(e.getX()));
            }
            return true;
        });
    }

    public void setColors(int[] c) {
        colors = c;
        invalidate();
    }

    public void setOnColorSelectedListener(OnColorSelectedListener l) {
        listener = l;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float w = getWidth() / 8f;
        for (int i = 0; i < 8; i++) {
            paint.setColor(colors[i]);
            canvas.drawRect(i * w, 0, (i + 1) * w, getHeight(), paint);
        }
    }

    public int getColorAt(float x) {
        int idx = (int) (x / (getWidth() / 8f));
        if (idx < 0) idx = 0;
        if (idx > 7) idx = 7;
        return colors[idx];
    }
}