package com.sriramsv.aiotaskerplugin.command

import android.content.Context
import android.content.Intent
import com.joaomgcd.taskerpluginlibrary.action.TaskerPluginRunnerActionNoOutput
import com.joaomgcd.taskerpluginlibrary.config.TaskerPluginConfig
import com.joaomgcd.taskerpluginlibrary.config.TaskerPluginConfigHelperNoOutput
import com.joaomgcd.taskerpluginlibrary.input.TaskerInput
import com.joaomgcd.taskerpluginlibrary.runner.TaskerPluginResult
import com.joaomgcd.taskerpluginlibrary.runner.TaskerPluginResultSucess
import com.sriramsv.aiotaskerplugin.ActivityConfigTaskerNoOutput
import kotlinx.android.synthetic.main.activity_addwidget.*
import kotlinx.android.synthetic.main.activity_aiocommand.editPassword


class AddWidgetHelper(config: TaskerPluginConfig<WidgetInput>) : TaskerPluginConfigHelperNoOutput<WidgetInput, AddWidgetRunner>(config) {
    override val runnerClass: Class<AddWidgetRunner> get() = AddWidgetRunner::class.java
    override val inputClass: Class<WidgetInput> get() = WidgetInput::class.java
    override fun addToStringBlurb(input: TaskerInput<WidgetInput>, blurbBuilder: StringBuilder) {
    }
}


class AddWidget: ActivityConfigTaskerNoOutput<WidgetInput, AddWidgetRunner, AddWidgetHelper>() {
    //Overrides

    override fun getNewHelper(config: TaskerPluginConfig<WidgetInput>) = AddWidgetHelper(config)
    override fun assignFromInput(input: TaskerInput<WidgetInput>) = input.regular.run {
        editPassword.setText(password)
    }

    override val inputForTasker get() = TaskerInput(WidgetInput(widgets.selectedItem.toString().lowercase(), editPassword.text.toString()))
    override val layoutResId = com.sriramsv.aiotaskerplugin.R.layout.activity_addwidget
}


class AddWidgetRunner : TaskerPluginRunnerActionNoOutput<WidgetInput>() {
    override fun run(context: Context, input: TaskerInput<WidgetInput>): TaskerPluginResult<Unit> {
        val intent = Intent()
        intent.action = "ru.execbit.aiolauncher.COMMAND"
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
        intent.putExtra("cmd",String.format("add_widget:%s",input.regular.widget) )
        intent.putExtra("password",input.regular.password)
        context.sendBroadcast(intent)
        return TaskerPluginResultSucess()
    }
}