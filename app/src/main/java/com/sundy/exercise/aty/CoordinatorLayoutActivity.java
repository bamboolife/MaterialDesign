package com.sundy.exercise.aty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.sundy.common.base.BaseActivity;
import com.sundy.exercise.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CoordinatorLayoutActivity extends BaseActivity {

    private List<Integer> listImage = new ArrayList();
@BindView(R.id.convenientBanner)
    ConvenientBanner mConvenientBanner ;

    @Override
    protected int getLayoutId() {
        return R.layout.bbl_coordinator_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        listImage.add(R.mipmap.bg01_02);
        listImage.add(R.mipmap.wom_02);
        setBaner(mConvenientBanner);
        handleList();
    }
    private void setBaner(ConvenientBanner mConvenientBanner) {

        mConvenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            }
        },listImage)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
        //设置翻页的效果，不需要翻页效果可用不设
//        .setPageTransformer(Transformer.DefaultTransform);    //集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
//        convenientBanner.setManualPageable(false);//设置不能手动影响
        mConvenientBanner.startTurning(3000);
    }


    private void handleList() {
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        BottomSheetDialogActivity.MyRecyclerAdapter adapter = new BottomSheetDialogActivity.MyRecyclerAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setData(getDatas());
        adapter.notifyDataSetChanged();
    }

    private List<String> getDatas() {
        List<String> list = new ArrayList<>();
        for (int i = 0 ;i<30 ;i++) {
            list.add("android："+i);
        }
        return list;
    }


    public static class MyRecyclerAdapter extends RecyclerView.Adapter{

        private List<String> mData ;

        public void setData(List<String> list) {
            mData = list ;
        }
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bbl_bottom_sheet_item,null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.index.setText(mData.get(position)+"");
            myViewHolder.name.setText(mData.get(position)+"");
        }

        @Override
        public int getItemCount() {
            return mData == null ? 0 :mData.size();
        }



        public static class MyViewHolder extends RecyclerView.ViewHolder{

            private TextView name ;
            private TextView index ;
            public MyViewHolder(View itemView) {
                super(itemView);
                name = (TextView) itemView.findViewById(R.id.bottom_sheet_dialog_item_name);
                index = (TextView) itemView.findViewById(R.id.bottom_sheet_dialog_item_index);
            }
        }
    }


    public class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, Integer data) {
            imageView.setImageResource(data);
        }
    }
}
