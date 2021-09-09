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
import android.util.AttributeSet
import android.view.*
import androidx.fragment.app.Fragment

/**
 * Log fragment
 * @link https://github.com/maoqiqi/AndroidLibraries
 * @e-mail fengqi.mao.march@gmail.com
 * @author March
 * @date 2021-02-01 21:01
 * @version v1.0.0
 */
open class LogFragment : Fragment() {

    protected var logInfo = LogUtils.LogInfo(javaClass.simpleName)

    init {
        LogUtils.v(logInfo, "${javaClass.simpleName} created:", this.toString())
    }

    // @deprecated Use {@link FragmentTransaction#setMaxLifecycle(Fragment, Lifecycle.State)} instead.
    @Suppress("DEPRECATION")
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        LogUtils.v(logInfo, "setUserVisibleHint(isVisibleToUser: Boolean),isVisibleToUser=$isVisibleToUser")
    }

    // 该方法只在我们直接用标签在布局中定义的时候才会被调用
    override fun onInflate(context: Context, attrs: AttributeSet, savedInstanceState: Bundle?) {
        super.onInflate(context, attrs, savedInstanceState)
        LogUtils.v(logInfo, "onInflate(context: Context, attrs: AttributeSet, savedInstanceState: Bundle?)")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        LogUtils.v(logInfo, "onAttach(context: Context)")
    }

    // 系统会在创建片段时调用此方法,只会调用一次。您应该在此初始化您想在片段暂停或停止后恢复时需要的数据。
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.v(logInfo, "onCreate(savedInstanceState: Bundle?)")
        childFragmentManager.addFragmentOnAttachListener { fragmentManager, fragment ->
            LogUtils.v(logInfo, "-->addFragmentOnAttachListener(),fragmentManager:${fragmentManager},fragment:${fragment.javaClass.name},$fragment")
        }
    }

    // 每次创建,绘制改Fragment的View组件时回调,会将显示的View返回
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        LogUtils.v(logInfo, "onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LogUtils.v(logInfo, "onViewCreated(view: View, savedInstanceState: Bundle?)")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        LogUtils.v(logInfo, "onViewStateRestored(savedInstanceState: Bundle?)")
    }

    override fun onStart() {
        super.onStart()
        LogUtils.v(logInfo, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        LogUtils.v(logInfo, "onResume()")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        LogUtils.v(logInfo, "onCreateOptionsMenu(menu: Menu, inflater: MenuInflater)")
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        LogUtils.v(logInfo, "onPrepareOptionsMenu(menu: Menu)")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        LogUtils.v(logInfo, "onOptionsItemSelected(item: MenuItem): Boolean")
        return super.onOptionsItemSelected(item)
    }

    override fun onOptionsMenuClosed(menu: Menu) {
        super.onOptionsMenuClosed(menu)
        LogUtils.v(logInfo, "onOptionsMenuClosed(menu: Menu)")
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        LogUtils.v(logInfo, "onDestroyOptionsMenu()")
    }

    override fun onPause() {
        super.onPause()
        LogUtils.v(logInfo, "onPause()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        LogUtils.v(logInfo, "onSaveInstanceState(outState: Bundle)")
    }

    override fun onStop() {
        super.onStop()
        LogUtils.v(logInfo, "onStop()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        LogUtils.v(logInfo, "onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.v(logInfo, "onDestroy()")
    }

    override fun onDetach() {
        super.onDetach()
        LogUtils.v(logInfo, "onDetach()")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LogUtils.v(logInfo, "onConfigurationChanged(newConfig: Configuration)")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        LogUtils.v(logInfo, "onHiddenChanged(hidden: Boolean),hidden=$hidden")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        LogUtils.v(logInfo, "-->onLowMemory()")
    }
}