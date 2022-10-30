package com.mucheng.web.devops.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.GravityCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mucheng.web.devops.base.BaseFragment
import com.mucheng.web.devops.databinding.FragmentMainHomePageBinding
import com.mucheng.web.devops.openapi.view.LoadingComponent
import com.mucheng.web.devops.path.ProjectDir
import com.mucheng.web.devops.ui.activity.EditorActivity
import com.mucheng.web.devops.ui.activity.SelectNewProjectActivity
import com.mucheng.web.devops.ui.adapter.MainHomePageAdapter
import com.mucheng.web.devops.ui.view.ComposableDialog
import com.mucheng.web.devops.ui.viewmodel.MainHomePageViewModel
import com.mucheng.web.devops.util.AppCoroutine
import com.mucheng.webops.plugin.check.ProjectCreationChecker
import com.mucheng.webops.plugin.data.Workspace
import com.mucheng.webops.plugin.data.info.ComponentInfo
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.withContext

class MainHomePageFragment : BaseFragment(), MainHomePageAdapter.OnActionListener {

    companion object {
        private const val REQUEST_CODE = 150
        private const val RENAME_PROJECT = "重命名"
        private const val DELETE_PROJECT = "删除工程"
    }

    private lateinit var viewBinding: FragmentMainHomePageBinding

    private val mainViewModel: MainHomePageViewModel by activityViewModels()

    private val mainHomePageAdapter by lazy {
        MainHomePageAdapter(
            requireContext(),
            mainViewModel.list
        ).also { it.setOnActionListener(this) }
    }

    private val coroutineLock = Mutex()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentMainHomePageBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    @Suppress("DEPRECATION")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = viewBinding.recyclerView
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = mainHomePageAdapter

        val fab = viewBinding.fab
        fab.setOnClickListener {
            startActivityForResult(
                Intent(activity, SelectNewProjectActivity::class.java),
                REQUEST_CODE
            )
        }

        viewBinding.swipeRefreshLayout.setOnRefreshListener {
            refresh()
        }

    }

    private fun refresh() {
        viewLifecycleOwner.lifecycleScope.launch {
            coroutineLock.lock()
            try {
                viewBinding.recyclerView.isEnabled = false
                viewBinding.swipeRefreshLayout.isRefreshing = true
                refreshWorkspaces(mainViewModel.fetchWorkspaces())
                viewBinding.recyclerView.isEnabled = true
                viewBinding.swipeRefreshLayout.isRefreshing = false
            } finally {
                coroutineLock.unlock()
            }
        }
    }

    @Deprecated(
        "Deprecated in Java", ReplaceWith(
            "super.onActivityResult(requestCode, resultCode, data)",
            "com.mucheng.web.devops.base.BaseFragment"
        )
    )
    @Suppress("DEPRECATION")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            val intent = data ?: return
            val action = intent.getStringExtra("action") ?: return
            if (action == "refresh") {
                refresh()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        refresh()
    }

    @SuppressLint("NotifyDataSetChanged")
    private suspend fun refreshWorkspaces(workspaces: List<Workspace>) {
        withContext(Dispatchers.IO) {
            val sourceList = mainViewModel.list
            val isNotEmpty = sourceList.isNotEmpty()
            if (isNotEmpty) {
                sourceList.clear()
                withContext(Dispatchers.Main) {
                    mainHomePageAdapter.notifyDataSetChanged()
                }
            }

            for (workspace in workspaces) {
                sourceList.add(workspace)
                withContext(Dispatchers.Main) {
                    mainHomePageAdapter.notifyDataSetChanged()
                }
            }

            withContext(Dispatchers.Main) {
                checkIsEmpty()
            }
        }
    }

    private fun checkIsEmpty() {
        val isEmpty = mainViewModel.list.isEmpty()
        if (isEmpty) {
            if (viewBinding.emptyLayout.visibility != View.VISIBLE) {
                viewBinding.emptyLayout.visibility = View.VISIBLE
                viewBinding.recyclerView.visibility = View.GONE
                viewBinding.lottieView.playAnimation()
            }
        } else {
            if (viewBinding.recyclerView.visibility != View.VISIBLE) {
                viewBinding.emptyLayout.visibility = View.GONE
                viewBinding.recyclerView.visibility = View.VISIBLE
            }
        }
    }

    override fun onClick(view: View, workspace: Workspace, position: Int) {
        val intent = Intent(requireContext(), EditorActivity::class.java)
        intent.putExtra("path", "$ProjectDir/${workspace.getName()}/.WebDevOps/Workspace.xml")
        startActivity(intent)
    }

    override fun onLongClick(view: View, workspace: Workspace, position: Int) {
        val popupMenu = PopupMenu(requireContext(), view, Gravity.BOTTOM or GravityCompat.END)

        val menu = popupMenu.menu
        menu.add(RENAME_PROJECT)
        menu.add(DELETE_PROJECT)
        popupMenu.setOnMenuItemClickListener {
            when (it.title) {
                RENAME_PROJECT -> {
                    showRenameProjectDialog(workspace, position)
                }

                DELETE_PROJECT -> {
                    showDeleteProjectDialog(workspace, position)
                }
            }
            true
        }

        popupMenu.show()
    }

    private fun showRenameProjectDialog(workspace: Workspace, position: Int) {
        ComposableDialog(requireContext())
            .setTitle("重命名工程")
            .setComponents(
                listOf(
                    ComponentInfo.InputInfo(
                        title = null,
                        hint = "工程名称",
                        isSingleLine = true
                    )
                )
            )
            .onComplete {
                val inputInfo = it[0] as ComponentInfo.InputInfo
                val projectName = inputInfo.title ?: ""
                if (!ProjectCreationChecker.checkProjectName(
                        requireContext(),
                        ProjectDir,
                        projectName
                    )
                ) {
                    false
                } else {
                    val loadingComponent = LoadingComponent(requireContext())
                    loadingComponent.setContent("正在重命名工程...")
                    AppCoroutine.launch(CoroutineName("RenameProjectCoroutine")) {
                        mainViewModel.renameProject(workspace, projectName)
                        loadingComponent.dismiss()
                        mainHomePageAdapter.notifyItemChanged(position)
                    }
                    true
                }
            }
            .setCancelable(false)
            .setNeutralButton("取消", null)
            .setPositiveButton("确定", null)
            .show()
    }

    private fun showDeleteProjectDialog(workspace: Workspace, position: Int) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("删除工程")
            .setMessage("你确定要删除工程 ${workspace.getName()} 吗?")
            .setNeutralButton("取消", null)
            .setPositiveButton("确定") { _, _ ->
                mainViewModel.list.removeAt(position)
                mainHomePageAdapter.notifyItemRemoved(position)
                checkIsEmpty()

                AppCoroutine.launch(CoroutineName("DeleteProjectCoroutine")) {
                    mainViewModel.deleteProject(workspace)
                }
            }
            .show()
    }

}