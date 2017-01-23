package com.google.smartcity.ui.activity;

/*import com.google.smartcity.utils.CommonUtil;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.qq.smartcity.R;

package com.qq.smartcity.activity;

public class RegistActivity extends Activity
        implements View.OnClickListener
{
    private static final String THE_KEY = "e6rAk(y^%I";
    private ImageButton btn_back;
    private EditText et_regist_password ;
    private EditText et_regist_repassword ;
    private EditText et_regist_username ;
    @ViewInject(R.id.)
    private Button regist ;
    private String regist_password ;
    private String regist_repassword ;
    private String regist_username ;
    private TextView txt_title ;

    public static boolean check(String paramString)
    {
        int i = paramString.charAt(0);
        boolean j;
        if ((i < 97) || (i > 122))
        {
            j = false;
            if (i >= 65)
            {
                j = false;
                if (i > 90);
            }
        }
        else
        {
            j = true;
        }
        return j;
    }


    private void load()
    {
        RequestParams localRequestParams = new RequestParams();
        localRequestParams.addBodyParameter("username", this.regist_username);
        localRequestParams.addBodyParameter("password", CommonUtil.md5(this.regist_password));
        localRequestParams.addBodyParameter("key", CommonUtil.md5(this.regist_username + CommonUtil.md5(this.regist_password) + "e6rAk(y^%I"));
        LogUtils.d(this.regist_username);
        LogUtils.d(CommonUtil.md5(this.regist_password));
        LogUtils.d(CommonUtil.md5(this.regist_username + CommonUtil.md5(this.regist_password) + "e6rAk(y^%I"));
        loadData(HttpRequest.HttpMethod.POST, "http://zhbj.qianlong.com/client/user/register", localRequestParams, new RequestCallBack()
        {
            public void onFailure(HttpException paramHttpException, String paramString)
            {
                ToastUtil.getInstance().showToast("注册失败,请检查网络");
            }

            public void onStart()
            {
                RegistActivity.this.showProgressDialog("正在注册");
                super.onStart();
            }

            public void onSuccess(ResponseInfo<String> paramResponseInfo)
            {
                LogUtils.d((String)paramResponseInfo.result);
                RegistActivity.this.closeProgressDialog();
                RegisterBean localRegisterBean = (RegisterBean)QLParser.parse((String)paramResponseInfo.result, RegisterBean.class);
                if (localRegisterBean.retcode == 0)
                    ToastUtil.getInstance().showToast("该用户名已被使用");
                do
                {
                    return;
                    if (localRegisterBean.retcode != -1)
                        continue;
                    ToastUtil.getInstance().showToast("注册失败");
                    return;
                }
                while (localRegisterBean.retcode != 200);
                ToastUtil.getInstance().showToast("注册成功");
                SharePrefUtil.saveString(RegistActivity.this.ct, "ql_token", localRegisterBean.data);
                SharePrefUtil.saveString(RegistActivity.this.ct, "ql_username", RegistActivity.this.regist_username);
                RegistActivity.this.finish();
            }
        });
    }

    public static boolean test(String paramString)
    {
        int i = paramString.charAt(0);
        boolean j;
        if ((i < 65) || (i > 90))
        {
            j = false;
            if (i >= 97)
            {
                j = false;
                if (i > 122);
            }
        }
        else
        {
            j = true;
        }
        return j;
    }

    protected void initData()
    {
    }

    protected void initView()
    {
        setContentView(R.layout.activity_regist);
        //initTitleBar();
        this.titleTv.setText("注册");
        this.regist = ((Button)findViewById(2131165246));
        this.regist.setOnClickListener(this);

    }

    public void onClick(View paramView)
    {
        switch (paramView.getId())
        {
            default:
                return;
            case 2131165214:
                startActivity(new Intent(this.ct, LoginActivity.class));
                finish();
                return;
            case 2131165246:
        }
        this.regist_username = this.et_regist_username.getText().toString().trim();
        this.regist_repassword = this.et_regist_repassword.getText().toString().trim();
        this.regist_password = this.et_regist_password.getText().toString().trim();
        if (TextUtils.isEmpty(this.regist_username))
        {
            ToastUtil.getInstance().showToast("请输入用户名");
            return;
        }
        if (!check(this.regist_username))
        {
            ToastUtil.getInstance().showToast("用户名字母开头");
            return;
        }
        if ((this.regist_username.length() > 20) || (this.regist_username.length() < 3))
        {
            System.out.println("regist_username.length()" + this.regist_username.length());
            ToastUtil.getInstance().showToast("用户名长度只能在3-20位字符之间");
            return;
        }
        if (TextUtils.isEmpty(this.regist_password))
        {
            ToastUtil.getInstance().showToast("请输入密码");
            return;
        }
        if (StringUtil.vd(this.regist_password))
        {
            ToastUtil.getInstance().showToast("密码不能为中文");
            return;
        }
        if ((this.regist_password.length() < 6) || (this.regist_password.length() > 20))
        {
            ToastUtil.getInstance().showToast("密码长度只能在6-20位字符之间");
            return;
        }
        if (!this.regist_password.equals(this.regist_repassword))
        {
            ToastUtil.getInstance().showToast("两次密码输入不一致");
            return;
        }
        load();
    }

    protected void processClick(View paramView)
    {
    }
}*/
