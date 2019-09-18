package com.example.bottombar_navigation_with_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class NotificationsFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listitems;
    FirebaseFirestore myDb;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notifications, null);
    }

    @Override
    public void onStart() {
        super.onStart();
        final ProgressDialog progressDialog=new ProgressDialog(getActivity());
//        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        FirebaseApp.initializeApp(getActivity());
        myDb=FirebaseFirestore.getInstance();
        recyclerView = getActivity().findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listitems = new ArrayList<>();
        myDb.collection("Orders").get().addOnSuccessListener(
                new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if(!(documentSnapshots.isEmpty()))
                        {
                            List<DocumentSnapshot> small_list=documentSnapshots.getDocuments() ;
                            for(DocumentSnapshot d : small_list ){
                                String items=d.getString("items");
                                String order_id=d.getString("order_id");
                                String pay_method=d.getString("payment_method");
                                String total_cost=String.valueOf(d.get("total_cost"));
                                String username=d.getString("username");
                                String status=d.getString("status");
                                ListItem listitem=new ListItem(username,order_id,items,pay_method,Integer.parseInt(total_cost),status);
                                listitems.add(listitem);
                                adapter= new myadapter(listitems,getActivity());
                                recyclerView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                progressDialog.dismiss();
                            }
                        }
                    }
                }
        );
//        progressDialog.dismiss();
//        for(int i=0;i<=10;i++){
//            Listitem listitem =new Listitem(
//                    "heading" +(i+1),"Yaaaa Hey Iam Here !"
//            );
    }
}
