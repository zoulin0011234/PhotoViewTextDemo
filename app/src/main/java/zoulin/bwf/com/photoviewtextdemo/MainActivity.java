package zoulin.bwf.com.photoviewtextdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zoulin.bwf.com.photoviewtextdemo.photoView.photoAvtivity;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.gid_view_one)
    GridView gidViewOne;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.spring)
    SpringView spring;
    @BindView(R.id.smart_refresh_layout)
    RelativeLayout smartRefreshLayout;
    private MyAdpter adpter;
    private List<String> data;
    private List<String> getData;
    private List<String> databean = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        inView();

    }

    /**
     * 处理图片网址
     */
    private void init() {
        data = new ArrayList<>();
        for (int i = 0; i < pohourl.length; i++) {
            String url = pohourl[i];
            data.add(url);
        }
        getData = new ArrayList<>();
        for (int i = 0; i < phobe2.length; i++) {
            String url = phobe2[i];
            getData.add(url);
        }
    }

    public List<String> AddList(List<String> databea) {
        databean.addAll(databea);
        return databean;
    }

    /**
     * 加载适配器
     */
    private void inView() {
        adpter = new MyAdpter(this);
        adpter.addDatas(AddList(data));
        gidViewOne.setAdapter(adpter);
        gidViewOne.setOnItemClickListener(new ItemClickListener());
        //上啦加载下啦刷新
        spring.setType(SpringView.Type.FOLLOW);
        spring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //下啦刷新后的效果
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //清空了原来里面的数据
                        databean.clear();
                        adpter = new MyAdpter(App.context);
                        adpter.addDatas(AddList(getData));
                        gidViewOne.setAdapter(adpter);
                        spring.onFinishFreshAndLoad();//只是处理动作完成后你的控件复原原来动作前的位置状态
                    }
                }, 2000);
            }

            @Override
            public void onLoadmore() {
                //上啦加载
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //在后面添加新的数据
                        databean.clear();
                        adpter.addDatas(AddList(getData));
                        spring.onFinishFreshAndLoad();
                    }
                }, 2000);
            }
        });
        //添加你的上啦或者下啦的布局以及动画效果  这里只是为了展示效果所以直接用的包里面的 没有自定义
        //如果你需要自定义你的动画效果，文字只需要给一个布局 然后新建一个MyHeader基础自BaseHeader在里面重写即可
        spring.setHeader(new DefaultHeader(this));
        spring.setFooter(new DefaultFooter(this));
    }


    // 当AdapterView被单击(触摸屏或者键盘)，则返回的Item单击事件
    class ItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //处理大图浏览模式
            Intent intent = new Intent(MainActivity.this, photoAvtivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("position", String.valueOf(position));
            intent.putExtras(bundle);
            //传递你当前点击的图片位置
            Log.e("ItemClickListener", "position:" + position);
            startActivity(intent);
        }
    }

    private String[] pohourl = new String[]{
            "http://p3.so.qhmsg.com/t01f836fae7cd33f0cd.jpg",
            "http://titanimg.titan24.com/game/20120823/3cf487b657c7f396500027e83c9847e0.jpg",
            "http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1205/25/c2/11755122_1337938898582.jpg",
            "http://www.sinaimg.cn/dy/slidenews/21_img/2011_28/2236_417251_177498.jpg",
            "http://p4.so.qhmsg.com/t019a51cf14f304f234.jpg",
            "http://p1.so.qhmsg.com/t0125faec48b166841b.jpg",
            "http://p1.so.qhmsg.com/t01c92630c76b94e571.jpg",
            "http://p2.so.qhmsg.com/t01dd5f03f993f46ea7.jpg",
            "http://p1.so.qhmsg.com/t01ba1bdf373f56f511.jpg",
            "http://s4.sinaimg.cn/middle/6b1cb522g907497d5bc63&690",
            "http://sc.jb51.net/uploads/allimg/131215/8-13121516331Q00.jpg",
            "http://ww4.sinaimg.cn/mw600/94715684jw1dyfg525pypj.jpg",
            "http://files.jb51.net/file_images/photoshop/201212/2012120812051514.jpg",
            "http://img.hb.aicdn.com/52957de309ce8129f31fb7bbecd9191851a2167e9b33-ZAP3Mj_fw658",
            "http://img.hb.aicdn.com/7e04c6790bfc1952bd0fb7e443c520f40655a59a2cf83-TIJRSk_fw554",
            "http://img.douxie.cn/upload/news/1206/1340940417_dk92qo08gh.jpg"
    };

    private String[] phobe2 = new String[]{
            "http://www.cq.xinhuanet.com/photonews/2008-04/25/xin_13304052510154531417855.jpg",
            "http://news.cctv.com/20080105/images/1199521085035_1199521085035_r.jpg",
            "http://lady.southcn.com/6/images/attachement/jpg/site4/20150601/90fba609e42716d661ce5b.jpg",
            "http://pic41.nipic.com/20140517/18717717_004957343122_2.jpg",
            "http://www.cq.xinhuanet.com/photonews/2008-04/25/xin_14304052510151092398756.jpg",
            "http://www.cnnb.com.cn/pic/0/03/92/46/3924627_229422.jpg",
            "http://m3.biz.itc.cn/pic/new/t/50/82/Img4158250_t.jpg",
            "http://www.sznews.com/photo/images/attachement/jpg/site3/20150626/4487fcdc05e516f6de1302.jpg",
            "http://www.ce.cn/xwzx/shgj/gdxw/200611/13/W020061113410040126654.jpg",
            "http://pic.nen.com.cn/600/14/97/75/14977550_737911.jpg",
            "http://photocdn.sohu.com/20120315/Img337849607.jpg",
            "http://ent.china.com/zh_cn/star/news/11052670/20070802/images/14256217_2007080211021032182200.jpg",
            "http://news.cctv.com/20080105/images/1199521972180_1199521972180_r.jpg"
    };
}
