package com.lingkai.mymusic.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.lingkai.mymusic.fragment.GuideFragment;


/**G
 * Created by smile on 03/03/2018.
 */

public class GuideAdapter extends BaseFragmentPagerAdapter<Integer> {
    public GuideAdapter(Context context, FragmentManager fm) {
        super(context, fm);
    }

    @Override
    public Fragment getItem(int position) {
        return GuideFragment.newInstance(getData(position));
    }
}
