package zoulin.bwf.com.photoviewtextdemo.photoView;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import zoulin.bwf.com.photoviewtextdemo.R;

/**
 * Created by Zoulin on 2017/1/18.
 */

public class PhotoAdapter extends PagerAdapter {
    private List<String> datas;
    private List<View> views;
    private Context context;

    public PhotoAdapter(List<String> datas, Context context) {
        this.datas = datas;
        this.context = context;
        views = new ArrayList<>();
        if (datas != null) {
            for (Object o : datas) {
                View view = View.inflate(context, R.layout.gallery_xml_adpter, null);
                views.add(view);
            }
        }
    }

    @Override
    public int getCount() {
        return views == null ? 0 : views.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view =views.get(position);
        PhotoView photoViewmain= (PhotoView) view.findViewById(R.id.photoView_main);
        ImageLoader.getInstance().displayImage(datas.get(position),photoViewmain);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
