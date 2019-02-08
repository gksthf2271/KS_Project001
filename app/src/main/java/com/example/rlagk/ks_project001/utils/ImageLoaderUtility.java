//package com.example.rlagk.ks_project001.utils;
//
//import android.content.Context;
//
//import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
//import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
//import com.nostra13.universalimageloader.core.DisplayImageOptions;
//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
//import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
//import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
//
//public class ImageLoaderUtility {
//    private Context mContext;
//    private static ImageLoaderUtility instance;
//    private ImageLoaderUtility(){}
//
//    public static ImageLoaderUtility getInstance(){
//        if (instance == null){
//            instance = new ImageLoaderUtility();
//        }
//        return instance;
//    }
//
//    public void initImageLoader(){
//        if (!ImageLoader.getInstance().isInited()){
//            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this.getmContext())
//                    .memoryCacheExtraOptions(480, 800)
//                    .threadPoolSize(3)
//                    .threadPriority(Thread.NORM_PRIORITY - 1)
//                    .tasksProcessingOrder(QueueProcessingType.FIFO)
//                    .denyCacheImageMultipleSizesInMemory()
//                    .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
//                    .memoryCacheSize(2 * 1024 * 1024).memoryCacheSizePercentage(13) // default
//                    .discCacheSize(50 * 1024 * 1024)
//                    .discCacheFileCount(100)
//                    .discCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
//                    .imageDownloader(new BaseImageDownloader(this.getmContext())) // default
//                    .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
//                    .writeDebugLogs().build();
//            ImageLoader imageLoader = ImageLoader.getInstance();
//            imageLoader.init(config);
//
//        }
//    }
//
//    public Context getmContext() {
//        return mContext;
//    }
//
//    public void setmContext(Context mContext) {
//        this.mContext = mContext;
//    }
//}
