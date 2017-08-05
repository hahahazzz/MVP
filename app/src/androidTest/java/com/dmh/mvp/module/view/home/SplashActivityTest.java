package com.dmh.mvp.module.view.home;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.dmh.mvp.R;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * @Author : QiuGang
 * @Email : 1607868475@qq.com
 * @Date : 2017/7/13 14:59
 */
@RunWith(AndroidJUnit4.class)
public class SplashActivityTest {
    @Rule
    public ActivityTestRule<SplashActivity> rule = new ActivityTestRule<SplashActivity>(SplashActivity.class);

    @After
    public void after() {
        SystemClock.sleep(3000);
    }

    @Test
    public void testTypeTest() {
        onView(withId(R.id.text_splash_hello)).perform(replaceText("aaaaaaaa"));
    }
}