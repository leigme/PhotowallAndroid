package me.leig.baselibrary.activity

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import me.leig.baselibrary.comm.BaseInterface
import me.leig.baselibrary.comm.Constant
import me.leig.baselibrary.fragment.BaseFragment

/**
 * 主程序入口基类
 *
 * @author leig
 * @version 20180301
 *
 */

abstract class BaseActivity constructor(protected val title: String = BaseActivity::class.java.name): AppCompatActivity(), BaseInterface {

    private lateinit var toast: Toast

    private var requestCode = 9009

    override fun initBaseData() {
        print("initBaseData")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getRootView())

        toast = Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT)

        initBaseData()

        initView()

        initData()

        initEvent()
    }

    abstract fun getRootView(): Int

    abstract fun getFragmentId(): Int

    override fun initData() {
        print("initData")
    }

    override fun initView() {
        print("initView")
    }

    override fun initEvent() {
        print("initEvent")
    }

    protected fun startActivity(clazz: Class<out Activity>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }

    protected fun addFragment(baseFragment: BaseFragment) {
        if (0 >= getFragmentId()) {
            return
        }
        var bundle = baseFragment.arguments
        if (null == bundle) {
            bundle = Bundle()
        }
        bundle.putInt(Constant.FRAGMENT_ID, getFragmentId())
        baseFragment.arguments = bundle
        val ft = fragmentManager.beginTransaction()
        ft.add(getFragmentId(), baseFragment)
        ft.addToBackStack(null)
        ft.commit()
    }

    /**
     * 简单日志打印
     *
     */
    fun log(msg: String) {
        Log.d(title, msg)
    }

    /**
     * 简单吐司提示
     *
     */
    fun toast(msg: String) {
        runOnUiThread {
            toast.setText(msg)
            toast.show()
        }
    }

    /**
     * 请求权限
     *
     */
    fun requestPermission(permissions: Array<String>, requestCode: Int = 9009) {
        this.requestCode = requestCode
        if (checkPermissions(permissions)) {
            permissionSuccess(requestCode)
        } else {
            val needPermissions = getDeniedPermissions(permissions)
            ActivityCompat.requestPermissions(this, needPermissions, requestCode)
        }
    }

    /**
     * 权限检查
     *
     */
    private fun checkPermissions(permissions: Array<String>): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }
        for (permission in permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, permission)) {
                return false
            }
        }
        return true
    }

    /**
     *
     *
     */
    private fun getDeniedPermissions(permissions: Array<String>): Array<String> {
        val needRequestPermissionList = mutableListOf<String>()
        for (permission in permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, permission) || ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                needRequestPermissionList.add(permission)
            }
        }
        return needRequestPermissionList.toTypedArray()
    }

    /**
     * 确认所有的权限是否都已授权
     *
     * @param grantResults
     * @return
     */
    private fun verifyPermissions(grantResults: IntArray): Boolean {
        for (grantResult in grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    /**
     * 获取权限回调
     *
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (this.requestCode == requestCode) {
            if (verifyPermissions(grantResults)) {
                permissionSuccess(requestCode)
            } else {
                permissionFailure(requestCode)
                showPermissionTipsDialog()
            }
        }
    }

    /**
     * 显示警告对话框
     *
     */
    private fun showPermissionTipsDialog() {
        android.support.v7.app.AlertDialog.Builder(this)
                .setTitle("警告")
                .setMessage("需要必要的权限才可以正常使用该应用的功能，您已拒绝获得该权限。\n如果需要重新授权，您可以点击“确定”按钮进入系统设置进行授权")
                .setNegativeButton("取消") { _, _ -> finish() }
                .setPositiveButton("确定") { _, _ -> startAppSettings(Settings.ACTION_APPLICATION_DETAILS_SETTINGS) }.show()
    }

    /**
     * 启动当前应用设置页面
     *
     */
    private fun startAppSettings(name: String) {
        val intent = Intent(name)
        intent.data = Uri.parse("package:$packageName")
        startActivity(intent)
    }

    /**
     * 权限成功
     *
     */
    private fun permissionSuccess(requestCode: Int) {
        log("请求成功: $requestCode")
    }

    /**
     * 权限失败
     *
     */
    private fun permissionFailure(requestCode: Int) {
        log("请求失败: $requestCode")
    }

}