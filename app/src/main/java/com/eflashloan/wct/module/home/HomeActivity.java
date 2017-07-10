package com.eflashloan.wct.module.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.eflashloan.wct.R;
import com.eflashloan.wct.base.BaseActivity;
import com.eflashloan.wct.base.BaseFragment;
import com.eflashloan.wct.mvp.contract.HomeContract;
import com.eflashloan.wct.mvp.presenter.HomePresenter;
import com.eflashloan.wct.util.LogUtils;

import java.util.HashMap;

import butterknife.BindView;

public class HomeActivity extends BaseActivity<HomeContract.Presenter> implements HomeContract.View {
    @BindView(R.id.toolbar_home)
    Toolbar toolbarHome;
    @BindView(R.id.bnb_home_menu)
    BottomNavigationBar bnbHomeMenu;

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
        bnbHomeMenu
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Home").setInactiveIconResource(R.mipmap
                        .ic_launcher))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Order").setInactiveIconResource(R.mipmap
                        .ic_launcher))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "User").setInactiveIconResource(R.mipmap
                        .ic_launcher))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Account").setInactiveIconResource(R.mipmap
                        .ic_launcher))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Test").setInactiveIconResource(R.mipmap
                        .ic_launcher))
                .setActiveColor("#008899")
                .setInActiveColor("#dcdcdc")
                .setBarBackgroundColor("#0099ff")
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(int position) {
                        LogUtils.d("current select tab %d", position);
                    }

                    @Override
                    public void onTabUnselected(int position) {

                    }

                    @Override
                    public void onTabReselected(int position) {

                    }
                })
                .initialise();
    }
}
