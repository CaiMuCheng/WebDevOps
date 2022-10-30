package com.mucheng.web.devops.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.mucheng.web.devops.R
import com.mucheng.web.devops.base.BaseActivity
import com.mucheng.web.devops.data.model.FileItem
import com.mucheng.web.devops.databinding.ActivityFileSelectorBinding
import com.mucheng.web.devops.path.StorageDir
import com.mucheng.web.devops.ui.adapter.FileSelectorLargeAdapter
import java.io.File

class FileSelectorActivity : BaseActivity(), FileSelectorLargeAdapter.FileSelectorCallback {

    companion object {
        const val RESULT_CODE = 200
    }

    private lateinit var viewBinding: ActivityFileSelectorBinding

    private var currentPath = StorageDir.absolutePath

    private val fileItems: MutableList<FileItem> = ArrayList()

    private val adapter by lazy {
        FileSelectorLargeAdapter(this, fileItems).also {
            it.setFileSelectorCallback(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFileSelectorBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val toolbar: MaterialToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onStart() {
        super.onStart()
        scanPath()
        adapter.notifyDataSetChanged()
    }

    private fun scanPath() {
        fileItems.clear()
        val currentFile = File(currentPath)
        val files = currentFile.listFiles() ?: return
        val fileIcon = ContextCompat.getDrawable(this, R.drawable.ic_file)!!
        val folderIcon = ContextCompat.getDrawable(this, R.drawable.ic_folder)!!

        for (file in files) {
            val icon = if (file.isFile) {
                fileIcon
            } else {
                folderIcon
            }
            fileItems.add(FileItem(file.name, file, icon))
        }

        fileItems.sortWith { o1, o2 ->
            if (o1.file.isDirectory && o2.file.isFile) {
                -1
            } else if (o1.file.isFile && o2.file.isDirectory) {
                1
            } else {
                0
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_file_selector, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }

            R.id.up -> {
                val file = File(currentPath)
                val parent = file.parent
                if (parent != null) {
                    currentPath = parent
                    scanPath()
                    adapter.notifyDataSetChanged()
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onFileItemClick(view: View, fileItem: FileItem, position: Int) {
        val type = fileItem.file.isFile
        if (type) {
            val intent = Intent()
            intent.putExtra("path", fileItem.file.absolutePath)
            setResult(RESULT_CODE, intent)
            finish()
        } else {
            val path = fileItem.file.absolutePath
            currentPath = path

            scanPath()
            adapter.notifyDataSetChanged()
        }
    }

}