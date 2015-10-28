package wody.wody;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by dasas on 12/09/15.
 */
public class menu_fragment1 extends Fragment {
    View rootview;
    List<String> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.menu1_layout, container, false);

        return rootview;

    }
}
