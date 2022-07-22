package ramizbek.aliyev.nbu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ramizbek.aliyev.nbu.databinding.BottomsheetdialogBinding
import ramizbek.aliyev.nbu.databinding.ItemRvBinding
import ramizbek.aliyev.nbu.models.User

class RvAdapter2(var list: ArrayList<User>) :
    RecyclerView.Adapter<RvAdapter2.VH>() {

    inner class VH(var itemRV: BottomsheetdialogBinding) :
        RecyclerView.ViewHolder(itemRV.root) {
        fun onBind(user: User, position: Int) {
            itemRV.tvIdBottomsheet.text = user.id.toString()
            itemRV.tvCode.text = user.Code
            itemRV.tvKupyuraBottomsheet.text = user.CcyNm_RU
            itemRV.tvKupyuraInfoUZBottomsheet.text = user.CcyNm_UZ
            itemRV.tvKupyuraInfoUZCBottomsheet.text = user.CcyNm_UZC
            itemRV.tvKupyuraInfoENBottomsheet.text = user.CcyNm_EN
            itemRV.tvNominal.text = user.Nominal
            itemRV.tvRateBottomsheet.text = user.Rate
            itemRV.tvDiff.text = user.Diff
            itemRV.tvDateBottomsheet.text = user.Date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            BottomsheetdialogBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}