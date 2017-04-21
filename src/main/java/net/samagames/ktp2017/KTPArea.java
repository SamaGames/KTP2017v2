package net.samagames.ktp2017;

import com.google.gson.JsonObject;
import net.samagames.api.SamaGamesAPI;
import net.samagames.tools.LocationUtils;
import org.bukkit.Location;
import java.util.TreeSet;
import java.util.UUID;

public class KTPArea {

    private int areaId;
    private Location areaLocation;
    private TreeSet<UUID> inArea;

    public KTPArea(int id){

        this.areaId = id;
        this.inArea = new TreeSet<UUID>();
        JsonObject configuration = SamaGamesAPI.get().getGameManager().getGameProperties().getConfigs();

        this.areaLocation = LocationUtils.str2loc(configuration.get("world-name").getAsString() + ", " + configuration.get("area_" + this.areaId).getAsString());

    }

    public int getAreaId(){
        return this.areaId;
    }

    public Location getAreaLocation(){
        return this.areaLocation;
    }

    public TreeSet<UUID> getAreaPlayers(){
        return this.inArea;
    }

}
