package com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor;

import net.minecraftforge.event.entity.living.LivingHurtEvent;

public interface HitEventListener {
    void onWearerHit(LivingHurtEvent event);
}
