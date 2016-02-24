package it.jaschke.alexandria.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import it.jaschke.alexandria.R;
import it.jaschke.alexandria.model.BooksModel;
import it.jaschke.alexandria.ui.activity.AddBookActivity;
import it.jaschke.alexandria.utils.Constants;

/**
 * Created by AdonisArifi on 15.1.2016 - 2016 . alexandria
 */
public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder> {

    private List<BooksModel> booksModelList;
    private Context context;


    public BooksAdapter(List<BooksModel> booksModelList, Context context) {
        this.booksModelList = booksModelList;
        this.context = context;
    }

    @Override
    public BooksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //Inflate custom layout
        View myView = inflater.inflate(R.layout.book_list_item, parent, false);

        //return holder instance
        BooksViewHolder booksViewHolder = new BooksViewHolder(myView);

        return booksViewHolder;
    }

    @Override
    public void onBindViewHolder(BooksViewHolder holder, int position) {

        final BooksModel booksModel = booksModelList.get(position);
        ImageView imageView = holder.imageView_book;
        TextView textView_title = holder.txttitle_item_book;
        TextView textView_author = holder.txt_item_author;

        Glide.with(context).load(booksModel.getImageLinks()).into(imageView);
        textView_title.setText(booksModel.getTitle());
        textView_author.setText(booksModel.getAuthor());

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                BooksModel bModel = new BooksModel();
                bModel = booksModel;

                Log.d("myTest", "po");
                Intent intent = new Intent(context, AddBookActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra(Constants.LIST_RESPONS_BOOK_INTENT_KEY, bModel);
                intent.putExtra(Constants.FROM_WHERE_OPNE_BOOK, false);
                //  bundle.putParcelable(Constants.LIST_RESPONS_BOOK_INTENT_KEY, listResponseBooks);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return booksModelList == null ? 0 : booksModelList.size();
    }

    public class BooksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ItemClickListener clickListener;
        @Bind(R.id.img_item_book)
        ImageView imageView_book;
        @Bind(R.id.txttitle_item_book)
        TextView txttitle_item_book;
        @Bind(R.id.txt_item_author)
        TextView txt_item_author;

        public BooksViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getPosition(), false);
        }

        public void setClickListener(ItemClickListener itemClickListener1) {
            this.clickListener = itemClickListener1;
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }
    }

    public void setFilter(List<BooksModel> booksModels) {

        booksModelList = new ArrayList<>();
        booksModelList.addAll(booksModels);
        notifyDataSetChanged();
    }
}
