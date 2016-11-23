package com.kaixin.testfor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.kaixin.testfor.utils.Api.LoginApi;
import com.kaixin.testfor.utils.ImageUtil;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE=1;
    private SimpleDraweeView fresco_image;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fresco_image = (SimpleDraweeView) findViewById(R.id.fresco_image);
        button = (Button) findViewById(R.id.button_1);
        String url = "http://tc.sinaimg.cn/maxwidth.2048/tc.service.weibo.com/p/cms_api_aheading_com/55082e40d1308705873e994f5345c094.jpg";
        ImageUtil.setImage(fresco_image,url);
        LoginApi.login("18202806032","123456");
        button.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_1:
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE){
            //处理扫面结果在界面上显示
            if (null != data){
                Bundle bundle = data.getExtras();
                if (bundle == null){
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS){
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果"+result, Toast.LENGTH_SHORT).show();
                }else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED){
                    Toast.makeText(this, "解析失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
