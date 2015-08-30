package com.qing.ttdt.custom;

import com.qing.ttdt.R;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

public class CustomDialog extends Dialog {

    private static int WIDTH = 320;
    private static int HEIGHT = 220;
    /*
     * R.style.customDialog
     * 
     <style name="customDialog" parent="@android:style/Theme.Dialog">
        <item name="android:windowBackground">@android:color/transparent</item>
        <item name="android:windowNoTitle">true</item>
     </style>
     */
    public CustomDialog(Context context){
        this(context,R.style.customDialog);
    }
    
    public CustomDialog(Context context, int theme) {
        super(context, theme);
        create(context);
    }
    //只定义内容显示样式，不改变title样式时，直接重写父类方法即可
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_custom_dialog);
    }*/
    
    //整个dialog都自定义
    public void create(Context context){
        this.setContentView(R.layout.dialog_custom_dialog);
        
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        float density = getDensity(context);
        
        params.width = (int) (WIDTH * density);
        params.height = (int) (HEIGHT * density);
        params.gravity = Gravity.CENTER;
        
        window.setAttributes(params);
    }
    
    //获取dp和px的比例
    public float getDensity(Context context){
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.density;
    }
    
}
