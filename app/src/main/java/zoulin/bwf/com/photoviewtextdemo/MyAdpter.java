package zoulin.bwf.com.photoviewtextdemo;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Zoulin on 2017/1/13.
 */
public class MyAdpter extends BaseAdapter {
    private int[] sizelength;
    private List<String> datas;
    private LayoutInflater inflater;

    public MyAdpter(Context context) {
        this.datas = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    public void addDatas(List<String> databean) {
        datas.addAll(databean);
        notifyDataSetChanged();
        size(datas);
        arrylist(datas);
    }

    /**
     * 传递当前显示图片的个数
     *
     * @param datas
     */
    public void size(List<String> datas) {
        App.context.setSizeLength(datas.size());
    }

    /**
     * 传递当前显示图片的集合
     *
     * @return
     */
    public void arrylist(List<String> datasn) {
        App.context.setUrllist(datasn);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_view_adpter, null);
        }
        holder = new ViewHolder(convertView);
        holder.simpleImageview.setImageURI(Uri.parse(datas.get(position)));
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.simple_imageview)
        SimpleDraweeView simpleImageview;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
