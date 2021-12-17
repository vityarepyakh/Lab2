package com.example.lab2;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.lab2.ui.login.LoginActivity;
import com.squareup.otto.Bus; // используем otto
import com.squareup.otto.Subscribe;

public class MainActivity extends Activity implements FragmentInterface.Callback{
    TextView textView;
    FragmentTransaction fragmentTransaction;
    private Bus bus; // шина
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.callbacktext); // текст который отобразит каллбек
        bus = new Bus(); // создаем шину
        bus.register(this); // регистрируем

    }
    // переключение фрагментов по кнопкам
    public void onClick(View view) {
        fragmentTransaction = getFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.fragmentbutton1:
                fragmentTransaction.replace(R.id.frgcontainer, new BlankFragment1()); // если нажата кнопка Fragment 1
                break;
            case R.id.fragmentMeny:
                fragmentTransaction.replace(R.id.frgcontainer, new FragmentInterface()); // если нажата кнопка Fragment-Menu
                break;
            case R.id.fragmentbutton2:
                fragmentTransaction.replace(R.id.frgcontainer, new BlankFragment2()); // если нажата кнопка Fragment 2
                break;

            case R.id.loginbutton:
                bus.post(new Activator()); // если нажата кнопка Login
                break;
        }
        fragmentTransaction.addToBackStack(null); // добавление к стеку
        fragmentTransaction.commit();   // коммит
    }
    // интент флага
    @Subscribe
    public void ActivityStarter(Activator event){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    // заменяет текст при каллбеке
    @Override
    public void callingBack() {
        textView.setText("каллбек");
    }
}