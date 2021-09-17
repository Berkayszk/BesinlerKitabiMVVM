package com.berkaysazak.besinlerkitabi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkaysazak.besinlerkitabi.R
import com.berkaysazak.besinlerkitabi.adapter.BesinRecyclerAdapter
import com.berkaysazak.besinlerkitabi.model.Besin
import com.berkaysazak.besinlerkitabi.viewmodel.BesinListesiViewModel
import kotlinx.android.synthetic.main.fragment_besin_listesi.*


class BesinListesiFragment : Fragment() {

    private lateinit var  viewModel : BesinListesiViewModel
    private val recyclerBesinAdapter = BesinRecyclerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_besin_listesi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(BesinListesiViewModel::class.java)
        viewModel.refleshData()

        besinListRecycler.layoutManager = LinearLayoutManager(context)
        besinListRecycler.adapter = recyclerBesinAdapter

        swipeRefreshLayout.setOnRefreshListener {
            besinYukleniyor.visibility = View.VISIBLE
            besinHataMesaji.visibility = View.GONE
            besinListRecycler.visibility = View.GONE
            viewModel.refleshFromInternet()
            swipeRefreshLayout.isRefreshing = false

        }

        observeLiveData()

    }

    fun observeLiveData(){
         viewModel.besinler.observe(viewLifecycleOwner, Observer {
             it?.let {
                 besinListRecycler.visibility = View.VISIBLE
                 recyclerBesinAdapter.besinListesiniGuncelle(it)
             }
         })
        viewModel.besinHataMesaji.observe(viewLifecycleOwner, Observer { hata ->
            hata?.let {
                if (it==true){
                    besinHataMesaji.visibility = View.VISIBLE
                    besinListRecycler.visibility = View.GONE
                }else{
                    besinHataMesaji.visibility = View.GONE
                }

            }

        })
        viewModel.besinYukleniyor.observe(viewLifecycleOwner, Observer { yukleniyor ->
            yukleniyor?.let {
                if (it==true){
                    besinListRecycler.visibility= View.GONE
                    besinYukleniyor.visibility = View.GONE
                    besinYukleniyor.visibility = View.VISIBLE
                }else{
                    besinYukleniyor.visibility = View.GONE
                }
            }
        })
    }

}