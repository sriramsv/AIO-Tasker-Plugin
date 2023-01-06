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
import kotlinx.android.synthetic.main.activity_aiocommand.*


class BasicActionHelper(config: TaskerPluginConfig<CommandInput>) : TaskerPluginConfigHelperNoOutput<CommandInput, BasicActionRunner>(config) {
    override val runnerClass: Class<BasicActionRunner> get() = BasicActionRunner::class.java
    override val inputClass: Class<CommandInput> get() = CommandInput::class.java
    override fun addToStringBlurb(input: TaskerInput<CommandInput>, blurbBuilder: StringBuilder) {
    }
}


class AIOCommand : ActivityConfigTaskerNoOutput<CommandInput, BasicActionRunner, BasicActionHelper>() {
    //Overrides

    override fun getNewHelper(config: TaskerPluginConfig<CommandInput>) = BasicActionHelper(config)
    override fun assignFromInput(input: TaskerInput<CommandInput>) = input.regular.run {
        editPassword.setText(password)
    }

    override val inputForTasker get() = TaskerInput(CommandInput("", editPassword.text.toString()))
    override val layoutResId = com.sriramsv.aiotaskerplugin.R.layout.activity_aiocommand
}


class BasicActionRunner : TaskerPluginRunnerActionNoOutput<CommandInput>() {
    override fun run(context: Context, input: TaskerInput<CommandInput>): TaskerPluginResult<Unit> {
        val intent = Intent()
        intent.action = "ru.execbit.aiolauncher.COMMAND"
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
        intent.putExtra("cmd","add_widget:feed")
        intent.putExtra("password",input.regular.password)
        context.sendBroadcast(intent)
        return TaskerPluginResultSucess()
    }
}