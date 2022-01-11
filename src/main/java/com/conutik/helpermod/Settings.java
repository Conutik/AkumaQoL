package com.conutik.helpermod;

import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.JVMAnnotationPropertyCollector;
import gg.essential.vigilance.data.Property;
import gg.essential.vigilance.data.PropertyType;

import java.io.File;

public class Settings extends Vigilant {

    public static final File CONFIG_FILE = new File("config/akuma.toml");
    @Property(
            type = PropertyType.SWITCH,
            category = "Settings",
            subcategory = "Advanced",
            name = "Discord RPC",
            description = "SHOULD THE MOD SHOW RPC"
    )
    public static boolean manipulationCheck = true;

    public Settings() {
        super(CONFIG_FILE, "UwU Client Configuration", new JVMAnnotationPropertyCollector());
        initialize();
    }
}
