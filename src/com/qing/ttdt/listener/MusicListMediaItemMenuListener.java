package com.qing.ttdt.listener;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qing.ttdt.R;
import com.qing.ttdt.adapter.MusicListItemMenuAdapter;
import com.qing.ttdt.custom.CustomAlertDialog;
import com.qing.ttdt.domain.MusicInfo;
import com.qing.ttdt.utils.HomePageUtils;

public class MusicListMediaItemMenuListener implements OnClickListener {
    
    private Context context;
    private MusicInfo music;
    private CustomAlertDialog dialog;
    
    public MusicListMediaItemMenuListener(Context context, MusicInfo music){
        this.context = context;
        this.music = music;
        dialog = new CustomAlertDialog(context);
    }
    
    String songName;
    @Override
    public void onClick(View v) {
        songName = music.getTitle();
        //songName = songName.substring(songName.indexOf(".")+2);
        
        dialog.createDialog();
        customDialog(dialog);
        
        //dialog1();
    }
    
    TextView titleTxt;
    ImageButton titleBtn;
    TextView dialogContent;
    ListView dialogLV;
    TextView menu_item_txt;
    
    public void customDialog(CustomAlertDialog dialog) {
        dialog.getDialogTitleIcon().setVisibility(View.GONE);
        dialog.getDialogBtnGroup().setVisibility(View.GONE);
        
        titleTxt = dialog.getDialogTitleTxt();
        titleTxt.setText(songName);
        
        titleBtn = dialog.getDialogTitleBtn();
        titleBtn.setVisibility(View.VISIBLE);
        titleBtn.setBackgroundResource(R.drawable.btn_pressed_bg_selector);
        titleBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "分享了..", Toast.LENGTH_SHORT).show();
            }
        });
        
        dialogContent = dialog.getDialogContentTV();
        dialogContent.setVisibility(View.GONE);
        //dialogContent.setText("1啊啊啊啊啊啊\n2啊啊啊啊啊啊\n");
        
        dialogLV = dialog.getDialogContentLV();
        dialogLV.setVisibility(View.VISIBLE);
        dialogLV.setDivider(context.getResources().getDrawable(android.R.drawable.divider_horizontal_textfield));
        dialogLV.setDividerHeight(2);
        dialogLV.setAdapter(new MusicListItemMenuAdapter(context));
        //解决listview只显示一个item项的问题
        HomePageUtils.setListViewHeightBasedOnChildren(dialogLV);
        
        //listview点击后的监听
        dialogLV.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                menu_item_txt = (TextView) view.findViewById(R.id.menu_item_txt);
                Toast.makeText(context, menu_item_txt.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    //自定义标题
    /*
    public void dialog1() {
        View contentView = LayoutInflater.from(context).inflate(R.layout.布局文件, null);
        ListView lv = (ListView) contentView.findViewById(R.id.布局文件里的listview控件);
        lv.setAdapter(new MusicListItemMenuAdapter(context));
        
        Builder dialog_menu = new AlertDialog.Builder(context);
        dialog_menu.setInverseBackgroundForced(true);
        
        TextView title = createTextView();
        title.setText(songName);
        dialog_menu.setCustomTitle(title);
        dialog_menu.setView(contentView);
        dialog_menu.show();
    }
    */
    @SuppressLint("ResourceAsColor")
    public TextView createTextView() {
        TextView tv = new TextView(context);
        tv.setWidth(LayoutParams.MATCH_PARENT);
        tv.setHeight(LayoutParams.MATCH_PARENT);
        tv.setMinHeight(200);
        tv.setPadding(20, 0, 0, 0);
        tv.setBackgroundColor(Color.BLUE);
        tv.setGravity(Gravity.VERTICAL_GRAVITY_MASK);
        tv.setTextColor(Color.BLACK);
        tv.setTextSize(16);
        return tv;
    }
}
