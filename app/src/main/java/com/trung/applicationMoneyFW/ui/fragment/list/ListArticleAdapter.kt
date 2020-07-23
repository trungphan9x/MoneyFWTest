package com.trung.applicationMoneyFW.ui.fragment.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.trung.applicationMoneyFW.data.remote.response.Article
import com.trung.applicationMoneyFW.databinding.ItemListArticleBinding
import com.trung.applicationMoneyFW.util.extension.hideKeyboard

class ListArticleAdapter : ListAdapter<Article, ListArticleAdapter.ViewHolder>(DiffCallback) {
    val items: ArrayList<Article> = arrayListOf()

    lateinit var binding: ItemListArticleBinding

    private var onItemClickListener: ((Int, Article, view: View) -> Unit)? = null

    var articleList = ArrayList<Article>()

    init {
        articleList = items
    }

    fun setIetms(items: List<Article>?) {
        items?.let {
            this.items.clear()
            this.items.addAll(items)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    fun setOnItemClickListener(onItemClickListener: ((Int, Article, View) -> Unit)) {
        this.onItemClickListener = onItemClickListener
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemListArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(articleList[position], onItemClickListener)
    }

    /**
     * The ViewHolder constructor takes the binding variable from the associated
     * ItemListArticle, which nicely gives it access to the full [article] information.
     */
    class ViewHolder(private val binding: ItemListArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            article: Article,
            onItemClickListener: ((Int, Article, View) -> Unit)?
        ) {

            binding.article = article

            binding.root.setOnClickListener {
                binding.root.hideKeyboard()
                onItemClickListener?.invoke(position, article, binding.ivPhotoUrl)
            }

            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }


    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [ChannelList]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Article>() {
        //Use Kotlin's referential equality operator (===), which returns true if the object references for oldItem and newItem are the same.
        //override fun areItemsTheSame(oldItem: PhotoDetailEntity, newItem: PhotoDetailEntity) = oldItem === newItem
        override fun areItemsTheSame(oldItem: Article, newItem: Article) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: Article, newItem: Article) =
            oldItem == newItem
    }
}