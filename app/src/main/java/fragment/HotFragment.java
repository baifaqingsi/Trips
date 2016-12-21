package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.zed.trips.LoginActivity;
import com.zed.trips.R;

public class HotFragment extends BaseFragment implements View.OnClickListener {

    private ImageButton fab;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        fab = (ImageButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                startActivity(new Intent(getActivity(), LoginActivity.class));
        }
    }
}
