package com.example.lab2;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

// наследуется от Fragment и имплиминтирует интерфейс View.OnClickListener
public class FragmentInterface extends Fragment implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public FragmentInterface() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_interface, container, false);
    }
    // созадет кнопку и ждет нажатия
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn = (Button) view.findViewById(R.id.CallBackButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback!= null)
                    callback.callingBack();
            }
        });
    }
    @Override
    public void onClick(View view) {

    }
    // inner interface
    public interface Callback{
        void callingBack();
    }
    // переменная callback которую передадим
    private Callback callback;
    // регистрируем callback
    public void registerCallBack(Callback callback){
        this.callback = callback;
    }


    //аттаче у фрагмента нужно проверяем имплиментирован ли он
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (Callback) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement Callback");
        }
    }
}