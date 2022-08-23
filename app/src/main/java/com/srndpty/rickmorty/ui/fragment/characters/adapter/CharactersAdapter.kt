package com.srndpty.rickmorty.ui.fragment.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.srndpty.rickmorty.databinding.ItemCharacterBinding
import com.srndpty.rickmorty.domain.entity.Character


class CharactersAdapter(private val result: Result) :
    PagingDataAdapter<Character, CharactersAdapter.CharactersViewHolder>(CharactersCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder =
        CharactersViewHolder(
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )


    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.onBind(position)
        holder.initBtn(position)
    }

    inner class CharactersViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            val item = getItem(position) ?: return
            with(binding){
                charImg.load(item.image)
                charName.text = item.name
                charGender.text = item.gender
                charType.text = item.type
                charSpecies.text = item.species
                charStatus.text = item.status
            }
        }

        fun initBtn(position: Int) {
            val item = getItem(position) ?: return
            binding.root.setOnClickListener { result.onClickListener(item.id) }
        }
    }

    interface Result {
        fun onClickListener(id: Int)
    }
}

class CharactersCallBack : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}
