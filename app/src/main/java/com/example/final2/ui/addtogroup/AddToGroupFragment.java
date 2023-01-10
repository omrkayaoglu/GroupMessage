package com.example.final2.ui.addtogroup;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.final2.GroupModel;
import com.example.final2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class AddToGroupFragment extends Fragment {
    FirebaseAuth mAuth;
    FirebaseFirestore mStore;

    RecyclerView groupRv,contactRv;
    TextView selectedGroupTextView;

    GroupModel selectedModel;
    ArrayList<GroupModel> groupModelArrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_add_to_group, container, false);
        mAuth=FirebaseAuth.getInstance();
        mStore=FirebaseFirestore.getInstance();

        groupRv=root.findViewById(R.id.addtogroup_groupRecysclerView);
        contactRv=root.findViewById(R.id.adtogroup_contactsRecyclerView);
        selectedGroupTextView=root.findViewById(R.id.addtogroup_selectedGroupTextView);

        groupModelArrayList=new ArrayList<>();

        FetchGroup();
        return root;
    }
    private void FetchGroup(){
        String userId=mAuth.getCurrentUser().getUid();
        mStore.collection("/userdata/"+userId+"/groups").get().addOnSuccessListener(queryDocumentSnapshots -> {
            groupModelArrayList.clear();
            for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                GroupModel groupModel=new GroupModel(documentSnapshot.getString("name"),documentSnapshot.getString("description"),documentSnapshot.getString("image"),(List<String>) documentSnapshot.get("numbers"),documentSnapshot.getId());
                groupModelArrayList.add(groupModel);
            }
            groupRv.setAdapter(new GroupAdapter(groupModelArrayList, position -> {
                selectedModel=groupModelArrayList.get(position);
                selectedGroupTextView.setText("Seçili Grup: "+selectedModel.getName());
            }));
            LinearLayoutManager linearLayout=new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
            groupRv.setLayoutManager(linearLayout);
        });
    }
}