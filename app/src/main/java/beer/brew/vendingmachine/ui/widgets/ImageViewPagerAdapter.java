package beer.brew.vendingmachine.ui.widgets;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Locale;

import beer.brew.vendingmachine.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageViewPagerAdapter extends PagerAdapter {

    private Context context;

    private List<String> displayImages;
    private Deque<View> cachedViews;

    public ImageViewPagerAdapter(Context context) {
        this.context = context;
        cachedViews = new ArrayDeque<>();
        displayImages = new ArrayList<>();
    }

    public void loadImageUri(List<String> displayImages) {
        this.displayImages = displayImages;

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
            view = View.inflate(context, R.layout.image_container_layout, null);
            ViewHolder viewHolder = new ViewHolder(view, context);
            view.setTag(viewHolder);
        } else {
            view = cachedViews.pop();
        }

        ((ViewHolder) view.getTag())
                .populate(displayImages.get(position), position + 1, displayImages.size());

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

    public class ViewHolder {

        private static final String BADGE_DENOMINATOR_FMT = "/%s";
        private final Context context;

        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.image_badge)
        ViewGroup imageBadgeContainer;
        @BindView(R.id.image_badge_numerator)
        TextView imageBadgeNumerator;
        @BindView(R.id.image_badge_denominator)
        TextView imageBadgeDenominator;

        public ViewHolder(View view, Context context) {
            this.context = context;
            ButterKnife.bind(this, view);
        }

        void populate(String uri, int index, int total) {
            if (TextUtils.isEmpty(uri)) {
                imageBadgeContainer.setVisibility(View.GONE);
            } else {
                imageBadgeContainer.setVisibility(View.VISIBLE);
                imageBadgeNumerator.setText(String.valueOf(index));
                imageBadgeDenominator.setText(
                        String.format(Locale.US, BADGE_DENOMINATOR_FMT,
                                String.valueOf(total)));
                Picasso.with(context)
                        .load(uri)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(image);
            }
        }
    }

}
