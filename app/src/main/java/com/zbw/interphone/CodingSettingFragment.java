package com.zbw.interphone;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zbw.interphone.model.DTMFSetting;
import com.zbw.interphone.model.InterphoneGlobal;
import com.zbw.interphone.util.ResourceUtil;
import com.zbw.interphone.view.LineSettingView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ZBW on 2017/4/29.
 */

public class CodingSettingFragment extends Fragment {
    private Activity mAppActivity;
    private InterphoneGlobal mGlobal;
    private DTMFSetting mSetting;

    private ResourceUtil RUtil;

    @BindView(R.id.viewLaunchSpeed)
    public LineSettingView viewLaunchSpeed;
    @BindView(R.id.viewFirstLaunchDelay)
    public LineSettingView viewFirstLaunchDelay;
    @BindView(R.id.viewFirstLaunchTime)
    public LineSettingView viewFirstLaunchTime;
    @BindView(R.id.viewCallCode1)
    public LineSettingView viewCallCode1;
    @BindView(R.id.viewCallCode2)
    public LineSettingView viewCallCode2;
    @BindView(R.id.viewCallCode3)
    public LineSettingView viewCallCode3;
    @BindView(R.id.viewPTTIdCode)
    public LineSettingView viewPTTIdCode;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppActivity = getActivity();
        mGlobal = InterphoneGlobal.get(mAppActivity);
        mSetting = mGlobal.getDTMFSetting();
        RUtil = new ResourceUtil(mAppActivity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coding_setting, container, false);
        ButterKnife.bind(this, view);

        viewLaunchSpeed.setContent(RUtil.getShowResource(R.array.launchSpeed_values, mSetting.getLaunchSpeed()));
        viewFirstLaunchDelay.setContent(RUtil.getShowResource(R.array.firstLaunchDelay_values, mSetting.getFirstLaunchDelay()));
        viewFirstLaunchTime.setContent(RUtil.getShowResource(R.array.firstLaunchTime_values, mSetting.getFirstLaunchTime()));
        viewCallCode1.setContent(mSetting.getCallCode1());
        viewCallCode2.setContent(mSetting.getCallCode2());
        viewCallCode3.setContent(mSetting.getCallCode3());
        viewPTTIdCode.setContent(mSetting.getPTTIdCode());

        return view;
    }

    @OnClick({R.id.viewLaunchSpeed,
            R.id.viewFirstLaunchDelay,
            R.id.viewFirstLaunchTime
    })
    public void createChoiceListDialog(final LineSettingView view) {
        int title = 0, items = 0;
        int index = -1;
        Method setMethod = null;
        try {
            switch (view.getId()) {
                case R.id.viewLaunchSpeed:
                    title = R.string.launch_speed;
                    items = R.array.launchSpeed_values;
                    index = mSetting.getLaunchSpeed();
                    setMethod = DTMFSetting.class.getMethod("setLaunchSpeed", int.class);
                    break;
                case R.id.viewFirstLaunchDelay:
                    title = R.string.first_launch_delay;
                    items = R.array.firstLaunchDelay_values;
                    index = mSetting.getFirstLaunchDelay();
                    setMethod = DTMFSetting.class.getMethod("setFirstLaunchDelay", int.class);
                    break;
                case R.id.viewFirstLaunchTime:
                    title = R.string.first_launch_time;
                    items = R.array.firstLaunchTime_values;
                    index = mSetting.getFirstLaunchTime();
                    setMethod = DTMFSetting.class.getMethod("setFirstLaunchTime", int.class);
                    break;
            }
        } catch (NoSuchMethodException e) {
            Toast.makeText(mAppActivity, "获取对话框数据出错:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        final Method finalSetMethod = setMethod;
        final int finalItems = items;
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
                                InterphoneGlobal.get(mAppActivity).setDTMFSetting(mSetting);
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
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

    @OnClick({R.id.viewCallCode1,
            R.id.viewCallCode2,
            R.id.viewCallCode3,
            R.id.viewPTTIdCode})
    public void createInputDialog(final LineSettingView view) {
        int title = 0, inputType = InputType.TYPE_CLASS_TEXT;
        String inputPreFill = "", inputHint = "";
        Method setMethod = null;
        Class valueType = String.class;
        try {
            switch (view.getId()) {
                case R.id.viewCallCode1:
                    title = R.string.call_code_1;
                    inputHint = mAppActivity.getResources().getString(title);
                    inputPreFill = mSetting.getCallCode1();
                    setMethod = DTMFSetting.class.getMethod("setCallCode1", String.class);
                    break;
                case R.id.viewCallCode2:
                    title = R.string.call_code_2;
                    inputHint = mAppActivity.getResources().getString(title);
                    inputPreFill = mSetting.getCallCode2();
                    setMethod = DTMFSetting.class.getMethod("setCallCode2", String.class);
                    break;
                case R.id.viewCallCode3:
                    title = R.string.call_code_3;
                    inputHint = mAppActivity.getResources().getString(title);
                    inputPreFill = mSetting.getCallCode3();
                    setMethod = DTMFSetting.class.getMethod("setCallCode3", String.class);
                    break;
                case R.id.viewPTTIdCode:
                    title = R.string.PTT_id_code;
                    inputHint = mAppActivity.getResources().getString(title);
                    inputPreFill = mSetting.getPTTIdCode();
                    setMethod = DTMFSetting.class.getMethod("setPTTIdCode", String.class);
                    break;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        final Method finalSetMethod = setMethod;
        new MaterialDialog.Builder(mAppActivity)
                .title(title)
                .inputType(inputType)
                .input(inputHint, inputPreFill, false, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        try {
                            finalSetMethod.invoke(mSetting, input.toString());
                            InterphoneGlobal.get(mAppActivity).setDTMFSetting(mSetting);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        view.setContent(input.toString());
                    }
                })
                .show();
    }
}
