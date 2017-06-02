package com.chad.baserecyclerviewadapterhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.chad.baserecyclerviewadapterhelper.adapter.HomeAdapter;
import com.chad.baserecyclerviewadapterhelper.entity.HomeItem;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class HomeActivity extends AppCompatActivity {
    private static final Class<?>[] ACTIVITY = {
            AnimationUseActivity.class, MultipleItemUseActivity.class,
            HeaderAndFooterUseActivity.class, PullToRefreshUseActivity.class,
            SectionUseActivity.class, EmptyViewUseActivity.class,
            ItemDragAndSwipeUseActivity.class, ItemClickActivity.class,
            ExpandableUseActivity.class, DataBindingUseActivity.class,
            UpFetchUseActivity.class};
    private static final String[] TITLE = {
            "Animation", "MultipleItem", "Header/Footer",
            "PullToRefresh", "Section", "EmptyView",
            "DragAndSwipe", "ItemClick", "ExpandableItem",
            "DataBinding", "UpFetchData"};
    private static final int[] IMG = {
            R.mipmap.gv_animation, R.mipmap.gv_multipleltem, R.mipmap.gv_header_and_footer,
            R.mipmap.gv_pulltorefresh, R.mipmap.gv_section, R.mipmap.gv_empty,
            R.mipmap.gv_drag_and_swipe, R.mipmap.gv_item_click, R.mipmap.gv_expandable,
            R.mipmap.gv_databinding,R.drawable.gv_up_fetch};

    private ArrayList<HomeItem> mDataList;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature( Window.FEATURE_NO_TITLE );  //不显示程序的标题栏
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN ); //不显示系统的标题栏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,3));
        initData();
    }

    private void initData() {
        mDataList = new ArrayList<>();
        for (int i = 0; i < TITLE.length; i++) {
            HomeItem item = new HomeItem();
            item.setTitle(TITLE[i]);
            item.setActivity(ACTIVITY[i]);
            item.setImageResource(IMG[i]);
            mDataList.add(item);
        }
        initAdapter();
    }

    private void initAdapter() {
        BaseQuickAdapter homeAdapter = new HomeAdapter(R.layout.home_item_view, mDataList);
        homeAdapter.openLoadAnimation();
        View top = getLayoutInflater().inflate(R.layout.top_view, (ViewGroup) mRecyclerView.getParent(), false);
        homeAdapter.addHeaderView(top);
        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(HomeActivity.this, ACTIVITY[position]);
                startActivity(intent);
            }
        });

        mRecyclerView.setAdapter(homeAdapter);
    }



}


