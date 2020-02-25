package com.alisa.diabet.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alisa.diabet.DB.DbHelper;
import com.alisa.diabet.DB.DbHelperAvatar;
import com.alisa.diabet.R;

import java.io.ByteArrayInputStream;
import java.util.List;

public class Fragment4 extends Fragment {

    ImageView avatar;
    DbHelperAvatar dbHelperAvatar;
    DbHelper dbHelper;
    TextView usName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment4, null);

     /*   avatar = v.findViewById(R.id.imageView6);
        dbHelperAvatar = new DbHelperAvatar(v.getContext());
        dbHelper = new DbHelper(v.getContext());

        try {
            byte[] bytes = dbHelperAvatar.getAvatarList();
            ByteArrayInputStream imageStream = new ByteArrayInputStream(bytes);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            avatar.setImageBitmap(theImage);
        } catch (Exception e){ }

        List <String> accList = dbHelper.getAccountList();
*/

       return  v;
    }


}
