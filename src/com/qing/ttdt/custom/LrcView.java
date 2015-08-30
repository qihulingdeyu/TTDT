package com.qing.ttdt.custom;

import java.util.ArrayList;
import java.util.List;

import com.qing.ttdt.utils.LrcHandle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class LrcView extends TextView {

    private List<String> wordList = new ArrayList<String>();
    private Paint loseFocusPaint;
    private Paint onFocusPaint;
    private float mX = 0;
    private float middleY = 0;
    private float mY = 0;
    private static final int DY = 50;
    private int index = 0;
    private LrcHandle lrcHandle;
    
    public LrcView(Context context) {
        super(context);
        init();
    }
    public LrcView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init() {
        setFocusable(true);
        
        lrcHandle = new LrcHandle();
        //读取文件
        lrcHandle.readLRC("");
        wordList = lrcHandle.getWordList();
        
        loseFocusPaint = new Paint();
        loseFocusPaint.setAntiAlias(true);
        loseFocusPaint.setTextSize(22);
        loseFocusPaint.setColor(Color.WHITE);
        loseFocusPaint.setTypeface(Typeface.SERIF);
        
        onFocusPaint = new Paint();
        onFocusPaint.setAntiAlias(true);
        onFocusPaint.setColor(Color.YELLOW);
        onFocusPaint.setTextSize(30);
        onFocusPaint.setTypeface(Typeface.SANS_SERIF);
        
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        canvas.drawColor(Color.BLACK);
        Paint p = loseFocusPaint;
        p.setTextAlign(Paint.Align.CENTER);
        Paint p2 = onFocusPaint;
        p2.setTextAlign(Paint.Align.CENTER);
        
        canvas.drawText(wordList.get(index), mX, middleY, p2);
        
        int alphaValue = 25;
        float tempY = middleY;
        for (int i = index-1; i >= 0; i--) {
            tempY -= DY;
            if(tempY < 0){
                break;
            }
            p.setColor(Color.argb(255-alphaValue, 254, 254, 254));
            canvas.drawText(wordList.get(i), mX, tempY, p);
            alphaValue += 25;
        }
        alphaValue = 25;
        tempY = middleY;
        for (int i = index+1, len = wordList.size(); i < len; i++) {
            tempY += DY;
            if(tempY > mY){
                break;
            }
            p.setColor(Color.argb(255-alphaValue, 254, 254, 254));
            canvas.drawText(wordList.get(i), mX, tempY, p);
            alphaValue += 25;
        }
        index++;
        
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mX = w * 0.5f;
        mY = h;
        middleY = h * 0.3f;
    }
}
