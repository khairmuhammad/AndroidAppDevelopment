package com.example.android.semester_project.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.semester_project.Adapter.UserAdapter;
import com.example.android.semester_project.Model.Chat;
import com.example.android.semester_project.Model.Chatlist;
import com.example.android.semester_project.Model.User;
import com.example.android.semester_project.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {

    public RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> mUsers;

    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

    private List<Chatlist> usersList;

      @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

          View view = inflater.inflate(R.layout.fragment_chat, container, false);

          recyclerView = view.findViewById(R.id.recycler_view);
          recyclerView.setHasFixedSize(true);
          recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

          firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

          usersList = new ArrayList<>();

          databaseReference = FirebaseDatabase.getInstance().getReference("Chatlist")
          .child(firebaseUser.getUid());
          databaseReference.addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                  usersList.clear();

                  for (DataSnapshot snapshot: dataSnapshot.getChildren())
                  {
                      Chatlist chatlist = snapshot.getValue(Chatlist.class);
                      usersList.add(chatlist);
                  }

                  chatList();
              }

              @Override
              public void onCancelled(@NonNull DatabaseError databaseError) {

              }
          });

          return view;
      }

    private void chatList() {
          mUsers = new ArrayList<>();
          databaseReference = FirebaseDatabase.getInstance().getReference("Users");
          databaseReference.addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                  mUsers.clear();
                  for (DataSnapshot snapshot: dataSnapshot.getChildren())
                  {
                      User user = snapshot.getValue(User.class);
                      for (Chatlist chatlist : usersList)
                      {
                          if (user.getId().equals(chatlist.getId()))
                          {
                              mUsers.add(user);
                          }
                      }
                  }

                  userAdapter = new UserAdapter(getContext(),mUsers,true);
                  recyclerView.setAdapter(userAdapter);

              }
              @Override
              public void onCancelled(@NonNull DatabaseError databaseError) {

              }
          });
      }
}
