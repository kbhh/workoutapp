package com.uffey.workoutapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.uffey.workoutapp.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter(private val list:ArrayList<ExerciseModel> ):RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder> () {

    inner class ViewHolder(binding: ItemExerciseStatusBinding):RecyclerView.ViewHolder(binding.root) {
         var exStatusV = binding.exStatusView;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context), parent,  false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var model: ExerciseModel =  list[position];
        holder.exStatusV.text = model.getId().toString();
        when{
           model.getIsCompleted() -> {
               holder.exStatusV.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.item_circular_white_background)
               holder.exStatusV.setTextColor(Color.parseColor("#ffffff"))
           }
           model.getIsSelected() -> {
               holder.exStatusV.background = ContextCompat.getDrawable(holder.itemView.context, R.drawable.item_circular_white_background)
               holder.exStatusV.setTextColor(Color.parseColor("#ffffff"))
           }
           else -> {
               holder.exStatusV.background =
                   ContextCompat.getDrawable(holder.itemView.context, R.drawable.item_circular_gray_background)
               holder.exStatusV.setTextColor(Color.parseColor("#212121"))
           }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}