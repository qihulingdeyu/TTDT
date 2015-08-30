package com.qing.ttdt.custom;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.qing.ttdt.R;

public class CustomAlertDialog {
    
    private Context context;
    private Builder builder;
    private AlertDialog customDialog;
    private Window window;
    
    private LinearLayout dialogTitleLayout,dialogContentLayout,dialogBtnGroup;
    
    private ImageView dialogTitleIcon;
    private TextView dialogTitleTxt;
    private ImageButton dialogTitleBtn;
    private ImageView dialogDivider;
    
    private ScrollView dialogSV;
    private TextView dialogContentTV;
    private ListView dialogContentLV;
    
    private Button dialogPositiveBtn;
    private Button dialogNeutralBtn;
    private Button dialogNegativeBtn;

    public CustomAlertDialog(Context context) {
        this.context = context;
        builder = new AlertDialog.Builder(context);
        //createDialog();
    }

    public void createDialog(){
        customDialog = builder.create();
        customDialog.show();
        window =  customDialog.getWindow();
        window.setContentView(R.layout.dialog_custom_dialog);
        dialogTitleLayout = (LinearLayout) window.findViewById(R.id.dialogTitleLayout);
        dialogTitleIcon = (ImageView) window.findViewById(R.id.dialogTitleIcon);
        dialogTitleTxt = (TextView) window.findViewById(R.id.dialogTitleTxt);
        dialogTitleBtn = (ImageButton) window.findViewById(R.id.dialogTitleBtn);
        dialogDivider = (ImageView) window.findViewById(R.id.dialogDivider);
        dialogSV = (ScrollView) window.findViewById(R.id.dialogSV);
        dialogContentLayout = (LinearLayout) window.findViewById(R.id.dialogContentLayout);
        dialogContentTV = (TextView) window.findViewById(R.id.dialogContentTV);
        dialogContentLV = (ListView) window.findViewById(R.id.dialogContentLV);
        dialogBtnGroup = (LinearLayout) window.findViewById(R.id.dialogBtn);
        dialogPositiveBtn = (Button) window.findViewById(R.id.dialogPositiveBtn);
        dialogNeutralBtn = (Button) window.findViewById(R.id.dialogNeutralBtn);
        dialogNegativeBtn = (Button) window.findViewById(R.id.dialogNegativeBtn);
    }
    
    public Context getContext() {
        return context;
    }
    public AlertDialog getCustomDialog() {
        return customDialog;
    }
    public Window getWindow() {
        return window;
    }
    public LinearLayout getDialogTitleLayout() {
        return dialogTitleLayout;
    }
    public ImageView getDialogTitleIcon() {
        return dialogTitleIcon;
    }
    public TextView getDialogTitleTxt() {
        return dialogTitleTxt;
    }
    public ImageButton getDialogTitleBtn() {
        return dialogTitleBtn;
    }
    public ImageView getDialogDivider() {
        return dialogDivider;
    }
    public ScrollView getDialogSV() {
        return dialogSV;
    }
    public LinearLayout getDialogContentLayout() {
        return dialogContentLayout;
    }
    public LinearLayout getDialogBtnGroup() {
        return dialogBtnGroup;
    }
    public TextView getDialogContentTV() {
        return dialogContentTV;
    }
    public ListView getDialogContentLV() {
        return dialogContentLV;
    }
    public Button getDialogPositiveBtn() {
        return dialogPositiveBtn;
    }
    public Button getDialogNeutralBtn() {
        return dialogNeutralBtn;
    }
    public Button getDialogNegativeBtn() {
        return dialogNegativeBtn;
    }
    
}