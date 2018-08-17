package zzl.pigeonpush;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

public class MyApplicition extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

//        /**
//         * AndroidManifest.xml文件中已经配置好  使用该方法
//         * 初始化common库
//         * 参数1:上下文，不能为空
//         * 参数2:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
//         * 参数3:Push推送业务的secret
//         */
//        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "az1eksp6x8l0awaf0a91uilpepgmia5h");

        /**
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:【友盟+】 AppKey
         * 参数3:【友盟+】 Channel
         * 参数4:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数5:Push推送业务的secret
         */
        UMConfigure.init(this, "5b7634adb27b0a7a74000088", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "80e19eb4e116f2c3a36bf690d1a62151");


        PushAgent mPushAgent = PushAgent.getInstance(this);

        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.e("123","注册成功的ToKen: "+deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.e("123","注册失败的消息: "+s+"      "+s1);
            }
        });



    }





}
