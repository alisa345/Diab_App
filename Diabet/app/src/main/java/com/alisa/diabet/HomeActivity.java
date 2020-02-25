package com.alisa.diabet;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.alisa.diabet.Fragments.Fragment1;
import com.alisa.diabet.Fragments.Fragment2;
import com.alisa.diabet.Fragments.Fragment3;
import com.alisa.diabet.Fragments.Fragment4;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        LinearLayout ll = (LinearLayout) findViewById(R.id.layout);
        ll.setBackgroundColor(getResources().getColor(R.color.colorBack));

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorBlack));
        toolbar.setTitleTextAppearance(this, R.style.TextAppearance);
        toolbar.setNavigationIcon(R.drawable.icon_menu_or);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorIdent));

        AccountHeader.Result nameApp = imageNavigationDrawer();
        initializeNavigationDrawer();
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment1(), "Дневник");
        adapter.addFragment(new Fragment2(), "Графики");
        adapter.addFragment(new Fragment3(), "Напоминания");
        adapter.addFragment(new Fragment4(), "Аккаунт");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public AccountHeader.Result imageNavigationDrawer() {
        return new AccountHeader()
                .withActivity(this)
                .withHeaderBackground(R.mipmap.night)
                .build();
    }

    public void initializeNavigationDrawer() {
        final PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withSelectedTextColor(R.color.colorBlack).withName(R.string.item_dnev);
        final PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withSelectedTextColor(R.color.colorBlack).withName(R.string.item_graf);
        final PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(1).withSelectedTextColor(R.color.colorBlack).withName(R.string.item_notif);
        final PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(2).withSelectedTextColor(R.color.colorBlack).withName(R.string.item_akk);
        final PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(2).withSelectedTextColor(R.color.colorBlack).withName(R.string.item_help);
        final PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(2).withSelectedTextColor(R.color.colorBlack).withName("Экспорт");

        Drawer.Result drawerResult = new Drawer()
                .withActivity(this)
                .withActionBarDrawerToggle(false)
                .withToolbar(toolbar)
                .withDisplayBelowToolbar(true)
                .addDrawerItems(
                        item1,
                        item2,
                        item3,
                        item4,
                        new DividerDrawerItem(),
                        item5,
                        item6
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {

                        switch (position) {
                            case 0:
                                viewPager.setCurrentItem(0);
                                break;
                            case 1:
                                viewPager.setCurrentItem(1);
                                break;
                            case 2:
                                viewPager.setCurrentItem(2);
                                break;
                            case 3:
                                viewPager.setCurrentItem(3);
                                break;
                        }
                    }
                })
                .build();
    }
}
