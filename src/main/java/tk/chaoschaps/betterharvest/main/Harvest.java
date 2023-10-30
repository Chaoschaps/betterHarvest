package tk.chaoschaps.betterharvest.main;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class Harvest  implements Listener {
    World world = Bukkit.getWorld("world");
    @EventHandler
    public void harvestrc(PlayerInteractEvent rightclick) {
        // check for rightclick event and return if applied to a crop
        if (rightclick.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (!(rightclick.getClickedBlock().getBlockData() instanceof Ageable)) return;

        //check if crop at maximum Age
        Ageable crop = (Ageable) rightclick.getClickedBlock().getBlockData();
        if (crop.getAge() != crop.getMaximumAge()) return;

        Material cropType = rightclick.getClickedBlock().getType();
        Player player = rightclick.getPlayer();
        ItemMeta currItemMeta = player.getInventory().getItemInMainHand().getItemMeta();
        if (currItemMeta != null && currItemMeta.hasCustomModelData() && currItemMeta.getCustomModelData() == 1) {
            System.out.println("Item held is Sickle");
            Location location = rightclick.getClickedBlock().getLocation();
            int[] around = {-2, -1, 0, 1, 2};
            for (int transpo : around) {
                float yaw = player.getLocation().getYaw();
                Block curBlock;
                if (yaw >= 45 && yaw < 135 || yaw >= -135 && yaw < -45) {
                    curBlock = world.getBlockAt(location.getBlockX(), location.getBlockY(), location.getBlockZ() + transpo);

                } else {
                    curBlock = world.getBlockAt(location.getBlockX() + transpo, location.getBlockY(), location.getBlockZ());

                }
                if (curBlock.getBlockData() instanceof Ageable && ((Ageable) curBlock.getBlockData()).getAge() == ((Ageable) curBlock.getBlockData()).getMaximumAge()) {
                    curBlock.breakNaturally();
                    curBlock.setType(cropType);
                    crop.setAge(0);
                    curBlock.setBlockData(crop);
                }
            }
        } else {
            rightclick.getClickedBlock().breakNaturally();
            rightclick.getClickedBlock().setType(cropType);
            crop.setAge(0);
            rightclick.getClickedBlock().setBlockData(crop);
        }
    }
}

