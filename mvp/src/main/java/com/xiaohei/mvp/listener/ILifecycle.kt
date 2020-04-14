package com.xiaohei.mvp.listener

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle

interface ILifecycle{
      fun onCreate(savedInstanceState: Bundle?)

      fun onViewStateRestored(savedInstanceState: Bundle?)

      fun onSaveInstanceState(outState: Bundle)

      fun onConfigurationChanged(newConfig: Configuration)

      fun onDestroy()

      fun onStart()

      fun onStop()

      fun onResume()

      fun onPause()
}