package com.example.assignment.fragments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.databinding.ItemEmpolyeesBinding
import com.example.assignment.db.EntityModel

class MyAdapter(): ListAdapter<EntityModel, MyAdapter.ViewHolder>(object : DiffUtil.ItemCallback<EntityModel>(){
    override fun areItemsTheSame(oldItem: EntityModel, newItem: EntityModel): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: EntityModel, newItem: EntityModel): Boolean {
        return oldItem==newItem
    }

}) {
    inner class ViewHolder( val binding: ItemEmpolyeesBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemEmpolyeesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.username.text  = item.username
        holder.binding.age.text  = "${item.age} years"
        holder.binding.address.text  = item.address+" "+item.city
    }
}