package zoulin.bwf.com.photoviewtextdemo.photoView;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zoulin.bwf.com.photoviewtextdemo.App;
import zoulin.bwf.com.photoviewtextdemo.R;

public class photoAvtivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {


    @BindView(R.id.crunnt_now_number)
    TextView crunntNowNumber;
    @BindView(R.id.crunnt_zong_number)
    TextView crunntZongNumber;
    @BindView(R.id.photos)
    ViewPager photos;
    @BindView(R.id.activity_photo_avtivity)
    RelativeLayout activityPhotoAvtivity;
    private List<String> urls;
    private PagerAdapter adpter;
    private int sizeLength;
    private int currnnum;//图片点击位置

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_avtivity);
        ButterKnife.bind(this);
        //获取图片集合
        urls = App.context.getUrllist();
        //获取集合的长度
        sizeLength = App.context.getSizeLength();
        //显示当前图片总数
        crunntZongNumber.setText(sizeLength + "");
        //数据传入到适配器
        adpter = new PhotoAdapter(urls, this);
        photos.setAdapter(adpter);
        //设置当前图片显示的位置
        String rs = getIntent().getExtras().getString("position");
        currnnum = Integer.parseInt(rs);
        Log.e("photoAvtivity", "currnnum:" + currnnum);
        crunntNowNumber.setText((currnnum+1)+"");
        //绑定指示器与viewpager点击的当前位置
        photos.setCurrentItem(currnnum);
        photos.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        crunntNowNumber.setText((position % urls.size() + 1) + "");
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
