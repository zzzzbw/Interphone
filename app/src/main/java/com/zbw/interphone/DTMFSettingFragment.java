package com.zbw.interphone;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zbw.interphone.view.LineSettingView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ZBW on 2017/4/29.
 */

public class DTMFSettingFragment extends Fragment {

    private Activity mAppActivity;

    @BindView(R.id.viewCodingSetting)
    public LineSettingView viewCodingSetting;
    @BindView(R.id.viewDecodingSetting)
    public LineSettingView viewDecodingSetting;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dtmf, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick({R.id.viewCodingSetting, R.id.viewDecodingSetting})
    public void goActivtity(final LineSettingView view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.viewCodingSetting:
                intent = new Intent(mAppActivity, CodingSettingActivity.class);
                break;
            case R.id.viewDecodingSetting:
                intent = new Intent(mAppActivity, DecodingSettingActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
