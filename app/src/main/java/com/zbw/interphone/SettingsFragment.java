package com.zbw.interphone;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;


import com.afollestad.materialdialogs.MaterialDialog;
import com.zbw.interphone.model.ChannelList;
import com.zbw.interphone.model.InterphoneGlobal;
import com.zbw.interphone.model.InterphoneSetting;
import com.zbw.interphone.util.ResourceUtil;
import com.zbw.interphone.view.LineSettingView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zbw on 2017/4/26.
 */

public class SettingsFragment extends Fragment {
    private InterphoneGlobal mGlobal;
    private InterphoneSetting mSetting;
    private Activity mAppActivity;

    private ResourceUtil RUtil;

    @BindView(R.id.viewJZLevel)
    public LineSettingView viewJZLevel;
    @BindView(R.id.viewTimer)
    public LineSettingView viewTimer;
    @BindView(R.id.viewSKZYLevel)
    public LineSettingView viewSKZYLevel;
    @BindView(R.id.viewKeyA)
    public LineSettingView viewKeyA;
    @BindView(R.id.viewKeyB)
    public LineSettingView viewKeyB;
    @BindView(R.id.viewKeyC)
    public LineSettingView viewKeyC;
    @BindView(R.id.viewKeyD)
    public LineSettingView viewKeyD;

    @BindView(R.id.viewPowerSaving)
    public LineSettingView viewPowerSaving;
    @BindView(R.id.viewIdRecognition)
    public LineSettingView viewIdRecognition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppActivity = getActivity();
        mGlobal = InterphoneGlobal.get(mAppActivity);
        mSetting = mGlobal.getSetting();
        RUtil = new ResourceUtil(mAppActivity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);

        viewJZLevel.setContent(RUtil.getShowResource(R.array.JZlevel_values, mSetting.getJZLevel()));
        viewTimer.setContent(RUtil.getShowResource(R.array.timer_values, mSetting.getTimer()));
        viewSKZYLevel.setContent(RUtil.getShowResource(R.array.SKZYLevel_values, mSetting.getSKZYLevel()));
        viewKeyA.setContent(RUtil.getShowResource(R.array.key_use_values, mSetting.getKeyB()));
        viewKeyB.setContent(RUtil.getShowResource(R.array.key_use_values, mSetting.getKeyA()));
        viewKeyC.setContent(RUtil.getShowResource(R.array.key_use_values, mSetting.getKeyC()));
        viewKeyD.setContent(RUtil.getShowResource(R.array.key_use_values, mSetting.getKeyD()));
        setSwtiches();

        return view;
    }

    @OnClick({R.id.viewJZLevel,
            R.id.viewTimer,
            R.id.viewSKZYLevel,
            R.id.viewKeyA,
            R.id.viewKeyB,
            R.id.viewKeyC,
            R.id.viewKeyD})
    public void createChoiceListDialog(final LineSettingView view) {
        int title = 0, items = 0;
        int index = -1;
        Method setMethod = null;
        try {
            switch (view.getId()) {
                case R.id.viewJZLevel:
                    title = R.string.JZ_level;
                    items = R.array.JZlevel_values;
                    index = mSetting.getJZLevel();
                    setMethod = InterphoneSetting.class.getMethod("setJZLevel", int.class);
                    break;
                case R.id.viewTimer:
                    title = R.string.timer;
                    items = R.array.timer_values;
                    index = mSetting.getTimer();
                    setMethod = InterphoneSetting.class.getMethod("setTimer", int.class);
                    break;
                case R.id.viewSKZYLevel:
                    title = R.string.SKZY_level;
                    items = R.array.SKZYLevel_values;
                    index = mSetting.getSKZYLevel();
                    setMethod = InterphoneSetting.class.getMethod("setSKZYLevel", int.class);
                    break;
                case R.id.viewKeyA:
                    title = R.string.key_A;
                    items = R.array.key_use_values;
                    index = mSetting.getKeyA();
                    setMethod = InterphoneSetting.class.getMethod("setKeyA", int.class);
                    break;
                case R.id.viewKeyB:
                    title = R.string.key_B;
                    items = R.array.key_use_values;
                    index = mSetting.getKeyB();
                    setMethod = InterphoneSetting.class.getMethod("setKeyB", int.class);
                    break;
                case R.id.viewKeyC:
                    title = R.string.key_C;
                    items = R.array.key_use_values;
                    index = mSetting.getKeyC();
                    setMethod = InterphoneSetting.class.getMethod("setKeyC", int.class);
                    break;
                case R.id.viewKeyD:
                    title = R.string.key_D;
                    items = R.array.key_use_values;
                    index = mSetting.getKeyD();
                    setMethod = InterphoneSetting.class.getMethod("setKeyD", int.class);
                    break;
            }
        } catch (NoSuchMethodException e) {
            Toast.makeText(mAppActivity, "获取对话框数据出错:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        final int finalItems = items;
        final Method finalSetMethod = setMethod;
        new MaterialDialog.Builder(mAppActivity)
                .title(title)
                .items(items)
                .itemsCallbackSingleChoice(index, new MaterialDialog.ListCallbackSingleChoice() {

                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        if (text == null) {
                            return false;
                        }

                        int value = RUtil.getIndex(finalItems, text.toString());
                        if (finalSetMethod != null) {
                            try {
                                finalSetMethod.invoke(mSetting, value);
                                InterphoneGlobal.get(mAppActivity).setSetting(mSetting);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }

                        }
                        view.setContent(text.toString());
                        return true;
                    }
                })
                .positiveText(R.string.global_ok)
                .show();
    }

    private void setSwtiches() {
        viewPowerSaving.setSwitch(mSetting.isPowerSaving());
        viewIdRecognition.setSwitch(mSetting.isIdRecognition());

        viewPowerSaving.setSwitchOnCheckedChangeListener(new SettingsFragment.SwitchOnCheckedChangeListener(viewPowerSaving));
        viewIdRecognition.setSwitchOnCheckedChangeListener(new SettingsFragment.SwitchOnCheckedChangeListener(viewIdRecognition));
    }


    private void clickSwitch(final LineSettingView view) {
        switch (view.getId()) {
            case R.id.viewPowerSaving:
                mSetting.setPowerSaving(view.isSwitch());
                break;
            case R.id.viewIdRecognition:
                mSetting.setIdRecognition(view.isSwitch());
                break;
        }
        InterphoneGlobal.get(mAppActivity).setSetting(mSetting);
    }

    private class SwitchOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        private LineSettingView mView;

        public SwitchOnCheckedChangeListener(LineSettingView view) {
            this.mView = view;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            clickSwitch(mView);
        }
    }
}
