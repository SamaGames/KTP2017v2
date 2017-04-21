package net.samagames.ktp2017;

import net.samagames.api.games.Game;
import org.bukkit.GameMode;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import static org.bukkit.Bukkit.getOnlinePlayers;
import static org.bukkit.Bukkit.getWorlds;

public class KTP2017Game extends Game<KTPPlayer> {

    /**
     * There is the internal representation of the Game.
     * @author Vialonyx
     */

    private GamePhase current;
    private BukkitTask remotenessTask;
    private WorldBorder worldBorder;

    private List<KTPArea> avaibleAreas;
    private KTPArea currentlyPlayedArea;
    private KTPArea nextPlayableArea;

    public static KTPArea area1 = new KTPArea(1);
    public static KTPArea area2 = new KTPArea(2);

    public KTP2017Game(KTPMain instance, String gameCodeName, String gameName, String gameDescription, Class<KTPPlayer> gamePlayer) {
        super(gameCodeName, gameName, gameDescription, gamePlayer);

        // Initializing all the things
        this.avaibleAreas = new ArrayList<>();
        this.worldBorder = getWorlds().get(0).getWorldBorder();

        // Registering areas
        this.avaibleAreas.add(KTP2017Game.area1);
        this.avaibleAreas.add(KTP2017Game.area2);

        // Setting current phase to WAIT
        this.current = GamePhase.WAIT;

        // Setting-up default Area
        this.setupArea(area1);

        // Set the next playable Area (Generated automatically and randomly in the future!)
        this.nextPlayableArea = KTP2017Game.area2;

        // Starting remoteness detection (for ALL players)
        this.remotenessTask = KTPMain.getInstance().getServer().getScheduler().runTaskTimer(KTPMain.getInstance(), new Runnable() {

            @Override
            public void run() {

                for(Player p : getOnlinePlayers()){
                    if(p.getLocation().distance(getCurrentlyPlayedArea().getCheckableEntity().getLocation()) >= 20){
                        p.teleport(getCurrentlyPlayedArea().getAreaLocation().clone().add(0.5, 10.00D, 0.5));
                    }
                }

            }

        }, 0L, 100L);

        // Debugging game variables during developement phase
        logDebug();

    }

    @Override
    public void handleLogin(Player player){

        if(this.getCurrentGamePhase() == GamePhase.WAIT || this.getCurrentGamePhase() == GamePhase.AREA_STARTED){

            this.getCurrentlyPlayedArea().getAreaPlayers().add(player.getUniqueId());
            player.teleport(this.getCurrentlyPlayedArea().getAreaLocation());

        } else {
            player.setGameMode(GameMode.SPECTATOR);
        }

    }

    public static enum GamePhase {

        WAIT, AREA_STARTED, GAME_PHASE1, GAME_PHASE2, GAME_DONE;

    }

    private void setupArea(KTPArea area){

        // Setting-up WorldBorder
        this.worldBorder.setSize(32);
        this.worldBorder.setCenter(area.getAreaLocation());
        this.worldBorder.setWarningDistance(3);
        this.worldBorder.setDamageAmount(0);

        // Update the current played Area
        this.currentlyPlayedArea = area;

    }

    public void logDebug(){
        KTPMain.getInstance().getLogger().log(Level.INFO,"------- DEBUG -------");
        KTPMain.getInstance().getLogger().log(Level.INFO,"Current game phase : " + getCurrentGamePhase());
        KTPMain.getInstance().getLogger().log(Level.INFO,this.avaibleAreas.size() + " areas registered. " + avaibleAreas.toArray());
        KTPMain.getInstance().getLogger().log(Level.INFO,"-------- END --------");
    }

    public GamePhase getCurrentGamePhase(){
        return this.current;
    }

    public KTPArea getCurrentlyPlayedArea(){
        return this.currentlyPlayedArea;
    }

    public KTPArea getNextPlayableArea(){
        return this.nextPlayableArea;
    }

}
