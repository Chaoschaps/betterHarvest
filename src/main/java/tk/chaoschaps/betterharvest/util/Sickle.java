package tk.chaoschaps.betterharvest.util;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class Sickle {
    public static void registerSickle(JavaPlugin plugin) {
        NamespacedKey sickleKey = new NamespacedKey(plugin, "SICKLE_BASE");
        ItemStack sickle = new ItemStack(Material.IRON_HOE);
        ItemMeta itemMeta = sickle.getItemMeta();

        itemMeta.setCustomModelData(1);

        itemMeta.getPersistentDataContainer().set(sickleKey, PersistentDataType.STRING, "Sichel");
        itemMeta.displayName(Component.text("Sichel"));
        sickle.setItemMeta(itemMeta);

        ShapedRecipe recipe = new ShapedRecipe(sickleKey, sickle);
        recipe.shape("SID", "S I", "S  ");
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('D', Material.DIAMOND);

        plugin.getServer().addRecipe(recipe);
    }
}
