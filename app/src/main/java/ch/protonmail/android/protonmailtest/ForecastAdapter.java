package ch.protonmail.android.protonmailtest;

import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ProtonMail on 2/25/19.
 */
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.DayViewHolder> {

    String[] data;

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        holder.titleView.setText(data[0]);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class DayViewHolder extends RecyclerView.ViewHolder {

        private TextView titleView;
        public DayViewHolder(@NonNull TextView v) {
            super(v);
            titleView = v;
        }
    }
}
