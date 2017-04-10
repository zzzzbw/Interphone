package com.zbw.interphone;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zbw.interphone.model.InterphoneGlobal;
import com.zbw.interphone.model.InterphoneSetting;
import com.zbw.interphone.util.ResourceUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by zbw on 2017/4/4.
 */

public class SettingFragment extends Fragment {
    private InterphoneSetting mSetting;
    private InterphoneGlobal gloabl;
    private Activity mAppActivity;

    private ResourceUtil RUtil;

    @BindView(R.id.JZlevel_button)
    public Button JZlevelButton;
    @BindView(R.id.timer_button)
    public Button timerButton;
    @BindView(R.id.SKZYlevel_button)
    public Button SKZYlevelButton;
    @BindView(R.id.keyA_button)
    public Button keyAButton;
    @BindView(R.id.keyB_button)
    public Button keyBButton;
    @BindView(R.id.keyC_button)
    public Button keyCButton;
    @BindView(R.id.keyD_button)
    public Button keyDButton;
    @BindView(R.id.power_save_checkbox)
    public CheckBox powerSavingCheckBox;
    @BindView(R.id.id_recognition_checkbox)
    public CheckBox idRecognitionCheckBox;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppActivity = getActivity();
        RUtil = new ResourceUtil(mAppActivity);
        gloabl = InterphoneGlobal.get(mAppActivity);
        mSetting = gloabl.getSetting();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);
        JZlevelButton.setText(RUtil.getShowResource(R.array.JZlevel_values, mSetting.getJZLevel()));
        timerButton.setText(RUtil.getShowResource(R.array.timer_values, mSetting.getTimer()));
        SKZYlevelButton.setText(RUtil.getShowResource(R.array.SKZYLevel_values, mSetting.getSKZYLevel()));
        keyAButton.setText(RUtil.getShowResource(R.array.key_use_values, mSetting.getKeyA()));
        keyBButton.setText(RUtil.getShowResource(R.array.key_use_values, mSetting.getKeyB()));
        keyCButton.setText(RUtil.getShowResource(R.array.key_use_values, mSetting.getKeyC()));
        keyDButton.setText(RUtil.getShowResource(R.array.key_use_values, mSetting.getKeyD()));
        powerSavingCheckBox.setChecked(mSetting.isPowerSaving());
        idRecognitionCheckBox.setChecked(mSetting.isIdRecognition());
        return view;

    }

    @OnClick({R.id.JZlevel_button,
            R.id.timer_button,
            R.id.SKZYlevel_button,
            R.id.keyA_button,
            R.id.keyB_button,
            R.id.keyC_button,
            R.id.keyD_button})
    public void createDialog(final Button button) {
        int title = 0, items = 0;
        int index = -1;
        Method setMethod = null;

        try {
            switch (button.getId()) {
                case R.id.JZlevel_button:
                    title = R.string.JZ_level;
                    items = R.array.JZlevel_values;
                    setMethod = InterphoneSetting.class.getMethod("setJZLevel", int.class);
                    break;
                case R.id.timer_button:
                    title = R.string.timer;
                    items = R.array.timer_values;
                    setMethod = InterphoneSetting.class.getMethod("setTimer", int.class);
                    break;
                case R.id.SKZYlevel_button:
                    title = R.string.SKZY_level;
                    items = R.array.SKZYLevel_values;
                    setMethod = InterphoneSetting.class.getMethod("setSKZYLevel", int.class);
                    break;
                case R.id.keyA_button:
                    title = R.string.key_A;
                    items = R.array.key_use_values;
                    setMethod = InterphoneSetting.class.getMethod("setKeyA", int.class);
                    break;
                case R.id.keyB_button:
                    title = R.string.key_B;
                    items = R.array.key_use_values;
                    setMethod = InterphoneSetting.class.getMethod("setKeyB", int.class);
                    break;
                case R.id.keyC_button:
                    title = R.string.key_C;
                    items = R.array.key_use_values;
                    setMethod = InterphoneSetting.class.getMethod("setKeyC", int.class);
                    break;
                case R.id.keyD_button:
                    title = R.string.key_D;
                    items = R.array.key_use_values;
                    setMethod = InterphoneSetting.class.getMethod("setKeyD", int.class);
                    break;
                default:
                    Toast.makeText(mAppActivity, "4", Toast.LENGTH_SHORT).show();
                    break;
            }
        } catch (NoSuchMethodException e) {
            Toast.makeText(mAppActivity, "获取对话框数据出错:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        index = RUtil.getIndex(items, button.getText().toString());
        final int finalItems = items;
        final Method finalSetMethod = setMethod;
        new MaterialDialog.Builder(mAppActivity)
                .title(title)
                .items(items)
                .itemsCallbackSingleChoice(index, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        int value = RUtil.getIndex(finalItems, text.toString());
                        try {
                            if (finalSetMethod != null) {
                                finalSetMethod.invoke(mSetting, value);
                                gloabl.setSetting(mSetting);
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        button.setText(text);
                        return true;
                    }
                })
                .positiveText(R.string.global_ok)
                .show();

    }

    @OnCheckedChanged({R.id.power_save_checkbox, R.id.id_recognition_checkbox})
    public void clickCheckBox(final CheckBox checkbox) {
        if (checkbox.getId() == R.id.power_save_checkbox) {
            mSetting.setPowerSaving(checkbox.isChecked());
        } else if (checkbox.getId() == R.id.id_recognition_checkbox) {
            mSetting.setIdRecognition(checkbox.isChecked());
        }
        gloabl.setSetting(mSetting);
    }

}
