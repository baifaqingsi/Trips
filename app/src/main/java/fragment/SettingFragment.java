package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zed.trips.R;

/**
 * Created by hc on 16-12-16.
 */
public class SettingFragment extends BaseFragment {


    private ListView listView;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);


        listView = (ListView) view.findViewById(R.id.listView);
        String[] items = getResources().getStringArray(R.array.setting_lv);
        //创建ArrayAdapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getActivity(),R.layout.setting_item,items);
        //获取ListView对象，通过调用setAdapter方法为ListView设置Adapter设置适配器
        listView.setAdapter(adapter);
        return view;
    }
}
