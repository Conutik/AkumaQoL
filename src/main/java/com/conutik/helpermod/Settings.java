package com.conutik.helpermod;

import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.Category;
import gg.essential.vigilance.data.JVMAnnotationPropertyCollector;
import gg.essential.vigilance.data.Property;
import gg.essential.vigilance.data.PropertyData;
import gg.essential.vigilance.data.PropertyType;
import gg.essential.vigilance.data.SortingBehavior;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Settings extends Vigilant {

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

    public static final File CONFIG_FILE = new File("config/akuma.toml");
}
