package com.LukeTheDuke9801.progressivetechnicalities.objects.items.armor;

import net.minecraftforge.event.entity.living.LivingHurtEvent;

public interface HitEventListener {
    // event.setAmount(float) can be used to reduce damage
    void onWearerHit(LivingHurtEvent event);
}
