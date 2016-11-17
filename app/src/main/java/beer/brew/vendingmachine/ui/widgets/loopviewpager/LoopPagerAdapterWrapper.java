package beer.brew.vendingmachine.ui.widgets.loopviewpager;/*
 * Copyright (C) 2013 Leszek Mzyk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.os.Parcelable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;


class LoopPagerAdapterWrapper extends PagerAdapter {

    private PagerAdapter mAdapter;

    private View.OnClickListener listener;

    LoopPagerAdapterWrapper(PagerAdapter adapter) {
        this.mAdapter = adapter;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    int toRealPosition(int position) {
        int realCount = getRealCount();
        if (realCount == 0)
            return 0;
        int realPosition = (position - 1) % realCount;
        if (realPosition < 0)
            realPosition += realCount;

        return realPosition;
    }


    int toInnerPosition(int realPosition) {
        return realPosition + 1;
    }

    @Override
    public int getCount() {
        //fragment data has already mocked two so no need to +2
        if (getRealCount() == 1
                || mAdapter instanceof FragmentPagerAdapter
                || mAdapter instanceof FragmentStatePagerAdapter) {
            return mAdapter.getCount();
        }
        return mAdapter.getCount() + 2;
    }

    int getRealCount() {
        //FragmentPagerAdapter's real count = count - 2 mocks
        if (mAdapter instanceof FragmentPagerAdapter
                || mAdapter instanceof FragmentStatePagerAdapter) {
            return mAdapter.getCount() == 1 ? 1 : mAdapter.getCount() - 2;
        }
        return mAdapter.getCount();
    }

    void setContentClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int realPosition = (mAdapter instanceof FragmentPagerAdapter
                || mAdapter instanceof FragmentStatePagerAdapter
                || getRealCount() == 1)
                ? position
                : toRealPosition(position);
        Object item = mAdapter.instantiateItem(container, realPosition);

        if (item instanceof View) {
            ((View) item).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onClick(v);
                }
            });
        }
        return item;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        int realPosition = (mAdapter instanceof FragmentPagerAdapter
                || mAdapter instanceof FragmentStatePagerAdapter
                || getRealCount() == 1)
                ? position
                : toRealPosition(position);

        mAdapter.destroyItem(container, realPosition, object);
    }

    /*
     * Delegate rest of methods directly to the inner adapter.
     */

    @Override
    public int getItemPosition(Object object) {
        return mAdapter.getItemPosition(object);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        mAdapter.finishUpdate(container);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return mAdapter.isViewFromObject(view, object);
    }

    @Override
    public void restoreState(Parcelable bundle, ClassLoader classLoader) {
        mAdapter.restoreState(bundle, classLoader);
    }

    @Override
    public Parcelable saveState() {
        return mAdapter.saveState();
    }

    @Override
    public void startUpdate(ViewGroup container) {
        mAdapter.startUpdate(container);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mAdapter.setPrimaryItem(container, position, object);
    }

    /*
     * End delegation
     */
}