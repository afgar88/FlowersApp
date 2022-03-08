package com.example.flowersapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.flowersapp.adapter.FlowerAdapter
import com.example.flowersapp.databinding.FragmentFlowersBinding
import com.example.flowersapp.model.Flowers
import com.example.flowersapp.rest.FlowersAPI
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FlowersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FlowersFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val binding by lazy {
        FragmentFlowersBinding.inflate(layoutInflater)
    }

    private val flowersAdapter by lazy{
        FlowerAdapter()
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      binding.flowerRecycler.apply{
          layoutManager=GridLayoutManager(requireContext(),3,GridLayoutManager.VERTICAL,false)
          adapter=flowersAdapter
      }
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        FlowersAPI.retrofitService.getFlowers().enqueue(object : Callback<Flowers> {
            override fun onResponse(call: Call<Flowers>, response: Response<Flowers>) {
               if(response.isSuccessful){
                   response.body()?.let {
                       flowersAdapter.updateFlowers(it)
                       Snackbar.make(requireView(),it[0].name,Snackbar.LENGTH_LONG).show()
                   }?: Snackbar.make(requireView(),"Error",Snackbar.LENGTH_LONG).show()
               }
            }

            override fun onFailure(call: Call<Flowers>, t: Throwable) {
                Snackbar.make(requireView(),t.localizedMessage,Snackbar.LENGTH_LONG).show()
            }
        })
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FlowersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FlowersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}