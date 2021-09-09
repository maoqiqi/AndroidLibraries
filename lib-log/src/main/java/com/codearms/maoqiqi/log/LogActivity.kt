/*
 * Copyright [2021] [March]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codearms.maoqiqi.log

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

/**
 * Log activity
 * @link https://github.com/maoqiqi/AndroidLibraries
 * @e-mail fengqi.mao.march@gmail.com
 * @author March
 * @date 2021-02-01 21:01
 * @version v1.0.0
 */
open class LogActivity : AppCompatActivity() {

    protected var logInfo = LogUtils.LogInfo(javaClass.simpleName)

    init {
        LogUtils.v(logInfo, "${javaClass.simpleName} created:", this.toString())
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        LogUtils.v(logInfo, "-->attachBaseContext(newBase: Context?)")
    }

    // The activity is being created.
    // 应该在此方法中执行所有正常的静态设置—创建视图、将数据绑定到列表等等.始终后接onStart().
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.v(logInfo, "-->onCreate(savedInstanceState: Bundle?)")
        supportFragmentManager.addFragmentOnAttachListener { fragmentManager, fragment ->
            LogUtils.v(logInfo, "-->addFragmentOnAttachListener(),fragmentManager:${fragmentManager},fragment:${fragment.javaClass.name},$fragment")
        }
    }

    // 在Activity已停止并即将再次启动前调用.始终后接onStart().
    override fun onRestart() {
        super.onRestart()
        LogUtils.v(logInfo, "-->onRestart()")
    }

    // The activity is about to become visible.
    // 如果Activity转入前台,则后接onResume(),如果Activity转入隐藏状态,则后接onStop().
    override fun onStart() {
        super.onStart()
        LogUtils.v(logInfo, "-->onStart()")
    }

    // 您应始终调用onRestoreInstanceState()的父类实现,以便默认实现可以恢复视图层次结构的状态。
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        LogUtils.v(logInfo, "-->onRestoreInstanceState(savedInstanceState: Bundle)")
    }

    // The activity has become visible (it is now "resumed").
    // 此时,Activity处于Activity堆栈的顶层,并具有用户输入焦点.始终后接onPause().
    override fun onResume() {
        super.onResume()
        LogUtils.v(logInfo, "-->onResume()")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        LogUtils.v(logInfo, "-->onAttachedToWindow()")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        LogUtils.v(logInfo, "-->onCreateOptionsMenu(menu: Menu?): Boolean")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        LogUtils.v(logInfo, "-->onPrepareOptionsMenu(menu: Menu?): Boolean")
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        LogUtils.v(logInfo, "onOptionsItemSelected(item: MenuItem): Boolean")
        return super.onOptionsItemSelected(item)
    }

    override fun onOptionsMenuClosed(menu: Menu?) {
        super.onOptionsMenuClosed(menu)
        LogUtils.v(logInfo, "onOptionsMenuClosed(menu: Menu?)")
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        LogUtils.v(logInfo, "-->onWindowFocusChanged(hasFocus: Boolean),hasFocus=$hasFocus")
    }

    // Another activity is taking focus (this activity is about to be "paused").
    // 此方法通常用于确认对持久性数据的未保存更改、停止动画以及其他可能消耗CPU的内容.
    // 它应该非常迅速地执行所需操作,因为它返回后,下一个Activity才能继续执行.
    override fun onPause() {
        super.onPause()
        LogUtils.v(logInfo, "-->onPause()")
    }

    // 始终调用超类,以便它可以保存视图层次结构状态
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        LogUtils.v(logInfo, "-->onSaveInstanceState(outState: Bundle)")
    }

    // The activity is no longer visible (it is now "stopped")
    // 如果Activity被销毁,或另一个Activity(一个现有Activity或新Activity)继续执行并将其覆盖,就可能发生这种情况.
    // 如果Activity恢复与用户的交互,则后接onRestart(),如果Activity被销毁,则后接onDestroy().
    override fun onStop() {
        super.onStop()
        LogUtils.v(logInfo, "-->onStop()")
    }

    // The activity is about to be destroyed.
    // 当Activity结束(有人对Activity调用了finish()),或系统为节省空间而暂时销毁该Activity实例时,可能会调用它.
    // 您可以通过isFinishing()方法区分这两种情形.
    override fun onDestroy() {
        super.onDestroy()
        LogUtils.v(logInfo, "-->onDestroy()")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        LogUtils.v(logInfo, "-->onDetachedFromWindow()")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LogUtils.v(logInfo, "-->onConfigurationChanged(newConfig: Configuration)")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        LogUtils.v(logInfo, "-->onLowMemory()")
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        LogUtils.v(logInfo, "-->onTrimMemory(level: Int)")
    }
}