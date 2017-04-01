package net.samagames.ktp2017;

import com.google.gson.JsonObject;
import net.samagames.api.SamaGamesAPI;
import net.samagames.tools.LocationUtils;
import org.bukkit.Location;
import java.util.TreeSet;
import java.util.UUID;

public class Area<T> {

    private T identity;
    private Location areaLocation;
    private TreeSet<UUID> inArea;

    public Area(T identity){

        this.identity = identity;
        this.inArea = new TreeSet<UUID>();
        JsonObject configuration = SamaGamesAPI.get().getGameManager().getGameProperties().getConfigs();

        this.areaLocation = LocationUtils.str2loc(configuration.get("world-name").getAsString() + ", " + configuration.get("area_" + identity).getAsString());

    }

    public Location getAreaLocation(){
        return this.areaLocation;
    }

    public TreeSet<UUID> getAreaPlayers(){
        return this.inArea;
    }

}
