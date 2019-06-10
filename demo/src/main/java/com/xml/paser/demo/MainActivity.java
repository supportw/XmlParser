package com.xml.paser.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xml.paser.XmlPaser;
import com.xml.paser.demo.bean.Root;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.btn_test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Root root = XmlPaser.getInstance().fromXml(Root.class, getAssets().open("test.xml"));
                    Log.e(TAG, "onClick: "+root.getCategoryList() );
                    Log.e(TAG, "onClick: "+root.getName() );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
