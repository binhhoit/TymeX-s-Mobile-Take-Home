package com.tymex.take_home.ui.feature.dashboard

import android.annotation.SuppressLint
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.data.network.model.UserDTO
import com.tymex.takehome.R
import com.tymex.takehome.databinding.ItemLoadMoreBinding
import com.tymex.takehome.databinding.ItemUserInfoBinding


class ProfileUsersAdapter
    : ListAdapter<UserDTO, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    var callback: (UserDTO) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            ViewHolder(
                ItemUserInfoBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
            )
        } else {
            ViewHolderLoading(
                ItemLoadMoreBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.bind(getItem(position))
        }
    }

    inner class ViewHolder(val binding: ItemUserInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: UserDTO) = with(binding) {
            tvDisplayName.text = item.login + " "+ item.id
            tvUrlProfile.text = item.htmlUrl
            Glide
                .with(itemView.context)
                .load(item.avatarUrl)
                .placeholder(R.drawable.ic_logo)
                .centerCrop()
                .into(rivAvatar)

            tvUrlProfile.text =  Html.fromHtml(item.htmlUrl, Html.FROM_HTML_MODE_COMPACT)
            tvUrlProfile.movementMethod = LinkMovementMethod.getInstance()
            root.setOnClickListener { item.login?.let { callback.invoke(item) } }

        }
    }

    inner class ViewHolderLoading(val binding: ItemLoadMoreBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserDTO>() {
            override fun areItemsTheSame(oldItem: UserDTO, newItem: UserDTO): Boolean {
                return oldItem.login == newItem.login
            }

            override fun areContentsTheSame(oldItem: UserDTO, newItem: UserDTO): Boolean {
                return oldItem.login == newItem.login &&
                        oldItem.avatarUrl == newItem.avatarUrl &&
                        oldItem.htmlUrl == newItem.htmlUrl
            }
        }

        private const val VIEW_TYPE_ITEM = 0
        private const val VIEW_TYPE_LOADING = 1
    }
}