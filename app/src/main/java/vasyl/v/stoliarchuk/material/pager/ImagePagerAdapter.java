package vasyl.v.stoliarchuk.material.pager;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import vasyl.v.stoliarchuk.material.R;

public class ImagePagerAdapter extends PagerAdapter {

    private List<Integer> data;

    public ImagePagerAdapter(List<Integer> data) {
        this.data = data;
    }


    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View imageLayout = LayoutInflater.from(container.getContext())
                .inflate(R.layout.pager_item, container, false);

        final ImageView imageView = imageLayout.findViewById(R.id.pager_image);

        imageView.setImageResource(data.get(position));

        container.addView(imageLayout);

        return imageLayout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
