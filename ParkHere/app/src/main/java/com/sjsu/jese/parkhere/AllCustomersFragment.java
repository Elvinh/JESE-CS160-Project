package com.sjsu.jese.parkhere;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sjsu.jese.parkhere.model.Customer;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllCustomersFragment extends Fragment {

    private ListView cusListView;
    private ArrayList<String> mCustomers = new ArrayList<>();
    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference customerRef = mRootRef.child("Customers");

    public AllCustomersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_customers, container, false);

        cusListView = (ListView) view.findViewById(R.id.customer_list);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mCustomers);
        cusListView.setAdapter(arrayAdapter);
        return view;
    }

}
