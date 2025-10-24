package com.grupp09.kassasystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemGroups {
    private static final List<ItemGroup> groups = new ArrayList<>();

    public static final ItemGroup FARDIGMAT = new ItemGroup("FARDIGMAT", 0);
    public static final ItemGroup TOBAK = new ItemGroup("TOBAK", 18);
    public static final ItemGroup GODIS = new ItemGroup("GODIS", 0);
    public static final ItemGroup DRYCK = new ItemGroup("DRYCK", 0);
    public static final ItemGroup FRUKT_GRONT = new ItemGroup("FRUKT_GRONT", 0);
    public static final ItemGroup MEJERI = new ItemGroup("MEJERI", 0);
    public static final ItemGroup KOTT = new ItemGroup("KOTT", 0);
    public static final ItemGroup SKALDJUR = new ItemGroup("SKALDJUR", 0);
    public static final ItemGroup BROD = new ItemGroup("BROD", 0);
    public static final ItemGroup DRYCK_ALKOHOL = new ItemGroup("DRYCK_ALKOHOL", 18);

    static {
        groups.add(FARDIGMAT);
        groups.add(TOBAK);
        groups.add(GODIS);
        groups.add(DRYCK);
        groups.add(FRUKT_GRONT);
        groups.add(MEJERI);
        groups.add(KOTT);
        groups.add(SKALDJUR);
        groups.add(BROD);
        groups.add(DRYCK_ALKOHOL);
    }

    public static List<ItemGroup> getAllGroups() {
        return Collections.unmodifiableList(groups);
    }

    
}
