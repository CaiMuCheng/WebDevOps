package com.mucheng.web.devops.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mucheng.web.devops.base.BaseFragment
import com.mucheng.web.devops.data.model.MainCommonItem
import com.mucheng.web.devops.databinding.FragmentMainCommonPageBinding
import com.mucheng.web.devops.ui.activity.DisplayPluginActivity
import com.mucheng.web.devops.ui.adapter.MainCommonAdapter
import com.mucheng.web.devops.ui.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.withContext

class MainCommonPageFragment : BaseFragment(), MainCommonAdapter.MainCommonItemCallback {

    private lateinit var viewBinding: FragmentMainCommonPageBinding

    private val mainViewModel: MainViewModel by activityViewModels()

    private val mainCommonAdapter by lazy {
        MainCommonAdapter(requireContext(), mainViewModel.mainCommonItems).also {
            it.setMainCommonItemCallback(this)
        }
    }

    private val mainCommonItemCoroutineLock = Mutex()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentMainCommonPageBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = viewBinding.recyclerView
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = mainCommonAdapter

        viewBinding.swipeRefreshLayout.setOnRefreshListener {
            refresh()
        }

        refresh()
    }

    private fun refresh() {
        lifecycleScope.launch(CoroutineName("FetchMainCommonItemCoroutine")) {
            mainCommonItemCoroutineLock.lock()
            viewBinding.recyclerView.isEnabled = false
            viewBinding.swipeRefreshLayout.isRefreshing = true
            try {
                refreshMainCommonItems(mainViewModel.fetchMainCommonItems())
                viewBinding.recyclerView.isEnabled = true
                viewBinding.swipeRefreshLayout.isRefreshing = false
            } finally {
                mainCommonItemCoroutineLock.unlock()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private suspend fun refreshMainCommonItems(fetchMainCommonItems: List<MainCommonItem>) {
        return withContext(Dispatchers.IO) {
            mainViewModel.mainCommonItems.clear()
            mainViewModel.mainCommonItems.addAll(fetchMainCommonItems)
            withContext(Dispatchers.Main) {
                mainCommonAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onMainCommonItemClick(view: View, mainCommonItem: MainCommonItem, position: Int) {
        val intent = Intent(requireContext(), DisplayPluginActivity::class.java)
        intent.putExtra("title", mainCommonItem.simpleName)
        intent.putExtra("url", mainCommonItem.displayUrl)
        startActivity(intent)
    }

}