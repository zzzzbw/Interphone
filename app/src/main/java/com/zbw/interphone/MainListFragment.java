package com.zbw.interphone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zbw.interphone.model.InterphoneGlobal;
import com.zbw.interphone.model.InterphoneInfo;
import com.zbw.interphone.model.InterphoneSetting;

/**
 * Created by zbw on 2017/4/4.
 */

public class MainListFragment extends ListFragment {
    private InterphoneInfo mInfo;
    private InterphoneGlobal gloabl;
    private Activity mAppActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppActivity = getActivity();
        gloabl = InterphoneGlobal.get(mAppActivity);
        mInfo = gloabl.getInfo();

        String list_info = getActivity().getString(R.string.main_list_info);
        String list_setting = getActivity().getString(R.string.main_list_setting);
        String list_channel = getActivity().getString(R.string.main_list_channel);
        String list_DTMF = getActivity().getString(R.string.main_list_DTMF);
        String list_scan = getActivity().getString(R.string.main_list_scan);

        String[] array = {list_info, list_setting, list_channel, list_DTMF, list_scan};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_main, array);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case 0:
                createInfoDialog();
                break;
            case 1:
                goSettingActivity();
                break;
            case 2:
                goChannelListActivity();
                break;
            case 3:
                //Todo DTMF信令
                break;
            case 4:
                //Todo 扫描
                break;
            default:
                Toast.makeText(getActivity(), "没有此选项", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    private void createInfoDialog() {
        MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                .title(getActivity().getString(R.string.main_list_info))
                .customView(R.layout.list_info, true)
                .negativeText(android.R.string.cancel)
                .build();
        View view = dialog.getCustomView();
        ((TextView) view.findViewById(R.id.InterphoneInfo_type)).setText(mInfo.getType());
        ((TextView) view.findViewById(R.id.InterphoneInfo_channel)).setText(mInfo.getChannel());
        ((TextView) view.findViewById(R.id.InterphoneInfo_version)).setText(mInfo.getVersion());
        dialog.show();
    }

    private void goSettingActivity() {
        Intent intent = new Intent(mAppActivity, SettingActivity.class);
        startActivity(intent);
    }

    private void goChannelListActivity(){
        Intent intent=new Intent(mAppActivity,ChannelListActivity.class);
        startActivity(intent);
    }


}
