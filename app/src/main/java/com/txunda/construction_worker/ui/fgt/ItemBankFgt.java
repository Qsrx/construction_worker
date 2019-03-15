package com.txunda.construction_worker.ui.fgt;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.google.gson.JsonSyntaxException;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseFgt;
import com.txunda.construction_worker.bean.ItemBankBean;
import com.txunda.construction_worker.ui.aty.ChapterExercisesAty;
import com.txunda.construction_worker.ui.aty.DoRecordAty;
import com.txunda.construction_worker.ui.aty.EverydayPunchClockTestAty;
import com.txunda.construction_worker.ui.aty.MistakesCollectionAty;
import com.txunda.construction_worker.ui.aty.MyCollectsAty;
import com.txunda.construction_worker.ui.aty.SpecialExercisesAty;
import com.txunda.construction_worker.ui.aty.TrueTestQuestionsAty;
import com.txunda.construction_worker.utils.AllStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.txunda.construction_worker.ui.fgt.HomeFgt.Industry_ID;


@Layout(R.layout.fgt_item_bank)
public class ItemBankFgt extends BaseFgt implements View.OnClickListener{
    RelativeLayout rl_dk;
    LinearLayout ll_collect,ll_fail,ll_do,ll_zxlx,ll_title,ll_everyday,ll_chapter,ll_test,ll_real;
    TextView tv_title,tv_sum,tv_sure,tv_days;
    private AlertDialog dialog_phone;
    private ListView lv;
    private List<String> lists = new ArrayList<>();
    private List<String> id_lists = new ArrayList<>();
    public static String subject_id = null;
    private ItemBankBean bankBean;
    private boolean is_have = false;
    @Override
    public void initViews() {
        super.initViews();
        rl_dk = findViewById(R.id.fgt_item_bank_rl_dk);
        ll_collect = findViewById(R.id.fgt_item_bank_ll_collect);
        ll_fail = findViewById(R.id.fgt_item_bank_ll_fail);
        ll_do = findViewById(R.id.fgt_item_bank_ll_do);
        ll_zxlx = findViewById(R.id.fgt_item_bank_ll_zxlx);
        ll_title = findViewById(R.id.fgt_item_bank_ll_title);
        tv_title = findViewById(R.id.fgt_item_bank_tv_title);
        tv_sum = findViewById(R.id.fgt_item_bank_tv_sum);
        tv_sure = findViewById(R.id.fgt_item_bank_tv_sure);
        tv_days = findViewById(R.id.fgt_item_bank_tv_days);
        ll_everyday = findViewById(R.id.fgt_item_bank_ll_everyday);
        ll_chapter = findViewById(R.id.fgt_item_bank_ll_chapter);
        ll_test = findViewById(R.id.fgt_item_bank_ll_test);
        ll_real = findViewById(R.id.fgt_item_bank_ll_real);
        dialog_phone = new AlertDialog.Builder(me).create();
//        lists = new ArrayList<>();
//        lists.add("全部");
//        lists.add("专项练习题");
//        lists.add("章节练习题");
//        lists.add("模拟考试题");
//        lists.add("真题考试提");
    }

    @Override
    public void setEvents() {
        super.setEvents();
        rl_dk.setOnClickListener(this);
        ll_collect.setOnClickListener(this);
        ll_fail.setOnClickListener(this);
        ll_do.setOnClickListener(this);
        ll_zxlx.setOnClickListener(this);
        ll_title.setOnClickListener(this);
        ll_everyday.setOnClickListener(this);
        ll_chapter.setOnClickListener(this);
        ll_test.setOnClickListener(this);
        ll_real.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fgt_item_bank_rl_dk:
                jump(EverydayPunchClockTestAty.class);
                break;
            case R.id.fgt_item_bank_ll_collect:
                jump(MyCollectsAty.class);
                break;
            case R.id.fgt_item_bank_ll_fail:
                jump(MistakesCollectionAty.class);
                break;
            case R.id.fgt_item_bank_ll_do:
                jump(DoRecordAty.class);
                break;
            case R.id.fgt_item_bank_ll_zxlx:
                jump(SpecialExercisesAty.class);
                break;
            case R.id.fgt_item_bank_ll_title:
                showPop();
                break;
            case R.id.fgt_item_bank_ll_everyday:
                jump(EverydayPunchClockTestAty.class);
                break;
            case R.id.fgt_item_bank_ll_chapter:
                jump(ChapterExercisesAty.class);
                break;
            case R.id.fgt_item_bank_ll_real:
                Intent intent = new Intent(me, TrueTestQuestionsAty.class);
                intent.putExtra("type","2");
                startActivity(intent);
                break;
            case R.id.fgt_item_bank_ll_test:
                Intent intent2 = new Intent(me, TrueTestQuestionsAty.class);
                intent2.putExtra("type","1");
                startActivity(intent2);
                break;
                default:break;
        }
    }
    private void  showPop() {
        View popWiew = LayoutInflater.from(me).inflate(R.layout.pop_center, null);
        dialog_phone.show();
        dialog_phone.setCanceledOnTouchOutside(true);
        Window window2 = dialog_phone.getWindow();
        window2.setGravity(Gravity.BOTTOM);
        window2.setContentView(popWiew);
        window2.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        window2.setBackgroundDrawableResource(R.color.transparent);
        lv = window2.findViewById(R.id.pop_add_list);
        lv.setAdapter(new PopAdapter(lists,me));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tv_title.setText(lists.get(i));
                subject_id = bankBean.getData().getList().get(i).getSubject_id();
                httpData();
                dialog_phone.dismiss();
            }
        });
    }

    /**
     * dialog列表适配器
     */
    private class PopAdapter extends BaseAdapter {
        private List<String> list;
        private Context context;

        public PopAdapter(List<String> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            PopAdapter.ViewHolder vh = null;
            if (view == null){
                view = LayoutInflater.from(context).inflate(R.layout.pop_tv_layout,null);
                vh = new PopAdapter.ViewHolder();
                vh.tv = view.findViewById(R.id.pop_tv_content);
                view.setTag(vh);
            }else {
                vh = (PopAdapter.ViewHolder) view.getTag();
            }
            vh.tv.setText(list.get(i));
            return view;
        }
        class ViewHolder{
            TextView tv;
        }
    }

    @Override
    public void initDatas() {
        super.initDatas();
        httpData();
        Log.d("itembankdata", "onResponse: ===========subject_id"+subject_id);
        Log.d("itembankdata", "onResponse: ===========subject_id"+Industry_ID);
    }

    /**
     * 加载本页数据
     */
    private void httpData(){
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/index", new Parameter()
                .add("token",token)
                .add("subject_id",subject_id)
                .add("industry_id", Industry_ID)
                , new ResponseListener() {
            @Override
            public void onResponse(String response, Exception error) {
                if (error == null) {
                    Map<String, Object> objectMap = JSONUtils.parseJsonObjectStrToMap(response);
                    if (objectMap.get("code").equals("1")) {
                        try {
                            bankBean = GsonUtil.GsonToBean(response, ItemBankBean.class);
                            lists.clear();
                            tv_days.setText("坚持打卡做题"+bankBean.getData().getDays()+"天");
                            try {
                                if (subject_id == null){
                                    subject_id = bankBean.getData().getList().get(0).getSubject_id();
                                    tv_title.setText(bankBean.getData().getList().get(0).getSubject());
                                }
                                for (int i = 0; i < bankBean.getData().getList().size(); i++) {
                                    lists.add(bankBean.getData().getList().get(i).getSubject());
                                    if (subject_id.equals(bankBean.getData().getList().get(i).getSubject_id())){
                                        is_have = true;
                                        tv_title.setText(bankBean.getData().getList().get(i).getSubject());
                                        if (bankBean.getData().getList().get(i).getCount()==null||bankBean.getData().getList().get(i).getCount().equals("")){
                                            tv_sum.setText("0");
                                        }else {
                                            tv_sum.setText(bankBean.getData().getList().get(i).getCount());
                                        }
                                        if (String.valueOf(bankBean.getData().getList().get(i).getAccuracy()) == null || String.valueOf(bankBean.getData().getList().get(i).getAccuracy()).equals(""))
                                        {
                                            tv_sure.setText("0");
                                        }else {
                                            tv_sure.setText(bankBean.getData().getList().get(i).getAccuracy()+"");
                                        }
                                    }else {

                                    }
                                }
                            }catch (NullPointerException e){
                                dialog_phone.dismiss();
                                toast("本科目暂无题目,请稍后再来或提醒教务老师上传新题");
//                                subject_id = "1";
                                try {
                                    subject_id = bankBean.getData().getList().get(0).getSubject_id();
                                }catch (NullPointerException ee){

                                }
                                httpData();
                            }
                        }catch (JsonSyntaxException e){
                            toast("数据类型异常");
                        }
                    }else {
                        toast(objectMap.get("message"));
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        httpData();
    }
}