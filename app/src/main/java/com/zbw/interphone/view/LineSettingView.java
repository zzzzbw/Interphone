package com.zbw.interphone.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.zbw.interphone.R;

import static android.content.ContentValues.TAG;

/**
 * Created by zbw on 2017/4/27.
 */

public class LineSettingView extends LinearLayout {
    //主题
    private String subject;

    //标题
    private String title;

    //标题对应的内容
    private String content;

    //是否能够跳转
    private boolean canNav;

    //是否位于此主题下的最后一行
    private boolean isBottom;

    private TextView tv_title;

    private TextView tv_content;

    private Switch switch_nav;

    private OnClickListener mClickListener;
    private OnCheckedChangeListener mSwitchChangeListener;

    public LineSettingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_line_setting, this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LineSettingView, 0, 0);
        try {
            subject = ta.getString(R.styleable.LineSettingView_subject);
            title = ta.getString(R.styleable.LineSettingView_title);
            content = ta.getString(R.styleable.LineSettingView_content);
            canNav = ta.getBoolean(R.styleable.LineSettingView_canNav, false);
            isBottom = ta.getBoolean(R.styleable.LineSettingView_isBottom, false);
            initView();
        } finally {
            ta.recycle();
        }
    }

    private void initView() {
        this.setClickable(true);
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        TextView tv_subject = (TextView) findViewById(R.id.tv_subject);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_content = (TextView) findViewById(R.id.tv_content);
        switch_nav = (Switch) findViewById(R.id.switch_nav);
        View view_bottomLine = findViewById(R.id.view_bottomLine);
        if (subject == null || subject.length() == 0) {
            tv_subject.setVisibility(GONE);
        } else {
            tv_subject.setText(subject);
        }
        tv_title.setText(title);
        tv_content.setText(content);
        switch_nav.setVisibility(canNav ? VISIBLE : INVISIBLE);
        view_bottomLine.setVisibility(isBottom ? VISIBLE : GONE);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action=ev.getAction();
        if(action==MotionEvent.ACTION_UP){
            if(mClickListener!=null){
                mClickListener.onClick(this);
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    public void setTitle(String title) {
        this.title = title;
        tv_title.setText(title);
    }

    public void setContent(String content) {
        this.content = content;
        tv_content.setText(content);
    }

    public String getContent() {
        return content;
    }

    public void setSwitch(boolean isSwitch){
        if(switch_nav.getVisibility()==INVISIBLE){
            return;
        }
        switch_nav.setChecked(isSwitch);
    }

    public boolean isSwitch(){
        if(switch_nav.getVisibility()==INVISIBLE){
            return false;
        }
        return switch_nav.isChecked();
    }


    public void setSwitchOnCheckedChangeListener(@Nullable CompoundButton.OnCheckedChangeListener l){
        switch_nav.setOnCheckedChangeListener(l);
    }


    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        this.mClickListener=l;
    }
}
