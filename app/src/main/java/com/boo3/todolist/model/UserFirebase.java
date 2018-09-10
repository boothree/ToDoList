package com.boo3.todolist.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.widget.ImageView;

import com.boo3.todolist.R;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import javax.annotation.Nullable;

public class UserFirebase extends BaseObservable {

    public static UserFirebase userFirebase;

    private FirebaseUser firebaseUser;

    public UserFirebase() {
    }

    public static UserFirebase getInstance() {
        if (userFirebase == null) {
            userFirebase = new UserFirebase();
        }
        return userFirebase;
    }

    @Bindable
    @Nullable
    public String getNameUser() {
        return firebaseUser.getDisplayName();
    }


    @Bindable
    @Nullable
    public String getEmailUser() {
        return firebaseUser.getEmail();
    }

    @Bindable
    @Nullable
    public String getImageUrl(){ return firebaseUser.getPhotoUrl().toString();}

    @BindingAdapter({"app:url"})
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).into(view);
    }

    public FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }

    public void setFirebaseUser(FirebaseUser firebaseUser) {
        this.firebaseUser = firebaseUser;
    }
}
