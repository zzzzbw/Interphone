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

public class DecodingSettingFragment extends Fragment {
    private Activity mAppActivity;
    private InterphoneGlobal mGlobal;
    private DTMFSetting mSetting;

    private ResourceUtil RUtil;

    @BindView(R.id.viewPersonId)
    public LineSettingView viewPersonId;
    @BindView(R.id.viewTeamId)
    public LineSettingView viewTeamId;
    @BindView(R.id.viewGroupId)
    public LineSettingView viewGroupId;
    @BindView(R.id.viewYBCode)
    public LineSettingView viewYBCode;
    @BindView(R.id.viewYYCode)
    public LineSettingView viewYYCode;
    @BindView(R.id.viewReviveCode)
    public LineSettingView viewReviveCode;

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
        View view = inflater.inflate(R.layout.fragment_decoding_setting, container, false);
        ButterKnife.bind(this, view);

        viewPersonId.setContent(mSetting.getPersonId());
        viewTeamId.setContent(mSetting.getTeamId());
        viewGroupId.setContent(mSetting.getGroupId());
        viewYBCode.setContent(mSetting.getYBCode());
        viewYYCode.setContent(mSetting.getYYCode());
        viewReviveCode.setContent(mSetting.getReviveCode());

        return view;
    }

    @OnClick({R.id.viewPersonId,
            R.id.viewTeamId,
            R.id.viewGroupId,
            R.id.viewYBCode,
            R.id.viewYYCode,
            R.id.viewReviveCode})
    public void createInputDialog(final LineSettingView view) {
        int title = 0, inputType = InputType.TYPE_CLASS_TEXT;
        String inputPreFill = "", inputHint = "";
        Method setMethod = null;
        try {
            switch (view.getId()) {
                case R.id.viewPersonId:
                    title = R.string.person_id;
                    inputHint = mAppActivity.getResources().getString(title);
                    inputPreFill = mSetting.getPersonId();
                    setMethod = DTMFSetting.class.getMethod("setPersonId", String.class);
                    break;
                case R.id.viewTeamId:
                    title = R.string.team_id;
                    inputHint = mAppActivity.getResources().getString(title);
                    inputPreFill = mSetting.getTeamId();
                    setMethod = DTMFSetting.class.getMethod("setTeamId", String.class);
                    break;
                case R.id.viewGroupId:
                    title = R.string.group_id;
                    inputHint = mAppActivity.getResources().getString(title);
                    inputPreFill = mSetting.getGroupId();
                    setMethod = DTMFSetting.class.getMethod("setGroupId", String.class);
                    break;
                case R.id.viewYBCode:
                    title = R.string.YB_code;
                    inputHint = mAppActivity.getResources().getString(title);
                    inputPreFill = mSetting.getYBCode();
                    setMethod = DTMFSetting.class.getMethod("setYBCode", String.class);
                    break;
                case R.id.viewYYCode:
                    title = R.string.YY_code;
                    inputHint = mAppActivity.getResources().getString(title);
                    inputPreFill = mSetting.getYYCode();
                    setMethod = DTMFSetting.class.getMethod("setYYCode", String.class);
                    break;
                case R.id.viewReviveCode:
                    title = R.string.revive_code;
                    inputHint = mAppActivity.getResources().getString(title);
                    inputPreFill = mSetting.getReviveCode();
                    setMethod = DTMFSetting.class.getMethod("setReviveCode", String.class);
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
                            finalSetMethod.invoke(mSetting,input.toString());
                            InterphoneGlobal.get(mAppActivity).setDTMFSetting(mSetting);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        view.setContent(input.toString());
                    }
                }).show();
    }
}
