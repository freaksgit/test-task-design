package vasyl.v.stoliarchuk.material;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
        setSupportActionBar(toolbar);

        TextView textView = findViewById(R.id.feedback);
        SpannableString content = new SpannableString(getString(R.string.feedback));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        textView.setText(content);


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
