package com.sriramsv.aiotaskerplugin.command

import com.joaomgcd.taskerpluginlibrary.input.TaskerInputField
import com.joaomgcd.taskerpluginlibrary.input.TaskerInputRoot
import com.sriramsv.aiotaskerplugin.R


@TaskerInputRoot
class CommandInput @JvmOverloads constructor(
    @field:TaskerInputField(COMMAND_KEY, labelResId = R.string.command) var command: String? = null,
    @field:TaskerInputField("password",labelResId = R.string.password) var password: String? = null,
) {

    companion object {

        const val COMMAND_KEY = "command"
        const val COMMAND_FLASHLIGHT="flashlight"
        const val COMMAND_APPS_MENU = "apps_menu"
    }
}


@TaskerInputRoot
class WidgetInput @JvmOverloads constructor(
    @field:TaskerInputField("widget", labelResId = R.string.widget) var widget: String? = null,
    @field:TaskerInputField("password",labelResId = R.string.password) var password: String? = null,
)