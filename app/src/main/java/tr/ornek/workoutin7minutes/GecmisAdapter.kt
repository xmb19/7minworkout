package tr.ornek.workoutin7minutes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tr.ornek.workoutin7minutes.databinding.ItemGecmisRowBinding


class GecmisAdapter(private val items:ArrayList<String>):RecyclerView.Adapter<GecmisAdapter.ViewHolder>() {




    class ViewHolder(binding: ItemGecmisRowBinding):RecyclerView.ViewHolder(binding.root){
        val gecmisItemMain= binding.gecmismain
        val tvItem= binding.tvItem
        val tvPozisyon = binding.tvPozisyon
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemGecmisRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date : String = items.get(position)
        holder.tvPozisyon.text = (position+1).toString()
        holder.tvItem.text = date
    }

    override fun getItemCount(): Int {
        return items.size
    }

}