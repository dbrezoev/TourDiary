package fragments;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.telerik.everlive.sdk.core.transport.Communicator;

import tourdiary.theroadrunner.com.tourdiary.R;

/**
 * Created by Dobromir on 13.10.2014 г..
 */
public class FragmentDescription extends Fragment {
    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_description, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView = (TextView)getActivity().findViewById(R.id.textView);
    }

    public void changeData(int index){
        //
        Resources resources = getResources();
        String[] arr = resources.getStringArray(R.array.descriptions);
        textView.setText(arr[index]);

        //Toast.makeText(getActivity(), arr[index], Toast.LENGTH_SHORT).show();
    }
}
