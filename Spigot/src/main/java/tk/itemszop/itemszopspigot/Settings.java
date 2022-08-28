package tk.itemszop.itemszopspigot;

import net.elytrium.java.commons.config.YamlConfig;

public class Settings extends YamlConfig {
    @Ignore
    public static final Settings IMP = new Settings();
    public String NO_PERMISSION = "&cNo permission.";
    public String INVALID_ARGUMENT = "&cInvalid argument.";
    @Comment("Change to true if you want to check if the plugin connects to the WebSocket")
    public boolean DEBUG = false;
    @Comment("Interval of checking if the plugin is connected to the WebSocket. The default is 120 seconds or 2 minutes. We recommend that you leave this option at 120.")
    public Integer CHECK_TIME = 120;
    public String CHECK_CONSOLE = "&eCheck the console to see the connection status with the store.";
    public String KEY = "";

}