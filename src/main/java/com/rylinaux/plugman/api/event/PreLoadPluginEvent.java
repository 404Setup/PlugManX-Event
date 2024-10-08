package com.rylinaux.plugman.api.event;

import com.rylinaux.plugman.PlugMan;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginDescriptionFile;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public class PreLoadPluginEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private final @NotNull Path pluginPath;
    private final @NotNull PluginDescriptionFile desc;

    private String cancelledReason;
    private boolean isCancelled;

    public PreLoadPluginEvent(@NotNull Path pluginPath, @NotNull PluginDescriptionFile desc) {
        this.pluginPath = pluginPath;
        this.desc = desc;
        this.isCancelled = false;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.isCancelled = cancelled;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public void setCancelledReason(@NotNull String reason) {
        this.cancelledReason = reason;
    }

    public @NotNull String getCancelledReason() {
        if (this.cancelledReason == null) {
            // default message
            return PlugMan.getInstance().getMessageFormatter().format("cancel.load");
        }
        return cancelledReason;
    }

    public @NotNull Path getPluginPath() {
        return pluginPath;
    }

    public @NotNull PluginDescriptionFile getDesc() {
        return desc;
    }
}