package vasyl.v.stoliarchuk.material;

import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {

    private List<Item> data;

    public RecyclerAdapter(List<Item> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView description;
        private final ImageView icon;

        public ItemViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.card_second_title);
            description = itemView.findViewById(R.id.card_second_description);
            icon = itemView.findViewById(R.id.card_second_icon);
        }


        public void bind(Item item) {
            title.setText(item.getTitle());
            description.setText(item.getDescription());
            icon.setImageResource(item.getIconRes());
        }
    }
}
