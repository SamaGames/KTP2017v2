package net.samagames.ktp2017;

import net.minecraft.server.v1_10_R1.EntityFireworks;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_10_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftFirework;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

public class Utils {

    /**
     * Lunch firework.
     * @param location The location.
     * @param effect The firework's effect.
     */

    public static void launchfw(Location location, final FireworkEffect effect) {
        Firework fw = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();
        fwm.addEffect(effect);
        fwm.setPower(0);
        fw.setFireworkMeta(fwm);
        ((CraftFirework) fw).getHandle().setInvisible(true);

        KTPMain.getInstance().getServer().getScheduler().runTaskLater(KTPMain.getInstance(), () -> {
            net.minecraft.server.v1_10_R1.World world = (((CraftWorld) location.getWorld()).getHandle());
            EntityFireworks fireworks = ((CraftFirework) fw).getHandle();
            world.broadcastEntityEffect(fireworks, (byte) 17);
            fireworks.die();
        }, 1);
    }

    /**
     * Singular or plural?
     * @param seconds Amount
     * @return Correct string.
     */

    public static String formatSeconds(int seconds){
        if(seconds > 1){
            return "secondes";
        } else {
            return "seconde";
        }
    }

}
