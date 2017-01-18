package zoulin.bwf.com.photoviewtextdemo;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.List;



/**
 * Created by Zoulin on 2017/1/13.
 */

public class App extends Application {


    private int sizeLength;
    private List<String> urllist;
    public static App context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        Fresco.initialize(this);
        initImageLoader(this);
    }
    /**
     * 初始化imageloader
     */
    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }
    public List<String> getUrllist() {
        return urllist;
    }

    public void setUrllist(List<String> urllist) {
        this.urllist = urllist;
    }

    public int getSizeLength() {
        return sizeLength;
    }

    public void setSizeLength(int sizeLength) {
        this.sizeLength = sizeLength;
    }

}
