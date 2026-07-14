package site.linacre.uninstaller

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class AppInfo(
    val name: String,
    val packageName: String,
    val icon: android.graphics.drawable.Drawable,
    val isSystem: Boolean,
    val size: Long
)

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var emptyView: TextView
    private lateinit var searchEditText: EditText
    private lateinit var tabLayout: TabLayout
    
    private val adapter = AppAdapter()
    private var allApps = mutableListOf<AppInfo>()
    private var currentFilter = ""
    private var isSystemTab = false
    
    private val scope = CoroutineScope(Dispatchers.Main + Job())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recycler_view)
        swipeRefresh = findViewById(R.id.swipe_refresh)
        progressBar = findViewById(R.id.progress_bar)
        emptyView = findViewById(R.id.empty_view)
        searchEditText = findViewById(R.id.search_edit_text)
        tabLayout = findViewById(R.id.tab_layout)

        setupUI()
        loadApps()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        tabLayout.addTab(tabLayout.newTab().setText(R.string.user_apps))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.system_apps))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                isSystemTab = tab?.position == 1
                filterApps()
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                currentFilter = s?.toString() ?: ""
                filterApps()
            }
        })

        swipeRefresh.setOnRefreshListener {
            loadApps()
        }

        adapter.onDeleteClick = { app ->
            showUninstallDialog(app)
        }
    }

    private fun loadApps() {
        swipeRefresh.isRefreshing = false
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        emptyView.visibility = View.GONE

        scope.launch {
            val apps = withContext(Dispatchers.IO) {
                getInstalledApps()
            }
            allApps.clear()
            allApps.addAll(apps)
            filterApps()
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }

    private fun getInstalledApps(): List<AppInfo> {
        val pm = packageManager
        val packages = pm.getInstalledApplications(PackageManager.GET_META_DATA)
        
        return packages.mapNotNull { appInfo ->
            try {
                val isSystem = (appInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 0 || 
                              (appInfo.flags and ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0
                
                AppInfo(
                    name = pm.getApplicationLabel(appInfo).toString(),
                    packageName = appInfo.packageName,
                    icon = pm.getApplicationIcon(appInfo),
                    isSystem = isSystem,
                    size = 0L // Getting size requires separate API calls that can be slow
                )
            } catch (e: Exception) {
                null
            }
        }.sortedBy { it.name.lowercase() }
    }

    private fun filterApps() {
        val filtered = allApps.filter { app ->
            val matchesTab = app.isSystem == isSystemTab
            val matchesSearch = currentFilter.isEmpty() || 
                              app.name.contains(currentFilter, ignoreCase = true) ||
                              app.packageName.contains(currentFilter, ignoreCase = true)
            matchesTab && matchesSearch
        }

        adapter.submitList(filtered)
        
        if (filtered.isEmpty()) {
            emptyView.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        } else {
            emptyView.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }

    private fun showUninstallDialog(app: AppInfo) {
        AlertDialog.Builder(this, R.style.Theme_LinacreUninstaller_Dialog)
            .setTitle(R.string.uninstall_prompt_title)
            .setMessage(getString(R.string.uninstall_prompt_message, app.name))
            .setPositiveButton(R.string.uninstall) { _, _ ->
                val intent = Intent(Intent.ACTION_DELETE)
                intent.data = Uri.parse("package:${app.packageName}")
                startActivity(intent)
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }
}

class AppAdapter : RecyclerView.Adapter<AppAdapter.AppViewHolder>() {

    private var items = listOf<AppInfo>()
    var onDeleteClick: ((AppInfo) -> Unit)? = null

    fun submitList(newItems: List<AppInfo>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_app, parent, false)
        return AppViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val app = items[position]
        holder.icon.setImageDrawable(app.icon)
        holder.name.text = app.name
        holder.packageName.text = app.packageName
        
        holder.deleteBtn.setOnClickListener {
            onDeleteClick?.invoke(app)
        }
    }

    override fun getItemCount() = items.size

    class AppViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.app_icon)
        val name: TextView = view.findViewById(R.id.app_name)
        val packageName: TextView = view.findViewById(R.id.package_name)
        val deleteBtn: ImageView = view.findViewById(R.id.delete_btn)
    }
}
