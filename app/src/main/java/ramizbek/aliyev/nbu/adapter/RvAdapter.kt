package ramizbek.aliyev.nbu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ramizbek.aliyev.nbu.databinding.ItemRvBinding
import ramizbek.aliyev.nbu.models.User

class RvAdapter(var list: ArrayList<User>, var rvClickCourses: RVClickCourses) :
    RecyclerView.Adapter<RvAdapter.VH>() {

    inner class VH(var itemRV: ItemRvBinding) :
        RecyclerView.ViewHolder(itemRV.root) {
        fun onBind(user: User, position: Int) {
            itemRV.tvId.text = user.id.toString()
            itemRV.tvKupyura.text = user.Ccy
            itemRV.tvKupyuraInfo.text = user.CcyNm_EN
            itemRV.tvRate.text = user.Rate
            itemRV.tvDate.text = user.Date

            itemRV.root.setOnClickListener {
                rvClickCourses.onClick(user, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface RVClickCourses {
        fun onClick(user: User, position: Int)
    }
}