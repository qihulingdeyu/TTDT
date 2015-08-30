package com.qing.ttdt.listener;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class CustomHomePageCheckListener implements OnCheckedChangeListener {
    
//    private Context context;
    private SharedPreferences prefer;
    private String itemName;
    private Editor editor;
    
    public CustomHomePageCheckListener(Context context,SharedPreferences prefer,String itemName){
//        this.context = context;
        this.prefer = prefer;
        this.itemName = itemName;
    }
    
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked != prefer.getBoolean(itemName, false)){
            editor = prefer.edit();
            editor.putBoolean(itemName, isChecked);
            editor.commit();
        }
    }

}
