package de.royzer.perspektive.settings

import de.royzer.perspektive.CameraDistanceOption
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.components.Button
import net.minecraft.client.gui.components.Tooltip
import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.CommonComponents
import net.minecraft.network.chat.Component

class PerspektiveSettingsScreen(
    private val lastScreen: Screen
) : Screen(Component.literal("Perspektive")) {

    override fun init() {
        // return to first person
        addRenderableWidget(
            Button.builder(
                Component.translatable("perspektive.returnToFirstPerson").append(": ")
                    .append(if (PerspektiveSettings.shouldReturnToFirstPerson) CommonComponents.OPTION_ON else CommonComponents.OPTION_OFF)
            ) {
                PerspektiveSettings.shouldReturnToFirstPerson = !PerspektiveSettings.shouldReturnToFirstPerson
                this.minecraft?.setScreen(this)
            }
                .pos(this.width / 2 - 185, this.height / 6 + 4 - 6)
                .size(250, 20)
                .build()
        )
        addRenderableWidget(
            Button.builder(Component.nullToEmpty("Reset")) {
                PerspektiveSettings.shouldReturnToFirstPerson = false
                this.minecraft?.setScreen(this)
            }
                .pos(this.width / 2 + 85, this.height / 6 + 4 - 6)
                .size(100, 20)
                .build()
        )
        // camera distance
        addRenderableWidget(
            CameraDistanceOption.cameraDistanceOption.createButton(
                this.minecraft!!.options,
                width / 2 - 185,
                height / 6 + 28,
                250
            )
        )
        addRenderableWidget(
            Button.builder(Component.nullToEmpty("Reset")) {
                PerspektiveSettings.cameraDistance = 0.0
                CameraDistanceOption.cameraDistanceOption.set(0)
                this.minecraft?.setScreen(this)
            }
                .pos(this.width / 2 + 85, this.height / 6 + 28)
                .size(100, 20)
                .build()
        )
        // disable scrolling
        addRenderableWidget(
            Button.builder(
                Component.translatable("perspektive.scrollingEnabled").append(": ")
                    .append(if (PerspektiveSettings.scrollingEnabled) CommonComponents.OPTION_ON else CommonComponents.OPTION_OFF)
            ) {
                PerspektiveSettings.scrollingEnabled = !PerspektiveSettings.scrollingEnabled
                this.minecraft?.setScreen(this)
            }
                .pos(this.width / 2 - 185, this.height / 6 + 58)
                .size(250, 20)
                .build()
        )
        addRenderableWidget(
            Button.builder(Component.nullToEmpty("Reset")) {
                PerspektiveSettings.scrollingEnabled = true
                this.minecraft?.setScreen(this)
            }
                .pos(this.width / 2 + 85, this.height / 6 + 58)
                .size(100, 20)
                .build()
        )
        // inventory scrolling
        addRenderableWidget(
            Button.builder(
                Component.translatable("perspektive.blockInventoryScrolling").append(": ")
                    .append(if (PerspektiveSettings.blockInventoryScrolling) CommonComponents.OPTION_ON else CommonComponents.OPTION_OFF)
            ) {
                PerspektiveSettings.blockInventoryScrolling = !PerspektiveSettings.blockInventoryScrolling
                this.minecraft?.setScreen(this)
            }
                .pos(this.width / 2 - 185, this.height / 6 + 88)
                .tooltip(Tooltip.create(Component.translatable("perspektive.tooltips.blockScrolling")))
                .size(250, 20)
                .build()
        )
        addRenderableWidget(
            Button.builder(Component.nullToEmpty("Reset")) {
                PerspektiveSettings.blockInventoryScrolling = true
                this.minecraft?.setScreen(this)
            }
                .pos(this.width / 2 + 85, this.height / 6 + 88)
                .size(100, 20)
                .build()
        )

        // third person camera distance
        addRenderableWidget(
            Button.builder(
                Component.translatable("perspektive.cameraDistanceInThirdPerson").append(": ")
                    .append(if (PerspektiveSettings.cameraDistanceAlsoIn3rdPerson) CommonComponents.OPTION_ON else CommonComponents.OPTION_OFF)
            ) {
                PerspektiveSettings.cameraDistanceAlsoIn3rdPerson = !PerspektiveSettings.cameraDistanceAlsoIn3rdPerson
                this.minecraft?.setScreen(this)
            }
                .pos(this.width / 2 - 185, this.height / 6 + 118)
                .size(250, 20)
                .tooltip(Tooltip.create(Component.translatable("perspektive.tooltips.cameraDistanceInThirdPerson")))
                .build()
        )
        addRenderableWidget(
            Button.builder(Component.nullToEmpty("Reset")) {
                PerspektiveSettings.cameraDistanceAlsoIn3rdPerson = false
                this.minecraft?.setScreen(this)
            }
                .pos(this.width / 2 + 85, this.height / 6 + 118)
                .size(100, 20)
                .build()
        )

        addRenderableWidget(
            Button.builder(CommonComponents.GUI_DONE) {
                onClose()
            }
                .pos(this.width / 2 - 100, this.height - 35)
                .size(200, 20)
                .build()
        )
    }

    override fun onClose() {
        saveConfig()
        this.minecraft?.setScreen(lastScreen)
    }

    override fun render(guiGraphics: GuiGraphics, i: Int, j: Int, f: Float) {
        renderBackground(guiGraphics)
        super.render(guiGraphics, i, j, f)
    }
}

//val cameraDistanceOption = OptionInstance("perspektive.distance", OptionInstance.noTooltip(), { optionText, value ->
//        Options.genericValueLabel(optionText, Component.literal((value.toString().toDouble() / 10.0).toString()))
//}, OptionInstance.IntRange(0, 640), (PerspektiveSettings.cameraDistance * 10).toInt()) {
//    PerspektiveSettings.cameraDistance = it.toString().toDouble() / 10
//}