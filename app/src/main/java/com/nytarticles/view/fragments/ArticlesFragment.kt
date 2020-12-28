package com.nytarticles.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nytarticles.view.activities.MainActivity
import com.nytarticles.R
import com.nytarticles.adapter.ArticlesAdapter
import com.nytarticles.db.entities.ArticlesEntity
import com.nytarticles.viewholder.OnItemClickListener
import com.nytarticles.viewmodel.ArticlesActivityViewModel
import kotlinx.android.synthetic.main.fragment_all_articles.*

class ArticlesFragment : Fragment(), OnItemClickListener<ArticlesEntity> {

    private lateinit var rootView: View
    //private lateinit var rvArticles: RecyclerView
    //private lateinit var tvErr: TextView

    private lateinit var viewModel : ArticlesActivityViewModel
    private var mAdapter: ArticlesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_all_articles, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        initViewModel()
    }

    private fun initViews() {
        viewModel = ViewModelProviders.of(requireActivity()).get(ArticlesActivityViewModel::class.java)

        //rvArticles = rootView.findViewById(R.id.rv_articles)
        //tvErr = rootView.findViewById(R.id.tv_err)

        mAdapter = ArticlesAdapter(requireContext())
        val tempLst = ArrayList<ArticlesEntity>()
        tempLst.add(ArticlesEntity(null))
        mAdapter?.setData(tempLst)
        mAdapter?.setListener(this)
        val layoutManager = LinearLayoutManager(requireContext())
        rv_articles.layoutManager = layoutManager
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rv_articles.adapter = mAdapter
    }

    private fun initViewModel() {
        viewModel.showError().observe(viewLifecycleOwner, object : Observer<String> {
            override fun onChanged(message: String) {
                if (!message.isNullOrEmpty())
                    tv_err.text = message
                tv_err.visibility = View.VISIBLE
                mAdapter?.notifyItemRemoved(0)
                mAdapter?.resetLst()
                mAdapter?.notifyDataSetChanged()
            }
        })

        viewModel.getArticles().observe(viewLifecycleOwner, object : Observer<ArrayList<ArticlesEntity>> {
            override fun onChanged(lst: ArrayList<ArticlesEntity>) {
                tv_err.visibility = View.GONE
                mAdapter?.setData(lst)
                mAdapter?.notifyDataSetChanged()
            }
        })

        viewModel.loadArticles()
    }

    override fun onItemClick(position: Int, obj: ArticlesEntity, actionType: Int) {
        viewModel.setSelectedArticle(obj)
        (activity as MainActivity).addFragment(ArticleDetailFragment(), null)
    }
}