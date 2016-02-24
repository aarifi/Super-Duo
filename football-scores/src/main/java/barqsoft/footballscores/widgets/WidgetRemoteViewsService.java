/*
 * Copyright 2015.  Emin Yahyayev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package barqsoft.footballscores.widgets;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import barqsoft.footballscores.R;
import barqsoft.footballscores.data.FixtureData;
import barqsoft.footballscores.data.TeamsData;
import barqsoft.footballscores.model.FixturesModelForLocal;
import barqsoft.footballscores.utils.SupportMethod;

public final class WidgetRemoteViewsService extends RemoteViewsService {
    public static final String TAG = WidgetRemoteViewsService.class.getSimpleName();


    private List<FixturesModelForLocal> fixturesModelForLocals = new ArrayList<>();

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    public WidgetRemoteViewsService() {
        super();
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {

            @Override
            public void onCreate() {
                Log.d(TAG, "onCreate");
            }

            @Override
            public void onDataSetChanged() {
                Log.d(TAG, "onDataSetChanged");


                populateListItem();

                final long identityToken = Binder.clearCallingIdentity();
                Binder.restoreCallingIdentity(identityToken);

            }

            @Override
            public void onDestroy() {

                if (fixturesModelForLocals != null) {
                    fixturesModelForLocals.clear();
                }
            }

            @Override
            public int getCount() {
                return fixturesModelForLocals == null ? 0 :
                        fixturesModelForLocals.size();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                if (position == AdapterView.INVALID_POSITION) {
                    return null;
                }
                RemoteViews views = new RemoteViews(getPackageName(),
                        R.layout.widget_match_item);

                String homeTeamName = fixturesModelForLocals.get(position).getHomeTeamName();
                String awayTeamName = fixturesModelForLocals.get(position).getAwayTeamName();
                Bitmap bitmapHomeTeam = null;
                Bitmap bitmapAwayTeam = null;
                try {
                    bitmapHomeTeam = Glide.with(WidgetRemoteViewsService.this)
                            .load(TeamsData.getTeamsDataInstance(getApplicationContext()).getCrestUrlById(Integer.parseInt(fixturesModelForLocals.get(position).getHomeTeamId())))
                            .asBitmap()
                            .error(R.drawable.ic_soccer_black_24dp)
                            .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                try {
                    bitmapAwayTeam = Glide.with(WidgetRemoteViewsService.this)
                            .load(TeamsData.getTeamsDataInstance(getApplicationContext()).getCrestUrlById(Integer.parseInt(fixturesModelForLocals.get(position).getAwayTeamId())))
                            .asBitmap()
                            .error(R.drawable.ic_soccer_black_24dp)
                            .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                views.setTextViewText(R.id.txt_widget_item_homeTeam_name, homeTeamName);
                views.setTextViewText(R.id.txt_widget_item_awayTeam_name, awayTeamName);
                views.setImageViewBitmap(R.id.imageview_widget_item_photo_homeTeam, bitmapHomeTeam);
                views.setImageViewBitmap(R.id.imageview_widget_item_photo_awayTeam, bitmapAwayTeam);

                if (fixturesModelForLocals.get(position).getGoalsHomeTeam() == null && fixturesModelForLocals.get(position).getGoalsAwayTeam() == null) {
                    views.setTextViewText(R.id.txt_widget_result, "- : -");
                } else {
                    views.setTextViewText(R.id.txt_widget_result, fixturesModelForLocals.get(position).getGoalsHomeTeam() + ":" + fixturesModelForLocals.get(position).getGoalsAwayTeam());

                }


                return views;
            }

            @Override
            public RemoteViews getLoadingView() {
                return new RemoteViews(getPackageName(), R.layout.widget_match_item);
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {

                return position;
            }

            @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
            private void setRemoteContentDescription(RemoteViews views, String description) {
                views.setContentDescription(R.id.imageview_widget_item_photo_homeTeam, description);
            }


            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
    }


    private void populateListItem() {
        if (fixturesModelForLocals != null) {
            fixturesModelForLocals.clear();
        }

        List<FixturesModelForLocal> fList = FixtureData.getTeamsDataInstance(getApplicationContext()).getAll();
        for (FixturesModelForLocal list : fList) {

            DateTime dateTime = SupportMethod.getSupportMethodInstance(getApplicationContext()).getDateFromString(list.getDate());
            DateTime dateTime2 = DateTime.now();
            if (dateTime.getDayOfMonth() == dateTime2.getDayOfMonth()) {
                fixturesModelForLocals.add(list);
            }

        }
    }

}