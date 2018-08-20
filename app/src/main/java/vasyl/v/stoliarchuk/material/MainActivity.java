package vasyl.v.stoliarchuk.material;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.labo.kaji.relativepopupwindow.RelativePopupWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vasyl.v.stoliarchuk.material.pager.ImagePagerAdapter;
import vasyl.v.stoliarchuk.material.popup.CardPopup;

public class MainActivity extends AppCompatActivity {

    private LinearLayout llPagerDots;
    private ImageView[] ivArrayDotsPager;
    private List<Integer> imageList;
    private CardPopup popup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupUnderlinedText();
        setupRecyclerView();
        setUpViewPager();

        popup = new CardPopup(this);
        popup.setText(R.string.card_second_description);

        findViewById(R.id.image_hart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.showOnAnchor(v, RelativePopupWindow.VerticalPosition.BELOW, RelativePopupWindow.HorizontalPosition.ALIGN_LEFT, true);
            }
        });

        findViewById(R.id.chat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.showOnAnchor(v, RelativePopupWindow.VerticalPosition.ABOVE, RelativePopupWindow.HorizontalPosition.ALIGN_LEFT, 0, -100,true);
            }
        });
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
        setSupportActionBar(toolbar);
    }

    private void setupUnderlinedText() {
        TextView textView = findViewById(R.id.feedback);
        SpannableString content = new SpannableString(getString(R.string.feedback));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        textView.setText(content);
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });

        recyclerView.setAdapter(new RecyclerAdapter(createMockData()));
    }

    private List<Item> createMockData() {
        final String title = getString(R.string.card_second_title);
        final String description = getString(R.string.card_second_description);
        final int[] iconsResIds = {R.drawable.ic_coin_handing, R.drawable.ic_sleeping_man, R.drawable.ic_key_card, R.drawable.ic_meal_rang};
        List<Item> items = new ArrayList<>(iconsResIds.length);

        for (int res : iconsResIds) {
            items.add(new Item(title, description, res));
        }
        return items;
    }

    public void setUpViewPager() {
        ViewPager viewPager = findViewById(R.id.view_pager);
        llPagerDots = findViewById(R.id.pager_dots);

        imageList = getImageList();
        ImagePagerAdapter adapter = new ImagePagerAdapter(imageList);
        viewPager.setAdapter(adapter);
        setupPagerIndicatorDots();
        ivArrayDotsPager[0].setImageResource(R.drawable.selected_dot);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < ivArrayDotsPager.length; i++) {
                    ivArrayDotsPager[i].setImageResource(R.drawable.default_dot);
                }
                ivArrayDotsPager[position].setImageResource(R.drawable.selected_dot);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private List<Integer> getImageList() {
        return new ArrayList<>(Arrays.asList(R.drawable.pager_1, R.drawable.pager_2, R.drawable.pager_3));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    private void setupPagerIndicatorDots() {
        ivArrayDotsPager = new ImageView[imageList.size()];
        for (int i = 0; i < ivArrayDotsPager.length; i++) {
            ivArrayDotsPager[i] = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(25, 10, 25, 10);
            ivArrayDotsPager[i].setLayoutParams(params);
            ivArrayDotsPager[i].setImageResource(R.drawable.default_dot);
            //ivArrayDotsPager[i].setAlpha(0.4f);
            ivArrayDotsPager[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.setAlpha(1);
                }
            });
            llPagerDots.addView(ivArrayDotsPager[i]);
            llPagerDots.bringToFront();
        }
    }


}
