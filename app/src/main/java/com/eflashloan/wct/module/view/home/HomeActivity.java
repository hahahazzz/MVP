package com.eflashloan.wct.module.view.home;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.eflashloan.wct.R;
import com.eflashloan.wct.base.BaseActivity;
import com.eflashloan.wct.base.BaseFragment;
import com.eflashloan.wct.module.view.home.fragment.BuyCarFragment;
import com.eflashloan.wct.module.view.home.fragment.CommunityFragment;
import com.eflashloan.wct.module.view.home.fragment.FinanceFragment;
import com.eflashloan.wct.module.view.home.fragment.PlayCarFragment;
import com.eflashloan.wct.module.view.home.fragment.ShoppingGuideFragment;
import com.eflashloan.wct.module.contract.HomeContract;
import com.eflashloan.wct.module.presenter.HomePresenter;
import com.eflashloan.wct.util.ActivityUtils;

import java.util.HashMap;

import butterknife.BindView;

public class HomeActivity extends BaseActivity<HomeContract.Presenter> implements HomeContract.View {
    @BindView(R.id.toolbar_home)
    Toolbar toolbarHome;
    @BindView(R.id.bnb_home_menu)
    BottomNavigationBar bnbHomeMenu;
    @BindView(R.id.drawer_home)
    DrawerLayout drawerLayout;
    @BindView(R.id.rl_menu)
    View rlMenu;
    @BindView(R.id.cl_content)
    View rlContent;
    @BindView(R.id.app_bar_home)
    AppBarLayout appbarHome;

    private final HashMap<Integer, BaseFragment> fragmentMap = new HashMap<>();

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_home;
    }

    @NonNull
    @Override
    protected HomeContract.Presenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void start() {
        setSupportToolbar(toolbarHome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            private static final float ANIM_RANGE = 0.8f;

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                rlMenu.setPivotY(rlMenu.getHeight() / 2);
                rlMenu.setPivotX(rlMenu.getWidth());
                rlContent.setPivotY(rlContent.getHeight() / 2);
                rlContent.setPivotX(0);
                rlContent.layout(rlMenu.getRight(), 0, rlMenu.getRight() + rlContent.getWidth(), rlContent.getHeight());
                final float range = 1f - ANIM_RANGE;
                rlContent.setScaleY(1 - slideOffset * range);
                rlContent.setScaleX(1 - slideOffset * range);
                rlMenu.setScaleY(ANIM_RANGE + range * slideOffset);
                rlMenu.setScaleX(ANIM_RANGE + range * slideOffset);
            }
        });
        bnbHomeMenu
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, R.string.tab_home_shopping_guide)
                        .setInactiveIconResource(R.mipmap.ic_launcher))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, R.string.tab_home_buy_car)
                        .setInactiveIconResource(R.mipmap.ic_launcher))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, R.string.tab_home_play_car)
                        .setInactiveIconResource(R.mipmap.ic_launcher))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, R.string.tab_home_finance)
                        .setInactiveIconResource(R.mipmap.ic_launcher))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, R.string.tab_home_community)
                        .setInactiveIconResource(R.mipmap.ic_launcher))
                .setActiveColor(R.color.colorHomeBottomNavigateTextActive)
                .setInActiveColor(R.color.colorHomeBottomNavigateTextInActive)
                .setBarBackgroundColor(R.color.colorHomeBottomNavigateBackground)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setTabSelectedListener(new BottomNavigationBar.SimpleOnTabSelectedListener() {
                    @Override
                    public void onTabSelected(int position) {
                        switchFragment(position);
                    }
                })
                .setFirstSelectedPosition(0)
                .initialise();
    }

    private void switchFragment(int position) {
        if (position < 0 || position > 4) {
            return;
        }
        if (fragmentMap.containsKey(position)) {
            switchFragment(fragmentMap.get(position));
            return;
        }
        BaseFragment fragment = null;
        switch (position) {
            case 0:
                fragment = new ShoppingGuideFragment();
                break;
            case 1:
                fragment = new BuyCarFragment();
                break;
            case 2:
                fragment = new PlayCarFragment();
                break;
            case 3:
                fragment = new FinanceFragment();
                break;
            case 4:
                fragment = new CommunityFragment();
                break;
        }
        fragmentMap.put(position, fragment);
        switchFragment(fragment);
    }

    private void switchFragment(BaseFragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_home_content, fragment)
                .commitNowAllowingStateLoss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    long[] hits = new long[2];

    @Override
    public void onBackPressed() {
        System.arraycopy(hits, 1, hits, 0, hits.length - 1);
        hits[hits.length - 1] = SystemClock.uptimeMillis();
        if (hits[hits.length - 1] - hits[0] > 2000) {
            showToast(R.string.toast_home_click_again_to_exit);
        } else {
            super.onBackPressed();
            ActivityUtils.get().exit();
        }
    }
}
