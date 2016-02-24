package barqsoft.footballscores.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import barqsoft.footballscores.R;
import barqsoft.footballscores.data.TeamsData;
import barqsoft.footballscores.model.FixturesModelForLocal;
import barqsoft.footballscores.ui.activity.DetailsMatchActivity;
import barqsoft.footballscores.utils.Constants;
import barqsoft.footballscores.utils.SupportMethod;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by AdonisArifi on 17.2.2016 - 2016 . Football_Scores-master
 */
public class FixturesMatchAdapter extends RecyclerView.Adapter<FixturesMatchAdapter.FixturesMatchViewHolder> {

    private List<FixturesModelForLocal> listFixtures;
    private Context context;

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public FixturesMatchAdapter(List<FixturesModelForLocal> fixtures, Context context) {
        this.listFixtures = fixtures;
        this.context = context;
    }


    @Override
    public FixturesMatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.match_item, parent, false);

        FixturesMatchViewHolder fixturesMatchViewHolder = new FixturesMatchViewHolder(view);

        return fixturesMatchViewHolder;
    }

    @Override
    public void onBindViewHolder(FixturesMatchViewHolder holder, int position) {

        try {

           final FixturesModelForLocal fixtures = listFixtures.get(position);

            TextView txt_item_homeTeam_name = holder.txt_item_homeTeam_name;
            TextView txt_item_awayTeam_name = holder.txt_item_awayTeam_name;
            ImageView imageview_item_photo_homeTeam = holder.imageview_item_photo_homeTeam;
            ImageView imageview_item_photo_awayTeam = holder.imageview_item_photo_awayTeam;
            TextView txt_result = holder.txt_resutl;
            txt_item_homeTeam_name.setText(fixtures.getHomeTeamName());
            txt_item_awayTeam_name.setText(fixtures.getAwayTeamName());


            Glide.with(context).load(TeamsData.getTeamsDataInstance(context)
                    .getCrestUrlById(Integer.parseInt(fixtures.getHomeTeamId())))
                    .asBitmap()
                    .error(R.drawable.no_icon)
                    .skipMemoryCache(false)
                    .into(imageview_item_photo_homeTeam);

            Glide.with(context).load(TeamsData.getTeamsDataInstance(context)
                    .getCrestUrlById(Integer.parseInt(fixtures.getAwayTeamId())))
                    .asBitmap()
                    .error(R.drawable.no_icon)
                    .into(imageview_item_photo_awayTeam);
            if (fixtures.getGoalsHomeTeam() == null && fixtures.getGoalsAwayTeam() == null) {
                txt_result.setText(SupportMethod.getSupportMethodInstance(context).getTimeFromDate(fixtures.getDate()));
            } else {
                txt_result.setText(fixtures.getGoalsHomeTeam() + ":" + fixtures.getGoalsAwayTeam());

            }
            holder.setClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isLongClick) {

                    Intent intent = new Intent(context, DetailsMatchActivity.class);
                    Bundle bundle = new Bundle();
                    intent.putExtra(Constants.MATCH_DETAILS_INTENT_KEY, fixtures);
                    context.startActivity(intent);
                }
            });


        } catch (Exception e) {
            e.getMessage();
        }


    }

    @Override
    public int getItemCount() {
        return listFixtures == null ? 0 : listFixtures.size();
    }

    public static class FixturesMatchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemClickListener clickListener;
        @Bind(R.id.txt_item_homeTeam_name)
        TextView txt_item_homeTeam_name;
        @Bind(R.id.txt_item_awayTeam_name)
        TextView txt_item_awayTeam_name;
        @Bind(R.id.imageview_item_photo_homeTeam)
        ImageView imageview_item_photo_homeTeam;
        @Bind(R.id.imageview_item_photo_awayTeam)
        ImageView imageview_item_photo_awayTeam;
        @Bind(R.id.txt_result)
        TextView txt_resutl;

        public FixturesMatchViewHolder(View itemView) {
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
    }
}
