package beer.brew.vendingmachine.ui.widgets;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import beer.brew.vendingmachine.util.ImageLoader;

public class ImageViewPagerAdapter extends PagerAdapter {

    private Context context;

    private List<String> displayImages;
    private ImageLoader imageLoader;
    private Deque<View> cachedViews;

    public ImageViewPagerAdapter(Context context) {
        this.context = context;
        cachedViews = new ArrayDeque<>();
        displayImages = new ArrayList<>();
    }

    public void loadImageUri(List<String> displayImages, ImageLoader imageLoader) {
        this.displayImages = displayImages;
        this.imageLoader = imageLoader;

        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return displayImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (displayImages.size() == 0) {
            return null;
        }

        View view;
        if (cachedViews.isEmpty()) {
            view = new ImageView(context);
        } else {
            view = cachedViews.pop();
        }

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        if (view != null) {
            container.removeView(view);
            cachedViews.push(view);
        }
    }

}
