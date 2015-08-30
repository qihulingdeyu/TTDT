package com.qing.ttdt.listener;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class AddAttentionListener implements OnClickListener {

    private Context context;
    public AddAttentionListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context, "被点击了。。", Toast.LENGTH_SHORT).show();
    }

}
