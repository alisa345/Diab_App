package com.alisa.diabet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alisa.diabet.DB.DbHelper;
import com.alisa.diabet.DB.DbHelperAvatar;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextUsername, editTextEmail, editTextPassword;
    private Button buttonRegister;
    private ProgressDialog progressDialog;
    ImageView imageView, imageViewGal, avatar;
    DbHelper dbHelper;
    DbHelperAvatar dbHelperAvatar;
    Bitmap bitmap;
    byte[] byteArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        imageView = findViewById(R.id.imageView4);
        imageViewGal = findViewById(R.id.imageView3);
        avatar = findViewById(R.id.imageView2);
        dbHelper = new DbHelper(this);
        dbHelperAvatar = new DbHelperAvatar(this);


        dbHelper.deleteAxx();
        dbHelperAvatar.deleteAvatar();
        buttonRegister.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);

        imageView.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        imageViewGal.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery, "Select Picture"),
                        1001);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == 1001) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();

                bitmap = BitmapFactory.decodeFile(picturePath);

                if (bitmap != null) {
                    avatar.setImageBitmap(bitmap);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
                    byteArray = stream.toByteArray();
                }
            }
        } catch (Exception e) {
        }
    }

    private void registerUser() {
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String username = editTextUsername.getText().toString().trim();

        if (password.length() >= 6 && username.length() >= 4) {


            progressDialog.setMessage("Регистрация пользователя...");
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constants.URL_REGISTER,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Toast.makeText(getApplicationContext(), jsonObject.getString(
                                        "message"), Toast.LENGTH_LONG).show();
                                String username2 = String.valueOf(editTextUsername.getText());

                                if (jsonObject.getBoolean("error") == true) {

                                } else {
                                    dbHelper.insertNewAcc(username2);
                                    if (byteArray != null) {
                                        dbHelperAvatar.insertImg(byteArray);
                                    }
                                    System.out.println(dbHelper.getAccountList());
                                    Intent intent2 = new Intent(SignUpActivity.this, HomeActivity.class);
                                    startActivity(intent2);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.hide();
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("username", username);
                    params.put("password", password);
                    params.put("email", email);
                    return params;
                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

        }
        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Слишком короткий пароль", Toast.LENGTH_LONG).show();
        }
        if (username.length() < 4) {
            Toast.makeText(getApplicationContext(), "Слишком короткий логин", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onClick(View view) {
        if (view == buttonRegister) {
            registerUser();

        }

    }
}
