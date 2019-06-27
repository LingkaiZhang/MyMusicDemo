package com.lingkai.mymusic.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.lingkai.mymusic.R;
import com.lingkai.mymusic.adapter.RecommendAdapter;
import com.lingkai.mymusic.api.Api;
import com.lingkai.mymusic.domain.Advertisement;
import com.lingkai.mymusic.domain.List;
import com.lingkai.mymusic.domain.Song;
import com.lingkai.mymusic.domain.response.ListResponse;
import com.lingkai.mymusic.reactivex.HttpListener;
import com.lingkai.mymusic.util.DataUtil;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * description ： 主页首屏主页面展示
 * author : lingkai
 * date : 2019/6/26 10:08
 */
public class RecommendFragment extends BaseCommonFragment {

    private LRecyclerView rv;
    private GridLayoutManager layoutManager;
    private RecommendAdapter adapter;
    private LRecyclerViewAdapter adapterWrapper;

    public static RecommendFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RecommendFragment fragment = new RecommendFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    protected void initViews() {
        super.initViews();
        rv = findViewById(R.id.rv);
        //rv.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(getActivity(), 3);
        rv.setLayoutManager(layoutManager);

    }

    @Override
    protected void initDatas() {
        super.initDatas();

        adapter = new RecommendAdapter(getActivity());
        adapterWrapper = new LRecyclerViewAdapter(adapter);
        adapterWrapper.setSpanSizeLookup(new LRecyclerViewAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                //                先获取ItemType
                int itemViewType = adapterWrapper.getItemViewType(position);
                if (position< adapterWrapper.getHeaderViewsCount() || position>(adapterWrapper.getHeaderViewsCount()+adapter.getItemCount())) {
                    //                    f当前位置的Item是header，占用列数spanCount一样
                    return ((GridLayoutManager) layoutManager).getSpanCount();
                }
                return adapter.setSpanSizeLookup(position);
            }
        });

        rv.setAdapter(adapterWrapper);

        rv.setPullRefreshEnabled(false);
        rv.setLoadMoreEnabled(false);

        fetchData();

    }

    private void fetchData() {
        //这里获取三种类型的数据，然后放到一个列表中
        //同时也是演示RecyclerView不同的ItemType的使用方法
        //详细的使用方法可以在我们的《详解RecyclerView》课程中学到

        Observable<ListResponse<List>> list = Api.getInstance().lists();
        final Observable<ListResponse<Song>> songs = Api.getInstance().songs();
        final Observable<ListResponse<Advertisement>> advertisements = Api.getInstance().advertisements();

        final ArrayList<Object> d = new ArrayList<>();
        d.add("推荐歌单");

        //为降低课程难度，不使用RxJava来合并请求
        list.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpListener<ListResponse<List>>(getMainActivity()) {
                    @Override
                    public void onSucceeded(final ListResponse<List> data) {
                        super.onSucceeded(data);
                        d.addAll(data.getData());

                        songs.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new HttpListener<ListResponse<Song>>(getMainActivity()) {
                                    @Override
                                    public void onSucceeded(ListResponse<Song> data) {
                                        super.onSucceeded(data);
                                        d.add("推荐单曲");
                                        d.addAll(DataUtil.fill(data.getData()));

                                        advertisements.subscribeOn(Schedulers.io())
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribe(new HttpListener<ListResponse<Advertisement>>(getMainActivity()){
                                                    @Override
                                                    public void onSucceeded(ListResponse<Advertisement> data) {
                                                        super.onSucceeded(data);
                                                        d.addAll(data.getData());

                                                        adapter.setData(d);
                                                        //rv.refreshComplete(Consts.DEFAULT_PAGE_SIZE);
                                                    }
                                                });
                                    }
                                });
                    }
                });
    }

    @Override
    protected View getLayoutView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recommend,null);
    }
}
