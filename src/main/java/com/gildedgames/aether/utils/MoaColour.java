package com.gildedgames.aether.utils;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class MoaColour {
    public int ID;
    public int colour;
    public int jumps;
    public int chance;
    public String name;
    private static int totalChance;
    public static List<MoaColour> colours;
    private static Random random;
    
    public MoaColour(final int i, final int j, final int k, final int l, final String s) {
        this.ID = i;
        this.colour = j;
        this.jumps = k;
        this.chance = l;
        MoaColour.totalChance += l;
        this.name = s;
        MoaColour.colours.add(this);
    }
    
    public String getTexture(final boolean saddled) {
        return "/assets/aether/stationapi/textures/entity/" + this.name + (saddled ? "MoaSaddle.png" : "Moa.png");
    }
    
    public static MoaColour pickRandomMoa() {
        int i = MoaColour.random.nextInt(MoaColour.totalChance);
        for (int j = 0; j < MoaColour.colours.size(); ++j) {
            if (i < ((MoaColour)MoaColour.colours.get(j)).chance) {
                return (MoaColour)MoaColour.colours.get(j);
            }
            i -= ((MoaColour)MoaColour.colours.get(j)).chance;
        }
        return (MoaColour)MoaColour.colours.get(0);
    }
    
    public static MoaColour getColour(final int ID) {
        for (int i = 0; i < MoaColour.colours.size(); ++i) {
            if (((MoaColour)MoaColour.colours.get(i)).ID == ID) {
                return (MoaColour)MoaColour.colours.get(i);
            }
        }
        return (MoaColour)MoaColour.colours.get(0);
    }
    
    static {
        MoaColour.colours = new ArrayList<MoaColour>();
        MoaColour.random = new Random();
        new MoaColour(0, 7829503, 3, 100, "Blue");
        new MoaColour(1, 2236962, 8, 5, "Black");
        new MoaColour(2, 16777215, 4, 20, "White");
    }
}
