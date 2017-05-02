package com.zbw.interphone;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zbw.interphone.model.InterphoneGlobal;
import com.zbw.interphone.model.ScanList;
import com.zbw.interphone.view.LineSettingView;

import java.util.ArrayList;

/**
 * Created by ZBW on 2017/4/29.
 */

public class ScanListFragment extends ListFragment {
    public static final String EXTRA_SCANLIST_ID = "com.zbw.interphone.scanlist_id";

    private InterphoneGlobal mGlobal;
    private ScanList mScanList;
    private int mScanListId;
    private Activity mAppActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppActivity = getActivity();
        mGlobal = InterphoneGlobal.get(mAppActivity);
        Intent intent = mAppActivity.getIntent();
        int scanList_id = intent.getIntExtra(EXTRA_SCANLIST_ID, 1);
        mScanListId = scanList_id;
        mScanList = mGlobal.getScan().getScanList(mScanListId - 1);

        ArrayAdapter<Integer> arrayAdapter = new ScanListAdapter(mScanList.getScanCodeArrayList());
        setListAdapter(arrayAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private class LineSettingViewListener implements View.OnClickListener {

        private int position;
        private LineSettingView view;

        public LineSettingViewListener(int position, LineSettingView view) {
            this.position = position;
            this.view = view;
        }

        @Override
        public void onClick(View v) {
            int title = R.string.main_list_channel;
            final String inputHint = mAppActivity.getResources().getString(title);
            String inputPreFill = mScanList.getScanCode(position) + "";
            new MaterialDialog.Builder(mAppActivity)
                    .title(title)
                    .inputType(InputType.TYPE_CLASS_NUMBER)
                    .input(inputHint, inputPreFill, false, new MaterialDialog.InputCallback() {
                        @Override
                        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                            int value = Integer.valueOf(input.toString());
                            mScanList.setScanCode(position, value);
                            view.setContent(input.toString());
                        }
                    }).show();
        }
    }

    private class ScanListAdapter extends ArrayAdapter<Integer> {

        public ScanListAdapter(ArrayList<Integer> scanCodeList) {
            super(mAppActivity, 0, scanCodeList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = mAppActivity.getLayoutInflater().inflate(R.layout.list_item_scan, null);
            }
            LineSettingView viewItem = (LineSettingView) convertView.findViewById(R.id.list_item_scan);
            String titlePre = mAppActivity.getResources().getString(R.string.scan_list_code);
            viewItem.setTitle(titlePre + ":" + (position + 1));
            viewItem.setContent(getItem(position).toString());
            viewItem.setOnClickListener(new LineSettingViewListener(position, viewItem));
            return convertView;
        }
    }
}
