package com.txunda.construction_worker.ui.aty;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.ants.theantsgo.gson.GsonUtil;
import com.ants.theantsgo.util.JSONUtils;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;
import com.txunda.construction_worker.R;
import com.txunda.construction_worker.base.BaseAty;
import com.txunda.construction_worker.bean.PointTestBean;
import com.txunda.construction_worker.ui.adapter.DoWorkFgtRvAdapter;
import com.txunda.construction_worker.utils.AllStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.txunda.construction_worker.ui.fgt.ItemBankFgt.subject_id;

@Layout(R.layout.aty_gesture_filp)
public class GestureFilpAty extends BaseAty implements GestureDetector.OnGestureListener{

    @BindView(R.id.viewFlipper)
    ViewFlipper mViewFlipper;
    //1.定义手势检测器对象
    GestureDetector mGestureDetector;
    //2.定义一个动画数组，用于为ViewFilpper指定切换动画效果。
    Animation[]  animations = new  Animation[4];
    //3.定义手势两点之间的最小距离
    final int FLIP_DISTANCE = 50 ;
    List<String> list = new ArrayList<>();
    List<String> titles = new ArrayList<>();
//    List<PointTestBean> mQuestion =  new ArrayList<>();
    PointTestBean pointTestBean = null;
    DoWorkFgtRvAdapter adapter;
    private String type;
    private List<PointTestBean.DataBean.ListBean> questions;
    public static int index = -1;
    @Override
    public void initViews() {
        super.initViews();
        ButterKnife.bind(this);
        //1.构建手势检测器
        mGestureDetector =  new GestureDetector(this,this);

        type = getIntent().getStringExtra("type");
        //2准备数据
        initData();
//        //3.为ViewFilpper添加子控件。
//        for (int i = 0;i< questions.size();i++){
//            PointTestBean.DataBean.ListBean question = questions.get(i);
//            mViewFlipper.addView(addQuestionView(question));
//        }
        //4.初始化Animation数组
        animations[0] = AnimationUtils.loadAnimation(this,R.anim.left_in);
        animations[1] = AnimationUtils.loadAnimation(this,R.anim.left_out);
        animations[2] = AnimationUtils.loadAnimation(this,R.anim.right_in);
        animations[3] = AnimationUtils.loadAnimation(this,R.anim.right_out);
    }


    private View addQuestionView(PointTestBean.DataBean.ListBean question){
        View view = View.inflate(this, R.layout.fgt_do_work, null);
        TextView tips = view.findViewById(R.id.aty_do_work_tv_title);
        TextView title = view.findViewById(R.id.aty_do_work_tv_content);
        TextView tv_jx = view.findViewById(R.id.fgt_do_work_jx);
        TextView tv_kd = view.findViewById(R.id.fgt_do_work_kd);
        TextView tv_see = view.findViewById(R.id.aty_do_work_tv_see);
        RecyclerView listview = view.findViewById(R.id.aty_do_work_rv);
        list.clear();
        titles.clear();
        for (int i = 0; i < question.getOption().size(); i++) {

            list.add(question.getOption().get(i).getAnswer());
            titles.add(question.getOption().get(i).getQuestion());
        }
        adapter = new DoWorkFgtRvAdapter(list,titles,me);
        listview.setLayoutManager(new LinearLayoutManager(me));
        listview.setAdapter(adapter);
        tips.setText(question.getTitle());
        title.setText(question.getSub_title());
        tv_jx.setText(question.getAnalysis());
        tv_kd.setText(question.getKey());
        return view;
    }




    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }
    //重点实现在这里切换
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e2.getX() - e1.getX()>FLIP_DISTANCE){
            if (mViewFlipper.getDisplayedChild() == 0) {
                mViewFlipper.stopFlipping();
                Toast.makeText(GestureFilpAty.this,"第一个题",Toast.LENGTH_SHORT).show();
                return false;
            } else {
                mViewFlipper.setInAnimation(animations[2]);
                mViewFlipper.setOutAnimation(animations[3]);
                mViewFlipper.showPrevious();
                return  true;
            }
        }else if (e1.getX() - e2.getX()>FLIP_DISTANCE){
            if (mViewFlipper.getDisplayedChild() == pointTestBean.getData().getList().size() - 1) {
                Toast.makeText(GestureFilpAty.this,"最后一个题",Toast.LENGTH_SHORT).show();
                mViewFlipper.stopFlipping();
                return false;
            }else {
                mViewFlipper.setInAnimation(animations[0]);
                mViewFlipper.setOutAnimation(animations[1]);
                mViewFlipper.showNext();
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将Activity上的触发的事件交个GestureDetector处理
        return this.mGestureDetector.onTouchEvent(event);
    }

    private void initData(){
        questions = new ArrayList<>();
        HttpRequest.POST(me, AllStatus.BASE_URL + "Exercises/practice", new Parameter()
                        .add("token", token)
                        .add("subject_id", subject_id)
                        .add("type", type)
                , new ResponseListener() {
                    @Override
                    public void onResponse(String response, Exception error) {
                        if (error == null) {
                            Map<String, Object> stringObjectMap = JSONUtils.parseJsonObjectStrToMap(response);
                            if (stringObjectMap.get("code").equals("1")) {
                                if (type.equals("1")){
                                    PointTestBean pointTestBean = GsonUtil.GsonToBean(response, PointTestBean.class);
                                    questions.addAll(pointTestBean.getData().getList());
                                    //3.为ViewFilpper添加子控件。
                                    for (int i = 0;i< questions.size();i++){
                                        PointTestBean.DataBean.ListBean question = questions.get(i);
                                        mViewFlipper.addView(addQuestionView(question));
                                    }
                                }
                                return;
                            }
                            stringObjectMap.get("message");
                            return;
                        }
                        showErrorTip(error.getMessage());
                    }
                });
//        return questions;
    }

    @Override
    public void setEvents() {
        super.setEvents();
        if (adapter != null){
            adapter.setOnItemClick(new DoWorkFgtRvAdapter.MyItemClick() {
                @Override
                public void onItemClick(View view, int postion) {
                index = postion;
                adapter.notifyDataSetChanged();
                }
            });
        }
    }
}