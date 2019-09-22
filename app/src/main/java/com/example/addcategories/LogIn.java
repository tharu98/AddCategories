package com.example.addcategories;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LogIn extends AppCompatActivity {
    EditText _txtUser,_txtPass;
    Button _btnLogin;
    Spinner _spinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        _txtPass=(EditText)findViewById(R.id.password);
        _txtUser=(EditText)findViewById(R.id.username);
        _btnLogin=(Button)findViewById(R.id.logBtn);
        _spinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.usertype,R.layout.support_simple_spinner_dropdown_item);
        _spinner.setAdapter(adapter);
        _btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item=_spinner.getSelectedItem().toString();
                if(_txtUser.getText().toString().equals("admin") && _txtPass.getText().toString().equals("admin")&& item.equals("admin")) {
                    Intent intent = new Intent(LogIn.this, Admin.class);
                    startActivity(intent);
                }else if(_txtUser.getText().toString().equals("admin") && _txtPass.getText().toString().equals("admin")&& item.equals("user")){
                    Intent intent = new Intent(LogIn.this, User.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
