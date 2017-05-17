package net.samagames.ktp2017;

import com.google.gson.JsonObject;
import net.samagames.api.SamaGamesAPI;
import net.samagames.ktp2017.events.AreaJoinEvent;
import net.samagames.tools.LocationUtils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import java.util.TreeSet;
import java.util.UUID;
import static org.bukkit.Bukkit.getWorld;

public class KTPArea {

    private int areaId;
    private Location areaLocation;
    private TreeSet<UUID> inArea;
    private ArmorStand checkableEntity;

    public KTPArea(int id){

        this.areaId = id;
        this.inArea = new TreeSet<UUID>();
        JsonObject configuration = SamaGamesAPI.get().getGameManager().getGameProperties().getGameOptions();
        World areaWorld = getWorld(configuration.get("world-name").getAsString());
        this.areaLocation = LocationUtils.str2loc(areaWorld.getName() + ", " + configuration.get("area_" + this.areaId).getAsString());

        this.checkableEntity = (ArmorStand) areaWorld.spawnEntity(this.areaLocation.clone().add(0.0D, 10.0D, 0.0D), EntityType.ARMOR_STAND);
        this.checkableEntity.setVisible(false);
        this.checkableEntity.setGravity(false);

    }

    public void joinArea(UUID player){
        this.inArea.add(player);
        KTPMain.getInstance().getServer().getPluginManager().callEvent(new AreaJoinEvent(player, this));
    }

    public void leaveArea(UUID player){
        this.inArea.remove(player);
    }

    public int getAreaId(){
        return this.areaId;
    }

    public ArmorStand getCheckableEntity(){
        return this.checkableEntity;
    }

    public Location getAreaLocation(){
        return this.areaLocation;
    }

    public TreeSet<UUID> getAreaPlayers(){
        return this.inArea;
    }

}
