package com.zbw.interphone;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zbw.interphone.model.ChannelList;
import com.zbw.interphone.model.InterphoneChannel;
import com.zbw.interphone.model.InterphoneGlobal;
import com.zbw.interphone.util.ResourceUtil;
import com.zbw.interphone.view.LineSettingView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ZBW on 2017/4/9.
 */

public class ChannelFragment extends Fragment {
    public static final String EXTRA_CHANNEL_ID = "com.zbw.interphone.channel_id";


    private InterphoneChannel mChannel;
    private Activity mAppActivity;

    private ResourceUtil RUtil;

    @BindView(R.id.viewChannelNickname)
    public LineSettingView viewChannelNickname;
    @BindView(R.id.viewChannelSpacing)
    public LineSettingView viewChannelSpacing;
    @BindView(R.id.viewPowerLevel)
    public LineSettingView viewPowerLevel;
    @BindView(R.id.viewFirstScan)
    public LineSettingView viewFirstScan;
    @BindView(R.id.viewScanList)
    public LineSettingView viewScanList;

    @BindView(R.id.viewValid)
    public LineSettingView viewValid;
    @BindView(R.id.viewPTTId)
    public LineSettingView viewPTTId;
    @BindView(R.id.viewChoiceCall)
    public LineSettingView viewChoiceCall;
    @BindView(R.id.viewBusyDeny)
    public LineSettingView viewBusyDeny;
    @BindView(R.id.viewInterfere)
    public LineSettingView viewInterfere;

    @BindView(R.id.viewReceiveRate)
    public LineSettingView viewReceiveRate;
    @BindView(R.id.viewReceiveCTCSSType)
    public LineSettingView viewReceiveCTCSSType;
    @BindView(R.id.viewReceiveCTCSSRate)
    public LineSettingView viewReceiveCTCSSRate;
    @BindView(R.id.viewReceiveCTCSSCode)
    public LineSettingView viewReceiveCTCSSCode;

    @BindView(R.id.viewLaunchRate)
    public LineSettingView viewLaunchRate;
    @BindView(R.id.viewLaunchCTCSSType)
    public LineSettingView viewLaunchCTCSSType;
    @BindView(R.id.viewLaunchCTCSSRate)
    public LineSettingView viewLaunchCTCSSRate;
    @BindView(R.id.viewLaunchCTCSSCode)
    public LineSettingView viewLaunchCTCSSCode;


    public static ChannelFragment newInstance(String channelId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CHANNEL_ID, channelId);
        ChannelFragment fragment = new ChannelFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppActivity = getActivity();
        RUtil = new ResourceUtil(mAppActivity);
        String channel_id = (String) getArguments().getSerializable(EXTRA_CHANNEL_ID);
        mChannel = InterphoneGlobal.get(mAppActivity).getChannelList().getChannel(channel_id);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_channel, container, false);
        ButterKnife.bind(this, view);


        viewChannelNickname.setContent(mChannel.getNickname());
        viewChannelSpacing.setContent(RUtil.getShowResource(R.array.channelSpacing_values, mChannel.getChannelSpacing()));
        viewPowerLevel.setContent(RUtil.getShowResource(R.array.powerLevel_values, mChannel.getPowerLevel()));
        viewFirstScan.setContent(RUtil.getShowResource(R.array.firstScan_values, mChannel.getFirstScan()));
        viewScanList.setContent("无");

        viewReceiveRate.setContent(mChannel.getReceiveRate() + "");
        viewReceiveCTCSSType.setContent(RUtil.getShowResource(R.array.CTCSSType_values, mChannel.getReceiveCTCSSType()));
        viewReceiveCTCSSRate.setContent(mChannel.getReceiveCTCSSRate() + "");
        viewReceiveCTCSSCode.setContent(mChannel.getReceiveCTCSSCode() + "");

        viewLaunchRate.setContent(mChannel.getLaunchRate() + "");
        viewLaunchCTCSSType.setContent(RUtil.getShowResource(R.array.CTCSSType_values, mChannel.getLaunchCTCSSType()));
        viewLaunchCTCSSRate.setContent(mChannel.getLaunchCTCSSRate() + "");
        viewLaunchCTCSSCode.setContent(mChannel.getLaunchCTCSSCode() + "");

        setSwtiches();
        return view;
    }


    @OnClick({R.id.viewChannelSpacing,
            R.id.viewPowerLevel,
            R.id.viewFirstScan,
            R.id.viewScanList,
            R.id.viewReceiveCTCSSType,
            R.id.viewReceiveCTCSSRate,
            R.id.viewReceiveCTCSSCode,
            R.id.viewLaunchCTCSSType,
            R.id.viewLaunchCTCSSRate,
            R.id.viewLaunchCTCSSCode})
    public void createChoiceListDialog(final LineSettingView view) {
        int title = 0, items = 0;
        int index = -1;
        Method setMethod = null;
        Class valueType = int.class;
        try {
            switch (view.getId()) {
                case R.id.viewChannelSpacing:
                    title = R.string.channel_spacing;
                    items = R.array.channelSpacing_values;
                    index = mChannel.getChannelSpacing();
                    setMethod = InterphoneChannel.class.getMethod("setChannelSpacing", int.class);
                    break;
                case R.id.viewPowerLevel:
                    title = R.string.power_level;
                    items = R.array.powerLevel_values;
                    index = mChannel.getPowerLevel();
                    setMethod = InterphoneChannel.class.getMethod("setPowerLevel", int.class);
                    break;
                case R.id.viewFirstScan:
                    title = R.string.first_scan;
                    items = R.array.firstScan_values;
                    index = mChannel.getFirstScan();
                    setMethod = InterphoneChannel.class.getMethod("setFirstScan", int.class);
                    break;
                case R.id.viewReceiveCTCSSType:
                    title = R.string.CTCSS_type;
                    items = R.array.CTCSSType_values;
                    index = mChannel.getReceiveCTCSSType();
                    setMethod = InterphoneChannel.class.getMethod("setReceiveCTCSSType", int.class);
                    break;
                case R.id.viewReceiveCTCSSRate:
                    title = R.string.CTCSS_rate;
                    items = R.array.CTCSSRate_values;
                    index = RUtil.getIndex(items, mChannel.getReceiveCTCSSRate());
                    setMethod = InterphoneChannel.class.getMethod("setReceiveCTCSSRate", float.class);
                    valueType = float.class;
                    break;
                case R.id.viewReceiveCTCSSCode:
                    title = R.string.CTCSS_code;
                    items=R.array.CTCSSCode_values;
                    index=RUtil.getIndex(items,mChannel.getReceiveCTCSSCode());
                    setMethod = InterphoneChannel.class.getMethod("setReceiveCTCSSCode", float.class);
                    valueType = float.class;
                    break;
                case R.id.viewLaunchCTCSSType:
                    title = R.string.CTCSS_type;
                    items = R.array.CTCSSType_values;
                    index = mChannel.getLaunchCTCSSType();
                    setMethod = InterphoneChannel.class.getMethod("setLaunchCTCSSType", int.class);
                    break;
                case R.id.viewLaunchCTCSSRate:
                    title = R.string.CTCSS_rate;
                    items = R.array.CTCSSRate_values;
                    index = RUtil.getIndex(items, mChannel.getLaunchCTCSSRate());
                    setMethod = InterphoneChannel.class.getMethod("setLaunchCTCSSRate", float.class);
                    valueType = float.class;
                    break;
                case R.id.viewLaunchCTCSSCode:
                    title = R.string.CTCSS_code;
                    items=R.array.CTCSSCode_values;
                    index=RUtil.getIndex(items,mChannel.getLaunchCTCSSCode());
                    setMethod = InterphoneChannel.class.getMethod("setLaunchCTCSSCode", float.class);
                    valueType = float.class;
                    break;

            }
        } catch (NoSuchMethodException e) {
            Toast.makeText(mAppActivity, "获取对话框数据出错:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        final int finalItems = items;
        final Method finalSetMethod = setMethod;
        final Class finalValueType = valueType;
        new MaterialDialog.Builder(mAppActivity)
                .title(title)
                .items(items)
                .itemsCallbackSingleChoice(index, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        if (text == null) {
                            return false;
                        }


                        if (finalSetMethod != null) {
                            try {
                                Object value;
                                if (finalValueType == float.class) {
                                    value = Float.parseFloat(text.toString());
                                    finalSetMethod.invoke(mChannel, value);
                                } else {
                                    value = RUtil.getIndex(finalItems, text.toString());
                                }
                                finalSetMethod.invoke(mChannel, value);
                                InterphoneGlobal.get(mAppActivity).getChannelList().updateChannel(mChannel);
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

    @OnClick({R.id.viewChannelNickname,
            R.id.viewReceiveRate,
            R.id.viewLaunchRate})
    public void createInputDialog(final LineSettingView view) {
        int title = 0, inputType = InputType.TYPE_CLASS_TEXT;
        String inputPreFill = "", inputHint = "";
        Method setMethod = null;
        Class valueType = String.class;
        try {
            switch (view.getId()) {
                case R.id.viewChannelNickname:
                    title = R.string.channel_nickname;
                    inputHint = mAppActivity.getResources().getString(title);
                    inputPreFill = mChannel.getNickname();
                    setMethod = InterphoneChannel.class.getMethod("setNickname", String.class);
                    valueType = String.class;
                    break;
                case R.id.viewReceiveRate:
                    title = R.string.rate;
                    inputType = InputType.TYPE_CLASS_PHONE;
                    inputHint = mAppActivity.getResources().getString(title);
                    inputPreFill = mChannel.getReceiveRate() + "";
                    setMethod = InterphoneChannel.class.getMethod("setReceiveRate", float.class);
                    valueType = Float.class;
                    break;
                case R.id.viewLaunchRate:
                    title = R.string.rate;
                    inputType = InputType.TYPE_CLASS_PHONE;
                    inputHint = mAppActivity.getResources().getString(title);
                    inputPreFill = mChannel.getLaunchRate() + "";
                    setMethod = InterphoneChannel.class.getMethod("setLaunchRate", float.class);
                    valueType = Float.class;
                    break;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        final Method finalSetMethod = setMethod;
        final Class finalValueType = valueType;
        new MaterialDialog.Builder(mAppActivity)
                .title(title)
                .inputType(inputType)
                .input(inputHint, inputPreFill, false, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        if (finalSetMethod != null) {
                            try {
                                if (finalValueType == Float.class) {
                                    finalSetMethod.invoke(mChannel, Float.parseFloat(input.toString()));
                                } else if (finalValueType == String.class) {
                                    finalSetMethod.invoke(mChannel, input.toString());
                                }
                                InterphoneGlobal.get(mAppActivity).getChannelList().updateChannel(mChannel);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                            view.setContent(input.toString());
                        }
                    }
                }).show();
    }


    private void setSwtiches() {
        viewValid.setSwitch(mChannel.isValid());
        viewPTTId.setSwitch(mChannel.isPTTID());
        viewChoiceCall.setSwitch(mChannel.isChoiceCall());
        viewBusyDeny.setSwitch(mChannel.isBusyDeny());
        viewInterfere.setSwitch(mChannel.isInterfere());

        viewValid.setSwitchOnCheckedChangeListener(new SwitchOnCheckedChangeListener(viewValid));
        viewPTTId.setSwitchOnCheckedChangeListener(new SwitchOnCheckedChangeListener(viewPTTId));
        viewChoiceCall.setSwitchOnCheckedChangeListener(new SwitchOnCheckedChangeListener(viewChoiceCall));
        viewBusyDeny.setSwitchOnCheckedChangeListener(new SwitchOnCheckedChangeListener(viewBusyDeny));
        viewInterfere.setSwitchOnCheckedChangeListener(new SwitchOnCheckedChangeListener(viewInterfere));
    }


    private void clickSwitch(final LineSettingView view) {
        switch (view.getId()) {
            case R.id.viewValid:
                mChannel.setValid(view.isSwitch());
                break;
            case R.id.viewPTTId:
                mChannel.setPTTID(view.isSwitch());
                break;
            case R.id.viewChoiceCall:
                mChannel.setChoiceCall(view.isSwitch());
                break;
            case R.id.viewBusyDeny:
                mChannel.setBusyDeny(view.isSwitch());
                break;
            case R.id.viewInterfere:
                mChannel.setInterfere(view.isSwitch());
                break;
        }
        InterphoneGlobal.get(mAppActivity).getChannelList().updateChannel(mChannel);
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