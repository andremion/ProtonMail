package ch.protonmail.android.protonmailtest.ui.home.forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ch.protonmail.android.protonmailtest.R
import ch.protonmail.android.protonmailtest.ui.home.forecast.model.ForecastItemModel
import com.bumptech.glide.RequestManager

class ForecastListAdapter(
    private val imageLoader: RequestManager,
    private val onItemClicked: (item: ForecastItemModel) -> Unit
) : ListAdapter<ForecastItemModel, ForecastViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_forecast, parent, false)
            .let { itemView ->
                ForecastViewHolder(imageLoader, itemView) { position ->
                    onItemClicked(getItem(position))
                }
            }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) =
        holder.bind(getItem(position))
}

class ForecastViewHolder(
    private val imageLoader: RequestManager,
    itemView: View,
    onItemClicked: (position: Int) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener { onItemClicked(bindingAdapterPosition) }
    }

    private val titleView: TextView = itemView.findViewById(R.id.title)
    private val imageView: ImageView = itemView.findViewById(R.id.image)

    fun bind(item: ForecastItemModel) {
        titleView.text = item.title
        imageLoader.load(item.imageFile)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(imageView)
    }
}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ForecastItemModel>() {

    override fun areItemsTheSame(oldItem: ForecastItemModel, newItem: ForecastItemModel): Boolean =
        oldItem.day == newItem.day

    override fun areContentsTheSame(oldItem: ForecastItemModel, newItem: ForecastItemModel): Boolean =
        oldItem == newItem
}
