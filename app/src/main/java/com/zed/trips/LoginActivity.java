package com.zed.trips;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import com.zed.Utils.Constans;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView cancel_login;
    private ImageView qq_login;
    private ImageView weibo_login;
    private ImageView wx_login;
    private TextView regester_login;
    private TextView pass_login;
    private Button account_login;
    private Button mobile_login;
    public static Tencent mTencent;
    private UserInfo mInfo;
    //  private Handler mHandler=new MainActivity().mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        // Log.d("hc","handler name L"+mHandler.toString());
    }

    private void initView() {
        setContentView(R.layout.activity_login);
        cancel_login = (ImageView) findViewById(R.id.cancel_login);
        cancel_login.setOnClickListener(this);
        qq_login = (ImageView) findViewById(R.id.qq_login);
        qq_login.setOnClickListener(this);
        weibo_login = (ImageView) findViewById(R.id.weibo_login);
        weibo_login.setOnClickListener(this);
        wx_login = (ImageView) findViewById(R.id.wx_login);
        wx_login.setOnClickListener(this);
        mobile_login = (Button) findViewById(R.id.mobile_login);
        mobile_login.setOnClickListener(this);
        account_login = (Button) findViewById(R.id.account_login);
        account_login.setOnClickListener(this);
        pass_login = (TextView) findViewById(R.id.pass_login);
        pass_login.setOnClickListener(this);
        regester_login = (TextView) findViewById(R.id.regester_login);
        regester_login.setOnClickListener(this);

        if (mTencent == null) {
            mTencent = Tencent.createInstance(Constans.QQ_APP_ID, this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_login:
                finish();
            case R.id.qq_login:
            case R.id.weibo_login:
            case R.id.wx_login:
                onClickLogin();
            case R.id.mobile_login:
            case R.id.account_login:
            case R.id.pass_login:
            case R.id.regester_login:
        }
    }

    private void onClickLogin() {
        if (!mTencent.isSessionValid()) {
            mTencent.login(this, "all", loginListener);
            Log.d("hc", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
            mTencent.logout(this);
        }
    }


    public static void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch (Exception e) {
        }
    }

    private void updateUserInfo() {
        if (mTencent != null && mTencent.isSessionValid()) {
            IUiListener listener = new IUiListener() {
                @Override
                public void onError(UiError e) {

                }

                @Override
                public void onComplete(final Object response) {
                    JSONObject json = (JSONObject) response;
                    Intent intent = new Intent();
                    try {
                        intent.putExtra("figureurl", json.getString("figureurl_qq_2"));
                        intent.putExtra("nickname", json.getString("nickname"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    setResult(1, intent);
                    finish();
                }

                @Override
                public void onCancel() {

                }
            };
            mInfo = new UserInfo(this, mTencent.getQQToken());
            mInfo.getUserInfo(listener);
        }
    }

    IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {
            Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:" + SystemClock.elapsedRealtime());
            initOpenidAndToken(values);
            updateUserInfo();
            //    updateLoginButton();
        }
    };

    private class BaseUiListener implements IUiListener {

        protected void doComplete(JSONObject values) {

        }

        @Override
        public void onComplete(Object response) {
            if (null == response) {
                Toast.makeText(LoginActivity.this, "返回为空,登录失败", Toast.LENGTH_SHORT).show();
                // 记住是否登陆
                saveLoginState();
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            Log.d("hc", "response" + ((JSONObject) response).toString());

            if (null != jsonResponse && jsonResponse.length() == 0) {
                Toast.makeText(LoginActivity.this, "返回为空,登录失败", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            Log.d("hc", "QQ_login" + response.toString());

            doComplete((JSONObject) response);
        }

        @Override
        public void onError(UiError e) {
            Toast.makeText(LoginActivity.this, "onError", Toast.LENGTH_SHORT).show();
            Log.d("hc", "QQ_login onError" + e.errorDetail);
        }

        @Override
        public void onCancel() {
            Toast.makeText(LoginActivity.this, "onCancel", Toast.LENGTH_SHORT).show();
        }


    }

    private void saveLoginState() {
        SharedPreferences sp = getSharedPreferences("Trips", MODE_PRIVATE);
        sp.edit().putBoolean("LoginState", mTencent.isSessionValid()).commit();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("hc", "-->onActivityResult " + requestCode + " resultCode=" + resultCode);
        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}
