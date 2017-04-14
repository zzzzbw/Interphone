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
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zbw.interphone.model.ChannelList;
import com.zbw.interphone.model.InterphoneChannel;
import com.zbw.interphone.util.ResourceUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by ZBW on 2017/4/9.
 */

public class ChannelFragment extends Fragment {
    public static final String EXTRA_CHANNEL_ID = "com.zbw.interphont.channel_id";

    private InterphoneChannel channel;
    private Activity mAppActivity;

    private ResourceUtil RUtil;

    @BindView(R.id.channel_nicknameEditText)
    public EditText nicknameEditText;
    @BindView(R.id.channel_channelSpacingButton)
    public Button channelSpacingButton;
    @BindView(R.id.channel_powerLevelButton)
    public Button powerLevelButton;
    @BindView(R.id.channel_firstScanButton)
    public Button firstScanButton;
    @BindView(R.id.channel_scanListButton)
    public Button scanListButton;

    @BindView(R.id.channel_receive_rateEditText)
    public EditText receiveRateEditText;
    @BindView(R.id.channel_receive_CTCSSTypeButton)
    public Button receiveCTCSSTypeButton;
    @BindView(R.id.channel_receive_CTCSSRateButton)
    public Button receiveCTCSSRateButton;
    @BindView(R.id.channel_receive_CTCSSCodeButton)
    public Button receiveCTCSSCodeButton;

    @BindView(R.id.channel_launch_rateEditText)
    public EditText launchRateEditText;
    @BindView(R.id.channel_launch_CTCSSTypeButton)
    public Button launchCTCSSTypeButton;
    @BindView(R.id.channel_launch_CTCSSRateButton)
    public Button launchCTCSSRateButton;
    @BindView(R.id.channel_launch_CTCSSCodeButton)
    public Button launchCTCSSCodeButton;

    @BindView(R.id.channel_validCheckBox)
    public CheckBox validCheckBox;
    @BindView(R.id.channel_PTTIDCheckBox)
    public CheckBox PTTIDCheckBox;
    @BindView(R.id.channel_choiceCallCheckBox)
    public CheckBox choiceCallCheckBox;
    @BindView(R.id.channel_busyDenyCheckBox)
    public CheckBox busyDenyCheckBox;
    @BindView(R.id.channel_interfereCheckBox)
    public CheckBox interfereCheckBox;

    public static ChannelFragment newInstance(UUID channelId) {
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
        UUID channel_id = (UUID) getArguments().getSerializable(EXTRA_CHANNEL_ID);
        channel = ChannelList.get(mAppActivity).getChannel(channel_id);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_channel, container, false);
        ButterKnife.bind(this, view);
        nicknameEditText.setText(channel.getNickname());
        channelSpacingButton.setText(RUtil.getShowResource(R.array.channelSpacing_values, channel.getChannelSpacing()));
        powerLevelButton.setText(RUtil.getShowResource(R.array.powerLevel_values, channel.getPowerLevel()));
        firstScanButton.setText(RUtil.getShowResource(R.array.firstScan_values, channel.getFirstScan()));
        scanListButton.setText("无");

        receiveRateEditText.setText(channel.getReceiveRate() + "");
        receiveCTCSSTypeButton.setText(RUtil.getShowResource(R.array.CTCSSType_values, channel.getReceiveCTCSSType()));
        receiveCTCSSRateButton.setText(channel.getReceiveCTCSSRate() + "");
        receiveCTCSSCodeButton.setText(channel.getReceiveCTCSSCode() + "");

        launchRateEditText.setText(channel.getLaunchRate() + "");
        launchCTCSSTypeButton.setText(RUtil.getShowResource(R.array.CTCSSType_values, channel.getLaunchCTCSSType()));
        launchCTCSSRateButton.setText(channel.getLaunchCTCSSRate() + "");
        launchCTCSSCodeButton.setText(channel.getLaunchCTCSSCode() + "");

        validCheckBox.setChecked(channel.isValid());
        PTTIDCheckBox.setChecked(channel.isPTTID());
        choiceCallCheckBox.setChecked(channel.isChoiceCall());
        busyDenyCheckBox.setChecked(channel.isBusyDeny());
        interfereCheckBox.setChecked(channel.isInterfere());
        return view;
    }

    @OnClick({R.id.channel_channelSpacingButton,
            R.id.channel_powerLevelButton,
            R.id.channel_firstScanButton,
            R.id.channel_scanListButton,
            R.id.channel_receive_CTCSSTypeButton,
            R.id.channel_receive_CTCSSRateButton,
            R.id.channel_receive_CTCSSCodeButton,
            R.id.channel_launch_CTCSSTypeButton,
            R.id.channel_launch_CTCSSRateButton,
            R.id.channel_launch_CTCSSCodeButton})
    public void createDialog(final Button button) {
        int title = 0, items = 0;
        final int index = -1;
        Method setMethod = null;

        try {
            switch (button.getId()) {
                case R.id.channel_channelSpacingButton:
                    title = R.string.channel_spacing;
                    items = R.array.channelSpacing_values;
                    setMethod = InterphoneChannel.class.getMethod("setChannelSpacing", int.class);
                    break;
                case R.id.channel_powerLevelButton:
                    title = R.string.power_level;
                    items = R.array.powerLevel_values;
                    setMethod = InterphoneChannel.class.getMethod("setPowerLevel", int.class);
                    break;
                case R.id.channel_firstScanButton:
                    title = R.string.first_scan;
                    items = R.array.firstScan_values;
                    setMethod = InterphoneChannel.class.getMethod("setFirstScan", int.class);
                    break;
                case R.id.channel_receive_CTCSSTypeButton:
                    title = R.string.CTCSS_type;
                    items = R.array.CTCSSType_values;
                    setMethod = InterphoneChannel.class.getMethod("setReceiveCTCSSType", int.class);
                    break;
                case R.id.channel_receive_CTCSSRateButton:
                    title = R.string.CTCSS_rate;
                    setMethod = InterphoneChannel.class.getMethod("setReceiveCTCSSRate", float.class);
                    break;
                case R.id.channel_receive_CTCSSCodeButton:
                    title = R.string.CTCSS_code;
                    setMethod = InterphoneChannel.class.getMethod("setReceiveCTCSSCode", float.class);
                    break;
                case R.id.channel_launch_CTCSSTypeButton:
                    title = R.string.CTCSS_type;
                    items = R.array.CTCSSType_values;
                    setMethod = InterphoneChannel.class.getMethod("setLaunchCTCSSType", int.class);
                    break;
                case R.id.channel_launch_CTCSSRateButton:
                    title = R.string.CTCSS_rate;
                    setMethod = InterphoneChannel.class.getMethod("setLaunchCTCSSRate", float.class);
                    break;
                case R.id.channel_launch_CTCSSCodeButton:
                    title = R.string.CTCSS_code;
                    setMethod = InterphoneChannel.class.getMethod("setLaunchCTCSSCode", float.class);
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
                        int value = RUtil.getIndex(finalItems, text.toString());
                        if (finalSetMethod != null) {
                            try {
                                finalSetMethod.invoke(channel, value);
                                ChannelList.get(mAppActivity).updateChannel(channel);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }

                        }
                        button.setText(text);
                        return true;
                    }
                })
                .positiveText(R.string.global_ok)
                .show();
    }

    @OnCheckedChanged({R.id.channel_validCheckBox,
            R.id.channel_PTTIDCheckBox,
            R.id.channel_choiceCallCheckBox,
            R.id.channel_busyDenyCheckBox,
            R.id.channel_interfereCheckBox})
    public void clickCheckBox(final CheckBox checkbox) {
        switch (checkbox.getId()) {
            case R.id.channel_PTTIDCheckBox:
                channel.setPTTID(checkbox.isChecked());
                break;
            case R.id.channel_choiceCallCheckBox:
                channel.setChoiceCall(checkbox.isChecked());
                break;
            case R.id.channel_busyDenyCheckBox:
                channel.setBusyDeny(checkbox.isChecked());
                break;
            case R.id.channel_interfereCheckBox:
                channel.setInterfere(checkbox.isChecked());
                break;
        }
        ChannelList.get(mAppActivity).updateChannel(channel);
    }

    @OnTextChanged(value = {R.id.channel_nicknameEditText,
            R.id.channel_receive_rateEditText,
            R.id.channel_launch_rateEditText}, callback = OnTextChanged.Callback.TEXT_CHANGED)
    public void changeEditText(CharSequence s, int start, int before, int count) {

        Toast.makeText(mAppActivity, "editchange:" + s, Toast.LENGTH_SHORT).show();
    }
}