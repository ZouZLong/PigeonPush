package zzl.pigeonpush;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;



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
//        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "80e19eb4e116f2c3a36bf690d1a62151");

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
                Log.e("123", "注册成功的ToKen: " + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.e("123", "注册失败的消息: " + s + "      " + s1);
            }
        });


        /**
         * 自定义对通知的操作
         */
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {

            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                Log.e("123", "这是Toast的内容：" + msg);
                Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
            }
        };
        mPushAgent.setNotificationClickHandler(notificationClickHandler);


        /**
         * 通知展示
         */
        UmengMessageHandler messageHandler = new UmengMessageHandler() {
            /**
             * 通知的回调方法
             * @param context
             * @param msg
             */
            @Override
            public void dealWithNotificationMessage(Context context, UMessage msg) {
                //调用super则会走通知展示流程，不调用super则不展示通知
                super.dealWithNotificationMessage(context, msg);
                Log.e("123", "通知展示");
            }
        };
        mPushAgent.setMessageHandler(messageHandler);


        /**
         * 自定义导航栏样式  样式在Layout 的View设计
         */
        UmengMessageHandler messageHandler_TYP = new UmengMessageHandler() {

            @Override
            public Notification getNotification(Context context, UMessage msg) {
                switch (msg.builder_id) {
                    case 1:
                        Notification.Builder builder = new Notification.Builder(context);
                        RemoteViews myNotificationView = new RemoteViews(context.getPackageName(),
                                R.layout.notification_view);  //View 布局
                        myNotificationView.setTextViewText(R.id.notification_title, msg.title);
                        myNotificationView.setTextViewText(R.id.notification_text, msg.text);
                        myNotificationView.setImageViewBitmap(R.id.notification_large_icon,
                                getLargeIcon(context, msg));
                        myNotificationView.setImageViewResource(R.id.notification_small_icon,
                                getSmallIconId(context, msg));
                        builder.setContent(myNotificationView)
                                .setSmallIcon(getSmallIconId(context, msg))
                                .setTicker(msg.ticker)
                                .setAutoCancel(true);

                        return builder.getNotification();
                    default:
                        //默认为0，若填写的builder_id并不存在，也使用默认。
                        return super.getNotification(context, msg);
                }
            }
        };

        mPushAgent.setMessageHandler(messageHandler_TYP);


        /**
         * 通知栏数量的显示
         * 参数number可以设置为0~10之间任意整数。当参数为0时，表示不合并通知。
         该方法可以多次调用，以最后一次调用时的设置为准。
         */
        mPushAgent.setDisplayNotificationNumber(0);

        /**
         * 客户端控制通知到达响铃、震动、呼吸灯
         * MsgConstant.NOTIFICATIONPLAYSERVER（服务端控制）
         * MsgConstant.NOTIFICATIONPLAYSDKENABLE（客户端允许）
         * MsgConstant.NOTIFICATIONPLAYSDKDISABLE（客户端禁止）
         * Android 8.0及以上版本系统，需开发者重写UmengMessageHandler的getNotification方法，使用NotificationChannel类来控制。
         */

        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SERVER); //声音
        mPushAgent.setNotificationPlayLights(MsgConstant.NOTIFICATION_PLAY_SERVER);//呼吸灯
        mPushAgent.setNotificationPlayVibrate(MsgConstant.NOTIFICATION_PLAY_SERVER);//振动


    }


}
