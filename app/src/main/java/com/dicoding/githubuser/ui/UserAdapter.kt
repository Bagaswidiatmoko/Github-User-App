package com.dicoding.githubuser.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.dicoding.githubuser.data.response.ResponseUserGithub
import com.dicoding.githubuser.databinding.ItemRowUserBinding

class UserAdapter(
    private val data: MutableList<ResponseUserGithub.Item> = mutableListOf(),
    private val listener: (ResponseUserGithub.Item) -> Unit
) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    fun setData(data: MutableList<ResponseUserGithub.Item>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    class UserViewHolder(private val v: ItemRowUserBinding) : RecyclerView.ViewHolder(v.root) {
        fun bind(item: ResponseUserGithub.Item) {
            v.imgItemPhoto.load(item.avatar_url)
            v.tvItemName.text = item.login
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            ItemRowUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener(item)
        }
    }

    override fun getItemCount(): Int = data.size
}