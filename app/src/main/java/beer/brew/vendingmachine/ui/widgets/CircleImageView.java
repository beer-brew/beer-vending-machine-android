package beer.brew.vendingmachine.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

import beer.brew.vendingmachine.R;
import beer.brew.vendingmachine.ui.widgets.loopviewpager.LoopViewPager;
import beer.brew.vendingmachine.util.ImageLoader;


public class CircleImageView extends LoopViewPager {

    private ImageViewPagerAdapter imageViewPagerAdapter;
    private boolean enabled = true;
    private float imageAspectRatio;

    public CircleImageView(Context context) {
        this(context, null);
        enabled = true;
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.circle_view_paper);
        imageAspectRatio = typedArray.getFraction(R.styleable.circle_view_paper_image_aspect_ratio, 1, 1, 0.75f);
        typedArray.recycle();
        if (!isInEditMode()) {
            initAdapter(context);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (imageAspectRatio == 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            int height = (int) (View.MeasureSpec.getSize(widthMeasureSpec) * imageAspectRatio);
            super.onMeasure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY));
        }
    }

    private void initAdapter(Context context) {
        imageViewPagerAdapter = new ImageViewPagerAdapter(context);
        setAdapter(imageViewPagerAdapter);
    }

    public void setImageUri(List<String> images, ImageLoader imageLoader, boolean resetPosition) {
        imageViewPagerAdapter.loadImageUri(images, imageLoader);
        getAdapter().notifyDataSetChanged();
        if (resetPosition) {
            setCurrentItem(0, false);
        }
    }

    public int getCurrentImageIndex() {
        return getCurrentItem();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return enabled && super.onTouchEvent(event);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return enabled && super.onInterceptTouchEvent(event);

    }

    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
