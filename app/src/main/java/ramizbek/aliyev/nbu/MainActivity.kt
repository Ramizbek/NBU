package ramizbek.aliyev.nbu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ramizbek.aliyev.nbu.adapter.RvAdapter
import ramizbek.aliyev.nbu.adapter.RvAdapter2
import ramizbek.aliyev.nbu.databinding.ActivityMainBinding
import ramizbek.aliyev.nbu.databinding.BottomsheetdialogBinding
import ramizbek.aliyev.nbu.models.User

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAdapter: RvAdapter
    private lateinit var rvAdapter2: RvAdapter2
    private lateinit var list1: ArrayList<User>

    //    private lateinit var list: ArrayList<User>
    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list1 = ArrayList()

        requestQueue = Volley.newRequestQueue(this)
        loadList()

    }

    private fun loadList() {
        VolleyLog.DEBUG = true

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            "http://cbu.uz/uzc/arkhiv-kursov-valyut/json/",
            null,
            {
                val type = object : TypeToken<ArrayList<User>>() {}.type
                list1 = Gson().fromJson<ArrayList<User>>(it.toString(), type)

                rvAdapter = RvAdapter(list1, object : RvAdapter.RVClickCourses {
                    override fun onClick(user: User, position: Int) {
                        var bottom = BottomSheetDialog(binding.root.context)
                        var bottomSheetDialogitem = BottomsheetdialogBinding.inflate(layoutInflater)
                        bottomSheetDialogitem.tvIdBottomsheet.text = user.id.toString()
                        bottomSheetDialogitem.tvCode.text = user.Code
                        bottomSheetDialogitem.tvKupyuraBottomsheet.text = user.CcyNm_RU
                        bottomSheetDialogitem.tvKupyuraInfoUZBottomsheet.text = user.CcyNm_UZ
                        bottomSheetDialogitem.tvKupyuraInfoUZCBottomsheet.text = user.CcyNm_UZC
                        bottomSheetDialogitem.tvKupyuraInfoENBottomsheet.text = user.CcyNm_EN
                        bottomSheetDialogitem.tvNominal.text = user.Nominal
                        bottomSheetDialogitem.tvRateBottomsheet.text = user.Rate
                        bottomSheetDialogitem.tvDiff.text = user.Diff
                        bottomSheetDialogitem.tvDateBottomsheet.text = user.Date
                        bottom.setContentView(bottomSheetDialogitem.root)
                        bottom.show()
                    }
                })

                binding.rv.adapter = rvAdapter
            },
            {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        )
        requestQueue.add(jsonArrayRequest)
    }
}