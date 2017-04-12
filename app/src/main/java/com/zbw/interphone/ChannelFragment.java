package com.zbw.interphone;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.zbw.interphone.model.ChannelList;
import com.zbw.interphone.model.InterphoneChannel;
import com.zbw.interphone.util.ResourceUtil;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        return view;
    }
}
