package com.ninetaildemonfox.zdl.tongcheng_cai.utlis.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ninetaildemonfox.zdl.tongcheng_cai.R;
import com.ninetaildemonfox.zdl.tongcheng_cai.adp.ImageViewAdp;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.GlidUtils;
import com.ninetaildemonfox.zdl.tongcheng_cai.utlis.map.JSONUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/16 0016.
 * item_recycler.xml 的适配器
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.SimpleHolder> implements RecyclerItemView.onSlidingButtonListener {

    private Context context;

    private List<Map<String, String>> list = new ArrayList<>();

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public List<Map<String, String>> dataAll() {
        return list;
    }

    public void addData(List<Map<String, String>> data) {
        if (data == null || data.size() == 0) {
            return;
        }
        list.addAll(data);
        notifyDataSetChanged();
    }

    public void setNewData(List<Map<String, String>> data) {
        if (data == null || data.size() == 0) {
            return;
        }
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }

    private onSlidingViewClickListener onSvcl;

    private RecyclerItemView recyclers;

    @Override
    public SimpleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_forum_list_my, parent, false);
        return new SimpleHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleHolder holder, final int position) {
        Map<String, String> map = list.get(position);
        GlidUtils.circular(context, map.get("head_pic"), holder.image_head);
        holder.text_name.setText(map.get("nick_name"));
        holder.text_time.setText(map.get("create_time"));
        holder.text_post_content.setText(map.get("post_content"));
        holder.layout_left.getLayoutParams().width = RecyclerUtils.getScreenWidth(context);

        holder.layout_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断是否有删除菜单打开
                if (menuIsOpen()) {
                    closeMenu();//关闭菜单
                } else {
                    //获得布局下标（点的哪一个）
                    int subscript = holder.getLayoutPosition();
                    onSvcl.onItemClick(view, subscript);
                }
            }
        });
        holder.other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //编辑
                int subscript = holder.getLayoutPosition();
                onSvcl.onEditClick(view, subscript);
                closeMenu();//关闭菜单
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //删除
                int subscript = holder.getLayoutPosition();
                onSvcl.onDeleteBtnCilck(view, subscript);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list == null || list.size() == 0) {
            return 0;
        }
        return list.size();
    }

    @Override
    public void onMenuIsOpen(View view) {
        recyclers = (RecyclerItemView) view;
    }

    @Override
    public void onDownOrMove(RecyclerItemView recycler) {
        if (menuIsOpen()) {
            if (recyclers != recycler) {
                closeMenu();
            }
        }
    }

    class SimpleHolder extends RecyclerView.ViewHolder {
        public TextView other;
        public TextView delete;
        public LinearLayout layout_left;

        public ImageView image_head;
        //点赞
        public TextView text_snap;
        //消息
        public TextView text_comment;

        public TextView text_name;

        public TextView text_time;
        public TextView text_post_content;


        public SimpleHolder(View view) {
            super(view);
            image_head = view.findViewById(R.id.image_head);
            text_post_content = view.findViewById(R.id.text_post_content);
            text_snap = view.findViewById(R.id.text_snap);
            text_comment = view.findViewById(R.id.text_comment);
            text_name = view.findViewById(R.id.text_name);
            text_time = view.findViewById(R.id.text_time);
            other = view.findViewById(R.id.other);
            delete = view.findViewById(R.id.delete);
            layout_left = view.findViewById(R.id.layout_left);
            ((RecyclerItemView) view).setSlidingButtonListener(RecyclerViewAdapter.this);
        }
    }

    //删除数据
    public void removeData(int position) {
        if (list.size() == 0 || list == null) {
            return;
        }
        list.remove(position);
        notifyItemRemoved(position);

    }

    //关闭菜单
    public void closeMenu() {
        recyclers.closeMenu();
        recyclers = null;

    }

    // 判断是否有菜单打开
    public Boolean menuIsOpen() {
        if (recyclers != null) {
            return true;
        }
        return false;
    }

    //设置在滑动侦听器上
    public void setOnSlidListener(onSlidingViewClickListener listener) {
        onSvcl = listener;
    }

    // 在滑动视图上单击侦听器
    public interface onSlidingViewClickListener {
        void onItemClick(View view, int position);

        void onEditClick(View view, int position);

        void onDeleteBtnCilck(View view, int position);
    }

}
