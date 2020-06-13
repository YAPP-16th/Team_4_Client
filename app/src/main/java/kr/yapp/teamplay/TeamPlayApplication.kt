package kr.yapp.teamplay;

import android.app.Application
import android.content.Context
import androidx.databinding.DataBindingUtil
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import kr.yapp.teamplay.presentation.matchresultinput.BindingComponent

class TeamPlayApplication : Application() {

    companion object {
        private const val TAG: String = "TeamPlayApplication"
        private const val PRINT_STACK_COUNT: Int = 5

        var appContext: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        DataBindingUtil.setDefaultComponent(BindingComponent())
        appContext = applicationContext
        setLogger()
    }

    private fun setLogger() {
        PrettyFormatStrategy.newBuilder()
            .showThreadInfo(true)
            .tag(TAG)
            .methodCount(PRINT_STACK_COUNT)
            .build()
            .let { formatStrategy ->
                Logger.addLogAdapter(
                    object: AndroidLogAdapter(formatStrategy) {
                        override fun isLoggable(priority: Int, tag: String?): Boolean {
                            // DEBUG 모드에서만 로그 출력
                            return BuildConfig.DEBUG
                        }
                    }
                )
            }
    }
}
