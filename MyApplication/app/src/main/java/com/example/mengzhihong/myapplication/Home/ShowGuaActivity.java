package com.example.mengzhihong.myapplication.Home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mengzhihong.myapplication.R;

import java.lang.reflect.Array;

public class ShowGuaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_gua);
        showContent();
    }

    public void back(View v){
        finish();
    }


    private void showContent(){

        TextView textView=(TextView)findViewById(R.id.textView);

        Intent intent=getIntent();
        int guaNumber=intent.getIntExtra("guaNumber",0);
        String type=intent.getStringExtra("type");

        Log.i("type",type);


        String textViewText=handleString(guaNumber,type);

        textView.setText(textViewText);

        Log.i("gua",textViewText);



    }

    private String handleString(int guaNumber,String type){

       String content="";

        int pre=guaNumber/10;
        int next=guaNumber%10;
//        是否有两个卦
        boolean isDouble=false;

        if ((pre==next)||(pre+next==9)){
            isDouble=true;
        }

        BenMingDetail benMingDetail=new BenMingDetail();
        GuaName guaName=new GuaName();


        if (type.equals("本命卦")){
            content=benMingDetail.getBenMingDetail(guaNumber);


        }else if (type.equals("成功卦")){

            int gua[]=guaName.getSuccess(guaNumber);

            if (isDouble){
                String text1=benMingDetail.getBenMingDetail(gua[0]);
                String text2=benMingDetail.getBenMingDetail(gua[1]);

                content=String.format(text1+"\n\n\n"+text2);

            }else {
                String text1=benMingDetail.getBenMingDetail(gua[0]);
                content=text1;
            }


        }else if (type.equals("失败卦")){

            int gua[]=guaName.getFailure(guaNumber);

            if (isDouble){
                String text1=benMingDetail.getBenMingDetail(gua[0]);
                String text2=benMingDetail.getBenMingDetail(gua[1]);

                content=String.format(text1+"\n\n\n"+text2);

            }else {
                String text1=benMingDetail.getBenMingDetail(gua[0]);
                content=text1;
            }

        }else {
//            衰变卦

            int gua[]=guaName.getFailure(guaNumber);

            if (isDouble){
                String text1=benMingDetail.getBenMingDetail(gua[0]);
                String text2=benMingDetail.getBenMingDetail(gua[1]);

                content=String.format(text1+"\n\n\n"+text2);

            }else {
                String text1=benMingDetail.getBenMingDetail(gua[0]);
                content=text1;
            }

        }


        return content;

    }

}
