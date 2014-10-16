package fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import interfaces.Communicator;
import tourdiary.theroadrunner.com.tourdiary.R;

/**
 * Created by Dobromir on 13.10.2014 г..
 */
public class FragmentInfo extends Fragment implements AdapterView.OnItemClickListener{

    ListView listView;
    Communicator communicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        communicator = (Communicator)getActivity();
        listView = (ListView)getActivity().findViewById(R.id.listView);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.titles, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        communicator.respond(position);
    }
}
