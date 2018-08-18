package zzl.pigeonpush;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.umeng.message.PushAgent;
import com.umeng.message.inapp.IUmengInAppMsgCloseCallback;
import com.umeng.message.inapp.InAppMessageManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 应用内插入一个内部的消息
         *
         * label是插屏消息的标签，用来标识该消息。
         * 客户端需先调用showCardMessage，把label发送到服务器，之后U-Push后台【展示位置】才会出现可选label。
         * 以label为单位，生产模式请求服务器的最小间隔是30分钟，测试模式的最小间隔是1秒。
         * 插屏消息的图片会自动缓存，并在有新消息到来时，删除旧消息的缓存。
         * 注意：安装到设备上后，每个版本（versionCode）的App最多打10个标签。
         */
        InAppMessageManager.getInstance(this).showCardMessage(this, "main",
                new IUmengInAppMsgCloseCallback() {
                    //插屏消息关闭时，会回调该方法
                    @Override
                    public void onColse() {
                        Toast.makeText(MainActivity.this, "消息关闭", Toast.LENGTH_SHORT).show();
                    }
                });


    }
}
